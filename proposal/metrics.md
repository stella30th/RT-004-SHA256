# Week 7 proposal: outcome metrics

Owner: Hồ Lê Thiên An. Reviewer: Huỳnh Gia Hào. Status: ready for review; definitions must be frozen before the full experiment.

## Unit and fixed environment

One observation is one frozen HumanEval-Java problem under one condition: reference baseline, ChatGPT, or Gemini. All three conditions use the same correct Java SUT for that problem. Compile and run each test suite in an isolated Maven workspace with Java 17, JUnit Jupiter 5.10.2, and Maven Surefire 3.2.5 (the versions currently in `workspace-template/pom.xml`). The AI conditions use the same frozen prompt and at most one compiler-feedback repair. The reference condition receives no AI repair.

Before an observed failure is attributed to a test suite, a fixture/smoke test must establish that the SUT, JDK, Maven dependencies, JUnit discovery, and runner work in that workspace. Preserve the raw response, extracted test source, commands, exit codes, output, timeouts, and timestamps for audit. A zero-exit command with no discovered tests is invalid, not a successful execution.

## Operational definitions

| Field | Type | Rule for a valid observation | Logging and denominator |
| --- | --- | --- | --- |
| `compile_success_initial` | Boolean | `1` when the original generated suite completes `mvn -q -DskipTests test-compile` with exit code 0; otherwise `0` for a test-caused compiler error. | Log the first compiler exit code and diagnostic. This is a secondary, pre-repair outcome. |
| `compile_success` | Boolean | `1` when the final eligible suite compiles: the original suite if it compiled initially, otherwise the single repaired suite if allowed and received. `0` after a test-caused compiler failure. | Use the final eligible attempt for every condition. Report success / valid observations. |
| `execution_success` | Boolean | `1` only when `compile_success=1`, JUnit discovers at least one test, the frozen `mvn -q test` command completes within its timeout, and Surefire reports zero failures and zero errors. `0` if compilation did not succeed or execution fails because of the test. | Preserve test counts, process exit code, and Surefire XML. Report success / valid observations; compilation failures remain `0` in this denominator. |
| `combined_success` | Boolean | `compile_success AND execution_success`. This is the primary per-problem outcome after the permitted repair. | Derive rather than enter independently. Report `sum(combined_success) / number of valid problems` for each condition, overall and in each category. |
| `repair_needed` | Boolean or blank | `1` only for an AI suite with a test-caused initial compiler failure and one permitted compiler-feedback request; `0` for an AI suite that compiled initially. Blank for the reference condition. | Log whether a repair request was sent and its raw response. No second request is allowed. |
| `repair_success` | Boolean or blank | Among AI observations with `repair_needed=1`, `1` only if the single repaired suite both compiles and executes successfully; otherwise `0`. Blank when no repair was eligible. | Conditional rate = successful repairs / valid repair-eligible observations. Also report the count so this rate is not confused with overall success. |
| `failure_category` | Enum | `NONE`, `INVALID_OUTPUT`, `MODEL_COMPILE`, `EXECUTION`, or `INFRA`, assigned by the deterministic rules in `threshold.md`. | Log one final category plus an optional subtype and diagnostic; retain the initial compiler failure separately after a successful repair. |
| `valid_run` | Boolean | `1` when the frozen environment is confirmed and the observation has a final test outcome; `0` for `INFRA`. | Exclude `INFRA` from model/baseline success-rate denominators and report its count separately. Never silently drop an invalid or empty model response. |

An empty, malformed, or non-JUnit model response is `INVALID_OUTPUT` with all three success metrics set to `0` when the environment is healthy. It is still part of the valid AI denominator. A timeout caused by the submitted test belongs to `MODEL_COMPILE` or `EXECUTION` according to stage; a workspace, dependency, or service failure unrelated to the suite is `INFRA`. Ambiguous cases require an independent fixture check and a recorded adjudication before analysis.

For paired comparisons, include only problem IDs with valid observations for all three conditions, and report the number excluded for infrastructure reasons. Keep the full per-condition descriptive counts as well. The statistical tests and confidence intervals are specified by the statistics owner; they do not redefine the binary outcomes here.

## Current code handoff

`scripts/logger.py` has the existing final outcome fields but does not yet enforce these rules or contain `compile_success_initial`, `failure_category`, `valid_run`, runner exit codes, test counts, or artifact paths. `scripts/compile_and_run.py` only returns command-level success. These fields and the automatic classifier must be implemented and checked in the pilot before the full run. `results/experiment_results.csv` is currently an empty header, not empirical evidence.
