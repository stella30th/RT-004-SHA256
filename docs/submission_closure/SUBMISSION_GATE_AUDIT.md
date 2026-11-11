# OAPR ICST 2027 NIER — independent submission gate audit

**Assessment of supplied 11 November 2026 project archive: NOT READY FOR EXTERNAL SUBMISSION.** This is distinct from the completed Round 2 JUnit/PIT execution audit.

## Resolved by this patch

- CE023 internally consistent local source hash is documented; API mismatch and reclassification as an excluded matched-source case explained.
- Venue scope, page limit, anonymity, AI disclosure, paper candidate, and deadline documented against official ICST 2027 NIER CFP.
- The paper candidate contains explicit disclosure placeholders and limitations, without fabricating confirmations.
- Owner signoff checklist and an executable CE023 upstream comparison are included.

## Remaining hard gates

| Gate | Evidence now | Decision |
|---|---|---|
| Historical model/session identifiers | 99 artifact-level integrity checks; no full session attestation | **NOT VERIFIED**; do not claim exact model/version |
| Prospectively frozen ClassEval-T protocol | `PROTOCOL_FROZEN_DRAFT.md`, no signed prior freeze record in snapshot | **NOT VERIFIED**; mark exploratory/retrospective |
| Funding/COI | Draft statements pending all-author confirmation | **BLOCKED ON AUTHORS** |
| AI-generated text disclosure and focus-statement authorship | No author-certified scope of AI writing | **BLOCKED ON AUTHORS** |
| Artifact URL / license / double-anonymity | No verified anonymous link/DOI; unverified placeholder in `docs/venue_decision.md` | **BLOCKED ON ARTIFACT PUBLICATION** |
| CE023 upstream source mismatch | Local copies identical, upstream hash absent | **PENDING PINNED UPSTREAM COMPARISON** |
| NIER final PDF QA | Two-page anonymous draft is present, but no signed editorial/venue check | **REQUIRES FINAL REVIEW** |
| Full 188-pair historical numbers | Matched-source revision is 187; adjusted 188 is sensitivity only | **EXPLAINED; KEEP SEPARATE** |
| 41 reviewer tracker items | Reviewer tracker is broader than this submission closure | **NOT ALL VERIFIED**; do not mark entire tracker DONE |

## Official venue check (11 Nov 2026)

- ICST 2027, *New Ideas and Emerging Results* (NIER): Jan 13, 2027 submission deadline, AoE (UTC−12), as currently posted.
- Main paper: up to **4 pages** inclusive of text, appendices and figures; up to **2 further pages for references only**, IEEE conference letter format.
- **Double anonymous** manuscript and replication artifacts.
- Explicit policy on AI-generated text: disclosure in acknowledgements and citation to AI system in sections containing generated text.
- CFP's submission website link is still labeled **LINK TO BE UPDATED** at time of check; do not invent a destination.
- Conference text distinguishes idea vs reflection papers; team must select category.

Official CFP: https://conf.researchr.org/track/icst-2027/icst-2027-nier

## Review alignment

Action-tracker IDs affected directly: **H2, H3, H4, H7, H13, H15, H17, M1, M4, M10, M17, M18, M19, M22**, with the CE023 explanation also informing the paper's sensitivity and validity language. Other blocking/high/medium items are not automatically satisfied by this narrow patch.

Do not label the manuscript `submission_ready` until gate sign-off is complete.
