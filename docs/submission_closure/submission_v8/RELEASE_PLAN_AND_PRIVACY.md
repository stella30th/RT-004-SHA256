# Anonymous-first artifact release plan

1. Assemble an **audited reproducibility subset**: source snapshots with license/attribution, exact prompts, generated suites, Surefire/PIT reports, 188-pair register, 187-pair sensitivity ledger, setup scripts, SHA-256 manifest, provenance limitations and README.
2. Remove author names, usernames, email addresses, local absolute paths, Git metadata, API keys, private AI-session URLs, and personal data. Review both file paths and file bytes; automated pattern matching alone is insufficient.
3. Verify upstream licenses, permissions to redistribute task code and any third-party logs. Do not copy a public Git repository including `.git` into an anonymous artifact. Any artifact URL must not reveal submitters' identity or link back to an identifying repository.
4. Test rebuilding in a clean Java 17 environment, preserving nonpass/excluded rows. Provide deterministic commands and known failure limitations. Do not mark all historical AI sessions as validated solely because tests run.
5. Publish to an anonymous reviewer-accessible host *after* authors approve the exact package and license; verify URL from an incognito session. Record URL, version/revision, checksum, release date, and access check. The final paper uses that exact URL. After review, consider public GitHub/Zenodo release.

**Current status:** `CANDIDATE_ONLY` because no hosted anonymous URL has been provided or verified. GitHub and Zenodo links must not be invented. This choice is compatible with the author's approved plan but does not execute publication.
