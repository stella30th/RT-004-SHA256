# Week 6 Team PRISMA — Working Synthesis

## Scope

This is the **team-level Week 6 working PRISMA synthesis** created from the four members' repository outputs.

It is **not the final SLR PRISMA** because:
- Hào's IEEE source is incomplete (`IEEE-A02`–`IEEE-A04` pending).
- Semantic Scholar historical screened counts were not recorded.
- Raw hit counts from query engines are overlapping and not directly summable.
- Team full-text eligibility screening of all deduplicated candidates is still pending.

## Source-level collection status

| Member | Source | Source-level result |
|---|---|---|
| Huỳnh Gia Hào | Google Scholar | ~600 visible result slots → 456 unique screened → 19 retained |
| Huỳnh Gia Hào | IEEE Xplore | A01 exported (85 records); A02–A04 pending |
| Hồ Lê Thiên An | ACM DL + snowballing | 747 ACM unique + 76 snowball new = 823 round-1 records → 62 eligible after round 2 → 6 selected |
| Nguyễn Hoàng Khôi | OpenAlex | 3,441 raw hits → 23 screened → 18 included |
| Nguyễn Nam Khoa | Semantic Scholar | 5 collected/full-text candidates included; historical screened count unavailable |

## Team merge flow

- Source-level candidate records supplied to team merge: **48**
  - Hào / Google Scholar: 19
  - An / ACM + snowballing: 6
  - Khôi / OpenAlex: 18
  - Khoa / Semantic Scholar: 5
- Cross-source duplicate records removed by exact DOI or exact normalized title: **12**
- Unique publication records after cross-source dedup: **36**
- Known related preprint/published version groups flagged for study-level reconciliation: **1**
- Evidence studies currently synthesized from full-text/member evidence: **13**

## PRISMA interpretation

```text
Source searches
  ├─ Google Scholar
  ├─ IEEE Xplore (incomplete)
  ├─ ACM Digital Library + snowballing
  ├─ OpenAlex
  └─ Semantic Scholar
          ↓
Source-level screening / selection
          ↓
48 candidate records handed to team merge
          ↓
12 duplicate records removed
          ↓
36 unique publication records
          ↓
publication-version reconciliation + team eligibility/full-text screening
          ↓
FINAL included studies (TBD)
```

## Why raw hits are not summed

Google Scholar and Semantic Scholar report approximate query hit counts; multiple queries overlap heavily.
OpenAlex reports 3,441 raw hits but only 23 were actually screened in the recorded run.
Therefore a single fabricated "records identified = sum of all hit counters" would not be reproducible.

The team should report per-source identification counts and use the auditable **48 → 36** merge counts for the current Week 6 synthesis.

## Current Week 6 evidence status

The merged evidence table contains **13 high-relevance studies** extracted from full-text/member evidence.
These support:
- compile success and execution success as separate outcomes,
- compiler-feedback/test repair,
- Java/JUnit benchmark relevance,
- failure-category analysis,
- context/dependency complexity as a runnability factor.

## Remaining before final PRISMA

1. Complete Hào's IEEE-A02–A04.
2. Normalize/screen IEEE and add its retained candidates.
3. Re-run cross-source dedup.
4. Reconcile preprint/published study versions.
5. Perform team-level eligibility/full-text screening on the unique merged set.
6. Record exclusion reasons.
7. Produce final included-study count and final PRISMA.
