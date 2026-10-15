# Week 7 proposal: success criteria and failure decisions

Owner: Hồ Lê Thiên An. Reviewer: Huỳnh Gia Hào. Status: ready for review; freeze with the experiment protocol before the full run.

## Per-suite decision procedure

1. Validate the frozen Java 17 / JUnit Jupiter 5.10.2 / Surefire 3.2.5 workspace using a known-good fixture and the correct SUT. If setup, dependencies, the JVM, or the test runner fail independently of the submitted suite, record `INFRA`, `valid_run=0`, and the diagnostic. Fix the environment and rerun the same frozen input; do not score this as a model failure.
2. Preserve the original response. If it is empty, cannot be extracted as a Java JUnit suite by the frozen extraction rule, or contains no test method, record `INVALID_OUTPUT`, `valid_run=1`, and all success flags `0`. Do not invent or manually edit test code.
3. Run `mvn -q -DskipTests test-compile` with the frozen compile timeout. Exit code 0 gives `compile_success_initial=1`. A test-caused nonzero exit or timeout gives `0`; capture the exact compiler diagnostic.
4. For an AI test with initial test-caused compilation failure, send exactly one repair request containing the original prompt, original test, and compiler feedback. Preserve that response. Compile the extracted repaired test once. Do not repair the reference suite, retry for test assertion failure, or keep sampling until a pass appears.
5. Set final `compile_success=1` only if the original or eligible repaired suite exits compilation with code 0. Otherwise set `0`. A healthy environment with a missing or unusable repair response remains a model outcome.
6. When final compilation succeeds, run `mvn -q test` with the frozen execution timeout. Set `execution_success=1` only when the process exits 0, Surefire reports at least one discovered test, and its failure and error counts are both 0. Assertion failures, exceptions, and test-caused timeouts give `0`. Record exact counts and diagnostics. Set `execution_success=0` when compilation fails.
7. Derive `combined_success=compile_success AND execution_success`. For repair-eligible AI suites, set `repair_success=combined_success` after the repaired attempt. Assign the final `failure_category` from the table below.

The timeout values currently proposed in `config/experiment.example.json` are 30 seconds for each command. Pilot measurements must confirm these values are workable. Any change requires a documented protocol decision before the full experiment. A command's exit code alone cannot establish that JUnit discovered and ran a test.

## Final category rules

| Category | Rule | Counted as model/baseline failure? |
| --- | --- | --- |
| `NONE` | Final compilation and execution both succeed, with at least one test discovered. | No. |
| `INVALID_OUTPUT` | Empty, unextractable, or no runnable JUnit test from an AI response in a healthy environment. | Yes, AI outcome. |
| `MODEL_COMPILE` | Final eligible test fails Java compilation, including a test-caused compile timeout or failed single repair. | Yes. |
| `EXECUTION` | Final test compiles but Surefire reports failed/error tests, discovers no tests, or a test-caused execution timeout. | Yes. Preserve subtypes such as `ASSERTION`, `RUNTIME`, `NO_TESTS`, `TIMEOUT`. |
| `INFRA` | Independent fixture or diagnostics show a missing/broken JDK, dependency, Maven workspace, runner, or service unrelated to submitted test code. | No; `valid_run=0`, reported separately. |

Use this precedence: prove `INFRA` first with independent evidence; otherwise classify invalid output; then final compile failure; then execution failure; otherwise `NONE`. A repair may make the final category `NONE` while the initial compile failure remains logged. Do not infer `INFRA` solely from a failed model-generated test.

## Aggregate criteria and interpretation

The primary reported quantity is the observed proportion of valid problems with `combined_success=1` for each condition and predefined task category. An AI condition has a descriptively lower success rate than the baseline in a category only when its paired rate is numerically lower on the same valid problem IDs. Report numerator, denominator, difference, and uncertainty; do not invent a minimum percentage or a literature-derived pass-rate cutoff. The inferential rejection rule and multiple-comparison adjustment belong in the frozen statistical analysis plan.

The cited studies support separating compilation from running and observing repair effects. Their published success percentages come from different datasets, tools, and repair procedures, so they are evidence for choosing measurements, not performance thresholds for this experiment. See `ho-le-thien-an/SLR/metric-evidence-table.csv`.
