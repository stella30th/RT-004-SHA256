# Week 7 Proposal Contribution — Huỳnh Gia Hào

## A. Title / positioning

**Task Categories Where ChatGPT- and Gemini-Generated JUnit Tests Fail to Run**

Positioning: empirical extension of LLM-based unit-test-generation research, focused on **runnability failures by task category** rather than only aggregate coverage or pass rate.

## B. Problem statement / GAP

Automated unit-test generation with LLMs is promising, but generated tests may be unusable before quality metrics such as coverage are meaningful. Existing evidence reports syntactic/compilation failures, runtime/assertion failures, context/dependency problems, and benefits from compiler-feedback repair. Direct HumanEval-Java/JUnit evidence with Gemini and GPT-4o also exists.

The unresolved gap is narrower: **the reviewed studies do not provide a controlled, paired comparison that asks which task categories are associated with ChatGPT/Gemini JUnit runnability failures on the same frozen HumanEval-Java tasks, against a benchmark/reference baseline, with the same prompt and a fixed one-repair policy.**

This project therefore studies *where* generated tests fail, not only how much coverage they eventually achieve.

## C. Research questions

### RQ1
For a frozen, category-stratified 30-task subset of HumanEval-Java, how do benchmark/reference JUnit tests, ChatGPT-generated tests, and Gemini-generated tests differ in:
1. compilation success,
2. execution success,
3. combined runnability,

after at most one compiler-feedback repair for AI-generated tests?

### RQ2
Which task categories and failure types are most frequently associated with ChatGPT- and Gemini-generated tests that fail to compile or execute?

## D. P/I/C/O

| Element | Operational definition |
|---|---|
| P | Frozen category-stratified subset of 30 HumanEval-Java tasks using correct implementations |
| I | ChatGPT-generated JUnit 4.13.2 tests, fixed structured prompt, one compiler-feedback repair maximum |
| C | Gemini under the same generation/repair protocol and benchmark/reference JUnit condition on the same tasks |
| O | compile_success, execution_success, combined_success; failure category/subtype |

## E. Dataset / benchmark decision

### Benchmark
**HumanEval-Java**

Local repository audit from Week 6:
- 164 HumanEval records upstream;
- local repository contains 163 correct implementations, 163 buggy implementations, and 163 JUnit test files;
- primary SUT is `humaneval.correct.*`;
- benchmark tests originally targeting buggy implementations may be mechanically retargeted to correct implementations without changing assertions.

### Pilot
Pilot first:
- HumanEval/0 — HAS_CLOSE_ELEMENTS
- HumanEval/1 — SEPARATE_PAREN_GROUPS
- HumanEval/2 — TRUNCATE_NUMBER
- HumanEval/3 — BELOW_ZERO
- HumanEval/4 — MEAN_ABSOLUTE_DEVIATION

### Full sample
Freeze **30 tasks** after pilot validation.

Selection policy:
1. define task-category taxonomy before final selection;
2. remove only tasks that are non-buildable for infrastructure reasons;
3. stratify by the frozen taxonomy;
4. use deterministic selection with a fixed seed;
5. publish the final manifest and do not change membership after the full run begins.

Rationale: 30 paired tasks keeps the experiment executable within the course timeline while supporting paired model comparisons. Category-level analyses are treated as exploratory because per-category n will be smaller.

## F. Baseline

Primary baseline: **benchmark-provided/reference JUnit tests** executed against the correct HumanEval-Java implementation.

Baseline is not “a threshold”. It is a comparison condition.

If a reference test imports `humaneval.buggy.*`, only the package/class target is mechanically changed to `humaneval.correct.*`; assertions are not edited.

## G. Outcomes / thresholds

No arbitrary score such as `0.85` is introduced.

Operational pass rules:
- `compile_success = 1` iff the generated JUnit source compiles successfully in the frozen Java/JUnit environment.
- `execution_success = 1` iff the compiled JUnit test run completes with all generated test cases passing against the correct implementation.
- `combined_success = compile_success AND execution_success`.
- `INFRASTRUCTURE` failures are quarantined and are not counted as model failures.

Repair:
- only after AI-test compile failure;
- compiler feedback is supplied;
- **maximum one AI repair attempt**;
- no second repair round in the primary protocol.

## H. Statistics

Primary outcome is paired binary data across three conditions on the same tasks.

1. Global: **Cochran's Q** for benchmark/reference vs ChatGPT vs Gemini.
2. Follow-up if warranted: pairwise **McNemar exact tests**.
3. Multiple comparisons: **Holm correction**.
4. Report success proportions, paired absolute differences, and 95% confidence intervals alongside p-values.
5. RQ2 category/failure analysis is exploratory; report counts/rates first. Exact association tests may be used only if cell sizes support meaningful interpretation.

Alpha: `0.05`.

Do not claim “no difference” merely because H0 is not rejected.

## I. Failure taxonomy

Primary categories:
- SYMBOL_RESOLUTION
- TYPE_MISMATCH
- ACCESS_OR_VISIBILITY
- FRAMEWORK_SCAFFOLD
- DEPENDENCY_CONTEXT
- INCOMPLETE_OR_FORMATTED_OUTPUT
- ASSERTION_FAILURE
- RUNTIME_EXCEPTION
- INFRASTRUCTURE
- OTHER

`INFRASTRUCTURE` is excluded from model-failure counts.

## J. Reproducibility decisions

Freeze before official run:
- ChatGPT exact model ID/version;
- Gemini exact model ID/version;
- API endpoint/access date;
- full prompt text;
- temperature/top_p/max_tokens;
- Java version;
- JUnit version;
- repository commit;
- task manifest;
- random seed used for sample selection.

Current fixed environment decision:
- Java 8
- JUnit 4.13.2
- correct HumanEval-Java SUT

## K. Threats to validity relevant to Hào's sections

### Internal
Prompt/model nondeterminism may change outputs.
Mitigation: fixed parameters, frozen prompt, recorded raw responses, same protocol per condition.

### External
30 HumanEval-Java tasks may not generalize to industrial Java projects.
Mitigation: describe benchmark scope explicitly; do not generalize beyond similar function-level Java tasks.

### Construct
“Runnability” does not equal test quality.
Mitigation: report compile/execution as the primary construct and keep coverage/mutation, if collected, secondary.

### Conclusion
Small sample and multiple comparisons can inflate uncertainty.
Mitigation: paired design, exact tests, Holm correction, effect estimates and CIs; category findings labeled exploratory.

## L. Hao's Week-7 handoff

Ready:
- individual `SLR/rq-check.md`;
- RQ/PICO;
- research gap and novelty label;
- dataset/benchmark section;
- sample/pilot/freeze policy;
- baseline and pass-rule definitions;
- statistics recommendation;
- validity notes.

Team input still required before proposal v1.0:
- exact ChatGPT API model ID/version;
- exact Gemini API model ID/version;
- API pricing/cost estimate;
- final 30-task manifest after pilot.
