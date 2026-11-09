import csv
from pathlib import Path

FIELDS = [
    "problem_id",
    "task_category",
    "condition",
    "model",
    "prompt_version",
    "attempt",
    "compile_success",
    "execution_success",
    "combined_success",
    "repair_needed",
    "repair_success",
    "failure_stage",
    "compiler_error",
    "notes",
]


def append_result(path: str | Path, row: dict) -> None:
    path = Path(path)
    path.parent.mkdir(parents=True, exist_ok=True)
    exists = path.exists()

    with path.open("a", newline="", encoding="utf-8") as f:
        writer = csv.DictWriter(f, fieldnames=FIELDS)
        if not exists:
            writer.writeheader()
        writer.writerow({key: row.get(key, "") for key in FIELDS})
