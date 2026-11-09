# OAPR-R2 experimental protocol — DRAFT; requires human approval

**Research type:** exploratory, not preregistered for the original P0/P1; the new round must be frozen before observing its outcomes.

**Study unit:** (model, task, condition, repetition). Main ChatGPT cohort: CE033 CE041 CE043 CE050 CE053 CE060 CE065 CE066 CE077 CE085 CE090. Model cohorts are not paired across the same failure set. Original 188-pair dataset accounting belongs to the retrospective paper and must not be described as newly executed P1 full-set regression.

**Conditions:** A: exact baseline P0 resampled; B: same frozen P0 task message plus a generic nine-item source-first checklist; C: identical B plus predeclared task-specific failure focus. The differences between conditions are disclosed in the prompt manifest. Condition C explicitly uses information learned from P0 errors; results measure *failure-informed workflow*, not a pure causal effect of checklist alone.

**Sampling:** At least 3 independent calls per condition and task. Rotate or randomize order of conditions before collecting responses; record order, context policy, UI/API interface, time and any output cap. Do not silently exclude empty/truncated responses. Match output/call budget as far as accessible. No selective reruns, no code editing, no tuning after results.

**SUT and infrastructure:** freeze SHA256 of all 94 production files and pom.xml; Java 17, JUnit 4.13.2, Maven Compiler 3.13.0, Surefire 3.2.5. Access to hosted AI models is not available from the offline project ZIP. Use explicit session record to establish the true model version; historic P1 prompt says 'ChatGPT 5.6 Sol', which conflicts with a newer claim 'ChatGPT 6 Sol'.

**Primary metric:** Suite compile-and-execute success. PASS iff compilation succeeds, at least 1 JUnit method runs, failures=0 and errors=0; skipped cases are logged and must not be treated as complete coverage. A tool/configuration error is quarantined as an infra event with evidence, not silently scored as model failure. If all tests are skipped, report separately.

**Secondary:** compilation rate, assertion failure count, runtime error count, per-case variation, task-level paired outcomes across conditions within repetition if paired seeds/order and comparable prompts can be justified, exact confidence intervals by task/cohort, descriptive cost/latency where logged. The results cannot by themselves establish defect detection or test-oracle correctness against an independent specification.

**Quarantine:** CE029 and CE087 original SQLite dependency issues were resolved without editing the original P0 test code. Gemini CE023 had an inconsistent supplied input, so the two input versions must not be mixed in a controlled prompt comparison. Report both raw and normalized sensitivity calculations. Never rewrite history.

**Pre-analysis:** Per condition, report task PASS fractions, complete-run denominators, 95% uncertainty intervals, and missing runs. The original 10/11 ChatGPT P1 result remains a historical single draw, not part of newly controlled 99 runs. Interpret task-specific differences descriptively unless statistical testing is planned in advance by An.

**Stop conditions:** missing original ChatGPT P0 prompt, unknown model configuration where equivalence is asserted, uncontrolled code edits, inability to save raw responses, or Maven setup mismatch -> stop and label unverified.

**Future checks (not yet performed):** rerun on all 94 ChatGPT P0-pass tasks for regression assessment; mutation testing of generated suites and test effectiveness; new held-out failures from a task set not used to design focus hints.
