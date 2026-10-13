# Week 6 Final Team Synthesis

## Research topic

**Task Categories Where ChatGPT- and Gemini-Generated JUnit Tests Fail to Run**

## What Week 6 has produced

Week 6 has moved beyond individual searches into an auditable team synthesis:

1. Four member literature outputs were collected.
2. Search logs were merged.
3. **48 source-level candidate records** were merged.
4. **12 duplicate records** were removed using exact DOI/title matching.
5. **36 unique publication records** remain in the current team candidate set.
6. One known preprint/published-version relationship is flagged separately for study-level reconciliation.
7. A **13-study evidence table** was rebuilt from higher-confidence full-text/member evidence.
8. A working team PRISMA was produced without inventing unavailable counts.

## Main evidence conclusion

The literature consistently supports the core premise of the RQ:

- LLM-generated unit tests frequently fail at compilation.
- A second distinct failure layer occurs after compilation during test execution.
- Compiler feedback, dependency context and repair mechanisms can materially improve runnability.
- Java/JUnit and HumanEval-related evaluations are represented in the evidence base.
- A binary pass/fail result alone is not enough; failure categories should be retained.

## Current evidence gaps

- Direct ChatGPT-vs-Gemini evidence is still weaker than generic multi-LLM evidence.
- The exact project design of a frozen 30-task HumanEval-Java subset with **one** repair round is not directly replicated by one prior study.
- YATE supports repair strongly but uses more elaborate multi-step repair, so the project's one-round rule remains a deliberate experimental control.
- IEEE is not yet closed for Hào.

## Week 6 handoff to Week 7

Week 7 should use:
- `candidate_papers_merged.csv` as the deduplicated literature pool,
- `evidence_table_merged.csv` as the current evidence base,
- `prisma_week6.md` as the working review flow,
- the shared RQ/protocol files to finalize dataset, metrics, baseline, experiment protocol and statistics.

Do not overwrite unknown PRISMA counts with estimates. Unknown values remain explicitly unknown until reproducible evidence exists.
