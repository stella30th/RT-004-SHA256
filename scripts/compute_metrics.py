import argparse
import csv
from collections import defaultdict


def load_rows(path: str) -> list[dict]:
    with open(path, newline="", encoding="utf-8") as f:
        return list(csv.DictReader(f))


def main() -> None:
    parser = argparse.ArgumentParser()
    parser.add_argument("--input", required=True)
    args = parser.parse_args()

    rows = load_rows(args.input)
    if not rows:
        print("No rows found.")
        return

    required = {"human", "chatgpt", "gemini"}
    if not required.issubset(rows[0].keys()):
        print("Long-format experiment file detected. Aggregate after pilot schema is frozen.")
        return

    totals = defaultdict(lambda: [0, 0])
    per_category = defaultdict(lambda: defaultdict(lambda: [0, 0]))

    for row in rows:
        category = row.get("task_category", "UNKNOWN")
        for condition in ("human", "chatgpt", "gemini"):
            value = int(row[condition])
            totals[condition][0] += value
            totals[condition][1] += 1
            per_category[category][condition][0] += value
            per_category[category][condition][1] += 1

    print("Overall success rate")
    for condition, (success, total) in totals.items():
        print(f"{condition:10s}: {success}/{total} = {success / total:.3f}")

    print("\nPer-category success rate")
    for category in sorted(per_category):
        print(f"\n[{category}]")
        for condition, (success, total) in per_category[category].items():
            print(f"{condition:10s}: {success}/{total} = {success / total:.3f}")

    print("\nPlanned inference after protocol freeze:")
    print("- Cochran's Q for the 3 paired conditions")
    print("- Pairwise McNemar tests if justified")
    print("- Holm correction for multiple comparisons")
    print("- Effect sizes and 95% confidence intervals")


if __name__ == "__main__":
    main()
