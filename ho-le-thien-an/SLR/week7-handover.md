# An's Week 7 handover

Owner: Hồ Lê Thiên An. Reviewer: Huỳnh Gia Hào. Scope: ACM Digital Library, metrics, thresholds, and baseline. Google Scholar Block B is assigned to Hào and is not part of this handover.

## ACM source status

The existing ACM package has raw ACM-S0–S3 exports, exact queries and dates in `search_log.csv`, 747 unique ACM records plus 76 snowball records in `01_all_records.csv`, within-source duplicate decisions, two-stage screening, and a source PRISMA. Six selected papers have full-text evidence extraction in `evidence_table.csv`; 47 more eligible records remain in `03_reserve_eligible.csv` without full extraction. `rq-check.md` documents the RQ overlap and counterevidence, especially ACM_0041. The six-paper selection is a focused evidence block, not the group's final included-study set. The PDFs are documented in `papers/README.md` but are not stored in this Git checkout.

Before ACM is marked complete for Week 7 merge, Hào should review the source-level counts and a sample of screening/extraction decisions, record discrepancies, and confirm the final source candidate set. Team-level full-text eligibility of the cross-source pool remains a separate merge task.

## Proposal files for review

- `proposal/metrics.md`: operational binary outcomes, denominators, and required log fields.
- `proposal/threshold.md`: deterministic per-suite success and failure rules.
- `proposal/baseline.md`: comparison design and the baseline provenance gate.
- `ho-le-thien-an/SLR/metric-evidence-table.csv`: literature-to-decision traceability.

The proposal uses Java 17, JUnit Jupiter 5.10.2, and Maven Surefire 3.2.5. It does not claim the baseline already exists: the repository has only one example test and an unfinished 30-problem manifest. Baseline origin, adaptation, and freeze records must be confirmed before the merged proposal calls it benchmark-provided or human-written. The runner and result schema still need the corresponding implementation and a pilot check.
