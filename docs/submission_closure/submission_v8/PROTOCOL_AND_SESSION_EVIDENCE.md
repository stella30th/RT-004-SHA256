# Protocol and historical session review

Repository ZIP snapshot (received 11 Nov 2026):

- `huynh-gia-hao/experiment/round2/PROTOCOL_FROZEN_DRAFT.md` explicitly says **DRAFT; requires human approval**. Its first Git history appearance in the extracted ZIP is commit `c9542f8` with author date 9 Nov 2026. This is NOT proof of a pre-generation freeze.
- `docs/experiment_protocol.md` first appears at `636129b` (6 Oct 2026), but describes a different experiment; it cannot serve as a Round-2 pre-registration without a documented matching scope.
- `huynh-gia-hao/experiment/round2/provenance_audit/provenance_summary.json` reports `local_integrity_ok=99`, `session_verified=0`, `model_verified_from_export=0`, `distinct_request_triplets_verified=0`, `replacement_runs=2`.
- Gemini round-2 run metadata reported with blank exact model/version/interface fields; generated files and logs do not alone confirm hosted model identities.
- Group decision-maker states generation logs/exports and a true protocol-freeze commit **do exist in GitHub**, but neither has been identified/verified in the offline archive. Use `scripts/LOCAL_EVIDENCE_DISCOVERY.ps1` from V8 against current working tree and submit its report for independent validation.

**Disposition:** `AUTHOR_REPORTED_NOT_YET_CORROBORATED`. Claim 'prospectively frozen' only after an immutable commit predating each relevant run can be established. Otherwise describe protocol as retrospectively documented, exploratory and not preregistered. No backdating.
