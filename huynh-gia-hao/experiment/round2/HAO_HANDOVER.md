# Hào — actual work completed vs remaining

## Completed in this revision
- Inspected uploaded ZIP layout; enumerated 94 production classes and 94/11 original ChatGPT P0/P1 suites.
- Preserved original source, tests and archived reports. Added a new isolated Maven profile `revision-run` and a single-suite execution script.
- Created an auditable three-condition prompt generator and disclosed task-specific focuses for the 11 cases.
- Added a result collector that records individual execution evidence without collapsing compile, runtime and infrastructure failures.
- Created a new ClassEval-T protocol, because repo root docs are still from the old HumanEval/JUnit5 design.

## NOT completed / not claimed
- No 99 ChatGPT model generations occurred; the offline ZIP contains no callable model API credentials and generation needs an authenticated user session.
- Maven itself is unavailable in the current container, so the generated Java runner has not been end-to-end exercised against Maven here.
- Exact original ChatGPT P0 task prompts are not independently archived in the supplied ZIP. Supply them before starting P0-resample; Gemini P0 prompt master is **not** proof they were identical.
- The model version used for historic P0/P1 is **unverified** and has an internal naming conflict (5.6 vs 6 Sol). Preserve original labels until verified.
- No new confidence intervals from repeated runs, no full regression and no mutation score have been claimed.

## Handover / acceptance
1. Hào: fill `frozen_p0/CE###.txt` for 11 original task prompts; sign off SHA manifest.
2. Hào: confirm model identifier/settings/interface and document extraction of generated Java responses.
3. Hào: run a CE066 pilot, then 99 independent generations (11×3×3) and 99 isolated Maven executions. Save every non-PASS outcome.
4. Khoa: reuse the same protocol and run Gemini seven-case cohort independently; do not silently change denominators.
5. An: audit missingness, compute uncertainty and baseline sensitivity, update manuscript figures and reviewer tracker with real evidence.
6. Khôi: audit dataset provenance and paper novelty/references; handle venue requirements.
