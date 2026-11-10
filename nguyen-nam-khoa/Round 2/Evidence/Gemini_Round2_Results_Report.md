Analysis date: 10 November 2026 \| Dataset: 63 generated JUnit suites across seven selected ClassEval-T tasks

# Executive Summary

This report analyzes the 63 Gemini-generated JUnit suites contained in the supplied Official_Run archive (three conditions × three independent generations × seven selected tasks). The suite-level compile-and-execute success rate was 50/63 (79.37%). Condition B achieved 18/21 successful suites (85.71%), followed by A at 17/21 (80.95%) and C at 15/21 (71.43%). All 13 unsuccessful suites compiled and ran JUnit, but failed one assertion each; none of the final results was an infrastructure error or compilation failure.

The tests were generated using **Gemini 3.8 Flash** through **Google AI Studio**, as reported by the experimenter and recorded in the run metadata. The exact model version was not independently verified from provider-side session records.

The data do not support a claim that task-specific diagnostic hints (C) improved suite success in this sample. Failures are concentrated in oracle construction and hard-coded fixtures, especially rail-fence/Vigenere operations (CE033/CE037), entity-unescaping boundary behavior (CE062), and sentence/word handling (CE073). These are descriptive results, not proof of causal superiority or inferiority of any prompt condition.

# 1. Study Design and Reproducibility

Unit of analysis: a model–task–condition–replicate combination. Tasks: CE033, CE037, CE039, CE062, CE073, CE079 and CE094; arms A (single-task baseline), B (A plus the nine-item source-first checklist), and C (B plus a predeclared task-specific diagnostic focus). Each task–arm pair has r01, r02 and r03; all 63 outcomes are present.

A successful suite is one that compiles, executes at least one JUnit method, and has zero assertion failures and zero errors. The experiment uses Java 17, JUnit 4.13.2 and Maven Surefire 3.2.5 as documented by the supplied project/protocol. **Model and interface.** The experimenter reported using **Gemini 3.8 Flash** via the **Google AI Studio** web interface for all 63 generations. These values are recorded in the per-run metadata as `model_version_verified: "Gemini 3.8 Flash"` and `interface: "Google AI Studio"`. The model label is self-reported; an independent provider-side model/session record was not supplied, so the precise deployed model version is not externally verified.

Evidence reviewed: Official_Run_Metadata_Updated.zip, including per-run prompts, raw responses, generated Java, compile/test logs, metadata and Surefire XML; PROTOCOL_FROZEN_DRAFT.md; and the supplied workspace-template production source. The earlier CE094 execution harness errors were corrected by an infrastructure-only rerun, and final CE094 outcomes are taken from execution_logs. The original protocol file remains labeled DRAFT and requires human approval.

# 2. Aggregate Results

| Condition | PASS | FAIL | Suites | Success rate (95% exact CI) |
|-----------|------|------|--------|-----------------------------|
| A         | 17   | 4    | 21     | 80.95% (58.1–94.6%)         |
| B         | 18   | 3    | 21     | 85.71% (63.7–97.0%)         |
| C         | 15   | 6    | 21     | 71.43% (47.8–88.7%)         |
| Overall   | 50   | 13   | 63     | 79.37% (67.3–88.5%)         |

Differences in observed PASS rates: B − A = +4.76 percentage points (one additional successful suite); C − A = −9.52 points (two fewer); C − B = −14.29 points (three fewer). Exact binomial confidence intervals above describe within-arm uncertainty under an independence assumption; repeated tests on the same tasks and shared prompt scaffolding violate simple independence for some inferences. These intervals are not causal tests of arm differences.

# 3. Task-Level A/B/C Comparison

| Task  | A (of 3) | B (of 3) | C (of 3) | Interpretation                                |
|-------|----------|----------|----------|-----------------------------------------------|
| CE033 | 1        | 2        | 0        | C fails all three Vigenere/rail-fence suites  |
| CE037 | 2        | 3        | 2        | B succeeds in all three; A/C each fail once   |
| CE039 | 3        | 3        | 3        | Stable success across conditions              |
| CE062 | 2        | 2        | 3        | C improves observed entity-unescaper outcomes |
| CE073 | 3        | 2        | 1        | C has two sentence/word failures              |
| CE079 | 3        | 3        | 3        | Stable success across conditions              |
| CE094 | 3        | 3        | 3        | All final rerun suites pass                   |

Task-level heterogeneity is substantial. CE039, CE079 and CE094 succeed in every arm and replicate (27/27 total), so they do not distinguish the conditions in this dataset. The decrease for C is driven mainly by CE033 (0/3) and CE073 (1/3), partly offset by CE062 (3/3). No evidence here establishes that the failure-informed hints caused the unfavorable CE033/CE073 outcomes.

# 4. Individual Analysis of All 13 Failed Suites

Every failure below is a JUnit assertion failure. Each suite completed compilation and executed tests; the listed assertion is the first reported failing method. “Cause” denotes the directly observed mismatch and an interpretation against the provided production implementation, not an independent domain-specification verdict.

## 1. A/r01/CE037: Vigenere expected ciphertext mismatch

Failing JUnit method: testVigenereCipherCaseInsensitiveKey. Tests: 8; failures: 1; errors: 0; skipped: 0.

Surefire mismatch: expected:\<kxrk\[ig\]kxbkal\> but was:\<kxrk\[gi\]kxbkal\>

Evidence-based analysis: The test expects kxrkigkxbkal for attackatdawn with key KEY, whereas the implementation returns kxrkgikxbkal. The fixture uses an inconsistent character sequence; source behavior and an independent character-by-character calculation should determine the valid oracle.

## 2. A/r02/CE033: Rail-fence handcrafted fixture mismatch

Failing JUnit method: testRailFenceDecipherThreeRails. Tests: 10; failures: 1; errors: 0; skipped: 0.

Surefire mismatch: expected:\<HelloWor\[l\]d\> but was:\<HelloWor\[r\]d\>

Evidence-based analysis: The supplied three-rail ciphertext HorelWrdlo does not decode to the asserted HelloWorld under the implementation. The test comment also lists an inconsistent character layout; the failing assertion is attributable to an invalid input–expected-output pair, subject to independent oracle checking.

## 3. A/r02/CE062: Numeric-entity truncation expectation mismatch

Failing JUnit method: testReplaceUnmatchedEntityPattern. Tests: 7; failures: 1; errors: 0; skipped: 0.

Surefire mismatch: expected:\<&\[\]\> but was:\<&\[#\]\>

Evidence-based analysis: For the unterminated input &#65, the test expects & but the source appends both & and \# before the loop ends. The observed result is &#. The test description (no semicolon should not decode) does not justify the asserted truncated value.

## 4. A/r03/CE033: Rail-fence cipher/decipher fixture mismatch

Failing JUnit method: testRailFenceDecipherRailsThree. Tests: 9; failures: 1; errors: 0; skipped: 0.

Surefire mismatch: expected:\<DEFENDTHE\[EAS\]TWALL\> but was:\<DEFENDTHE\[AST\]TWALL\>

Evidence-based analysis: For the hard-coded three-rail ciphertext DNETLEEDHATWLFTSA, the test expects DEFENDTHEEASTWALL; the actual output differs within the EAST segment. The first assertion fails, so later assertions in the same method were not evaluated.

## 5. B/r01/CE033: Two-rail cipher fixture mismatch

Failing JUnit method: testRailFenceDecipherTwoRails. Tests: 10; failures: 1; errors: 0; skipped: 0.

Surefire mismatch: expected:\<HELLO\[WORLD\]\> but was:\<HELLO\[ORWDL\]\>

Evidence-based analysis: The test asserts that HLORDELOWL decodes to HELLOWORLD, but returns HELLOORWDL. The hard-coded rail layout in the comment omits or misorders characters, making the oracle inconsistent with the input.

## 6. B/r03/CE062: Boundary-loop truncation miscalculation

Failing JUnit method: testReplaceEdgeCasesAndTruncation. Tests: 5; failures: 1; errors: 0; skipped: 0.

Surefire mismatch: expected:\<he\[\]\> but was:\<he\[l\]\>

Evidence-based analysis: The test correctly notices that replace() truncates non-entity suffixes, but expects he for hello. The loop condition pos \< length - 2 appends three characters, producing hel; the expected value is off by one.

## 7. B/r03/CE073: Word-count expectation miscount

Failing JUnit method: testProcessTextFileFindsMaxWordCount. Tests: 9; failures: 1; errors: 0; skipped: 0.

Surefire mismatch: expected:\<9\> but was:\<10\>

Evidence-based analysis: The test expects the maximum sentence word count to be 9, while the second sentence contains 10 whitespace-separated words; the implementation returns 10. The test comment itself lists ten words.

## 8. C/r01/CE033: Two-rail input construction mismatch

Failing JUnit method: testRailFenceDecipher. Tests: 6; failures: 1; errors: 0; skipped: 0.

Surefire mismatch: expected:\<H\[ello World\]\> but was:\<H\[ll oWorlde\]\>

Evidence-based analysis: The method first checks a well-known three-rail example and then asserts Hloolel Wrd -\> Hello World for two rails. The second fixture is inconsistent with the actual zigzag ordering; the observed output is Hll oWorlde.

## 9. C/r01/CE037: Three-rail expected ciphertext mismatch

Failing JUnit method: testRailFenceCipherWithThreeRails. Tests: 6; failures: 1; errors: 0; skipped: 0.

Surefire mismatch: expected:\<HOLELWR\[LOD\]\> but was:\<HOLELWR\[DLO\]\>

Evidence-based analysis: The test expects HOLELWRLOD when encrypting HELLOWORLD with three rails; the implementation returns HOLELWRDLO. The characters L/O/D are incorrectly ordered in the expected fixture.

## 10. C/r02/CE033: Vigenere punctuation/key-index mismatch

Failing JUnit method: testVigenereDecipherUppercaseKeyAndPunctuation. Tests: 8; failures: 1; errors: 0; skipped: 0.

Surefire mismatch: expected:\<Attac\[k, A\]tdawn!\> but was:\<Attac\[, Ka\]tdawn!\>

Evidence-based analysis: The test expects Attack, Atdawn! for ciphertext Lxfop, Vefrnhr! and key LEMON, while the source returns a different sequence around the comma. The test expected value is not consistent with the source key-index progression.

## 11. C/r02/CE073: Abbreviation segmentation assumption mismatch

Failing JUnit method: testSplitSentencesWithAbbreviations. Tests: 6; failures: 1; errors: 0; skipped: 0.

Surefire mismatch: expected:\<2\> but was:\<1\>

Evidence-based analysis: The test expects two sentences for a string containing Dr., Mr. and a.m., but the source regular expression returns one segment. The asserted natural-language boundary is not supported by the implemented regex.

## 12. C/r03/CE033: Vigenere key/ciphertext fixture mismatch

Failing JUnit method: testVigenereDecipherUppercaseKey. Tests: 10; failures: 1; errors: 0; skipped: 0.

Surefire mismatch: expected:\<ATT\[AC\]KATDAWN\> but was:\<ATT\[ZD\]KATDAWN\>

Evidence-based analysis: With key CIPHER and ciphertext CBIGHBCBSHAE, the test expects ATTACKATDAWN; the implementation returns a different string at positions in the middle. The ciphertext/key pair does not support the asserted plaintext under the supplied algorithm.

## 13. C/r03/CE073: Word-count expectation miscount

Failing JUnit method: testProcessTextFile. Tests: 5; failures: 1; errors: 0; skipped: 0.

Surefire mismatch: expected:\<11\> but was:\<10\>

Evidence-based analysis: For Short one. This sentence has many more words than the first one. Ok., the test expects a maximum of 11 words; the second sentence has 10, which matches the implementation output.

# 5. Failure Patterns

The 13 failed suites span four tasks: CE033 (6 failed suites), CE037 (2), CE062 (2), and CE073 (3). These counts correspond to 46.2%, 15.4%, 15.4%, and 23.1% of all failures, respectively. At the JUnit level, each failed suite reports exactly one assertion failure and zero runtime errors. All 63 suites compiled and ran at least one test.

- Cipher/oracle fixture mismatch (CE033 and CE037; eight failed suites): hand-constructed ciphertext or expected plaintext/ciphertext was inconsistent with source behavior. Particular failure modes involve rail ordering, Vigenere shift progression, and punctuation/key alignment.

- Source-boundary and truncation misunderstanding (CE062; two failed suites): expected values did not trace the actual while-loop exit condition or entity-prefix handling.

- Sentence splitting and word-count oracle errors (CE073; three failed suites): two incorrect word counts and one expectation that the regex would split after an abbreviation when it did not.

An assertion mismatch is not by itself proof that the production implementation is correct or that the test generator is universally incorrect. The primary study metric intentionally measures execution compatibility with the supplied implementation; evaluating semantic correctness would require an independently trusted oracle or specification.

# 6. Interpretation of Conditions

## A — baseline

A succeeded in 17/21 suites. Its four failures arose in Vigenere ciphertext expectations, rail-fence fixtures and a numeric-entity edge case. A therefore establishes a relatively high success baseline on this narrow selected subset.

## B — generic checklist

B succeeded in 18/21 suites, one more than A. The checklist might have helped some cases (for example CE037, 3/3), but errors remain in hard-coded rail-fence expectations, source-loop tracing and word counting. With just three repeats per task, this modest aggregate difference should not be presented as conclusive improvement.

## C — checklist plus historical failure focus

C succeeded in 15/21 suites, lower than A and B, but achieved 3/3 on CE062. Its six failures include three CE033 cases, two CE073 cases and one CE037 case. The additional hints are sourced from historical failure information; hence C assesses a failure-informed prompting workflow, not a neutral checklist extension. Its lower aggregate outcome is a genuine observed result, not an incentive to tune or regenerate prompts post hoc.

# 7. Validity Threats and Limitations

- Sample selection: seven previously failed Gemini tasks, not a random or representative set of all 94 tasks. The success fractions cannot be generalized to the full ClassEval-T benchmark.

- Repetition and pairing: three new generations per arm/task provide limited variance information; there are no guaranteed matched random seeds, controlled ordering or independent sessions verified in the supplied execution logs.

- Context comparability: single-task Round 2 prompts differ from the historical multi-task Round 1 context. “A” is a single-task baseline adaptation, not necessarily an exact replay of historical P0.

- Model identification: metadata records the model/interface from the user’s statement, not independently verified session information. A dated model identifier, settings and generation parameters are not documented here.

- Outcome definition: suite PASS indicates compile-and-run success against the provided production source. It does not measure branch coverage, mutation score, defect detection, or oracle correctness against a reference specification.

- CE094 reruns: final outcomes come from corrected harness execution; maintain the original harness-error evidence separately for audit, even though it is not part of the final CSV.

- Prompt contamination/freeze: the source prompts are consistent across repetitions in the archive, but files alone cannot prove the exact prompts, order, or generation settings displayed in Google AI Studio at collection time.

# 8. Recommendations and Next Steps

Retain all 63 raw model outputs and generated tests unchanged. Use the 13 assertion failures for a structured oracle-quality audit: independently recompute the expected results, compare with the production source, and annotate whether each mismatch reflects a fixture error, a source-specification disagreement, or an unresolved case. Record approval/freeze status of the protocol and preserve timestamped session model/settings evidence if available.

For the next study phase, predefine the statistical comparisons and whether repeated generations will be treated as clustered by task. To assess effectiveness beyond execution success, add held-out tasks and semantic test-quality measures such as mutation testing or trusted reference oracles. Do not tune the existing Round 2 conditions in response to these outcomes without starting and documenting a new experimental version.

# Appendix A — Reproducible Outcome Matrix

| Task  | A r01/r02/r03 | B r01/r02/r03 | C r01/r02/r03 |
|-------|---------------|---------------|---------------|
| CE033 | P/F/F         | F/P/P         | F/F/F         |
| CE037 | F/P/P         | P/P/P         | F/P/P         |
| CE039 | P/P/P         | P/P/P         | P/P/P         |
| CE062 | P/F/P         | P/P/F         | P/P/P         |
| CE073 | P/P/P         | P/P/F         | P/F/F         |
| CE079 | P/P/P         | P/P/P         | P/P/P         |
| CE094 | P/P/P         | P/P/P         | P/P/P         |

P = suite PASS; F = assertion-failure suite. Source of truth: per-suite Surefire XML under Official_Run/\<arm\>/\<rep\>/\<task\>/execution_logs/surefire-reports/.

# Appendix B — Source File References

Primary archive: Official_Run_Metadata_Updated.zip (63 run directories, original prompts/responses and final execution logs). Baseline method: PROTOCOL_FROZEN_DRAFT.md. Production classes examined for failure interpretation: DecryptionUtils.java, EncryptionUtils.java, NumericEntityUnescaper.java and SplitSentence.java from the supplied workspace-template in RT-SWT302-004-SHA256.zip. Final comparison table cross-checked against execution_summary.csv.
