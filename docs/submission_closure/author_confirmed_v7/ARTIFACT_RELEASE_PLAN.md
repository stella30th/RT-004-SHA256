# Double-anonymous artifact release plan

Decision from project lead: host/venue **not selected**. Therefore a public anonymous URL **does not yet exist** and none is claimed.

1. Choose a reviewer-anonymous host that accepts files without publishing author identities, and verify its anonymity and retention controls against ICST NIER guidance. Treat services such as anonymous.4open.science only as candidates, never as pre-existing links.
2. Produce a replication archive containing exact prompts, response/output mapping, generated suites, fixed SUT snapshots, build configuration, Maven/Surefire/PIT results, analysis scripts, run instructions, metadata limitations, and both historical and round-2 cohort manifests.
3. Audit source licenses/redistribution rights, individual identifiers, email addresses, absolute Windows paths, Git remotes/authors, PDF metadata, scripts, revision history, conversation export PII and hidden data. Do not distribute source with unknown license rights.
4. Add SHA-256 manifest, read-only release revision and public test procedure. Reproduce material counts on another clean checkout; where full reproduction of the LLM calls is impossible say so explicitly.
5. Upload only after these checks; open the link as an unauthenticated third party and verify each file, hash, and download; note verification date. Insert the verified URL and release revision into manuscript and tracker.
6. Keep named approval records in a separate private location. Public branch `feature/Hao` is appropriate for non-private work artifacts but does **not** itself satisfy double anonymity.

Publish gate: `NOT_READY` until steps 1–5 are done and all four authors' relevant approvals are documented.
