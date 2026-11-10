# Round 2 integration audit — updated 10/11/2026

Run python ho-le-thien-an/round2_analysis.py from the repository root. It checks the committed CSV indexes in huynh-gia-hao/experiment/round2/SWT_Hao_Round2_Final_Handover/ and writes round2_summary.json plus round2_task_run.csv.

| ChatGPT condition | JUnit PASS | Mutation eligible | Killed / denominator |
|---|---:|---:|---:|
| P0 resample | 23/33 | 23/33 | 649/730 |
| Checklist only | 33/33 | 33/33 | 924/1,164 |
| Checklist plus focus | 33/33 | 33/33 | 924/1,164 |

The 99 suite rows and 99 mutation rows contain each expected condition/run/task key exactly once. The mutation eligibility gate matches the JUnit PASS status. The two checklist conditions have the same recorded per-suite mutation scores in all 33 matched task-run cells. The P0 mutation score is conditional on its 23 baseline-valid suites; ten invalid P0 suites are excluded rather than scored zero.

The 33 rows per condition repeat 11 tasks and are not 33 independent task samples. Two P0 outputs are explicitly marked as replacements (CE085 run-01, CE033 run-03). The handover also reports two identical-source P0 run pairs. These facts limit any claim about independent generations or condition effects. Although Hào's five named ZIP packages are absent, the repository contains all 99 unpacked JUnit evidence sets and the 89 eligible PIT XML/log sets; the updated script checks their presence.

Khoa's historical Gemini package audits 94 P0/P1 task slots and confirms that CE023 P0 failed while a different corrected-source P1 suite passed. A later separate Round 2 package contains 63 Gemini run sets: seven tasks, A/B/C conditions, and three recorded runs per arm/task. A baseline passed 17/21; B checklist-only passed 18/21; C checklist-plus-focus passed 15/21. All 13 failures compiled and ran but failed assertions. The repo has raw prompts, responses, tests, and Surefire XML for all 63 sets, plus Official_Run.zip. All 63 committed per-run metadata files have null model-version and interface fields, so the experimenter's Gemini 3.8 Flash / AI Studio label is not independently confirmed there. No Gemini mutation endpoint is reported. The Gemini A prompt is a single-task adaptation, unlike historical multi-task P0; its protocol is marked DRAFT.

Do not combine the 99 ChatGPT or 63 Gemini round-2 rows with the historical model-task denominator, or pool the two differently designed round-2 cohorts as a controlled model comparison.

For the historical manuscript, excluding CE023 from the valid cohort gives 169/187 P0 passes, 18 selected failures, 15 recoveries, and 184/187 carry-forward passes. The previous 170/188 to 185/188 calculation treats CE023 as an adjusted P0 PASS without a verified rerun of the original P0 suite and is retained only as a sensitivity scenario. A raw-first-run scenario that also leaves CE029 and CE087 unresolved is 167/188 to 182/188 if the same 15 observed recoveries are carried forward.

## Paper integration

The modular manuscript now contains An's abstract, combined results, discussion, validity threats, conclusion, and draft declarations. It incorporates both round-2 handovers and distinguishes their designs and endpoints. The paper still requires a LaTeX render, page-budget review, anonymization for the selected venue, verification of non-An claims, protocol freeze status, author-confirmed disclosures, and a suitable public artifact location before external submission.
