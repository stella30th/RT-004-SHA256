# SWT / ClassEval-T — Round 2: Final Experimental Report

**Owner:** Hao (individual experimental handover)  
**Date:** 2026-11-09  
**Scope:** 11 ClassEval-T Java tasks, three prompt conditions, three runs per condition  
**Java toolchain:** Eclipse Temurin JDK 17.0.21; Apache Maven 3.9.16; JUnit 4.13.2; PIT 1.20.6  
**Data basis:** `SWT_Round2_FINAL_99.zip`, `SWT_Round2_Mutation_Java17.zip`, and the independently produced audit tables.  
**Status:** Experimental execution and descriptive analysis complete; generation provenance not fully verifiable.

## 1. Research purpose

This experiment evaluates how three strategies for prompting an AI to generate JUnit tests affect (a) whether generated test suites compile and pass against the provided reference implementation, and (b) whether valid suites kill injected mutants. It does not use JUnit PASS as a proxy for defect detection.

The three conditions are `p0_resample` (baseline prompt), `checklist_only` (test-design checklist), and `checklist_focus` (focused checklist). All were evaluated against the same 11 targets in three labeled runs, yielding 99 execution records. The Java runtime and compiler target for the final mutation campaign were fixed at Java 17. Original Java sources and execution evidence were retained without modifying assertions to force PASS.

## 2. Study design and artifacts

Each task/run/condition produces a separate Java suite and is evaluated on the reference implementation with Maven/JUnit. An execution PASS denotes no reported JUnit failures/errors. The mutation stage runs PIT only after a suite passes its reference baseline. Ten baseline-invalid P0 suites were excluded rather than assigned mutation score zero. Mutation score is computed as `KILLED / (KILLED + SURVIVED + NO_COVERAGE + TIMED_OUT)` from the recorded data; mutants with no coverage count as not killed.

### Reference task IDs

`CE033, CE041, CE043, CE050, CE053, CE060, CE065, CE066, CE077, CE085, CE090`

## 3. JUnit execution results

| Condition | Suites present | Suites PASS | PASS rate | Executed methods | JUnit failures | JUnit errors |
|---|---:|---:|---:|---:|---:|---:|
| P0 resample | 33/33 | 23 | 69.70% | 689 | 12 | 0 |
| Checklist only | 33/33 | 33 | 100.00% | 234 | 0 | 0 |
| Checklist focus | 33/33 | 33 | 100.00% | 267 | 0 | 0 |
| **Total** | **99/99** | **89** | **89.90%** | **1,190** | **12** | **0** |

P0 PASS by run: 8/11 (run-01), 8/11 (run-02), and 7/11 (run-03). Both checklist conditions passed all 11 targets in every run. P0's ten `TEST_FAILURE` suites involve CE041, CE050, CE053, CE060, and CE066. Checklist focus generated 33 more test methods than checklist only (267 vs. 234, +14.10%), but test count alone is not a quality measure.

## 4. Mutation results (PIT on Java 17)

| Condition | Completed / 33 | Baseline excluded | Killed / mutants | Micro mutation score | Mean suite score | Survived | No coverage |
|---|---:|---:|---:|---:|---:|---:|---:|
| P0 resample | 23/33 | 10 | 649/730 | 88.90% | 92.52% | 30 | 51 |
| Checklist only | 33/33 | 0 | 924/1,164 | 79.38% | 85.61% | 84 | 156 |
| Checklist focus | 33/33 | 0 | 924/1,164 | 79.38% | 85.61% | 84 | 156 |

The P0 score is **conditional on passing the reference implementation**. Because 10/33 P0 suites failed that eligibility gate, directly ranking its 88.90% against the checklist scores would be misleading: the denominators contain different sets of observations. On the 23 matched task/run observations where P0 and both checklist conditions have valid mutation results, P0 has a higher score in 7, the checklists in 1, and all are equal in 15. Mean suite scores on these matched observations are P0 92.52% and either checklist 90.68%. These 23 observations represent only 11 underlying tasks and are not 23 independent units of inference.

### Checklist only versus checklist focus

All **33 paired task/run combinations** have identical per-mutant identities and outcomes in the PIT XML reports, despite differing Java source SHA-256 hashes. Both conditions kill 924/1,164 recorded mutants. Therefore this experiment **does not demonstrate any mutation-detection improvement** for checklist focus over checklist only. The 14.10% increase in test methods does not translate to any extra killed mutant in these results.

## 5. Interpretation

1. Checklist prompting improved observed reference-suite validity in this dataset (33/33 PASS for both checklist conditions against 23/33 for P0). This is descriptive evidence, not a causal estimate with quantified uncertainty.
2. Among the suites that passed the reference baseline, P0's conditional mutation score was comparatively high. Selection bias and unbalanced eligibility prevent an unconditional superiority claim.
3. Checklist focus and checklist only were indistinguishable under the recorded mutant-killing endpoint. Any claimed advantage of focus would require other endpoints (maintainability, coverage, unique mutants in extended benchmarks, generation cost) or a new, preregistered experiment.
4. Mutation score measures outcomes on a set of artificial mutations, not defect detection on real-world faults; equivalent mutants and benchmark-specific behaviors remain limitations.

## 6. Audit findings and threats to validity

- **Data integrity:** all 99 JUnit records and 99 mutation records were present in the audited archives. The earlier audit verified the four evidence files (`generated.java`, `maven.log`, `surefire.xml`, `result.json`) per JUnit suite and matching SHA-256. Mutation analysis verified the recorded status/count consistency with `mutations.xml`.
- **Generation provenance:** `access_time_utc` and some model identifiers are `UNVERIFIED`, preventing full reconstruction of all independent AI generation sessions.
- **Replacement runs:** P0 CE085 run-01 and CE033 run-03 were generated after the originals could not be recovered. They are **replacement outputs**, not restored original generations. This prevents treating all 33 P0 records as an entirely intact original resampling batch.
- **Duplicate P0 source pairs:** CE043 run-01/run-02 and CE066 run-02/run-03 have identical source SHA-256. Textual identity alone does not prove whether runs were generated independently.
- **Baseline filtering:** 10 P0 `TEST_FAILURE` suites must remain excluded from mutation-score calculation and reported in all comparisons.
- **Potential dependence and mutation equivalence:** measurements from multiple runs of the same 11 tasks are correlated; equivalence and detection differences are not established from scores alone.
- **Configuration boundary:** test-generation prompts may have referred to Java 11 compatibility, but the final experimental execution standard was explicitly fixed at Java 17. Do not silently restate this study as Java 11 execution.

## 7. Work completed and handover checklist

- [x] Establish 11 ClassEval-T targets and three prompt conditions.
- [x] Collect 99 test-suite outputs, including two explicitly labeled P0 replacement outputs.
- [x] Run and audit Maven/JUnit 4 test suites.
- [x] Produce cross-condition execution comparisons.
- [x] Configure PIT for JDK 17 and run the pilot successfully.
- [x] Execute 89 eligible PIT suites and record 10 baseline exclusions.
- [x] Audit, compare, and tabulate mutation results.
- [x] Assemble final written results and handover artifact.
- [ ] If required by course supervisor: attach separately documented evidence of AI session provenance, clarify the protocol for replacement runs and same-source outputs, and agree on any formal statistical inference.

## 8. Files to retain as immutable primary evidence

1. `SWT_Round2_FINAL_99.zip` — original JUnit source/test/log/XML evidence and suite results.
2. `SWT_Round2_Mutation_Java17.zip` — original PIT output including `mutations.xml` and Maven logs.
3. `SWT_Round2_Comparative_Analysis.zip` — descriptive execution comparisons.
4. `SWT_Round2_Mutation_Analysis.zip` — audited mutation summary and paired results.
5. `SWT_Mutation_Java17_Toolkit.zip` — runner environment artifact; the local runner was subsequently patched to call `mvn.cmd` on Windows, so retain the patched runner separately if reproducibility is required.

**Repository preservation rule:** do not edit generated test sources, PIT XML, Maven logs or baseline JUnit results after the snapshot. Any new experimentation must use separately versioned outputs.

## 9. Final conclusion for handover

The SWT Round 2 experiment produced a complete 99-suite execution dataset and 99 mutation-stage records (89 completed and 10 excluded at baseline). Both checklist prompt variants achieved 100% reference-suite validity, compared with 69.70% for P0. The checklist focus variant generated more test methods than checklist only but did not kill more mutants. The results support a distinction between obtaining *valid executable tests* and obtaining *additional defect-detection effectiveness*. All claims remain conditional on benchmark scope, baseline filtering, and incomplete generation provenance.
