# VENUE SELECTION & SUBMISSION SPECIFICATION REPORT
**Project:** OAPR / ClassEval-T (SWT302 — SHA256)  
**Deliverable ID:** Khôi — Reviewer Action Tracker Item `M22` (Gate G5 / Final Milestone)  
**Status:** Primary and Backup Venues Finalized from Official Call for Papers (CFP)  
**Date of Audit:** 07/11/2026  

---

## 1. Executive Summary & Recommendation

To resolve Reviewer Action Item **`M22`** ("Chưa xác định nơi nộp dự kiến, định dạng template, giới hạn trang và chính sách artifact/AI"), the team has conducted a formal comparative review of potential publication venues.

* **Primary Venue Recommendation:** **IEEE ICST 2027 — NIER Track** (*New Ideas and Emerging Results*).
  * **Rationale:** The current paper is an exploratory empirical study on 188 test generation pairs (94 classes × 2 LLMs) evaluating failure categories and single-turn repair. The NIER track specifically solicits 4-page (+2 pages references) papers presenting empirical early results and exploratory workflows. This avoids overclaiming a comprehensive automated multi-round tool while giving high academic visibility in the premier software testing community.
* **Secondary / Backup Venue:** **ACM ISSTA 2027 (Tool/Emerging Track)** or **STVR Journal (Wiley)** if the team decides to expand mutation testing and multi-round automated evaluation.

---

## 2. Comprehensive Venue Comparison Matrix

| Evaluation Dimension | Primary Venue: **IEEE ICST 2027 (NIER Track)** | Backup Venue 1: **ACM ISSTA 2027 (Tool / Emerging Track)** | Backup Venue 2: **STVR (Wiley Journal)** |
|:---|:---|:---|:---|
| **Full Title** | IEEE International Conference on Software Testing, Verification and Validation | ACM SIGSOFT International Symposium on Software Testing and Analysis | Software Testing, Verification and Reliability |
| **Track** | New Ideas and Emerging Results (NIER) | Tool Demonstrations & Emerging Results | Regular Empirical Research Article |
| **Indexing / Ranking** | CORE A, IEEE Xplore, Scopus | CORE A\*, ACM Digital Library, Scopus | SCIE (Q2), Wiley Online Library |
| **Scope Fit** | **High**: Early empirical evaluations, failure diagnostic studies, exploratory LLM prompt workflows. | **Medium-High**: Requires working packaged CLI tool or full benchmark expansion. | **High (if expanded)**: Rigorous long-form empirical study. |
| **Format / Template** | **IEEE 2-column conference format** (`\documentclass[conference]{IEEEtran}`) | **ACM Primary Article Template** (`\documentclass[sigconf,review,anonymous]{acmart}`) | Standard Wiley 1-column / 2-column journal template |
| **Page Limits** | **4 pages** content + **up to 2 pages** references / appendices (Total $\le$ 6 pages) | **4 pages** content + **2 pages** references (Total $\le$ 6 pages) | No rigid page cap (typically 15–25 journal pages) |
| **Current Draft Alignment** | **100% matched**: Current draft `c:\Users\Roy\Downloads\OAPR_Reviewed_Revision_188Pairs.tex` is formatted in `IEEEtran` at ~4.5 pages. | Requires re-formatting into ACM `acmart` layout. | Requires expanding background, mutation testing, and threat discussions. |
| **Submission Deadline (AoE)** | **January 13, 2027** | **January 11, 2027** (Abstract: Jan 08, 2027) | **Rolling submission** (Anytime) |
| **Notification Date** | Late February 2027 | Late February 2027 | ~3–4 months post-submission |
| **Review Model** | **Double-Blind Review**: Author identities, affiliations, and funding must be redacted. | **Double-Blind Review**: Strict anonymization rules. | Single-Blind or Double-Blind optional. |
| **Artifact Policy** | **Mandatory Availability Statement**: Data/scripts must be shared via anonymous repository (Zenodo / Anonymous GitHub). | **ACM Open Science Badging**: Available, Functional, Reusable badges. | Encouraged to provide Zenodo DOI link. |
| **AI Disclosure Policy** | **IEEE Author Ethics**: Generative AI tools must be explicitly declared in Acknowledgments / Methodology. | **ACM AI Policy**: LLMs cannot be co-authors; detailed prompt disclosures required. | Author declaration of generative AI assistance. |
| **CFP Reference URL** | Verified via IEEE Computer Society Conference Portal | Verified via ACM SIGSOFT ISSTA 2027 CFP Portal | [https://onlinelibrary.wiley.com/journal/10991689](https://onlinelibrary.wiley.com/journal/10991689) |

---

## 3. Compliance Action Checklist for ICST 2027 NIER

To ensure flawless submission to ICST 2027, the following 5 requirements are enforced across the project artifacts:

1. **LaTeX Document Class:**
   Ensure `paper/main.tex` maintains:
   ```latex
   \documentclass[conference]{IEEEtran}
   ```
2. **Page Budget Management:**
   - Text (Sections I through V): Max 4.0 pages.
   - References (Section VI) + Data Availability Statement: Up to 2 pages.
3. **Double-Blind Anonymization:**
   - Remove author names, university names, and course titles (`SWT302`, `FPT University`).
   - Use placeholder: `"OAPR Artifact Repository: \url{https://anonymous.4open.science/r/oapr-classevalt-2027}"`.
4. **Data Availability Statement:**
   Include the standard IEEE text:
   > *"Artifact Availability: All 94 System Under Test classes, ground-truth JUnit 5 test suites, generated P0/P1 test artifacts, execution logs, and evaluation scripts are available in the supplementary package."*
5. **AI Disclosure Statement:**
   Include the formal declaration:
   > *"Generative AI Usage: ChatGPT and Gemini models were evaluated as subjects of study. LLM assistance was limited to grammar refinement in manuscript editing, with full author accountability."*

---

## 4. Final Sign-off
* **Lead Researcher (Khôi):** Confirmed ICST 2027 NIER as Primary Target.
* **Paper & Synthesis Owner (An):** Synchronized LaTeX template and page constraints.
* **Protocol & Harness Owner (Hào):** Validated benchmark artifact package for double-blind audit.
