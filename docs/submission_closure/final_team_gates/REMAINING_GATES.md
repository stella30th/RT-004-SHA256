# Project-wide readiness disposition (2026-11-11)

The repository has an auditable ClassEval-T CE023 upstream comparison, but the research project is **NOT YET VERIFIED SUBMISSION_READY**. A pass on a local script or a successful `git push` does not represent author approval, proof of historic model sessions, venue compliance, or reviewer acceptance.

## Verified engineering evidence

- CE023: inspected official upstream `wLinHoo/ClassEval-T` commit `fd41d22e6ee2e7497087f4724d4d0a8292ec9491`. `ClassEval_T/java/solutuon/Classroom.java` has SHA-256 `df08e31ecd6bf6d8cf66c60b126b3fc7b92b3837c04353dde0af2daabfd09ed8`, equal to team's earliest import commit `34e1d57`. A separate upstream source has hash `3a41626e411b53a534e6e1db07a7aa3c53e556d185bdf155b3a82f17f4bccc7b`. Later team-history and working-tree snapshots have different hashes; this is a *source/API change*, not proof of comparable P0/P1 runs.
- Round 2, provenance limitation report, mutation results, and CE023 team-history audit exist from the supplied materials. The provenance report has **zero** independently session-verified requests; do not report the study as prospectively locked or independently rerun unless supported.
- For the entire paper, the 41-item reviewer tracker includes unresolved items beyond the five author facts. `EVIDENCED_IN_DRAFT` is not reviewer sign-off.

## Mandatory decisions and real-world checks still open

1. **Model & AI usage:** record independently known interface, versions, actual prompt sessions, whether AI wrote manuscript text, and who generated/approved focus statements. Accept documented limitations for truly unrecoverable metadata, rather than guessing.
2. **Protocol:** either attach *pre-generation* immutable commit/timestamp/sign-off or describe it as *retrospectively documented, exploratory*; never backdate.
3. **Funding / conflicts:** each author confirms actual financial/nonfinancial interests and sponsorship; do not populate `none` without authorization.
4. **Artifact release:** create and personally verify an accessible *anonymous* and immutable artifact snapshot with license review, identity/PII scrub, plus archived URL/DOI. A private, editable or identifying GitHub branch is not sufficient.
5. **CE023:** cite the verified upstream snapshot and mismatch; retain conditional 187/188 sensitivity bookkeeping, mark historical generation-time upstream revision unknown without session evidence.
6. **Manuscript:** resolve all tracker entries to evidence, check external references, PDF layout/page limit, anonymity, venue's current CFP and disclosure rules, obtain author sign-off and advisor review.

## Sign-off protocol

Keep `AUTHOR_FACTS_UPSTREAM_PREFILLED.example.json` as an evidence template; do **not** simply rename and flip all booleans. Each signed field should point to an actual artifact, email, meeting approval or link; sensitive source evidence should stay private. The gate script is a checklist, not a verification of the factual truth of claimed sign-offs.

## Git safety

Only stage reviewed `docs/` and `paper/` files using explicit pathspecs. Run `git diff --cached --check`, `git diff --cached --stat`, inspect `git diff --cached` then commit. Do not `git add .` or force-push; double-anonymous artifacts require a separate sanitized release.
