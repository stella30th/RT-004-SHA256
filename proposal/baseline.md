# Week 7 proposal: reference JUnit baseline

Owner: Hồ Lê Thiên An. Reviewer: Huỳnh Gia Hào. Status: draft awaiting baseline provenance and dataset freeze.

## Comparison design

For each of the same 30 frozen HumanEval-Java problem IDs, compare three suites against the same correct Java SUT in the same Java 17, JUnit Jupiter 5.10.2, and Surefire 3.2.5 environment:

1. A benchmark-provided or otherwise traceable **reference JUnit suite** for that problem, designated before inspecting this study's ChatGPT and Gemini outputs.
2. A ChatGPT-generated suite using the frozen structured prompt and at most one compiler-feedback repair.
3. A Gemini-generated suite using the same prompt structure and repair rule.

The reference suite is the comparison condition, not a success threshold or a metric. The metrics are compilation, execution, and their conjunction. It receives no AI repair and its test assertions are not changed after looking at AI results. If an existing benchmark suite targets a buggy class, record a predefined adaptation to the corresponding correct SUT (package/class/method binding and JUnit 5 migration if required), preserve the original suite, and review every assertion change before the baseline is frozen. Do not silently rewrite an oracle to make a suite pass.

## Provenance and freeze record required for every problem

Store the original source URL/release/commit, license, problem ID, original test path, original target class, adapted test path, adaptation diff and reason, SHA-256 hashes, reviewer, and freeze date. Keep the frozen 30-ID manifest and category label with this record. Run the reference suite in the same validated workspace and log every failure, including infrastructure failures. A reference test's poor outcome is a measured result; do not assume baseline success is 100%.

## Current repository limit

`data/human-baseline/HE001/SolutionTest.java` is one locally stored JUnit 5 example. Its provenance is not recorded. `data/processed/humaneval_30.csv` has only HE001 marked TODO, and no 30-suite reference set or source/version manifest is present. The existing README and RQ text call the comparison “human-written” and imply the team wrote it before AI outputs; the Week 7 assignment instead requests a benchmark/reference baseline. Those claims cannot be reconciled from the current files alone. Until the actual suite is identified, describe it as a **proposed reference baseline**, not as an already obtained benchmark artifact or team-written human baseline. Confirm its origin and update the RQ, README, and protocol consistently before proposal merge.

## Literature boundary

Siddiq et al. (2024, DOI 10.1145/3661167.3661216, Table 1) compare LLM-generated JUnit 5 suites with manual tests on a Java adaptation of HumanEval. This supports having a reference comparison but does not establish the origin or behavior of this team's baseline. Chudic and Çalıklı (2026, DOI 10.1145/3794763.3794828, Table 1) use human-authored examples in a Python prompt; these are not a JUnit reference condition for this study. The baseline implementation must be justified by its own source and freeze record.
