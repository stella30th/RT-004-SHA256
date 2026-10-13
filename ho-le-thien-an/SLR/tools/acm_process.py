#!/usr/bin/env python3
"""acm_process.py: chuẩn hoá kết quả ACM (raw/acm_ACM-S*_results.json) sang schema chung.

Gộp bốn lượt tìm, bỏ trùng theo DOI rồi theo tiêu đề + năm, gắn cờ gợi ý, đếm cho PRISMA.
Không quyết định screening: screen_decision và exclusion_reason để trống cho người screening.
Năm và abstract đầy đủ lấy từ enrichment/openalex_by_doi.json nếu có (chạy acm_enrich.py trước).

Chạy từ thư mục ho-le-thien-an/SLR:
    python tools/acm_process.py --from-year 2022
"""
import argparse
import csv
import json
import re
import sys
import unicodedata
from pathlib import Path

SCHEMA = ("record_id title authors year venue doi abstract source source_url search_query_id "
          "screen_decision exclusion_reason notes seed").split()
AUDIT = ("record_id search_query_id rank_in_runs item_type year_source abstract_source access pdf_status "
         "auto_flags title").split()
DUP = "kept_record_id dup_run dup_rank dup_title matched_by same_run".split()
PROCEEDINGS = re.compile(r"^proceedings\b|^[A-Za-z0-9\-& ]+ ?['’]\d{2}:\s*(proceedings|companion)|\bproceedings of the\b", re.I)
DOWNLOADED = {"downloaded", "already_downloaded"}


def norm_doi(d):
    d = (d or "").strip().lower()
    return re.sub(r"^(https?://(dx\.)?doi\.org/|doi:\s*)", "", d)


def norm_title(t):
    return " ".join(re.findall(r"[^\W_]+", unicodedata.normalize("NFKC", t or "").casefold()))


def write_csv(path, cols, rows):
    with open(path, "w", encoding="utf-8-sig", newline="") as f:
        w = csv.DictWriter(f, fieldnames=cols, extrasaction="ignore")
        w.writeheader()
        w.writerows(rows)


def venue_year(venue):
    m = re.search(r"['’](\d{2})\b", venue or "")
    return 2000 + int(m.group(1)) if m else None


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--raw", default="raw")
    ap.add_argument("--enrich", default="enrichment/openalex_by_doi.json")
    ap.add_argument("--out", default=".")
    ap.add_argument("--from-year", type=int, default=2022)
    a = ap.parse_args()
    sys.stdout.reconfigure(encoding="utf-8")
    raw, out, from_year = Path(a.raw), Path(a.out), a.from_year

    files = sorted(raw.glob("acm_*_results.json"))
    if not files:
        sys.exit(f"Không có file acm_*_results.json trong {raw}/")
    enrich = {}
    if Path(a.enrich).exists():
        enrich = json.loads(Path(a.enrich).read_text(encoding="utf-8"))
    else:
        print(f"Chưa có {a.enrich}: chưa có năm/abstract đầy đủ (chạy tools/acm_enrich.py trước).", file=sys.stderr)

    unique, by_doi, by_title, dup_log, per_run = [], {}, {}, [], {}
    for p in files:
        run_id = re.search(r"(ACM-S\d+)", p.name).group(1)
        j = json.loads(p.read_text(encoding="utf-8-sig"))
        md, papers = j["metadata"], j["papers"]
        per_run[run_id] = {"file": p.name, "reported": md.get("reported_total"), "rows": len(papers),
                           "public": md.get("public_total"), "locked": md.get("locked_total"),
                           "unknown": md.get("unknown_total"), "scan_started_utc": md.get("started_at"),
                           "new_unique": 0, "duplicates": 0}
        for r in papers:
            doi = norm_doi(r.get("doi"))
            en = enrich.get(doi, {})
            year, ysrc = en.get("year"), "openalex"
            if not year:
                year, ysrc = venue_year(r.get("publication")), "venue_label"
            if not year:
                ysrc = ""
            tk = f"{norm_title(r.get('title'))}|{year or ''}"
            hit_doi = by_doi.get(doi) if doi else None
            hit = hit_doi or by_title.get(tk)
            if hit:
                per_run[run_id]["duplicates"] += 1
                same = run_id in hit["runs"]
                hit["runs"].append(run_id)
                hit["ranks"].append(f"{run_id}#{r.get('index')}")
                dup_log.append({"kept_record_id": hit["record_id"], "dup_run": run_id, "dup_rank": r.get("index"),
                                "dup_title": r.get("title"), "matched_by": "DOI" if hit_doi else "title+year",
                                "same_run": "yes" if same else "no"})
                continue
            full, snippet = en.get("abstract") or "", (r.get("abstract_snippet") or "").strip()
            abstract, asrc = (full, "openalex") if full else ((snippet, "acm_snippet") if snippet else ("", ""))
            rec = {"record_id": f"ACM_{len(unique) + 1:04d}", "title": r.get("title", ""),
                   "authors": "; ".join(r.get("authors") or []), "year": year or "", "year_source": ysrc,
                   "venue": r.get("publication") or "", "doi": doi, "abstract": abstract, "abstract_source": asrc,
                   "source": "ACM Digital Library", "source_url": r.get("acm_url") or "",
                   "item_type": r.get("date_type") or "", "access": r.get("access") or "",
                   "pdf_status": r.get("download_status") or "", "runs": [run_id], "ranks": [f"{run_id}#{r.get('index')}"]}
            unique.append(rec)
            per_run[run_id]["new_unique"] += 1
            if doi:
                by_doi[doi] = rec
            by_title[tk] = rec

    for r in unique:
        flags = []
        if r["year"] and r["year"] < from_year:
            flags.append(f"PRE_{from_year}")
        if not r["year"]:
            flags.append("NO_YEAR")
        if PROCEEDINGS.search(r["title"]) or re.search(r"proceedings|front.?matter", r["item_type"], re.I):
            flags.append("PROCEEDINGS_VOLUME")
        if r["access"] == "locked":
            flags.append("LOCKED")
        if r["pdf_status"] not in DOWNLOADED:
            flags.append("NO_PDF")
        if not r["abstract"]:
            flags.append("NO_ABSTRACT")
        elif r["abstract_source"] == "acm_snippet":
            flags.append("ABSTRACT_TRUNCATED")
        if not r["doi"]:
            flags.append("NO_DOI")
        r["auto_flags"] = ";".join(flags)
        r["search_query_id"] = ";".join(dict.fromkeys(r["runs"]))
        r["rank_in_runs"] = ";".join(r["ranks"])
        r["year_source"] = r["year_source"]
        notes = []
        if "ABSTRACT_TRUNCATED" in flags:
            notes.append("abstract rút gọn từ trang kết quả ACM")
        if "LOCKED" in flags:
            notes.append("ACM đánh dấu bị khoá (Get Access)")
        r["notes"] = "; ".join(notes)
        r["screen_decision"] = ""
        r["exclusion_reason"] = ""
        r["seed"] = ""

    out.mkdir(parents=True, exist_ok=True)
    write_csv(out / "01_acm_records.csv", SCHEMA, unique)
    write_csv(out / "records_audit.csv", AUDIT, unique)
    write_csv(out / "duplicate-log.csv", DUP, dup_log)

    def count(flag):
        return sum(flag in r["auto_flags"].split(";") for r in unique)

    flag_names = [f"PRE_{from_year}", "NO_YEAR", "PROCEEDINGS_VOLUME", "LOCKED", "NO_PDF", "NO_ABSTRACT", "ABSTRACT_TRUNCATED", "NO_DOI"]
    summary = {"from_year_rule": from_year, "per_run": per_run,
               "identified_total_rows": sum(p["rows"] for p in per_run.values()),
               "duplicates_removed": len(dup_log), "unique_records": len(unique),
               "flags": {f: count(f) for f in flag_names}}
    (out / "prisma-counts.json").write_text(json.dumps(summary, ensure_ascii=False, indent=2), encoding="utf-8")

    print("\n== Tổng kết (dán vào search-log.md / prisma-flow.md) ==")
    for rid, p in per_run.items():
        warn = f"  <-- LỆCH: ACM báo {p['reported']}, đọc được {p['rows']}" if p["reported"] != p["rows"] else ""
        print(f"{rid}: ACM báo {p['reported']} | đọc {p['rows']} | mới {p['new_unique']} | trùng {p['duplicates']}{warn}")
    print(f"Tổng dòng: {summary['identified_total_rows']} | trùng loại: {summary['duplicates_removed']} | duy nhất: {summary['unique_records']}")
    print("Cờ:", json.dumps(summary["flags"], ensure_ascii=False))


if __name__ == "__main__":
    main()
