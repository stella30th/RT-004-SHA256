"""Audit Hao's round-2 CSV indexes and keep them separate from historical OAPR.

Run from the repository root: python ho-le-thien-an/round2_analysis.py
Primary ZIP artifacts are not in Git; this checks the committed indexes only.
"""

from __future__ import annotations

import csv
import json
from collections import Counter, defaultdict
from pathlib import Path

from analysis import exact_ci


ROOT = Path(__file__).resolve().parents[1]
BASE = ROOT / "huynh-gia-hao" / "experiment" / "round2" / "SWT_Hao_Round2_Final_Handover"
OUT = Path(__file__).resolve().parent / "oapr-analysis"
CONDITIONS = ("p0_resample", "checklist_only", "checklist_focus")
RUNS = ("run-01", "run-02", "run-03")
TASKS = ("CE033", "CE041", "CE043", "CE050", "CE053", "CE060", "CE065", "CE066", "CE077", "CE085", "CE090")


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
            "The five primary Hào ZIP archives listed in SHA256_MANIFEST.csv are not committed; this audit checks CSV indexes, not all raw Maven/PIT XML.",
            "Two P0 round-2 cells are labeled replacement outputs; independent generation provenance is incomplete.",
            "Three runs per task are correlated; suite-level binomial intervals are descriptive, not independent-task inference.",
            "Khoa's new handover audits historical Gemini P0/P1; no Gemini three-arm repeated-run registry was found.",
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
