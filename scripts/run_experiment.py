# -*- coding: utf-8 -*-
"""
Script chạy thực nghiệm tự động cho đề tài Topic 6.2:
Đo lường tỷ lệ sinh JUnit test không chạy được của ChatGPT và Gemini,
kèm cơ chế sửa lỗi bằng phản hồi biên dịch (Compiler-Feedback Repair - 1 round).

Phụ trách: Nguyễn Hoàng Khôi (Vai trò: LLM Runner)
"""

import argparse
import csv
import json
import os
import shutil
import sys
from pathlib import Path

# Cấu hình encoding UTF-8 cho console Windows
if sys.stdout.encoding != "utf-8":
    try:
        sys.stdout.reconfigure(encoding="utf-8")
    except Exception:
        pass

from prompt_builder import build_generation_prompt, build_repair_prompt, load_prompt_template
from compile_and_run import compile_project, run_tests
from code_extractor import extract_java_code
from logger import append_result
from providers.chatgpt_provider import ChatGPTProvider
from providers.gemini_provider import GeminiProvider

ROOT = Path(__file__).resolve().parents[1]
TEMPLATE_DIR = ROOT / "workspace-template"
WORKSPACES_DIR = ROOT / "workspaces"


def load_config(path: str) -> dict:
    return json.loads(Path(path).read_text(encoding="utf-8"))


def load_dataset(path: str) -> list[dict]:
    with open(path, newline="", encoding="utf-8") as f:
        return list(csv.DictReader(f))


def prepare_workspace(problem_id: str) -> Path:
    """Tạo workspace kiểm thử Maven cô lập cho từng bài toán."""
    workspace = WORKSPACES_DIR / problem_id
    if workspace.exists():
        shutil.rmtree(workspace)

    shutil.copytree(TEMPLATE_DIR, workspace)

    main_java = workspace / "src" / "main" / "java" / problem_id
    test_java = workspace / "src" / "test" / "java" / problem_id
    main_java.mkdir(parents=True, exist_ok=True)
    test_java.mkdir(parents=True, exist_ok=True)

    solution_src = ROOT / "data" / "problems" / problem_id / "Solution.java"
    if not solution_src.exists():
        raise FileNotFoundError(f"Solution file not found: {solution_src}")

    shutil.copy2(solution_src, main_java / "Solution.java")
    return workspace


def get_provider(condition: str, config: dict):
    if condition == "chatgpt":
        cfg = config["models"]["chatgpt"]
        return ChatGPTProvider(model=cfg.get("model", "gpt-4o"), temperature=cfg.get("temperature", 0.0))
    elif condition == "gemini":
        cfg = config["models"]["gemini"]
        return GeminiProvider(model=cfg.get("model", "models/gemini-3.8-flash"), temperature=cfg.get("temperature", 0.0))
    else:
        raise ValueError(f"Unknown condition: {condition}")


def run_single_problem(problem: dict, condition: str, provider, config: dict, prompt_template: str, dry_run: bool = False):
    problem_id = problem.get("problem_id", "UNKNOWN")
    task_category = problem.get("task_category", "Uncategorized")
    prompt_version = config.get("prompt_version", "v1")
    model_name = getattr(provider, "model", condition)

    print("\n" + "=" * 70)
    print(f"RUNNING: Problem {problem_id} | Condition: {condition} ({model_name}) | Category: {task_category}")
    print("=" * 70)

    # 1. Xây dựng prompt có cấu trúc cố định (Frozen Structured Prompt)
    prompt = build_generation_prompt(prompt_template, problem)

    if dry_run:
        print("[DRY-RUN] Prompt:\n", prompt)
        return

    # 2. Khởi tạo workspace Maven cô lập
    workspace = prepare_workspace(problem_id)
    test_file_path = workspace / "src" / "test" / "java" / problem_id / "SolutionTest.java"

    # Thư mục lưu phản hồi thô
    raw_dir = ROOT / "results" / "raw_responses" / condition
    raw_dir.mkdir(parents=True, exist_ok=True)

    # 3. Gửi prompt đến LLM sinh test ban đầu
    print(f"[1/4] Querying {condition} for initial test generation...")
    raw_response = provider.generate(prompt)

    # Lưu nguyên văn (verbatim) phản hồi thô
    raw_attempt1_path = raw_dir / f"{problem_id}_attempt1.txt"
    raw_attempt1_path.write_text(raw_response, encoding="utf-8")
    print(f"      Saved raw response to: {raw_attempt1_path.relative_to(ROOT)}")

    # 4. Trích xuất mã JUnit
    generated_code = extract_java_code(raw_response)
    test_file_path.write_text(generated_code, encoding="utf-8")

    # 5. Biên dịch lần 1
    timeout = config.get("compile_timeout_seconds", 30)
    print(f"[2/4] Compiling generated JUnit test suite (timeout={timeout}s)...")
    compile_res = compile_project(workspace, timeout=timeout)

    compile_success = 1 if compile_res.success else 0
    repair_needed = 0
    repair_success = 0
    execution_success = 0
    combined_success = 0
    failure_stage = "none"
    compiler_error = ""
    attempt_count = 1

    if compile_success:
        print("      [PASS] Initial compilation succeeded!")
    else:
        print("      [FAIL] Initial compilation failed!")
        repair_needed = 1
        compiler_error = (compile_res.stderr or compile_res.stdout).strip()

        # 6. Sửa lỗi qua phản hồi biên dịch (Compiler-Feedback Repair - Đúng 1 vòng)
        if config.get("repair_rounds", 1) >= 1:
            attempt_count = 2
            print(f"[3/4] Initiating EXACTLY ONE compiler-feedback repair round...")
            repair_prompt = build_repair_prompt(prompt, generated_code, compiler_error[:2000])

            raw_repair_response = provider.generate(repair_prompt)
            raw_repair_path = raw_dir / f"{problem_id}_repair.txt"
            raw_repair_path.write_text(raw_repair_response, encoding="utf-8")
            print(f"      Saved raw repair response to: {raw_repair_path.relative_to(ROOT)}")

            repaired_code = extract_java_code(raw_repair_response)
            test_file_path.write_text(repaired_code, encoding="utf-8")

            print(f"      Recompiling repaired test suite...")
            recompile_res = compile_project(workspace, timeout=timeout)
            if recompile_res.success:
                print("      [PASS] Repair compilation succeeded!")
                repair_success = 1
                compile_success = 1
            else:
                print("      [FAIL] Repair compilation also failed.")
                repair_success = 0
                compile_success = 0
                failure_stage = "compilation"

    # 7. Nếu biên dịch thành công (dù ở lần 1 hay sau sửa) -> Thực thi test
    if compile_success:
        run_timeout = config.get("run_timeout_seconds", 30)
        print(f"[4/4] Executing JUnit test suite with maven surefire (timeout={run_timeout}s)...")
        test_res = run_tests(workspace, timeout=run_timeout)
        if test_res.success:
            print("      [PASS] JUnit tests passed successfully! (Runnability confirmed)")
            execution_success = 1
            combined_success = 1
            failure_stage = "none"
        else:
            print("      [FAIL] Test execution failed (Assertion error or runtime exception).")
            execution_success = 0
            combined_success = 0
            failure_stage = "execution"
    else:
        failure_stage = "compilation"

    # 8. Ghi nhận toàn bộ kết quả vào file kết quả chuẩn
    result_entry = {
        "problem_id": problem_id,
        "task_category": task_category,
        "condition": condition,
        "model": model_name,
        "prompt_version": prompt_version,
        "attempt": attempt_count,
        "compile_success": compile_success,
        "execution_success": execution_success,
        "combined_success": combined_success,
        "repair_needed": repair_needed,
        "repair_success": repair_success if repair_needed else "",
        "failure_stage": failure_stage,
        "compiler_error": compiler_error[:300].replace("\n", " ") if compiler_error else "",
        "notes": f"Automated execution completed via {condition}",
    }

    results_csv = ROOT / "results" / "experiment_results.csv"
    append_result(results_csv, result_entry)
    print(f"\n[DONE] Logged outcome to: {results_csv.relative_to(ROOT)}")
    print(f"Summary: compile={compile_success}, execute={execution_success}, combined={combined_success}, repair_needed={repair_needed}")


def main() -> None:
    parser = argparse.ArgumentParser(description="LLM Runner for Topic 6.2 JUnit experiment")
    parser.add_argument("--config", default="config/experiment.json", help="Path to config file")
    parser.add_argument("--condition", choices=["chatgpt", "gemini", "all"], default="gemini", help="LLM condition to run")
    parser.add_argument("--problem", default=None, help="Specific problem_id to run (e.g. HE001)")
    parser.add_argument("--dry-run", action="store_true", help="Print prompt only without calling LLM or compiling")
    args = parser.parse_args()

    config = load_config(args.config)
    dataset = load_dataset(config["dataset"]["subset_file"])
    template = load_prompt_template(config["prompt_file"])

    if args.problem:
        dataset = [p for p in dataset if p.get("problem_id") == args.problem]
        if not dataset:
            print(f"Error: Problem {args.problem} not found in dataset!")
            return

    conditions = ["gemini", "chatgpt"] if args.condition == "all" else [args.condition]

    print("=" * 70)
    print(f"EXPERIMENT RUNNER: {config.get('experiment_name')}")
    print(f"Problems to run: {[p.get('problem_id') for p in dataset]}")
    print(f"Conditions: {conditions}")
    print("=" * 70)

    for cond in conditions:
        try:
            provider = get_provider(cond, config)
        except Exception as e:
            print(f"\n[WARNING] Skipping condition '{cond}' because provider cannot be initialized: {e}")
            continue

        for problem in dataset:
            run_single_problem(problem, cond, provider, config, template, dry_run=args.dry_run)


if __name__ == "__main__":
    main()
