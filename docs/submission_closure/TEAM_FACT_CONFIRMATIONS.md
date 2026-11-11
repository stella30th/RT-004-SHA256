# Submission facts requiring team approval

**Status: ACTION REQUIRED — NOT YET SIGNED.** This file is a factual confirmation checklist, not a declaration of facts without evidence. Each author should record their response, dated approval, and any confidential supporting evidence privately. Do not publish personal accounts or unredacted session exports in an anonymous artifact package.

## 1. Model generation and AI-use disclosure

- Historical ChatGPT and Gemini model IDs, interface/endpoints, access dates, and system prompt: **UNVERIFIED unless supported by original session records**.
- Generation temperature, top-p, token caps and timeout/retry conditions: mark `NOT_EXPOSED` only where the actual interface did not expose them; otherwise provide logs.
- Distinguish **AI tested as the study subject** from **AI-generated manuscript text**, AI support for diagnosis, and AI-written focus statements. The people who wrote and inspected these steps must confirm.
- ICST 2027 NIER requires disclosure of AI-generated text in acknowledgements and citation to the AI system in sections containing AI-generated text; authors must determine whether this applies and follow venue instructions.
- The 99 Hào records have artifact integrity but do not prove original prompt sessions or fresh-context independence; describe as an observational repeated-output dataset.

Decision: [ ] confirmed model metadata with evidence  [ ] unavailable; limitations approved
Author(s) responsible: __________________  Date: __________  Evidence location: __________

## 2. Protocol lock / pre-registration

- `huynh-gia-hao/experiment/round2/PROTOCOL_FROZEN_DRAFT.md` is explicitly a **draft** requiring human approval.
- `docs/experiment_protocol.md` documents a different 30-HumanEval/JUnit-5 design; it must not be cited as proof that ClassEval-T Round 2 was frozen before response collection.
- Confirm frozen protocol commit, timestamp and authorized sign-off **preceding** generation. If unavailable, mark this as a retrospectively documented exploratory analysis, not preregistered or pre-frozen.
- No approval should be backdated.

Decision: [ ] frozen before experiment (evidence attached) [ ] retrospective/not prospectively locked
Signatory / date / immutable commit: __________________________________

## 3. Funding and competing interests

All authors must confirm the actual funding sources (grant and sponsor details if any), any vendor-provided compute/credits, and financial or nonfinancial competing interests. **Do not prefill “none”.**

Funding (author-approved): __________________________________
Competing interests (author-approved): ______________________
Author approvals / dates: __________________________________

## 4. Artifact publication, access and anonymization

- Public GitHub source repository is **not automatically an anonymous reviewer artifact**.
- Confirm which license permits redistribution of benchmark code, prompt data, outputs, and logs. Check upstream benchmark attribution and license requirements.
- Provide a real, reachable, reviewer-anonymous URL or DOI and permanent archive revision; avoid placeholder `anonymous.4open.science` URLs from older notes.
- Check for names, accounts, home paths, commit author identifiers, and AI transcript PII in all shared artifacts.

Reviewer-accessible anonymous artifact URL: ______________________________
Immutable revision / DOI / license: ______________________________________
Anonymization review performed by / date: _______________________________

## 5. CE023 upstream snapshot

Upstream URL + immutable commit: ______________________________________
Upstream source path + SHA-256: ________________________________________
Local SHA-256: `78784d46edd39fa8df3ecfe0714f0641ce0765375bd7a5741b87ee3796240c61`
Diff reviewed / behavior impact / approving authors: ____________________

## Submission sign-off

**Do not submit before the above facts are confirmed or explicitly disclosed as unavailable where venue policy permits.** For funding and conflict declarations, obtain actual author confirmation; “unavailable” is not a substitute for a truthful declaration.

Lead / date: ______________  Paper owner / date: ______________  All authors: ______________

## CE023 historical-source clarification (2026-11-11)

Git-history-verified adjusted source at `74b4d0f`: SHA-256 `2a3593e97b4707d841754caadc29035e8d43c85d2eb7d89e410784a76fe29095` (2174 bytes). First import `34e1d57`: SHA-256 `df08e31ecd6bf6d8cf66c60b126b3fc7b92b3837c04353dde0af2daabfd09ed8` (1337 bytes). A separately supplied project archive had a third observed hash `78784d46edd39fa8df3ecfe0714f0641ce0765375bd7a5741b87ee3796240c61` (2261 bytes). This archive/Git mismatch must remain visible in provenance. No upstream equivalence is claimed. See `ce023_history_verified/`.
