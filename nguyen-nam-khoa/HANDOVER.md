# RBL-1 Literature Search Handover

Member: Nguyen Nam Khoa  
Main role: Metrics & Statistics  
Primary literature source: Semantic Scholar  
Search date: 11 October 2026  
Method: Manual searching, collected metadata and supplied Markdown full-text review

## Delivered files

| File | Content | Status |
|---|---|---|
| SLR/search_log.csv | Six search runs and confirmed paper mappings | Recorded; historical screened counts unavailable |
| SLR/candidate_papers.csv | Five candidates with Included decisions and IC1-IC5 reasons | Full-text screening completed |
| SLR/evidence_table.csv | Models, datasets, settings, metrics, results, availability, limitations and RQ relevance | Extracted from supplied sources |
| SLR/rq_evidence_map.md | Contribution to the RQ and remaining evidence gaps | Completed |
| SLR/README.md | Manual search method, counting rules and verification boundaries | Completed |

## Search and screening results

| Run | Reported raw hits | Originally screened | Candidates | Included | Paper IDs |
|---|---:|---|---:|---:|---|
| SS-P01 | 37 | Not recorded | 1 | 1 | R001 |
| SS-P02 | 33 | Not recorded | 0 | 0 | None |
| SS-P03 | 6610 | Not recorded | 2 | 2 | R002, R003 |
| SS-P04 | 2230 | Not recorded | 1 | 1 | R004 |
| SS-P05 | 90100 | Not recorded | 1 | 1 | R005 |
| SS-P06 | 1650 | Not recorded | 0 | 0 | None |

Total collected candidates: 5. Full-text papers assessed: 5. Included: 5. Excluded: 0 within this collection. The original number of results inspected during searching is unknown. These are individual collection counts before team deduplication.

Raw-hit counts are per-query reports and may overlap; they are not a unique-study total. The Excel date was corrected in the SLR records using the researcher's confirmation. Paper mappings were also confirmed by the researcher.

## Main contributions

- R001 provides evidence on compilation, execution and assertion failures, plus iterative repair.
- R002 supports examining complex methods and branching/loop-related challenges.
- R003 supports structured, path-aware prompting as methodological background.
- R004 provides evidence on generation-repair feedback and correctness/coverage outcomes.
- R005 provides a compilation-success metric and evidence on prompt/context effects.

The studies support the experiment design but do not directly reproduce the exact Human/ChatGPT/Gemini, 30-problem HumanEval and one-repair-round comparison.

## Review instructions

1. Check each run's query, date, filters and confirmed paper mapping in search_log.csv.
2. Open each paper's DOI or collection URL and compare the title, authors, year and full-text version.
3. Cross-check the IC1-IC5 reasons against docs/ie_criteria.md and the supplied full texts.
4. Verify extracted metrics and results against the cited study sections/tables. Keep dataset denominators and percentage-point changes distinct from relative changes.
5. Deduplicate R001 against Khoi's P01 and R004 against Khoi's P16 during team merging. Review related publication versions under EC3 and retain the most complete study report.
6. Record peer-review findings separately. This handover does not claim peer review has already been completed.

## Remaining items

- Recover original per-run screened counts from contemporaneous evidence if available; otherwise leave them unknown. Any repeat search must be logged as a new session with its actual date and observed counts.
- Verify DOI metadata and current full-text/artifact availability. Reconcile the 2024 TestART preprint metadata with the supplied 2025 Markdown before formal citation; HITS Markdown retains bibliographic placeholders.
- Review whether the manual query variants satisfy the agreed search protocol. Record amendments or additional searches explicitly.
- Supplement missing Gemini, HumanEval/Java and exactly-one-round repair evidence as needed. No Google Scholar run is currently recorded.
- Obtain cross-review and integrate unique studies into the shared evidence table and team progress report.
- For the Metrics & Statistics role, separately prepare metric definitions, an outcome schema, a mock results table and the paired statistical-analysis plan. These experiment deliverables are not completed by this literature handover.
