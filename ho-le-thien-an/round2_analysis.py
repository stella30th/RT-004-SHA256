"""Audit both round-2 cohorts and keep them separate from historical OAPR.

Run from the repository root: python ho-le-thien-an/round2_analysis.py
The script validates indexes and presence of committed raw evidence, not AI sessions.
"""

from __future__ import annotations

import csv
import json
from collections import Counter, defaultdict
from pathlib import Path

from analysis import exact_ci


ROOT = Path(__file__).resolve().parents[1]
BASE = ROOT / "huynh-gia-hao" / "experiment" / "round2" / "SWT_Hao_Round2_Final_Handover"
HAO_RAW = ROOT / "huynh-gia-hao" / "experiment" / "round2" / "outputs"
GEMINI_BASE = ROOT / "nguyen-nam-khoa" / "Round 2" / "Evidence"
OUT = Path(__file__).resolve().parent / "oapr-analysis"
CONDITIONS = ("p0_resample", "checklist_only", "checklist_focus")
RUNS = ("run-01", "run-02", "run-03")
TASKS = ("CE033", "CE041", "CE043", "CE050", "CE053", "CE060", "CE065", "CE066", "CE077", "CE085", "CE090")
GEMINI_TASKS = ("CE033", "CE037", "CE039", "CE062", "CE073", "CE079", "CE094")
GEMINI_ARMS = ("A", "B", "C")
GEMINI_RUNS = ("r01", "r02", "r03")


def load_csv(name: str) -> list[dict[str, str]]:
    with (BASE / name).open(encoding="utf-8-sig", newline="") as stream:
        return list(csv.DictReader(stream))


def is_pass(row: dict[str, str]) -> bool:
    observed = (
        row["classification"] == "PASS"
        and row["compile_success"] == "1"
        and row["execution_success"] == "1"
        and int(row["tests"]) > 0
        and int(row["failures"]) == 0
        and int(row["errors"]) == 0
    )
    if observed != (row["classification"] == "PASS"):
        raise ValueError(f"PASS fields disagree for {(row['condition'], row['run'], row['task'])}")
    return observed


def audit_gemini() -> dict:
    with (GEMINI_BASE / "execution_summary.csv").open(encoding="utf-8-sig", newline="") as stream:
        rows = list(csv.DictReader(stream))
    expected = {(task, arm, run) for task in GEMINI_TASKS for arm in GEMINI_ARMS for run in GEMINI_RUNS}
    keys = [(row["task_id"], row["arm"], row["replicate"]) for row in rows]
    if len(keys) != 63 or len(set(keys)) != 63 or set(keys) != expected:
        raise ValueError("Gemini round 2 does not contain 7 tasks × 3 arms × 3 runs")
    by_arm = {}
    unverified_metadata = 0
    for row in rows:
        key = (row["task_id"], row["arm"], row["replicate"])
        passed = (row["compiled"] == "TRUE" and int(row["tests_run"]) > 0
                  and int(row["failures"]) == 0 and int(row["errors"]) == 0)
        if passed != (row["suite_pass"] == "TRUE"):
            raise ValueError(f"Gemini PASS fields disagree for {key}")
        if row["status"] not in ("PASS", "TEST_FAIL") or row["compiled"] != "TRUE":
            raise ValueError(f"Unexpected Gemini execution classification for {key}")
        run_dir = GEMINI_BASE / "Official_Run" / row["arm"] / row["replicate"] / row["task_id"]
        required = (
            run_dir / "request.txt",
            run_dir / "response.raw.txt",
            run_dir / f"GeneratedTest_{row['task_id']}.java",
            run_dir / "execution_logs" / "compile.log",
            run_dir / "execution_logs" / "test.log",
            run_dir / "execution_logs" / "surefire-reports" / f"TEST-org.example.GeneratedTest_{row['task_id']}.xml",
            run_dir / "execution_logs" / "metadata.json",
        )
        if any(not file.is_file() for file in required):
            raise ValueError(f"Gemini raw evidence missing for {key}")
        metadata = json.loads((run_dir / "execution_logs" / "metadata.json").read_text(encoding="utf-8-sig"))
        if not metadata.get("model_version_verified") or not metadata.get("interface"):
            unverified_metadata += 1
    for arm in GEMINI_ARMS:
        arm_rows = [row for row in rows if row["arm"] == arm]
        passed = sum(row["suite_pass"] == "TRUE" for row in arm_rows)
        by_arm[arm] = {
            "suites": 21,
            "pass": passed,
            "fail": 21 - passed,
            "by_task_pass": {task: sum(row["suite_pass"] == "TRUE" for row in arm_rows if row["task_id"] == task)
                             for task in GEMINI_TASKS},
        }
    if [(by_arm[arm]["pass"], by_arm[arm]["fail"]) for arm in GEMINI_ARMS] != [(17, 4), (18, 3), (15, 6)]:
        raise ValueError("Gemini headline counts changed from report")
    return {
        "source": "Khoa Official_Run execution_summary.csv and per-run files",
        "by_arm": by_arm,
        "overall_pass": 50,
        "overall_suites": 63,
        "raw_run_sets_present": 63,
        "model_or_interface_unverified_in_per_run_metadata": unverified_metadata,
        "mutation_results_available": False,
        "protocol_freeze_verified": False,
    }


def main() -> None:
    suites = load_csv("round2_suite_results.csv")
    mutation = load_csv("mutation_results.csv")
    expected = {(c, run, task) for c in CONDITIONS for run in RUNS for task in TASKS}
    keys = [(r["condition"], r["run"], r["task"]) for r in suites]
    if len(keys) != 99 or len(set(keys)) != 99 or set(keys) != expected:
        raise ValueError("Suite index does not contain exactly 11 tasks × 3 conditions × 3 runs")
    mkeys = [(r["condition"], r["run"], r["task_id"]) for r in mutation]
    if len(mkeys) != 99 or len(set(mkeys)) != 99 or set(mkeys) != expected:
        raise ValueError("Mutation index does not cover the same 99 task/run/condition cells")
    suite_by_key = dict(zip(keys, suites))
    mutation_by_key = dict(zip(mkeys, mutation))
    if any((mutation_by_key[k]["baseline_classification"] == "PASS") != is_pass(row) for k, row in suite_by_key.items()):
        raise ValueError("Mutation eligibility disagrees with JUnit PASS")

    conditions: dict[str, dict] = {}
    for condition in CONDITIONS:
        rows = [r for r in suites if r["condition"] == condition]
        passed = sum(is_pass(r) for r in rows)
        complete = [r for r in mutation if r["condition"] == condition and r["status"] == "MUTATION_COMPLETE"]
        excluded = [r for r in mutation if r["condition"] == condition and r["status"] == "BASELINE_FAIL_EXCLUDED"]
        if len(complete) != passed or len(excluded) != 33 - passed:
            raise ValueError(f"Mutation gate mismatch for {condition}")
        killed = sum(int(r["killed"]) for r in complete)
        denominator = sum(int(r["score_denominator"]) for r in complete)
        lo, hi = exact_ci(passed, 33)
        conditions[condition] = {
            "suites": 33,
            "pass": passed,
            "pass_fraction": passed / 33,
            "suite_level_exact_95_ci_descriptive_only": [lo, hi],
            "by_run_pass": {run: sum(is_pass(r) for r in rows if r["run"] == run) for run in RUNS},
            "executed_methods": sum(int(r["tests"]) for r in rows),
            "mutation_completed": len(complete),
            "mutation_baseline_excluded": len(excluded),
            "mutation_killed": killed,
            "mutation_denominator": denominator,
            "conditional_micro_mutation_score": killed / denominator,
        }
    if [(conditions[c]["pass"], conditions[c]["mutation_killed"], conditions[c]["mutation_denominator"]) for c in CONDITIONS] != [(23, 649, 730), (33, 924, 1164), (33, 924, 1164)]:
        raise ValueError("Round-2 headline counts changed from the handover")

    pair_cells = []
    for run in RUNS:
        for task in TASKS:
            p0 = suite_by_key[("p0_resample", run, task)]
            only = suite_by_key[("checklist_only", run, task)]
            focus = suite_by_key[("checklist_focus", run, task)]
            pair_cells.append({
                "run": run,
                "task": task,
                "p0_pass": int(is_pass(p0)),
                "only_pass": int(is_pass(only)),
                "focus_pass": int(is_pass(focus)),
                "p0_replacement": p0["replacement"],
                "p0_mutation_score": mutation_by_key[("p0_resample", run, task)]["mutation_score"],
                "only_mutation_score": mutation_by_key[("checklist_only", run, task)]["mutation_score"],
                "focus_mutation_score": mutation_by_key[("checklist_focus", run, task)]["mutation_score"],
            })
    if any(r["only_mutation_score"] != r["focus_mutation_score"] for r in pair_cells):
        raise ValueError("Checklist-only and focus mutation scores differ in the index")
    replacements = [f"{r['run']} {r['task']}" for r in pair_cells if r["p0_replacement"] == "True"]
    matched_mutation = [r for r in pair_cells if r["p0_pass"]]
    if len(matched_mutation) != 23 or len(replacements) != 2:
        raise ValueError("Unexpected matched mutation count or replacement count")

    hao_raw = HAO_RAW / "final_audit_99" / "evidence"
    for condition, run, task in expected:
        run_dir = hao_raw / condition / run / task
        if any(not (run_dir / name).is_file() for name in ("generated.java", "maven.log", "surefire.xml", "result.json")):
            raise ValueError(f"Hao raw JUnit evidence missing for {(condition, run, task)}")
        mutation_dir = HAO_RAW / "mutation_java17" / condition / run / task
        if not (mutation_dir / "mutation_result.json").is_file():
            raise ValueError(f"Hao mutation result missing for {(condition, run, task)}")
        if mutation_by_key[(condition, run, task)]["status"] == "MUTATION_COMPLETE":
            if any(not (mutation_dir / name).is_file() for name in ("mutations.xml", "baseline_maven.log", "pit_maven.log")):
                raise ValueError(f"Hao raw PIT evidence missing for {(condition, run, task)}")

    historical = json.loads((OUT / "summary.json").read_text(encoding="utf-8"))
    historical_187 = {
        "valid_pairs": 187,
        "p0_pass": historical["by_model"]["ChatGPT"]["adjusted_p0_pass"] + historical["by_model"]["Gemini"]["raw_p0_pass"],
        "eligible_p0_fail": historical["combined"]["eligible_failures"],
        "focused_p1_recovered": historical["combined"]["p1_recovered"],
    }
    historical_187["carry_forward_pass"] = historical_187["p0_pass"] + historical_187["focused_p1_recovered"]
    if historical_187 != {"valid_pairs": 187, "p0_pass": 169, "eligible_p0_fail": 18, "focused_p1_recovered": 15, "carry_forward_pass": 184}:
        raise ValueError("187-pair valid-cohort accounting failed")
    summary = {
        "round2_chatgpt_only": conditions,
        "round2_gemini_only": audit_gemini(),
        "round2_task_run_cells": 33,
        "round2_p0_to_checklist_pass": dict(Counter(f"{r['p0_pass']}->{r['only_pass']}" for r in pair_cells)),
        "round2_p0_replacement_outputs": replacements,
        "round2_only_focus_equal_mutation_scores": 33,
        "round2_matched_valid_p0_mutation_cells": len(matched_mutation),
        "historical_valid_cohort_excluding_gemini_ce023": historical_187,
        "historical_reported_adjusted_188": {
            "pairs": 188, "p0_pass": 170, "focused_p1_recovered": 15, "carry_forward_pass": 185,
            "ce023_p0_pass_verification": "absent; retain only as sensitivity scenario",
        },
        "evidence_limitations": [
            "Hao's 99 raw JUnit and 89 eligible PIT evidence sets are committed unpacked; the five named ZIP packages are absent.",
            "Two P0 round-2 cells are labeled replacement outputs; independent generation provenance is incomplete.",
            "Three runs per task are correlated in both model cohorts; suite-level binomial intervals are descriptive.",
            "Khoa's 63 Gemini A/B/C run sets are committed, but all per-run metadata files lack a verified model version/interface; no Gemini mutation results are reported.",
            "The Gemini round-2 protocol remains marked DRAFT, and its single-task baseline differs from historical P0.",
        ],
    }
    OUT.mkdir(parents=True, exist_ok=True)
    (OUT / "round2_summary.json").write_text(json.dumps(summary, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
    with (OUT / "round2_task_run.csv").open("w", encoding="utf-8-sig", newline="") as stream:
        writer = csv.DictWriter(stream, fieldnames=list(pair_cells[0]))
        writer.writeheader()
        writer.writerows(pair_cells)
    print(json.dumps(summary, ensure_ascii=False, indent=2))


if __name__ == "__main__":
    main()
