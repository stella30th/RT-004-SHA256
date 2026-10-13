#!/usr/bin/env python3
"""snowball_openalex.py: snowballing một seed (một lượt lùi, một lượt tiến) qua OpenAlex.

Backward: các tài liệu mà seed trích dẫn. Forward: các tài liệu trích dẫn seed.
Ghi raw vào snowball/<seed>.json, rồi tách bản ghi mới (chưa có trong 01_acm_records.csv) ra
snowball/new_records.csv để screening.

    python tools/snowball_openalex.py --seed-doi 10.1145/3661167.3661216 --from-year 2022
"""
import argparse
import csv
import json
import sys
import re
import time
import unicodedata
import urllib.parse
import urllib.request
from pathlib import Path

API = "https://api.openalex.org/works"
SELECT = "id,doi,title,publication_year,publication_date,type,authorships,primary_location,abstract_inverted_index,referenced_works"


def get(url):
    req = urllib.request.Request(url, headers={"User-Agent": "swt302-slr-metadata/1.0"})
    for attempt in range(4):
        try:
            with urllib.request.urlopen(req, timeout=60) as r:
                return json.load(r)
        except Exception:
            if attempt == 3:
                raise
            time.sleep(5 * (attempt + 1))


def abstract_of(inv):
    if not inv:
        return ""
    words = {p: w for w, ps in inv.items() for p in ps}
    return " ".join(words[i] for i in sorted(words))


def norm_doi(d):
    return re.sub(r"^https?://(dx\.)?doi\.org/", "", (d or "").strip().lower())


def norm_title(t):
    return " ".join(re.findall(r"[^\W_]+", unicodedata.normalize("NFKC", t or "").casefold()))


def flat(w):
    loc = (w.get("primary_location") or {}).get("source") or {}
    return {"openalex_id": w["id"], "doi": norm_doi(w.get("doi")), "title": w.get("title") or "",
            "year": w.get("publication_year"), "type": w.get("type"),
            "authors": "; ".join(a["author"]["display_name"] for a in w.get("authorships", [])),
            "venue": loc.get("display_name") or "", "abstract": abstract_of(w.get("abstract_inverted_index"))}


def paged(flt):
    out, cursor = [], "*"
    while cursor:
        q = urllib.parse.urlencode({"filter": flt, "per-page": 200, "cursor": cursor, "select": SELECT},
                                   safe=":|/,*")
        d = get(f"{API}?{q}")
        out += d["results"]
        cursor = d["meta"].get("next_cursor")
        time.sleep(0.4)
    return out


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--seed-doi", required=True)
    ap.add_argument("--from-year", type=int, default=2022)
    ap.add_argument("--records", default="01_acm_records.csv")
    ap.add_argument("--out", default="snowball")
    a = ap.parse_args()
    sys.stdout.reconfigure(encoding="utf-8")
    out = Path(a.out)
    out.mkdir(exist_ok=True)

    seed = get(f"{API}?filter=doi:https://doi.org/{a.seed_doi}&select={SELECT}")["results"][0]
    sid = seed["id"].rsplit("/", 1)[-1]
    refs = [r.rsplit("/", 1)[-1] for r in seed.get("referenced_works", [])]
    backward = []
    for i in range(0, len(refs), 50):
        backward += paged("openalex:" + "|".join(refs[i:i + 50]))
    forward = paged(f"cites:{sid}")

    known_doi, known_title = set(), set()
    with open(a.records, encoding="utf-8-sig", newline="") as f:
        for r in csv.DictReader(f):
            known_doi.add(norm_doi(r["doi"]))
            known_title.add(norm_title(r["title"]))

    rows, seen = [], set()
    for direction, items in (("backward", backward), ("forward", forward)):
        for w in items:
            x = flat(w)
            key = x["doi"] or norm_title(x["title"])
            status = "already_in_ACM_records" if (x["doi"] in known_doi or norm_title(x["title"]) in known_title) else (
                "pre_year_rule" if (x["year"] or 0) < a.from_year else "new")
            rows.append({**x, "direction": direction, "status": status, "dup_within_snowball": key in seen})
            seen.add(key)

    (out / f"seed_{a.seed_doi.replace('/', '-')}.json").write_text(json.dumps(
        {"seed": flat(seed), "n_references": len(refs), "backward": [flat(w) for w in backward],
         "forward": [flat(w) for w in forward]}, ensure_ascii=False, indent=1), encoding="utf-8")
    new = [r for r in rows if r["status"] == "new" and not r["dup_within_snowball"]]
    cols = "openalex_id doi title year type authors venue abstract direction".split()
    with open(out / "new_records.csv", "w", encoding="utf-8-sig", newline="") as f:
        w = csv.DictWriter(f, fieldnames=cols, extrasaction="ignore")
        w.writeheader()
        w.writerows(new)

    def c(d, s):
        return sum(1 for r in rows if r["direction"] == d and r["status"] == s)
    print(f"Seed: {seed.get('title')} ({seed.get('publication_year')})")
    print(f"Backward: {len(backward)} tài liệu (OpenAlex ghi nhận {len(refs)} tham chiếu)")
    print(f"Forward : {len(forward)} tài liệu trích dẫn seed")
    for d in ("backward", "forward"):
        print(f"  {d}: đã có trong bản ghi ACM {c(d,'already_in_ACM_records')} | trước {a.from_year} {c(d,'pre_year_rule')} | mới {c(d,'new')}")
    print(f"Bản ghi mới sau khi bỏ trùng: {len(new)} -> {out / 'new_records.csv'}")


if __name__ == "__main__":
    main()
