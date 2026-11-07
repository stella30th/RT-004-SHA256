"""Reproduce the archived ClassEval-T focused-recovery accounting.

Inputs are frozen copies of the archived pair register and P0-pass inventory
under ho-le-thien-an/oapr-analysis/.
This script does not infer an independently observed P1 result for P0-pass pairs.
Run: python ho-le-thien-an/analysis.py
"""

from __future__ import annotations

import csv
import json
import math
from collections import Counter
from pathlib import Path


ROOT = Path(__file__).resolve().parents[1]
OUT = Path(__file__).resolve().parent / "oapr-analysis"
ARCHIVE = OUT
MODELS = ("ChatGPT", "Gemini")
TASKS = {f"CE{i:03d}" for i in range(1, 95)}
ADJUSTED = {("ChatGPT", "CE029"), ("ChatGPT", "CE087"), ("Gemini", "CE023")}


def load_json(name: str) -> list[dict]:
    value = json.loads((ARCHIVE / name).read_text(encoding="utf-8"))
    if not isinstance(value, list):
        raise ValueError(f"{name} must contain a list")
    return value


def probability_at_least(k: int, n: int, p: float) -> float:
    return sum(math.comb(n, i) * p**i * (1 - p) ** (n - i) for i in range(k, n + 1))


def probability_at_most(k: int, n: int, p: float) -> float:
    return sum(math.comb(n, i) * p**i * (1 - p) ** (n - i) for i in range(k + 1))


def exact_ci(k: int, n: int, alpha: float = 0.05) -> tuple[float, float]:
    """Two-sided Clopper-Pearson interval, using binomial-tail inversion."""
    if not 0 <= k <= n or n < 1:
        raise ValueError((k, n))
    lo = 0.0
    hi = 1.0
    if k:
        left, right = 0.0, k / n
        for _ in range(80):
            mid = (left + right) / 2
            if probability_at_least(k, n, mid) < alpha / 2:
                left = mid
            else:
                right = mid
        lo = (left + right) / 2
    if k < n:
        left, right = k / n, 1.0
        for _ in range(80):
            mid = (left + right) / 2
            if probability_at_most(k, n, mid) > alpha / 2:
                left = mid
            else:
                right = mid
        hi = (left + right) / 2
    return lo, hi


def log_state(relative: str) -> str:
    if not relative:
        return "missing_path"
    path = ROOT / relative
    if not path.is_file():
        return "missing_file"
    content = path.read_text(encoding="utf-8", errors="replace")
    if "BUILD FAILURE" in content or "COMPILATION ERROR" in content or "<<< FAILURE!" in content or "<<< ERROR!" in content:
        return "fail"
    if "BUILD SUCCESS" in content or "Tests run:" in content and "Failures: 0, Errors: 0" in content:
        return "pass"
    return "unparsed"


def build_pairs() -> list[dict]:
    exceptional = load_json("source_data.json")
    p0_inventory = load_json("regression_targets.json")
    pairs: dict[tuple[str, str], dict] = {}
    for row in p0_inventory:
        key = (row["model"], row["task"])
        if key in pairs:
            raise ValueError(f"Duplicate inventory pair: {key}")
        pairs[key] = {
            "model": key[0], "task": key[1], "target_class": row["class"],
            "p0_raw": "PASS", "p0_adjusted": "PASS", "eligible": False,
            "p1": "NOT_RUN", "p0_log": row.get("p0_log", ""), "p1_log": "",
            "excluded_p1_observation": "", "excluded_p1_log": "",
            "adjustment_evidence": "corrected P0 log" if key in ADJUSTED else "not_applicable",
            "p0_failure_stage": "none",
            "classification": "archived P0-pass inventory",
        }
    seen = set()
    for row in exceptional:
        key = (row["model"], row["task"])
        if key in seen:
            raise ValueError(f"Duplicate exception pair: {key}")
        seen.add(key)
        eligible = row["eligible"] == "YES"
        if key in ADJUSTED and eligible:
            raise ValueError(f"Adjustment marked eligible: {key}")
        if key not in ADJUSTED and not eligible:
            raise ValueError(f"Unexplained ineligible failure: {key}")
        p1 = row["p1_status"] if eligible else "NOT_ELIGIBLE"
        if eligible and p1 not in {"PASS", "FAIL"}:
            raise ValueError(f"Missing P1 outcome: {key}")
        failure_label = row["p0_failure"].lower()
        if "assertion" in failure_label:
            stage = "assertion"
        elif "runtime" in failure_label:
            stage = "runtime"
        elif "compilation" in failure_label:
            stage = "compilation"
        else:
            raise ValueError(f"Unclassified P0 failure stage: {key}: {failure_label}")
        pairs[key] = {
            "model": key[0], "task": key[1], "target_class": row["target_class"],
            "p0_raw": "FAIL", "p0_adjusted": "PASS" if key in ADJUSTED else "FAIL",
            "eligible": eligible, "p1": p1, "p0_log": row.get("p0_log", ""),
            "p1_log": row.get("p1_log", "") if eligible else "",
            "excluded_p1_observation": row["p1_status"] if key == ("Gemini", "CE023") else "",
            "excluded_p1_log": row.get("p1_log", "") if key == ("Gemini", "CE023") else "",
            "adjustment_evidence": "P1 new suite only; P0 correction unverified" if key == ("Gemini", "CE023") else ("corrected P0 log" if key in ADJUSTED else "not_applicable"),
            "p0_failure_stage": stage,
            "classification": row.get("p0_taxonomy", ""),
        }
    expected = {(model, task) for model in MODELS for task in TASKS}
    if set(pairs) != expected:
        raise ValueError(f"Inventory mismatch: missing={sorted(expected - set(pairs))}, extra={sorted(set(pairs) - expected)}")
    result = [pairs[key] for key in sorted(pairs)]
    for row in result:
        row["p0_log_check"] = log_state(row["p0_log"])
        row["p1_log_check"] = log_state(row["p1_log"]) if row["eligible"] else "not_applicable"
        row["carry_forward"] = "PASS" if row["p0_adjusted"] == "PASS" or row["p1"] == "PASS" else "FAIL"
        key = (row["model"], row["task"])
        expected_p0_log = "pass" if row["p0_raw"] == "PASS" or key in ADJUSTED - {("Gemini", "CE023")} else "fail"
        if row["p0_log_check"] != expected_p0_log:
            raise ValueError(f"P0 log disagrees with archived classification: {key}")
        if row["eligible"] and row["p1_log_check"] != row["p1"].lower():
            raise ValueError(f"P1 log disagrees with archived classification: {key}")
        if key == ("Gemini", "CE023") and (row["excluded_p1_observation"], log_state(row["excluded_p1_log"])) != ("PASS", "pass"):
            raise ValueError("CE023 excluded P1 observation is missing or not PASS")
    return result


def summarize(rows: list[dict]) -> dict:
    def one(group: list[dict]) -> dict:
        n = len(group)
        raw = sum(r["p0_raw"] == "PASS" for r in group)
        adjusted = sum(r["p0_adjusted"] == "PASS" for r in group)
        selected = [r for r in group if r["eligible"]]
        recovered = sum(r["p1"] == "PASS" for r in selected)
        carried = sum(r["carry_forward"] == "PASS" for r in group)
        ci = exact_ci(recovered, len(selected))
        return {
            "pairs": n, "raw_p0_pass": raw, "adjusted_p0_pass": adjusted,
            "eligible_failures": len(selected), "p1_recovered": recovered,
            "p1_still_failed": len(selected) - recovered,
            "recovery_rate": recovered / len(selected),
            "exact_95_ci": [round(x, 8) for x in ci],
            "carry_forward_pass": carried,
            "raw_sensitivity_pass_if_only_observed_recoveries_carried": raw + recovered,
        }
    by_model = {m: one([r for r in rows if r["model"] == m]) for m in MODELS}
    total = one(rows)
    if (total["pairs"], total["raw_p0_pass"], total["adjusted_p0_pass"], total["eligible_failures"], total["p1_recovered"], total["carry_forward_pass"]) != (188, 167, 170, 18, 15, 185):
        raise ValueError("Archived totals no longer reconcile")
    return {
        "by_model": by_model, "combined": total,
        "provenance_flags": [
            "CE023 adjusted P0 PASS is provisional: archived P1 CE023 test source differs from P0 test source; P1 PASS does not verify rerun of the P0 suite."
        ],
        "selected_p0_failure_stages": dict(Counter(r["p0_failure_stage"] for r in rows if r["eligible"])),
        "p0_to_p1_selected": dict(Counter(f"FAIL_to_{r['p1']}" for r in rows if r["eligible"])),
        "remaining": [f"{r['model']} {r['task']}" for r in rows if r["eligible"] and r["p1"] == "FAIL"],
        "log_checks": {
            "p0": dict(Counter(r["p0_log_check"] for r in rows)),
            "p1_eligible": dict(Counter(r["p1_log_check"] for r in rows if r["eligible"])),
        },
    }


def write_csv(path: Path, rows: list[dict]) -> None:
    with path.open("w", newline="", encoding="utf-8-sig") as stream:
        writer = csv.DictWriter(stream, fieldnames=list(rows[0]))
        writer.writeheader()
        writer.writerows(rows)


def main() -> None:
    rows = build_pairs()
    summary = summarize(rows)
    OUT.mkdir(parents=True, exist_ok=True)
    write_csv(OUT / "pair_transitions.csv", rows)
    (OUT / "summary.json").write_text(json.dumps(summary, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
    print(json.dumps(summary, ensure_ascii=False, indent=2))


if __name__ == "__main__":
    main()
