import argparse
import json
import sys
import time
import urllib.error
import urllib.parse
import urllib.request
from pathlib import Path

API = "https://api.openalex.org/works"
BATCH = 50


def norm_doi(d):
    d = (d or "").strip().lower()
    for p in ("https://doi.org/", "http://doi.org/", "https://dx.doi.org/", "doi:"):
        if d.startswith(p):
            d = d[len(p):]
    return d


def rebuild_abstract(inv):
    if not inv:
        return ""
    words = {}
    for w, positions in inv.items():
        for p in positions:
            words[p] = w
    return " ".join(words[i] for i in sorted(words))


def fetch(dois):
    flt = "doi:" + "|".join("https://doi.org/" + d for d in dois)
    q = urllib.parse.urlencode({
        "filter": flt, "per-page": BATCH,
        "select": "id,doi,publication_year,publication_date,type,abstract_inverted_index",
    }, quote_via=urllib.parse.quote_plus, safe=":/|")
    req = urllib.request.Request(f"{API}?{q}", headers={"User-Agent": "swt302-slr-metadata/1.0"})
    for attempt in range(4):
        try:
            with urllib.request.urlopen(req, timeout=60) as r:
                return json.load(r)["results"]
        except urllib.error.HTTPError as e:
            if e.code in (429, 500, 502, 503) and attempt < 3:
                time.sleep(5 * (attempt + 1))
                continue
            raise
    return []


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--raw", default="raw")
    ap.add_argument("--out", default="enrichment/openalex_by_doi.json")
    a = ap.parse_args()
    sys.stdout.reconfigure(encoding="utf-8")

    dois = set()
    for p in sorted(Path(a.raw).glob("acm_*_results.json")):
        for r in json.loads(p.read_text(encoding="utf-8-sig"))["papers"]:
            if r.get("doi"):
                dois.add(norm_doi(r["doi"]))
    if not dois:
        sys.exit(f"Không thấy DOI nào trong {a.raw}/acm_*_results.json")

    out = Path(a.out)
    out.parent.mkdir(parents=True, exist_ok=True)
    cache = json.loads(out.read_text(encoding="utf-8")) if out.exists() else {}
    todo = sorted(d for d in dois if d not in cache)
    print(f"DOI duy nhất: {len(dois)} | đã có cache: {len(dois) - len(todo)} | cần hỏi: {len(todo)}")

    for i in range(0, len(todo), BATCH):
        chunk = todo[i:i + BATCH]
        found = {}
        for w in fetch(chunk):
            found[norm_doi(w.get("doi"))] = {
                "openalex_id": w.get("id"), "year": w.get("publication_year"),
                "publication_date": w.get("publication_date"), "type": w.get("type"),
                "abstract": rebuild_abstract(w.get("abstract_inverted_index")),
            }
        for d in chunk:
            cache[d] = found.get(d, {"openalex_id": None, "year": None, "publication_date": None, "type": None, "abstract": ""})
        out.write_text(json.dumps(cache, ensure_ascii=False, indent=1), encoding="utf-8")
        print(f"  {min(i + BATCH, len(todo))}/{len(todo)}")
        time.sleep(0.5)

    have = [d for d in dois if cache.get(d, {}).get("openalex_id")]
    print(f"OpenAlex có {len(have)}/{len(dois)} DOI | có năm: {sum(bool(cache[d]['year']) for d in dois)} | có abstract: {sum(bool(cache[d]['abstract']) for d in dois)}")


if __name__ == "__main__":
    main()
