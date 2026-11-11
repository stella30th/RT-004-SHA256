# Evidence intake (no retrospective invention)

The project lead reports that original consumer-web generation logs/exports **exist** and that the experiment protocol was frozen using a commit/document. Neither the original logs nor the freeze commit ID were supplied with the current authorization. An assertion that evidence exists is **not** evidence of its specific contents.

| Evidence | Reported fact | Required fields / verification | Status |
|---|---|---|---|
| ChatGPT original sessions | Export/log exists | Session timestamps, model as displayed, exact prompt, full response, output file SHA-256, run mapping, fresh context | REPORTED_NOT_EXAMINED |
| Gemini original sessions | Export/log exists | Same fields, browser/interface identifier, model version | REPORTED_NOT_EXAMINED |
| Generation settings | Consumer web | Which controls existed, whether settings exposed; record unknown as `NOT_EXPOSED` only when UI lacked settings | UNVERIFIED |
| Protocol freeze | Commit/document exists | Repository path, full commit SHA, commit timestamp, author approval, comparison against first generation timestamp, deviations | REPORTED_NOT_EXAMINED |
| Author approvals | Team lead says all authors have no COI and no outside funding | Dated approval for each named author or delegated authority record | REPORTED_NOT_EXAMINED |
| Artifact publication | No venue chosen | Anonymous URL, revision, license, complete inventory, third-party access test, integrity manifest | NOT_PUBLISHED |

**Do not upload unredacted personal conversation exports to the anonymous GitHub repository.** Store private logs outside Git or in access-controlled team storage, using redacted manifest references in public review materials. When logs arrive, validate 188 source/test pair mappings plus both Round-2 cohorts separately; avoid claims that all generations are independent unless session evidence establishes that.
