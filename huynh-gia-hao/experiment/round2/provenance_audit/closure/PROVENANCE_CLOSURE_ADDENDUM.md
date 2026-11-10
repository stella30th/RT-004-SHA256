# Round 2 — AI Generation Provenance Closure Addendum

## Audit decision
**AUDIT CLOSED WITH DOCUMENTED LIMITATIONS**, not **ALL SESSIONS VERIFIED**. The experiment has 99 output artifacts linked to prompts and execution evidence. Historic generation provenance is not fully recoverable from the provided repository.

## Unverified evidence
1. **Generation date/time:** no authenticated AI response timestamp for any of 99 outputs. Do not substitute Git, file system, Maven, PIT or archive timestamps.
2. **Model identity:** some labels are user-reported; model-version verification against underlying provider request metadata is absent. The label is not independently attested.
3. **Generation controls:** temperature, top_p and max_output_tokens were not exposed in the saved Web UI metadata. Record `NOT_EXPOSED`; do not infer defaults.
4. **Run independence:** no primary request IDs or fresh-context capture for the 33 condition/task triples. Outputs differing by SHA-256 show different content, not necessarily independent calls. Identical outputs do not prove copying.
5. **Replacement runs:** P0 CE085/run-01 and CE033/run-03 were generated later as replacements and must remain labelled separately, with non-original chronology.

## Observed duplicate content
- P0 CE043 run-01 = run-02
- P0 CE066 run-02 = run-03

## Analysis implications
JUnit validity and PIT mutation outcomes remain reportable as observational results for the retained artifacts. Avoid causal claims about the effect of prompting that depend on demonstrably independent generation or known model sampling parameters. In particular, do not call the 3 runs statistically independent replicates.

## Requirement closure
- Integrity / test execution / mutation testing: **completed within prior verified scope**.
- Historical session provenance: **closed as evidence unavailable**, not verified.
- No requirement to re-run 99 suites to honestly close this audit; a new study with captured request/response metadata would be a separate dataset.

## Recommended disclosure text
> The study retained 99 generated JUnit suites and linked them to prompt artifacts, test execution results and mutation testing evidence. Historical AI generation timestamps, provider-attested model identifiers, inaccessible sampling settings and independent invocation/context evidence were not available. These fields remain unverified. Two baseline outputs were replacement generations, and two P0 pairs shared identical source content. Results should therefore be interpreted as artifact-level observational comparisons, not fully independent and reproducible AI generation trials.

## Verification rules
Use only primary evidence to change `UNVERIFIED` to `VERIFIED`. Keep this addendum immutable once submitted, or issue a dated revised version identifying newly supplied evidence. Do not rewrite historic JUnit or PIT results.
