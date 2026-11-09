# Research Question Evidence Map

RQ: On a frozen subset of 30 HumanEval problems, using the same structured prompt and one compiler-feedback repair round, which task categories cause ChatGPT- or Gemini-generated JUnit test suites to have lower compile-and-execute success rates than a human-written baseline?

| Paper | Relevant RQ component | Evidence contribution | Boundary |
|---|---|---|---|
| R001 - ChatTester | Compile/execute outcomes; repair | Separates compilation errors from execution/assertion failures and evaluates iterative refinement | No Gemini comparison; not exactly one repair round |
| R002 - HITS | Task complexity; prompting | Studies complex Java methods, conditions/loops and method slicing | Primarily coverage; multiple repair rounds |
| R003 - SymPrompt | Structured prompting; path complexity | Uses path constraints and focal context to improve test generation | Python evidence; indirect for JUnit |
| R004 - TestART | Compiler/runtime feedback; repair | Evaluates generation-repair interaction and correctness/coverage outcomes | Template repair and multiple iterations; metadata/full-text version mismatch |
| R005 - LLM evaluation | Compile success metric; prompt control | Defines CSR and examines prompt/context effects in Java projects | Removes compilation-error test methods for coverage; no direct Gemini comparison |

## Implications for the planned experiment

- Record compilation success and execution success separately, together with the combined compile-and-execute outcome.
- Record pre-repair and post-repair outcomes to distinguish initial generation quality from recovery after the permitted repair round.
- Distinguish compiler errors from runtime failures and assertion failures. Use a predetermined error taxonomy rather than changing categories after seeing full results.
- Keep the prompt, context policy and repair budget consistent across models and problems.
- Consider branching and loop complexity when reviewing the frozen task taxonomy; these papers do not establish failure rates for the team's specific task categories.
- Do not treat high coverage as proof of execution correctness or transfer published performance figures to the team's experiment.

## Evidence gaps

These five studies do not directly answer the exact paired Human/ChatGPT/Gemini comparison on the frozen 30-problem HumanEval subset with exactly one compiler-feedback repair round. Direct Gemini evidence, HumanEval-to-Java justification and one-round repair evidence remain areas for supplementary searching. This collection does not establish that such evidence is absent from the wider literature.
