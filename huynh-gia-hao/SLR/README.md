# Huỳnh Gia Hào – SLR

Assigned sources:
- **Google Scholar – Block A**
- **IEEE Xplore**

Use the shared research question, search protocol, and inclusion/exclusion criteria from `team-synthesis/`.

## Current Week 6 status

### Google Scholar
Completed at source level.

Queries:
- `GS-A01`: `"ChatGPT" "unit test generation"`
- `GS-A02`: `ChatGPT JUnit`
- `GS-A03`: `ChatGPT "unit testing"`
- `GS-A04`: `"large language model" "unit test generation"`
- `GS-A05`: `LLM "unit testing" compilation`
- `GS-A06`: `LLM JUnit repair`

Working source-level flow:
- ~600 result-page slots reviewed
- 456 unique title-level candidates after within-source dedup
- 19 source-level candidates retained for later cross-source merge

Outputs are under `google-scholar/`.

### Core full-paper evidence
Five core papers were selected and read in full.
The evidence extraction and Week 6 synthesis are under `evidence/`.

### IEEE Xplore
`IEEE-A01` has been exported and checked.
`IEEE-A02`–`IEEE-A04` still need to be completed before the IEEE source block can be marked complete.

Raw IEEE evidence currently available:
- `ieee/raw/IEEE-A01.csv`

## Repository rules

- Record search sessions in `search_log.csv`.
- Record retained candidate papers in `candidate_papers.csv`.
- Keep raw exports unchanged.
- Deduplicate by DOI first, then normalized title + year.
- Do not treat source-level candidates as final studies for the whole SLR.
- Final team PRISMA is created only after all sources are merged, cross-source duplicates are removed, and full-text screening is complete.
