# Pilot CE033 / CE037 — after group approves protocol

1. Approve the single-task A/B/C prompt design, all source hashes and focus statements; freeze the prompt SHA-256 manifest.
2. Confirm the Gemini model/version and user interface, recording timestamp, limits (if exposed), session independence and condition order.
3. For each trial, open a fresh conversation and send one complete task/arm prompt, e.g. A_baseline/CE033.txt. Save `request.txt`, unmodified `response.raw.txt` and metadata.
4. Extract the generated Java test using ONE frozen deterministic extractor supporting the documented BEGIN/END markers, without hand-editing generated Java. Preserve malformed outputs too.
5. Run with the same isolated Maven Java 17, JUnit 4.13.2, frozen source and pom.xml setup used across arms; save compile.log, test.log, Surefire XML and metrics.
6. Manually cross-check parser's test-count/failure/error/skipped classification with Maven Surefire report. PASS requires compiled, tests>=1, failures=0, errors=0; all-skipped is not PASS.
7. Quarantine infra errors, preserve evidence and diagnose harness; a model-generated compile/assertion failure remains a valid FAIL observation.
8. Do not tune prompts after pilot based on whether model-generated tests pass. If procedure is changed due to pilot discovery, record protocol amendment and re-freeze before evaluation.
9. The main phase requires 7 tasks × 3 arms × 3 independent runs = 63 newly generated responses; pilot outputs are not automatically part of 63.
