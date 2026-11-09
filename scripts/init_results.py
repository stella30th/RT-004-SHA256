from pathlib import Path
import csv
from logger import FIELDS

path = Path("results/experiment_results.csv")
path.parent.mkdir(parents=True, exist_ok=True)

with path.open("w", newline="", encoding="utf-8") as f:
    csv.writer(f).writerow(FIELDS)

print(f"Initialized {path}")
