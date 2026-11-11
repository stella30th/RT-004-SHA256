# Round 2 provenance and protocol audit — verification disposition (11 November 2026)

## Evidence scope
This audit is grounded in the uploaded `SWT_PROVENANCE_PROTOCOL_REPORT.zip`, generated at commit `eb6a7e7c50c7d99a8fada7a824a622ea77ef0bfb` on branch `feature/Hao`. It contains a listing of 2,607 candidate files, 14 paths potentially related to protocol/prompt freeze, six Round-2 Git commits, and a previous ChatGPT provenance summary. **File presence is not session authentication.** None of the 2,607 files was inspected as an authenticated provider session record by the V11 script; its `contains_possible_session_evidence` column is `NOT_CHECKED`.

## Verified findings
- The earlier, general `docs/experiment_protocol.md` and `docs/review_protocol.md` appear at commit `636129b07fd1f1bfe9c8e0ad3d778cab6814552a`, committer time **2026-10-06 12:52:37 +07:00**. The files do not themselves prove that they lock the later ClassEval-T Round-2 experimental design.
- `huynh-gia-hao/experiment/round2/PROTOCOL_FROZEN_DRAFT.md` first appears at `c9542f8fb57954dc772e80340e17671cb934c73f`, **2026-11-09 19:16:54 +07:00** (committer time), in a commit titled `feat: complete Hao Round 2 experiments and mutation analysis`. The file remains explicitly **DRAFT**. This commit cannot independently certify prospective freeze.
- Of the 11 paths `round2/frozen_p0/CE*.txt`, eight were first present in the team's Oct 27 dataset import; three first appear with the Nov 9 Round-2 results commit. Presence of early prompt files is not proof that all interventions and evaluation decisions were frozen before generation.
- A previous ChatGPT artifact check reports **99/99 source files with local integrity OK**, **0/99 authenticated generation sessions**, **0 verified independent request triplets**, **0 verified fresh-context independence**, and **two replacement P0 runs**. Two P0 run pairs have identical source hashes (which do not establish identical requests).
- The provided inventory has **2,607 file candidates**, not 2,607 original model transcripts. The inventory does not support claiming authenticated ChatGPT/Gemini model labels, hidden system prompts, temperature or sampling settings at per-request granularity.

## Adjudication
`PROVENANCE_AUDIT_COMPLETE_WITH_LIMITATIONS`; `PROSPECTIVE_PROTOCOL_LOCK_UNVERIFIED`; `SESSION_MODEL_METADATA_UNVERIFIED`. The research may be reported as exploratory, retrospective and reproducible at the output/artifact and execution stages to the extent demonstrated. It must not be described as prospectively preregistered/frozen or as independently repeated fresh sessions.

## What can change these statuses
1. Produce a privacy-redacted original session export or provider record for each relevant generation, with session/request ID, timestamp, model label, exact request prompt, response, and hashed generated-suite linkage.
2. Supply an immutable approved protocol revision and its dated approval evidence, preceding the **earliest genuine generation session timestamp**, with matching definitions of cohort, prompts, P0-resample, checklist-only, checklist-plus-focus, run count, SUT snapshot, exclusion criteria and statistical plan.
3. Check commit chronology against session chronology. Git commit or file mtime alone does not establish an AI generation timestamp.
4. If original session evidence is absent, retain `UNVERIFIED` and provide the explicit disclosure below; do not backdate approvals or infer parameters.

## Manuscript-ready wording
> The authors report generating the archived JUnit suites through hosted ChatGPT and Gemini interfaces, but original provider-authenticated generation sessions were not available for inspection in the supplied provenance package. The ChatGPT Round-2 artifact audit found local integrity for 99/99 suite files but verified zero session identities or fresh-context-independent triplets; two P0 outputs were replacements. A repository inventory includes 2,607 candidate files, not 2,607 verified session records. An earlier general protocol was committed on October 6, 2026, while the specific Round-2 protocol remains marked DRAFT and first appears in the November 9 results commit. Neither its retrospective Git history nor the archived outputs demonstrate prospective protocol freezing. Consequently, all repeated-output comparisons are exploratory and descriptive; model-specific sampling controls and independent session provenance are not claimed.

## Note on model statements
The project representative identifies ChatGPT ("chat 6 sol") and Gemini ("gemini 3.8 flash") as interfaces used. These are *author-reported* labels, not independently verified historical serving-model identities. Do not silently replace them with different release identifiers.
