# CE023 — Git-history source discrepancy verification

## Scope
This document compares two historical **team-repository** versions of `Classroom.java`. It does not certify either version as the official upstream ClassEval-T release.

## Evidence

| Version | Commit | SHA-256 | Bytes |
|---|---|---|---:|
| Initial import | `34e1d57` | `df08e31ecd6bf6d8cf66c60b126b3fc7b92b3837c04353dde0af2daabfd09ed8` | 1337 |
| Adjusted `solution_human` | `74b4d0f` | `2a3593e97b4707d841754caadc29035e8d43c85d2eb7d89e410784a76fe29095` | 2174 |
| P0 reproduction | `74665e4` | `2a3593e97b4707d841754caadc29035e8d43c85d2eb7d89e410784a76fe29095` | 2174 |
| P1 reproduction | `74665e4` | `2a3593e97b4707d841754caadc29035e8d43c85d2eb7d89e410784a76fe29095` | 2174 |
| Round 2 snapshot | `c9542f8` | `2a3593e97b4707d841754caadc29035e8d43c85d2eb7d89e410784a76fe29095` | 2174 |

The original source references `ClassroomManagementTest.Course`. The adjusted source defines `Classroom.Course` as a static nested class and provides its constructor, equality and hash behavior. These are material Java API changes, not line-ending normalization. Consult `CE023_CONTENT_DIFF.patch`.

## Interpretation
CE023's initial imported source is not byte-identical to the adjusted source used for reproduction and Round 2. P0/P1 claims about CE023 should specify which snapshot and test source were used. Do not reclassify its original outcome as model-attributed failure or success without matched-SUT execution records.

### Sensitivity analysis (conditional arithmetic, NOT an observed rerun)
- If an adjusted baseline counts CE023 as one successful pair among 170/188, excluding it gives **169/187**.
- If an adjusted carry-forward count includes the same one successful pair among 185/188, excluding it gives **184/187**.
- The focused recovery figure **15/18** remains unchanged only if CE023 was not part of the 18 eligible failing pairs.
- These are bookkeeping sensitivity scenarios; they are not evidence that 187 tests were rerun on a fixed upstream snapshot.

## Claims not verifiable from the checked Git history
1. Whether `34e1d57` is identical to a specific official upstream ClassEval-T commit.
2. The exact original generation-time SUT snapshot for each CE023 prompt.
3. Whether changed CE023 test outcomes were caused by source adjustment, test regeneration, or both, absent matched execution provenance.

## Recommended manuscript wording
> CE023 is treated as a source-snapshot discrepancy. The earliest available team-repository import (commit 34e1d57) differs from the subsequently adopted Classroom implementation (commit 74b4d0f) in both hash and public type structure. In particular, the earlier implementation depends on ClassroomManagementTest.Course, whereas the adjusted version defines Classroom.Course. Therefore, CE023 results on the adjusted source are not used as evidence of an independently matched P0/P1 comparison under unchanged program input. We report a sensitivity analysis excluding this pair and retain the historical adjusted figures solely for reconciliation with earlier reports. The mapping to the official upstream revision remains unverified.

## Verification disposition
- Team-history SHA-256 discrepancy: **VERIFIED** if all `expected_hash_match` values are true.
- API adjustment: **VERIFIED** from Git-content diff.
- Official upstream identity: **NOT VERIFIED**.
- Submission readiness: **BLOCKED** until author declarations, artifact access, protocol status and applicable submission requirements are confirmed.
