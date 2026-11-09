# Patch Plan for Existing Repository

## Modified
- `README.md`
- `.gitignore`

## New shared research files
- `team-synthesis/rq_definition.md`
- `team-synthesis/review_protocol.md`
- `team-synthesis/ie_criteria.md`
- `team-synthesis/experiment_protocol.md`
- `team-synthesis/authorship.md`
- `team-synthesis/category_taxonomy.md`
- `team-synthesis/progress_report.md`
- `team-synthesis/search_log.csv`
- `team-synthesis/evidence_table.csv`
- `team-synthesis/prompt_v1.txt`

## New experiment config/data/result skeleton
- `config/experiment.example.json`
- `data/processed/humaneval_30.csv`
- `data/human-baseline/.gitkeep`
- `results/experiment_results.csv`
- `results/mock_results.csv`

## New code skeleton
- `scripts/providers/base.py`
- `scripts/providers/chatgpt_provider.py`
- `scripts/providers/gemini_provider.py`
- `scripts/prompt_builder.py`
- `scripts/compile_and_run.py`
- `scripts/logger.py`
- `scripts/init_results.py`
- `scripts/run_experiment.py`
- `scripts/compute_metrics.py`

## New presentation skeleton
- `presentation/progress_outline.md`

## New per-member SLR files
For each existing member folder:
- `SLR/README.md`
- `SLR/search_log.csv`
- `SLR/candidate_papers.csv`

## Intentionally NOT changed
- Existing `.git/`
- Existing paper folders and `.gitkeep` files
- Existing member-folder layout
- No API integration yet
- No full experiment code yet

The runner remains a skeleton until model IDs, API/interface, prompt version, dataset version, Java/JUnit environment, taxonomy and pilot protocol are frozen.
