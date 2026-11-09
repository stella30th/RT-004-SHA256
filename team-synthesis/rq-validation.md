# RQ Validation — Week 7 Draft

**Research topic:** Task Categories Where ChatGPT- and Gemini-Generated JUnit Tests Fail to Run  
**Lead synthesis:** Huỳnh Gia Hào  
**Evidence base:** Week-6 merged evidence table (17 high-relevance studies)

## 1. Evidence gate

All five RBL-2 evidence-table gates pass:
- papers: 17 ≥ 12;
- Tool/LLM populated: 100.0%;
- numeric result evidence: 82.4%;
- limitations populated: 100.0%;
- concrete metric field populated: 100.0%.

## 2. Validated RQ

### RQ1
For a frozen, category-stratified 30-task subset of HumanEval-Java, how do benchmark/reference JUnit tests, ChatGPT-generated tests, and Gemini-generated tests differ in compilation success, execution success, and combined runnability after at most one compiler-feedback repair?

### RQ2
Which task categories and failure types are most frequently associated with ChatGPT- and Gemini-generated tests that fail to compile or execute?

## 3. P/I/C/O

| Element | Definition |
|---|---|
| P | Frozen 30-task HumanEval-Java subset, correct implementations |
| I | ChatGPT-generated JUnit 4 tests under frozen prompt + max one compile-repair |
| C | Gemini same protocol + benchmark/reference JUnit condition |
| O | compile_success, execution_success, combined_success, failure taxonomy |

## 4. Strongest counterexample

The strongest overlap is the IEEE Access study using Gemini/GPT-4o on HumanEval-Java/JUnit. It overlaps strongly on P and O and partially on I, but uses a different comparison/refinement design and does not answer the task-category paired-failure question.

**Novelty label: extension.**

## 5. Gap

No reviewed study combines all of:
1. same frozen HumanEval-Java task set,
2. benchmark/reference baseline,
3. ChatGPT vs Gemini paired conditions,
4. same generation prompt,
5. maximum one compiler-feedback repair,
6. task-category analysis,
7. explicit compile vs execution failure taxonomy.

## 6. Feasibility

Feasible with the current course scope if:
- pilot is completed first;
- 30 tasks are frozen before full run;
- exact model IDs/API parameters are frozen before approval;
- infrastructure failures are quarantined.

## 7. Seven design elements

| Element | Decision |
|---|---|
| Dataset | HumanEval-Java; correct implementations; pilot 5 → freeze 30 |
| LLM/Tool | ChatGPT + Gemini; exact IDs must be frozen before approval |
| Metric | compile_success, execution_success, combined_success |
| Threshold | objective pass criteria from compiler/JUnit; no arbitrary numeric threshold |
| Baseline | benchmark/reference JUnit tests |
| Pipeline | generate → compile → max one compiler-feedback repair if compile fails → execute → classify |
| Statistical test | Cochran's Q; pairwise exact McNemar + Holm when warranted |

## 8. Proposal decision

Proceed to proposal with the RQ above.

Do not run the official experiment until proposal approval and model/API versions are frozen.
