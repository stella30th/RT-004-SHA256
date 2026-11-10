# OAPR submission readiness — 10 November 2026

This is a working audit for the An-owned synthesis, not an assertion that the manuscript is ready to submit.

## Completed checks

- Historical denominator: 187 valid model–task pairs after excluding Gemini CE023 from the matched-source cohort; 169 P0 PASS, 15/18 selected recoveries, 184/187 carry-forward PASS. The former 188-pair adjustment remains a sensitivity scenario.
- Separate round-2 datasets: Hào's 99 ChatGPT suites (23/33 P0 resample, 33/33 checklist-only, 33/33 checklist-plus-focus) and Khoa's 63 Gemini suites (17/21 A, 18/21 B, 15/21 C). They differ in task set and baseline prompt context and must not be pooled into a model comparison.
- Raw evidence in Git: 99 unpacked ChatGPT JUnit sets, 89 eligible PIT sets, and 63 Gemini request/response/test/Surefire sets. Hào's five named ZIP files are absent, but the main raw execution evidence is committed unpacked; Khoa's Official_Run.zip is present.
- The ClassEval-T authors report field dependencies in 65.3% of samples and method dependencies in 25.9%, with 66.7 mean LOC, 33.8 tests per task, and 99.7% statement / 98.2% branch coverage. The manuscript's earlier 76.2% method-coupling claim and unsupported HumanEval-Java numerical comparison were removed. Source: https://asi.cs.cityu.edu.hk/paper/FSE_3728940.pdf (Table 1 and Section 3.2).
- The seven author-defined domain categories in the paper match the 94 rows of docs/dataset_manifest.csv (23, 20, 19, 11, 10, 6, and 5). This checks internal consistency, not independent validation of the classification.
- Paper citation keys resolve in the local bibliography and LaTeX begin/end counts match. The Word reviewer tracker renders as two readable pages.
- TeX Live in WSL compiled paper/main_anonymous.tex to seven pages. The separate paper/icst_nier_draft.tex compiles without unresolved references or overfull boxes to a two-page anonymous PDF, paper/icst_nier_draft.pdf, and both pages were visually inspected. The shorter draft retains the core historical and two round-2 results.
- paper/main_anonymous.tex is an anonymized source variant. Its comments do not disclose team members.

## Gates before an external ICST 2027 NIER submission

The official track call at https://conf.researchr.org/track/icst-2027/icst-2027-nier limits the main paper to four pages, with up to two additional pages for references only; it requires a PDF, IEEE conference letter format, double anonymity, and disclosure of AI-generated manuscript text. The local venue_decision.md correctly identifies the 4+2 limit but contains an unverified placeholder artifact URL; do not insert that URL.

1. **PDF and page budget:** The full anonymous manuscript compiles to seven pages and exceeds ICST NIER's 4+2 limit because substantive content continues onto page seven. Use the separate two-page NIER draft for a short submission candidate; the full draft remains a reference manuscript. Final proofreading and venue-specific PDF checks remain necessary.
2. **Author facts:** Funding, competing interests, and the final author list require confirmation from the authors. The user asked to skip drafting an AI-use disclosure. The official ICST NIER call nevertheless requires disclosure of AI-generated manuscript text; this candidate cannot be represented as submission-ready until the authors address that policy.
3. **Protocol and sessions:** The Gemini round-2 protocol remains marked DRAFT. All 63 committed Gemini per-run metadata files have null model-version and interface fields, despite the self-reported label in the results report. Hào's generation sessions and replacement/duplicate runs also need provenance if a stronger independence claim is intended. Report the limitations if records cannot be recovered.
4. **Artifacts and anonymity:** Confirm an accessible, anonymous artifact location and license/availability statement. Remove identifying paths and metadata from the package intended for reviewers.
5. **Final source review:** Check remaining quantitative and literature claims against primary sources and the final PDF. The team-authored domain classifications and model labels still need independent validation.

None of these gates justifies changing observed test outcomes or filling missing metadata by inference.
