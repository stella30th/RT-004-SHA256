# Week 6 Final Team Synthesis — Updated After IEEE Full-Text Review

Research topic: **Task Categories Where ChatGPT- and Gemini-Generated JUnit Tests Fail to Run**

## Week 6 outputs now available

- Google Scholar source evidence + PRISMA
- IEEE five-paper full-text evidence + source-level PRISMA
- ACM + snowball source package
- OpenAlex source package
- Semantic Scholar evidence
- merged search log
- cross-source candidate merge
- duplicate log
- publication-version reconciliation log
- merged evidence table
- team working PRISMA

## Current merge result

- Candidate publication records: **53**
- Exact duplicate records removed: **14**
- Unique publication records: **39**
- Publication-version groups flagged: **2**
- High-relevance evidence studies synthesized: **17**

## Main evidence conclusion

The evidence supports:
- recording compilation/syntactic success separately from execution/pass success,
- retaining failure categories rather than one binary outcome,
- using compiler/runtime feedback as a controlled repair mechanism,
- freezing prompt structure to avoid confounding model comparisons,
- analyzing task/dependency complexity,
- using actual compiler/JUnit execution as the primary correctness oracle rather than LLM self-judgement.

The IEEE HumanEval-Java study with Gemini and GPT-4o is especially important because it directly reports compilation success and runtime failure separately on JUnit-generated tests.

## Handoff to Week 7

Use:
- `candidate_papers_merged.csv`
- `cross_source_duplicate_log.csv`
- `publication_version_links.csv`
- `evidence_table_merged.csv`
- `prisma_week6.md`
- `week6_team_synthesis.xlsx`

The only major review-layer task remaining before a final whole-SLR PRISMA is consistent team-level eligibility/full-text screening of the unique publication pool.
