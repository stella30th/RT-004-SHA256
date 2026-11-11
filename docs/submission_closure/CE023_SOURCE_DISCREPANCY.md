> **Historical snapshot warning (2026-11-11):** This earlier document and `CE023_source_hashes.csv` report hash `78784d46...` from a separately supplied project ZIP. The verified local Git-history audit instead finds initial import `df08e31e...` and adjusted snapshot `2a3593e9...`. These are different captured versions/inputs. The two hash inventories must not be treated as a single immutable snapshot. For commit-verified CE023 claims, refer to `ce023_history_verified/CE023_SOURCE_DISCREPANCY_VERIFIED.md` and `CE023_HASH_MANIFEST.csv`. Official upstream identity remains unverified.
# CE023: source identity, prompt mismatch, and cohort treatment

## Verified evidence from project snapshot (11 November 2026)

All five `Classroom.java` files present in the submitted project archive have the same **byte-for-byte** SHA-256:

`78784d46edd39fa8df3ecfe0714f0641ce0765375bd7a5741b87ee3796240c61`

The five locations are enumerated in `CE023_source_hashes.csv`: `data/ClassEval-T/solution_human`, `workspace-template/src/main`, the two independent `ce023-repro` folders, and the round-2 mutation workspace. This confirms **internal snapshot consistency only**, not identity with upstream ClassEval-T's original source at a particular commit.

The archived Gemini P0 test refers to `ClassroomManagementTest.Course`, whereas the supplied Java SUT exposes `Classroom.Course`. The reproduction P0 Maven log fails compilation with `package ClassroomManagementTest does not exist`. The P1 test instead uses `Classroom.Course` and the P1 reproduction advances past compilation. This is evidence of an **input/API identity mismatch**, not evidence that a matched-source P0 generation succeeded.

## Upstream comparison — NOT VERIFIED

The team reports that CE023 production code was modified relative to the original benchmark repository, leading to a SHA-256 mismatch. An upstream file at a **specified repository commit** has not been included in the submitted archive and was not byte-for-byte verified in this audit. Therefore do not claim the above hash matches upstream. Before making that claim, preserve:

- upstream repository URL and immutable commit SHA;
- exact upstream Java source file path, its SHA-256 and license;
- project snapshot SHA-256 from the table;
- unified diff (with semantics reviewed), whether modifications affect API/behavior;
- original P0 prompt/test and rerun status against each version.

Use `CHECK_CE023_UPSTREAM.ps1` from the patch to compare a locally supplied upstream `Classroom.java` against this project file. Do not silently replace the evaluated SUT with upstream code or overwrite historical outputs.

## Paper-ready interpretation (matched-source primary analysis)

> Gemini CE023 was excluded from the matched-source primary cohort because the archived baseline generated test targeted `ClassroomManagementTest.Course`, while the evaluated SUT defines `Classroom.Course`. In addition, the research copy of CE023 may differ from the upstream benchmark version; the supplied archive establishes internal identity of the local SUT copies but does not provide a pinned upstream file against which to validate that discrepancy. The successful corrected-source P1 rerun is therefore not a paired P0-to-P1 recovery under identical inputs. We retain the excluded case and its logs for audit and report the earlier adjusted 188-pair calculation only as a sensitivity scenario, rather than treating the correction as an observed P0 success.

## Denominator consequences

- Primary matched-source cohort: **187** pairs, P0 PASS **169/187**, selected P1 recoveries **15/18**, carry-forward **184/187**.
- Historical adjusted scenario: **188** pairs, P0 PASS **170/188**, selected P1 recoveries **15/18**, carry-forward **185/188**; **not** a matched-source observed experiment.
- This discrepancy does not justify changing the recorded JUnit/PIT results or generating a retroactive matching P0 sample.

## Reviewer evidence

- `workspace-template/src/tests-gemini-p0/java/org/GeneratedTest_CE023.java`
- `workspace-template/src/tests-gemini-p1/java/org/GeneratedTest_CE023.java`
- `ho-le-thien-an/oapr-analysis/ce023-repro-p0/rerun.log`
- `ho-le-thien-an/oapr-analysis/ce023-repro-p1/rerun.log`
- `paper/icst_nier_draft.tex` and `paper/submission_readiness_2026-11-10.md`

Original dataset repository: https://github.com/wLinHoo/ClassEval-T — this link is an upstream identification, **not** proof of the source file hash or pinned version.

