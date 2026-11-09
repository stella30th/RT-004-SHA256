# Nguyen Nam Khoa - Literature Search

Primary source: Semantic Scholar. Google Scholar is assigned as a supplementary source, but no Google Scholar session is recorded in this collection.

## Files

- `search_log.csv`: Six manual search runs on 11 October 2026, including exact queries, filters, reported hits, candidate counts and included counts.
- `candidate_papers.csv`: Five collected papers, bibliographic metadata, full-text screening decisions and criterion-specific reasons.
- `evidence_table.csv`: Structured extraction following `docs/evidence_table_template.csv`.
- `rq_evidence_map.md`: How each study informs the current research question.
- `../HANDOVER.md`: Progress, review instructions and unresolved items.

## Search method

Searches were conducted manually in Semantic Scholar using six recorded query variants, the 2024-2026 year filter and sorting by citation count. The shared concepts are test generation, ChatGPT/Gemini/LLMs, and compilation/execution or repair. These variants do not all preserve every term in the shared Boolean query; they are recorded as actual searches, not asserted to be equivalent executions of the complete query. String Version C is retained from the source workbook.

| Run | Exact query | Paper IDs |
|---|---|---|
| SS-P01 | JUnit ChatGPT compilation | R001 |
| SS-P02 | JUnit Gemini compilation | None collected |
| SS-P03 | LLM JUnit test generation | R002, R003 |
| SS-P04 | JUnit LLM repair | R004 |
| SS-P05 | Automated test generation Large Language Models | R005 |
| SS-P06 | "unit tests" ChatGPT runnable | None collected |

The researcher confirmed these mappings. Search date is 2026-10-11; the source workbook's 2026-11-10 date is incorrect. The original workbook and collected metadata remain unchanged.

## Screening and counting

All five supplied full texts were assessed against `docs/ie_criteria.md`; all meet IC1-IC5. No exclusions were identified within this five-paper collection. Code-Aware Prompting provides indirect evidence because it studies Python; the criteria do not require every included paper to evaluate Java/JUnit or Gemini.

`candidate` counts collected papers assigned to each run. `included` counts mapped papers with an Included decision. `screened` remains blank because the number of search results originally inspected per run was not recorded. Five collected papers have subsequently been full-text screened; this does not establish the historical title/abstract screening count. No collected paper in a run does not mean no results were inspected.

Reported raw hits may overlap across queries. Their arithmetic sum must not be reported as the number of unique studies identified. R001 and R004 overlap with Khoi's collection and must be deduplicated in the merged team review.

## Evidence and verification

Extraction uses the supplied Markdown full texts and `Nghiencuu/all_records.csv`. Original abstracts and source metadata are preserved. Artifact links are recorded as cited by the papers; their current accessibility and the experiments have not been independently verified. Resolve the TestART version discrepancy and the HITS template placeholders before final citation.
