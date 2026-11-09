# Literature Review Protocol

## Objective
Collect evidence related to LLM-generated unit/JUnit tests, compilability, executability, compiler-feedback repair, structured prompting and task-dependent failure.

## Shared Search String
```text
("unit test generation" OR "JUnit test suite" OR HumanEval)
AND
(ChatGPT OR Gemini OR "compiler-feedback repair" OR "structured prompting")
AND
(compilability OR executability OR "build success rate" OR "test execution rate")
```

## Databases
- IEEE Xplore
- ACM Digital Library
- OpenAlex
- Semantic Scholar
- Google Scholar (supplementary)

## Workflow
1. Run the same shared query.
2. Export raw results.
3. Deduplicate.
4. Title/abstract screening.
5. Full-text screening.
6. Record inclusion/exclusion reason.
7. Fill the evidence table.

## Required Evidence Fields
- Paper ID
- Citation
- Database
- Tool / LLM
- Dataset
- Test-generation setting
- Metric
- Main result
- Code/data availability
- Limitation
- Relevance to RQ
