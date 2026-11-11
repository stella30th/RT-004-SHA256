# OAPR ClassEval-T — final author and submission gates

This patch is a **truthful completion workflow**, not a fabricated author certification. It does not modify Java source, generated test outputs, or historical experiment logs. Run `APPLY_AND_AUDIT.ps1` and inspect the results. Confirm funding, competing interests, AI use and protocol history with all authors. `SUBMISSION_READY` must remain false until those confirmations, anonymous artifact publication and final-paper approval are real.

The authoritative upstream benchmark is https://github.com/wLinHoo/ClassEval-T (benchmark for class-level code translation). Do not confuse its current HEAD with the historical revision used in the team's experiments.

## Workflow

1. Run `APPLY_AND_AUDIT.ps1 -Repo <repo_path>`. This writes to `docs/submission_closure/final_team_gates/` only.
2. Fill `AUTHOR_FACTS.json` using real author confirmations; leave unknowns blank. `protocol_frozen_before_generation` can be false when no prospective lock existed; never backdate.
3. Run `CHECK_GATES.py` with Python 3. It creates `GATE_REPORT.json` and `GATE_REPORT.md`.
4. Optionally run `CHECK_UPSTREAM_CE023.ps1 -Repo <repo_path>` on Windows with Git/network, then inspect the upstream commit/path/source differences. This command **does not assert historical identity**.
5. Prepare an anonymous sanitized replication archive separate from identifiable Git history; provide its reviewer-access URL and immutable revision after publishing. Never upload `.git`, private transcripts, absolute home directories, names or email identities.
6. Have authors review citations, final NIER paper, and any AI-generated manuscript text in accordance with CFP.

No script can declare funding/COI on behalf of authors or publish an artifact without a destination/account and explicit authorization.
