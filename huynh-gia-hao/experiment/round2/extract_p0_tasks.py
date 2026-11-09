import pathlib
import re
import sys

master = pathlib.Path(sys.argv[1])
out = pathlib.Path(sys.argv[2])
out.mkdir(parents=True, exist_ok=True)

tasks = [
    "CE033", "CE041", "CE043", "CE050",
    "CE053", "CE060", "CE065", "CE066",
    "CE077", "CE085", "CE090"
]

content = master.read_text(encoding="utf-8-sig")

# Detect all CE task headings separated by lines of equals signs.
pattern = re.compile(
    r"(?m)^={20,}\s*\n"
    r"(CE\d{3})[ \t]+[^\r\n]+\r?\n"
    r"={20,}[ \t]*\r?\n"
)

matches = list(pattern.finditer(content))
blocks = {}

for i, match in enumerate(matches):
    task_id = match.group(1)
    start = match.end()
    end = (
        matches[i + 1].start()
        if i + 1 < len(matches)
        else len(content)
    )
    blocks[task_id] = content[start:end].strip()

missing = []
for task in tasks:
    body = blocks.get(task, "")

    if not body or f"TASK_ID: {task}" not in body:
        missing.append(task)
        print(f"[MISSING] {task}")
        continue

    if "TARGET_SOURCE:" not in body:
        missing.append(task)
        print(f"[INVALID] {task}: TARGET_SOURCE missing")
        continue

    target = out / f"{task}.txt"
    target.write_text(body + "\n", encoding="utf-8")
    print(f"[CREATED] {target} ({len(body)} chars)")

print(f"\nExtracted: {len(tasks) - len(missing)}/{len(tasks)}")
print(f"Master task blocks detected: {len(matches)}")

if missing:
    raise SystemExit("Missing/invalid: " + ", ".join(missing))
