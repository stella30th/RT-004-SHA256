# SWT Round 2 — PIT Mutation Testing Analysis (Java 17)

## Data and audit

Input: `SWT_Round2_Mutation_Java17.zip` (99 records). This analysis uses data from the supplied ZIP only.

- Java 17, JUnit 4.13.2, PIT 1.20.6, according to per-suite JSON metadata.
- 99/99 records present. 89 `MUTATION_COMPLETE`; 10 `BASELINE_FAIL_EXCLUDED`, all in `p0_resample`.
- Comparison and XML audit: no mismatches found in recorded metadata, XML mutation counts, and score calculations.
- Score is `KILLED / (KILLED + SURVIVED + NO_COVERAGE + TIMED_OUT)` per runner dataset. `NO_COVERAGE` is therefore counted as not killed; this is a *mutation score*, not PIT test strength.
- Source provenance remains incomplete; two P0 files were replacement generations and must be labeled as such. The evidence does not establish independence of three generations.

## Summary

| Condition | Complete / 33 | Excluded | Killed / total | Micro score | Mean suite score | Survived | No coverage |
|---|---:|---:|---:|---:|---:|---:|---:|
| `p0_resample` | 23/33 | 10 | 649/730 | 88.90% | 92.52% | 30 | 51 |
| `checklist_only` | 33/33 | 0 | 924/1164 | 79.38% | 85.61% | 84 | 156 |
| `checklist_focus` | 33/33 | 0 | 924/1164 | 79.38% | 85.61% | 84 | 156 |

**Important denominator warning:** `p0_resample` represents only the 23 baseline-valid suites. Comparing its aggregate score directly to the 33/33 valid suites of either checklist condition introduces selection bias. It is not evidence that P0 is better at defect detection overall.

## Paired comparison on the same eligible P0 task/runs

- 23 matched pairs where P0 and both checklist conditions have valid mutation measurements.
- P0 has higher score on 7 pairs, checklists higher on 1, and equal on 15 pairs.
- Mean suite score on these matched pairs: P0 92.52%; Checklist Only 90.68%; Checklist Focus 90.68%.
- These task/run observations should not be treated as 23 independent tasks for statistical inference; only 11 underlying targets exist.

## Main finding: Checklist Only and Checklist Focus

- **33/33 pairs have identical mutant-level status distributions and identities** in `mutations.xml` (`KILLED`, `SURVIVED`, `NO_COVERAGE`, etc.).
- **33/33 pairs have different Java SHA-256 hashes**. Thus the generated files differ, but both prompts yield the same detected mutants on this benchmark.
- Both have micro score 79.38%, macro suite score 85.61%, with 924 killed mutants out of 1,164 recorded mutations across 33 suites.
- As a result, the available evidence does **not** support a claim that `checklist_focus` improves fault detection over `checklist_only`. It can still differ in test count, readability, maintenance, or other dimensions not captured by mutant outcomes.

## Limitations and required follow-up

1. **Selection bias in P0:** 10 failed-baseline suites are excluded from PIT, rather than scored as zero. Report both the 23/33 eligible rate and the conditional mutation score. Never treat exclusions as equivalent to surviving mutants.
2. **P0 replacements:** CE085 run-01 and CE033 run-03 were generated later as replacements. They should not be described as the original missing outputs.
3. **Repeatability:** Identical SHA-256 in two P0 pairs was observed in the prior JUnit audit; repeated generations lack fully verified provenance.
4. **Mutation validity:** Equivalent mutants may remain; 100% mutation score is not an absolute guarantee of software correctness.
5. **Inference:** Summarize by task and use task-aware statistical analysis if confidence intervals or statistical tests are required; do not pretend the 99 runs are independent units.

## Recommended next step

Prepare the final SWT results section and handover package using the three CSV tables in this archive and the original, immutable JUnit/PIT evidence ZIPs. If the research claim requires `checklist_focus` superiority, this dataset **does not demonstrate it**; any extended experiment must be reported as a new protocol, not altered into the current results.
