#!/usr/bin/env python3
"""build_screening.py: gộp bản ghi ACM + snowballing và dựng các file screening từ screening_decisions.csv.

Đầu vào : 01_acm_records.csv, records_audit.csv, snowball/new_records.csv, screening_decisions.csv
Đầu ra  : 01_all_records.csv, 02_after_screening_v1.csv, 03_final_included.csv, candidate_papers.csv,
          search_log.csv, prisma-counts-final.json
Tuỳ chọn: --pdf-root <thư mục PDF> --papers papers/  để chép PDF của các bài included vào papers/.

    python tools/build_screening.py [--pdf-root <thư mục>]
"""
import argparse
import csv
import json
import os
import re
import shutil
import sys
from collections import Counter
from pathlib import Path

SEED_ID = "ACM_0041"
SCHEMA = ("record_id title authors year venue doi abstract source source_url search_query_id "
          "screen_decision exclusion_reason notes seed").split()
SCREEN = ("record_id title authors year venue doi abstract source source_url search_query_id screen_decision "
          "exclusion_reason reviewer_decision conflict? final_source_decision notes").split()
SECONDARY = {"ACM_0003", "SB_0007"}


def read(path):
    with open(path, encoding="utf-8-sig", newline="") as f:
        return list(csv.DictReader(f))


def write(path, cols, rows):
    with open(path, "w", encoding="utf-8-sig", newline="") as f:
        w = csv.DictWriter(f, fieldnames=cols, extrasaction="ignore")
        w.writeheader()
        w.writerows(rows)


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--pdf-root")
    ap.add_argument("--papers", default="papers")
    a = ap.parse_args()
    sys.stdout.reconfigure(encoding="utf-8")

    acm = read("01_acm_records.csv")
    audit = {r["record_id"]: r for r in read("records_audit.csv")}
    dec = {r["record_id"]: r for r in read("screening_decisions.csv")}

    rows = []
    for r in acm:
        r = dict(r)
        r["seed"] = "seed" if r["record_id"] == SEED_ID else ""
        rows.append(r)
    for i, r in enumerate(read("snowball/new_records.csv"), 1):
        direction = r["direction"].upper()
        rows.append({
            "record_id": f"SB_{i:04d}", "title": r["title"], "authors": r["authors"], "year": r["year"],
            "venue": r["venue"], "doi": r["doi"], "abstract": r["abstract"],
            "source": "OpenAlex (snowballing)", "source_url": f"https://doi.org/{r['doi']}" if r["doi"] else r["openalex_id"],
            "search_query_id": f"SNOWBALL-{direction}", "screen_decision": "", "exclusion_reason": "",
            "notes": "", "seed": SEED_ID})
    write("01_all_records.csv", SCHEMA, rows)

    out2, final, cand = [], [], []
    for r in rows:
        d = dec[r["record_id"]]
        r1, r2 = d["round1"], d["round2"]
        final_dec = r2 or "Exclude"
        code = d["round2_code"] if r2 == "Exclude" else (d["round1_code"] if r1 == "Exclude" else "")
        notes = " | ".join(x for x in (f"V1: {d['round1_note']}" if d["round1_note"] else "",
                                       f"V2: {d['round2_note']}" if d["round2_note"] else "") if x)
        out2.append({**r, "screen_decision": r1, "exclusion_reason": code, "reviewer_decision": "",
                     "conflict?": "", "final_source_decision": final_dec, "notes": notes})
        if r1 in ("Include", "Maybe"):
            cand.append({"paper_id": r["record_id"], "title": r["title"], "authors": r["authors"], "year": r["year"],
                         "doi_or_url": r["source_url"], "decision": "Included" if r2 == "Include" else "Excluded",
                         "reason": code or "I1+I2", "notes": d["round2_note"]})
        if r2 == "Include":
            final.append({**r, "study_type": "secondary (tổng quan)" if r["record_id"] in SECONDARY else "primary",
                          "notes": d["round2_note"]})
    write("02_after_screening_v1.csv", SCREEN, out2)
    write("03_final_included.csv", "record_id title authors year venue doi source source_url search_query_id seed study_type notes".split(), final)
    write("candidate_papers.csv", "paper_id title authors year doi_or_url decision reason notes".split(), cand)

    # search_log.csv (mẫu của nhóm)
    runs = {}
    for r in rows:
        if r["record_id"].startswith("ACM_"):
            for q in r["search_query_id"].split(";"):
                runs.setdefault(q, []).append(r["record_id"])
    meta = {}
    for p in sorted(Path("raw").glob("acm_*_results.json")):
        j = json.loads(p.read_text(encoding="utf-8-sig"))["metadata"]
        meta[re.search(r"ACM-S\d", p.name).group(0)] = j
    log = []
    for q in sorted(runs):
        ids = runs[q]
        query = urllib_unquote(meta[q]["search_url"])
        log.append({"database": "ACM Digital Library", "query": f"{q}: {query}", "date": "2026-10-11",
                    "raw_hits": meta[q]["reported_total"], "screened": len(ids),
                    "candidate": sum(dec[i]["round1"] in ("Include", "Maybe") for i in ids),
                    "included": sum(dec[i]["round2"] == "Include" for i in ids),
                    "notes": "Số screened = bản ghi duy nhất xuất hiện trong lượt này (một bài có thể thuộc nhiều lượt)."})
    sb = [r for r in rows if r["record_id"].startswith("SB_")]
    log.append({"database": "OpenAlex (snowballing seed ACM_0041)",
                "query": "backward + forward citations của ACM_0041, năm >= 2022", "date": "2026-10-11",
                "raw_hits": "", "screened": len(sb),
                "candidate": sum(dec[r["record_id"]]["round1"] in ("Include", "Maybe") for r in sb),
                "included": sum(dec[r["record_id"]]["round2"] == "Include" for r in sb),
                "notes": "Xem search-log.md, mục Snowballing."})
    write("search_log.csv", "database query date raw_hits screened candidate included notes".split(), log)

    c1 = Counter((d["round1"], d["round1_code"]) for d in dec.values())
    c2 = Counter((d["round2"], d["round2_code"]) for d in dec.values() if d["round1"] in ("Include", "Maybe"))
    summary = {"acm_unique_records": len(acm), "snowball_new_records": len(sb),
               "round1": {f"{k[0]}|{k[1]}": v for k, v in c1.items()},
               "round2": {f"{k[0]}|{k[1]}": v for k, v in c2.items()},
               "final_included": len(final), "final_included_acm": sum(r["record_id"].startswith("ACM_") for r in final),
               "final_included_snowball": sum(r["record_id"].startswith("SB_") for r in final)}
    Path("prisma-counts-final.json").write_text(json.dumps(summary, ensure_ascii=False, indent=2), encoding="utf-8")
    print(json.dumps(summary, ensure_ascii=False, indent=1))

    if a.pdf_root:
        pdfs = {}
        for p in Path(a.pdf_root).rglob("*.pdf"):
            pdfs.setdefault(p.name, p)
        doi_file = {}
        for f in sorted(Path("raw").glob("acm_*_results.json")):
            for r in json.loads(f.read_text(encoding="utf-8-sig"))["papers"]:
                if r.get("local_file") and r.get("doi"):
                    doi_file.setdefault(r["doi"].lower(), os.path.basename(r["local_file"]))
        Path(a.papers).mkdir(exist_ok=True)
        n = 0
        for r in final:
            src = pdfs.get(doi_file.get(r["doi"].lower(), ""))
            if src:
                shutil.copyfile(src, Path(a.papers) / f"{r['record_id']}_{re.sub(r'[^0-9A-Za-z]+', '-', r['doi'])}.pdf")
                n += 1
        print(f"Đã chép {n} PDF của bài included vào {a.papers}/")


def urllib_unquote(url):
    from urllib.parse import unquote_plus
    m = re.search(r"AllField=(.*)$", url)
    return unquote_plus(m.group(1)) if m else url


if __name__ == "__main__":
    main()
