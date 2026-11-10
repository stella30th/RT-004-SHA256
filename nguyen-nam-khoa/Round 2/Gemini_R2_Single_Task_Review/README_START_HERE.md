# Gemini OAPR Round 2 — single-task prompts (DRAFT for team review)

## Files
- `prompts/A_baseline/CE###.txt`: one-task baseline, without intervention.
- `prompts/B_checklist/CE###.txt`: same baseline + 9-item source-first checklist.
- `prompts/C_full_OAPR/CE###.txt`: identical B + one task-specific diagnostic focus.
- `prompt_manifest.csv`: SHA-256 of each full text.
- `pilot_CE033_CE037.md`: pilot execution checklist.
- `CHANGES_AND_RISKS.md`: methodological changes and approval decisions.

Each file includes the target Java source. Paste the full text of exactly one file into a fresh Gemini session; use the same model/interface and preapproved output policy for A/B/C. One run produces one test class. Three independent runs per arm/task in main evaluation.

**Not yet formally frozen:** Single-task prompts intentionally diverge from Round-1 historical 94-task one-chat P0. These are NOT exact historical P0 resamples. The group must approve this prospective study design and report the changed context. Do not treat files as official until this is approved.

**Do not repair generated tests to improve the PASS rate.** Archive original request and model output; preserve failures, record model/session metadata and evaluate with the same frozen Maven harness.
