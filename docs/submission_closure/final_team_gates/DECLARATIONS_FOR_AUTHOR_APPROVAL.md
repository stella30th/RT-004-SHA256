# Manuscript declarations — author-review templates, NOT signed statements

## Study models and provenance
The study examined generated JUnit suites attributed in the collection records to ChatGPT and Gemini. Exact historical model revisions, interface parameters, access dates and session-level independence were not independently established for all outputs. Where original model-session metadata are unavailable, these results are described as retrospective artifact-based observations rather than prospectively controlled comparisons. [Authors must verify the actually used tools/models and qualify or replace this paragraph.]

## Protocol transparency
The Round 2 protocol is preserved as `PROTOCOL_FROZEN_DRAFT.md`; this filename does not establish prospective locking. [Choose and substantiate ONE: (a) a dated, signed, pre-generation protocol with immutable commit; or (b) a retrospectively documented exploratory protocol with no claim of preregistration.] Do not call a retrospective approval a freeze before experimentation.

## Generative AI use and manuscript disclosure
[Each author identifies AI systems used for test generation, failure analysis, focus statements, editing and manuscript generation. Identify any AI-generated text, insert citations in affected sections and disclose its use in the acknowledgements as required by the target venue. Distinguish use of LLMs as research subjects from generated manuscript text.]

## Funding
[AUTHORS TO CONFIRM: list the grant, sponsor, credits or other support, or explicitly confirm no external funding. Do not silently assume “none.”]

## Competing interests
[AUTHORS TO CONFIRM: state relevant financial or non-financial competing interests, or explicitly confirm no conflicts to declare.]

## Data and artifact availability
[AUTHORS TO INSERT: reviewer-accessible **anonymous** URL, immutable revision or DOI, artifact contents, license/redistribution limitations, and any unreleased material with reasons. A public identifiable GitHub profile/commit history must not be used as a substitute for an anonymous review artifact.]

## CE023 input snapshot
CE023 is a source-snapshot discrepancy. The earliest available team import uses `ClassroomManagementTest.Course`, whereas a later adjusted implementation defines `Classroom.Course` as a static nested type. The working-tree copy has another SHA-256. Historical outcome attribution under an unchanged-SUT protocol cannot be established for this case without matching original inputs and execution records. Report the restricted-cohort bookkeeping sensitivity separately from observed executions.

## Author sign-off
Full author names, private signed record, approval dates and paper owner to be kept in a **private** record, not in the anonymous package. None of the preceding declarations are automatically approved.
