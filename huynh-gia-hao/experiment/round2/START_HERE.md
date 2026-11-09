# HAO / ClassEval-T OAPR Round 2: executable revision kit

## Scope and integrity
This kit **does not** generate ChatGPT responses, retroactively change P0/P1 tests or alter the 94 production classes. It provides a controlled way to perform a **new** evaluation after freezing prompts and settings. P0/P1 original data remain immutable and separately labeled.

### Verified ZIP snapshot (9 Nov 2026)
- `workspace-template/src/main/java/org/example`: 94 Java SUT classes.
- `workspace-template/src/tests-chatgpt-p0/java/org/example`: 94 JUnit source files.
- `workspace-template/src/tests-chatgpt-p1/java/org/example`: 11 JUnit source files.
- Baseline P0 ChatGPT task PASS: 83/94; focused P1 PASS: 10/11; unresolved CE066.
- The repo root `docs/experiment_protocol.md` and `README.md` describe an **earlier 30-task HumanEval** project (with JUnit 5). **Do not cite them as the final ClassEval-T protocol.** This kit is a new protocol and is not evidence that historical procedures were followed.
- The retained ChatGPT P1 prompt text self-identifies **ChatGPT 5.6 Sol**, while recent manuscript and conversation call the model **ChatGPT 6 Sol**. Exact model/provider/UI/session identity must be confirmed from real records. Do not silently relabel the original experimental model.
- The original per-task P0 prompt for ChatGPT was not located as a separate archived file in this ZIP. The Gemini P0 master prompt exists but **must not be assumed identical** without comparison.

## Gate 0: preserve evidence / freeze
1. Copy the project into a new folder. Never edit or replace existing `src/tests-chatgpt-p0`, `src/tests-chatgpt-p1`, archived reports or raw prompts.
2. Collect the **actual original** ChatGPT P0 prompts as `round2/frozen_p0/CE033.txt` ... for all 11 IDs. These must be exact original task messages including the relevant `TARGET_SOURCE`. Use archived chat exports or source records; **do not recreate from memory** and present them as the original.
3. Record model identifier, web/API interface, access timestamp, visible setting, response budget, retries, stop/truncation and any system context in `model_metadata.csv` (use `NOT_EXPOSED` where inaccessible).
4. Hash/archive inputs before running, and get team sign-off on the plan.

## Gate 1: build prompts (three conditions, 11 cases)
From repository root on Windows PowerShell:

```powershell
$KIT = '.\huynh-gia-hao\experiment\round2'
python "$KIT\scripts\audit_snapshot.py" --workspace '.\workspace-template' --out "$KIT\outputs\snapshot_audit.json"
python "$KIT\scripts\prepare_prompts.py" --p0-dir "$KIT\frozen_p0" --out "$KIT\prompts\generated"
```

If `prepare_prompts.py` reports missing files, **stop**. The generated prompts must share the exact same task context; only the controlled addendum differs. Save `prompt_manifest.json` with SHA256 before generating. The full P1 includes failure-specific focus; it is NOT a pure checklist treatment.

## Gate 2: collect 99 new ChatGPT outputs
11 failed task IDs × 3 conditions × 3 independent repetitions = 99 generation calls. Each response belongs in:

`huynh-gia-hao/experiment/round2/raw_outputs/chatgpt/<condition>/run-01/CE033.java`

Supported `<condition>` values: `p0_resample`, `checklist_only`, `checklist_focus`.

For every call, save the **entire raw response**, chat/session metadata, a timestamp and a record whether the output was empty, truncated or refused. Extraction of Java should follow one frozen deterministic rule; do not hand-edit generated tests or retry until passing.

## Gate 3: compile+execute ONE unchanged generated Java suite
```powershell
$KIT = '.\huynh-gia-hao\experiment\round2'
python "$KIT\scripts\run_suite.py" `
  --workspace '.\workspace-template' `
  --java "$KIT\raw_outputs\chatgpt\p0_resample\run-01\CE033.java" `
  --task CE033 --model chatgpt --condition p0_resample --run 1 `
  --out "$KIT\outputs\runs" `
  --model-identifier 'VERIFY_FROM_SESSION' --interface 'Web UI' `
  --access-time-utc 'VERIFY_FROM_SESSION'
```

Requires Maven installed and accessible on PATH. The `revision-run` Maven profile compiles ONLY the staged class alongside the SUT; it avoids duplicate generated class names. Each run invokes `mvn -B clean test -Prevision-run`; **do not run two invocations in parallel on the same workspace**. Original P0/P1 folders are not modified.

Each run generates `generated.java`, `maven.log`, `surefire.xml` (if a test executed), `result.json` with source hash, compilation/execution status and metadata. `TOOL_UNAVAILABLE`, `TIMEOUT`, `NO_TEST_REPORT` must **not** be quietly converted into task-level fail/pass counts without a documented protocol.

## Gate 4: aggregate, missingness and statistics
```powershell
python "$KIT\scripts\aggregate_results.py" `
  --results "$KIT\outputs\runs" `
  --out "$KIT\outputs\chatgpt_round2.csv" --repetitions 3
```

Only measured `result.json` files enter the CSV. The script produces `chatgpt_round2.missing.json` for incomplete conditions. Transfer the validated run manifest and raw logs to An. **Do not claim statistical superiority or 100% recovery until controlled results exist.**

## Reviewer mapping
- H2/M1/M10: model identity, call interface, exact prompts and metadata (requires actual session evidence).
- H7: publish original per-task prompt files/manifest (P0 missing input gate must be resolved).
- H8: equal-call-budget P0 resample control.
- H13/H15: checklist-only vs checklist+focus and disclose diagnosis mapping.
- H4/H6: >=3 repetitions; do not claim zero variance or no regression.
- H3: retain CE029/CE087/CE023 as separate sensitivity cases, not secretly reclassify.
- H5/H14: independent statistical analysis by An (binomial CIs and sensitivity).

## Review before rerun
- Does the source class compile unchanged?
- Does every generated class declare `package org.example;`?
- Are repeated calls independent and saved verbatim?
- Are there differences in model version, budget or interface between conditions? Report them.
- Have no answer values or reference tests leaked into the prompts?
