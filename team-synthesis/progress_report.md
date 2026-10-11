# Progress Report – Tuesday

## 1. Topic
Task Categories Where ChatGPT- and Gemini-Generated JUnit Tests Fail to Run

## 2. RQ + PICO
Use `rq_definition.md`.

## 3. Progress Checklist
- [ ] RQ/PICO drafted
- [ ] Shared search string frozen
- [ ] IC/EC drafted
- [ ] Search logs started by all members
- [ ] HumanEval source/version identified
- [ ] 30-problem manifest drafted
- [ ] Java/JUnit harness proof-of-concept
- [ ] ChatGPT/Gemini runner skeleton
- [ ] Metric/result schema
- [ ] Mock analysis

## 4. Literature Search Progress
| Member | Database | Raw hits | Screened | Candidate | Included |
|---|---|---:|---:|---:|---:|
| Hào | IEEE | | | | |
| An | ACM | | | | |
| Khôi | OpenAlex | 3441 | 23 | 23 | 18 |
| Khoa | Semantic Scholar / Scholar | | | | |

## 5. Technical Demo
```text
HumanEval problem
→ frozen prompt
→ ChatGPT / Gemini
→ generated JUnit
→ compile
→ optional one compiler-feedback repair
→ execute
→ structured result log
```

## 6. Risks
- HumanEval-to-Java conversion bias
- Human baseline contamination
- Model/version drift
- Prompt inconsistency
- Category-label disagreement

## 7. Next Milestone
Pilot 5–10 problems after freeze checklist is complete.
