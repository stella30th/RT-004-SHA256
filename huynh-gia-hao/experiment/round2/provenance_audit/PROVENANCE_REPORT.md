# Round 2 AI generation provenance — evidence audit

- Suite source / prompt / JUnit result integrity OK: **99/99**.
- Primary ChatGPT export matched to exact prompt and output with timestamps: **0/99**.
- Model labels verified from export metadata: **0/99**.
- Condition/task triples with three distinct request IDs: **0/33**.
- Fresh context / statistical independence: **NOT VERIFIED** without direct evidence of session context and generation controls.
- ChatGPT Web UI sampling parameters (temperature, top-p, max output tokens): **NOT_EXPOSED**, not assumed.
- Explicit replacement runs: **2** (do not backdate or mislabel).

## Method and constraints
Original suite files, JUnit and mutation results were not modified. Exact prompt and AI reply content plus linked request/reply IDs are required before automatic session verification. Duplicate outputs and distinct request IDs do not prove fresh context independence. Generation timestamps are never inferred from filesystem/Git/JUnit/PIT dates. Missing data must remain UNVERIFIED.

## Audit issues
- Identical source output: p0_resample/run-01/CE043, p0_resample/run-02/CE043 (not proof of same request)
- Identical source output: p0_resample/run-02/CE066, p0_resample/run-03/CE066 (not proof of same request)
