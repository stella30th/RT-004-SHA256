#!/usr/bin/env python3
"""fulltext_signals.py: đếm bằng chứng trong toàn văn PDF để hỗ trợ screening vòng 2 (tiêu chí I2).

Với mỗi bản ghi ở vòng 1 là Include/Maybe và có PDF, trích văn bản (bỏ phần References) rồi đếm các câu
nhắc đến biên dịch (compil*), thực thi/chạy được (execut*/runnab*) và sửa lỗi (repair/fix/self-debug...) gắn với
tỉ lệ, lỗi, thành công, thất bại. Con số chỉ là gợi ý để người screening đọc lại câu gốc, không tự quyết định.

Cần: pip install pypdf
    python tools/fulltext_signals.py --pdf-root <thư mục chứa các thư mục pdfs/>
"""
import argparse
import csv
import json
import os
import re
import sys
import tempfile
import logging
from pathlib import Path

logging.disable(logging.CRITICAL)
NEAR = r"(?:rate|success|succeed|error|fail|pass|correct|valid|%|percent|ratio|proportion|issue|problem)"
COMPIL = r"compil(?:ation|able|ability|es|e|ed|er|ers)\b"
EXEC = r"(?:execut(?:able|ability|ion)|runnab\w+|run successfully)"
PATTERNS = {
    "unit_test_mentions": re.compile(r"\bunit tests?\b|\btest cases?\b|\bJUnit\b", re.I),
    "compile_evidence": re.compile(rf"(?:{COMPIL}[^.\n]{{0,70}}{NEAR}|{NEAR}[^.\n]{{0,70}}{COMPIL})", re.I),
    "execute_evidence": re.compile(rf"(?:{EXEC}[^.\n]{{0,70}}{NEAR}|{NEAR}[^.\n]{{0,70}}{EXEC})", re.I),
    "repair_evidence": re.compile(r"(?:repair|self-?debug|self-?correct|refine|fix(?:ing)?)[^.\n]{0,70}(?:compil|build|error|fail)", re.I),
}


def body_of(text):
    i = text.lower().rfind("\nreferences")
    return text[:i] if i > len(text) * 0.5 else text


def extract(pdf, cache):
    out = cache / (re.sub(r"[^\w.-]", "_", os.path.basename(pdf)) + ".txt")
    if out.exists():
        return out.read_text(encoding="utf-8")
    from pypdf import PdfReader
    try:
        text = "\n".join((p.extract_text() or "") for p in PdfReader(pdf).pages)
    except Exception:
        text = ""
    text = text.encode("utf-8", "ignore").decode("utf-8")
    out.write_text(text, encoding="utf-8")
    return text


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--pdf-root", required=True)
    ap.add_argument("--raw", default="raw")
    ap.add_argument("--records", default="01_all_records.csv")
    ap.add_argument("--decisions", default="screening_decisions.csv")
    ap.add_argument("--out", default="fulltext_signals.csv")
    ap.add_argument("--cache", default=str(Path(tempfile.gettempdir()) / "slr_fulltext_cache"))
    a = ap.parse_args()
    sys.stdout.reconfigure(encoding="utf-8")
    cache = Path(a.cache)
    cache.mkdir(parents=True, exist_ok=True)

    pdfs = {}
    for p in Path(a.pdf_root).rglob("*.pdf"):
        pdfs.setdefault(p.name, str(p))
    doi_file = {}
    for f in sorted(Path(a.raw).glob("acm_*_results.json")):
        for r in json.loads(f.read_text(encoding="utf-8-sig"))["papers"]:
            if r.get("local_file") and r.get("doi"):
                doi_file.setdefault(r["doi"].lower(), os.path.basename(r["local_file"]))

    with open(a.records, encoding="utf-8-sig", newline="") as f:
        recs = {r["record_id"]: r for r in csv.DictReader(f)}
    with open(a.decisions, encoding="utf-8-sig", newline="") as f:
        cand = [r["record_id"] for r in csv.DictReader(f) if r["round1"] in ("Include", "Maybe") and r["record_id"] in recs]

    rows = []
    for rid in cand:
        r = recs[rid]
        pdf = pdfs.get(doi_file.get(r["doi"].lower(), ""))
        row = {"record_id": rid, "title": r["title"], "year": r["year"], "pdf_found": "yes" if pdf else "no",
               "text_chars": 0, **{k: 0 for k in PATTERNS}}
        if pdf:
            body = body_of(extract(pdf, cache))
            row["text_chars"] = len(body)
            for k, p in PATTERNS.items():
                row[k] = len(p.findall(body))
        rows.append(row)
        print(f"{rid} {row['pdf_found']} c={row['compile_evidence']} e={row['execute_evidence']} r={row['repair_evidence']}", file=sys.stderr)

    cols = ["record_id", "title", "year", "pdf_found", "text_chars"] + list(PATTERNS)
    with open(a.out, "w", encoding="utf-8-sig", newline="") as f:
        w = csv.DictWriter(f, fieldnames=cols)
        w.writeheader()
        w.writerows(rows)
    print(f"{len(rows)} bản ghi vòng 1 Include/Maybe | có PDF: {sum(r['pdf_found'] == 'yes' for r in rows)} -> {a.out}")


if __name__ == "__main__":
    main()
