OAPR BATCH TOOL - 33 SLOTS / CONDITION
====================================

Requirements: Python 3, existing Round2 project and Maven available in PATH.
This package does NOT connect to the ChatGPT website and does NOT generate new Java files automatically.
For a true 33 independent model samples, request the 33 generations independently (e.g. authorized model API or 33 separate calls). Sending ALL_33_PROMPTS.txt as one message does not create independent samples and may truncate.

1. Extract this ZIP so batch_33.py is located at:
   huynh-gia-hao\experiment\round2\scripts\batch_33.py

2. From project root:
   $KIT = ".\huynh-gia-hao\experiment\round2"
   $B = "$KIT\scripts\batch_33.py"

3. Export all 33 prompts for checklist_only:
   python "$B" prepare --kit "$KIT" --condition checklist_only
   Files created under: $KIT\batch_33\checklist_only\
   - prompts_33.zip (each independent slot has its own prompt)
   - ALL_33_PROMPTS.txt (reference only; do not send all in one model call)
   - HOW_TO_RETURN.txt (exact batch response format)
   - prompt_manifest.csv

4A. AI returns a ZIP with run-01/CE033.java ... run-03/CE090.java:
   python "$B" import-zip --kit "$KIT" --condition checklist_only --input ".\AI_33_OUTPUTS.zip"

4B. AI returns a TXT containing 33 delimited blocks:
   python "$B" import-txt --kit "$KIT" --condition checklist_only --input ".\AI_33_OUTPUTS.txt"

5. Execute ALL valid Java files using Maven (skips existing results):
   python "$B" run --kit "$KIT" --condition checklist_only --workspace ".\workspace-template"

6. Summarize at any point:
   python "$B" report --kit "$KIT" --condition checklist_only

Report paths:
   $KIT\batch_33\checklist_only\results_33.csv
   $KIT\batch_33\checklist_only\missing_33.txt
   $KIT\batch_33\checklist_only\import_log.csv

Repeat with --condition checklist_focus for next 33.

IMPORTANT: Results created with modified prompts must NOT be relabeled as frozen checklist_only or checklist_focus. Keep snapshots, provenance, model/date, and unmodified raw outputs.
The script does not modify source assertions. It skips results that already exist. Read import_log.csv for missing/unverified generated Java.
TXT importer expects exact markers as in HOW_TO_RETURN.txt, validates basic structure and quarantines invalid blocks as .unverified.txt.
TXT importer is intentionally strict. ZIP with 33 .java files is recommended.
TXT/ZIP batch output made in ONE model response is not 33 independent runs, even if formatted into 33 files.
