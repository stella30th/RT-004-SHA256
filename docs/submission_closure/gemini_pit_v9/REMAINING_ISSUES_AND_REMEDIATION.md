# OAPR/ClassEval-T: remaining paper issues after Gemini PIT

Status date: 2026-11-11. Scope: the uploaded NIER V8 manuscript, Gemini PIT report, historical action tracker, and previously audited repository snapshots. "Addressed in V9" means text was updated, **not** that the underlying experimental limitation disappeared.

## Confirmed resolved in this revision

- **PIT Gemini / Table II:** 63/63 runs represented; 50 baseline-PASS suites completed mutation testing; 13 JUnit-failing suites excluded. A: 17/21 PASS, 392/467 killed, 83.94%; B: 18/21 PASS, 453/534, 84.83%; C: 15/21 PASS, 366/430, 85.12%. Recomputed from the 63 per-suite CSV rows rather than relying on aggregate fields alone.
- **Stale claims:** Removed statements saying Gemini lacked a mutation endpoint or that only ChatGPT had PIT results. Updated abstract, protocol, table, results, validity, conclusion.
- **Comparability limitation:** Explicit that mutation scores condition on JUnit PASS, with changing eligible cohorts/denominators; ChatGPT and Gemini have different task sets.

## Remaining items, prioritized

| Priority | Tracker | Gap | Correct next action / evidence | Recommended paper treatment until resolved |
|---|---|---|---|---|
| BLOCKER | H2, M1, M10 | Web model names, version-at-generation, dates, prompt-level logs, generation settings not matched to archived suites | Produce a private session manifest linking prompt hash, output hash, timestamp, interface/model label and independent conversation ID. Release a redacted manifest only. Check names against session exports. | Say "author-reported web UI labels"; mark exact versions and parameters unverified where unsupported. |
| BLOCKER | H4, H6, H13 | `PROTOCOL_FROZEN_DRAFT.md` does not prove prospective lock; independent runs not proven for all suites | Identify full immutable Git commit and time preceding first generation, with document content and team approval. Verify provenance for each run. | Label Round 2 exploratory/repeated outputs, not prospectively preregistered/independent by assumption. |
| BLOCKER | H17, M4, M17-M19, M22 | No verified accessible anonymous replication URL; approval and disclosures must match final text | Assemble full prompts, 188 historical references, Round 2 suites, source revisions, JUnit/PIT logs, scripts, license and README; scan for PII/identity; publish an anonymized version with stable URL; verify access while logged out. Authors approve final disclosures. | Retain "planned release" rather than claim it is public. |
| HIGH | H3 | CE023 has two materially different APIs and conflicting local working-tree hashes; original generation SUT not independently identified | Keep 188 adjusted accounting clearly distinct from 187 matched-source sensitivity. Preserve upstream commit, path, SHA-256, all local source hashes and matched-run evidence. Assess CE029/CE087 with same contamination criteria. | No claim 188 represents 188 fixed-SUT directly observed outcomes. |
| HIGH | H8, H13, H14, H18, M13 | Conditions have selection and repeat confounding; equal-budget, per-task analysis and statistical plan incomplete | Export task-by-run 3-condition PASS matrix; report per-task paired discordance/uncertainty, do not treat 63 rows as independent tasks. Pre-specify any future significance test; do not apply historical McNemar to an unpaired or selected sample. | Keep results descriptive. Mutation score alone does not establish causal benefit. |
| HIGH | H9, H16 | 'Accuracy', 'oracle correctness', 'practical superiority' overshoot compile-and-pass endpoint | Audit title, abstract, contributions and conclusion; use "executable compatibility" / "observed recovery" consistently. | Already narrowed in V8/V9, but remaining team documents should be aligned. |
| HIGH | H10, H11, M5, M8, M12, M14 | Related-work evidence insufficient; references only four entries in V9 | Verify bibliographic records and compare ChatTester, TestART, ChatUniTest, Test Intention Guided, SBST/test generation by inputs, feedback, repair cycle, dataset, oracle validation. Add source-grounded comparison; do not invent citations. | Mark comparison as preliminary; avoid claiming novelty based solely on a checklist. |
| MEDIUM | H5, H14, M11, M15-M16 | CI/statistical uncertainty and validity organization could be clearer | Report CP exact interval and explain selection/dependence. Organize internal/construct/external/conclusion threats, include actual mitigations. | Distinguish 15/18 focused recovery from 185/188 carry-forward bookkeeping. |
| MEDIUM | B1, M3, M6-M7, M20-M21 | Abstract length, method diagram, readability and reviewer AI-detector threshold require review | Count abstract words and inspect target template; add workflow diagram if space; edit prose for precise scientific claims; do not attempt to game AI-detector percentages. | Reviewer acceptance remains pending. |
| BLOCKER | M22 | PDF final, venue compliance, metadata and citations still not signed off | Compile with `pdflatex` twice, inspect every page, check page budget, anonymous metadata, citations, links and PDF accessibility; final author approval. | Mark manuscript CANDIDATE, not SUBMISSION_READY. |

## Results interpretation for the manuscript

PIT per-condition aggregate counts are `killed / total reported mutants` among baseline-PASS suites. They do not estimate mutant detection over all 21 suites in a condition. Because A/B/C have 17/18/15 eligible suites, changes in the ratio can arise from cohort composition rather than test strength. Even within the same seven tasks, condition-level micro scores should not be interpreted causally without matched comparisons and exclusion analysis. ChatGPT Round 2 uses a different 11-task cohort; cross-model PIT comparisons are descriptive only.

## Submission checklist

- [ ] Full original session evidence attached privately and hashed to suite artifacts, or author-approved limitations accurately worded.
- [ ] Prospective protocol evidence checked or exploratory/retrospective designation approved.
- [ ] CE023 table and all 188/187 values checked against raw artifacts.
- [x] Gemini mutation outcomes updated from observed results (this revision).
- [ ] Expanded and verified related-work comparison.
- [ ] Reviewers' 41 rows revisited against the **final** manuscript (no automatic "Xong").
- [ ] Anonymous artifact URL and license reviewed and tested externally.
- [ ] Final IEEE NIER page, citations, declarations, and author sign-off completed.
