# CE023: upstream, modified SUT, and accounting disposition

**Approved treatment reported by project lead:** Retain CE023 in the historical 188-pair accounting; explicitly disclose the modified source and show an exclusion sensitivity analysis. The 188-pair adjusted accounting is not a matched-source experiment.

| Origin/snapshot | SHA-256 | Evidence |
|---|---|---|
| Upstream `wLinHoo/ClassEval-T` commit `fd41d22e6ee2e7497087f4724d4d0a8292ec9491`, `ClassEval_T/java/solutuon/Classroom.java` | `df08e31ecd6bf6d8cf66c60b126b3fc7b92b3837c04353dde0af2daabfd09ed8` | Upstream check reported by local `CHECK_UPSTREAM_CE023.ps1` execution |
| Upstream alternate `BatchTestTool/java/java automated tester/Test/compile_passed/solutions/Classroom.java` (same upstream commit) | `3a41626e411b53a534e6e1db07a7aa3c53e556d185bdf155b3a82f17f4bccc7b` | Separate upstream source; do not conflate with benchmark candidate |
| Team import commit `34e1d57` | `df08e31ecd6bf6d8cf66c60b126b3fc7b92b3837c04353dde0af2daabfd09ed8` | Historical Git object |
| Team adjusted `solution_human`, commit `74b4d0f` | `2a3593e97b4707d841754caadc29035e8d43c85d2eb7d89e410784a76fe29095` | Historical Git object, altered nested `Course` API |
| Separately supplied working-tree snapshot | `78784d46edd39fa8df3ecfe0714f0641ce0765375bd7a5741b87ee3796240c61` | Local working-tree inspection; exact origin not established |

A byte-identical match between the team's earliest import and an upstream file at an inspected commit is demonstrated; it is **not proof that the model was shown that same file during the original generation**. Both modified source and test generation could influence pass/fail outcomes.

Historical adjusted: baseline `170/188`; carry-forward `185/188`; targeted P1 recovery `15/18`. Sensitivity excluding the one CE023 adjusted PASS: baseline `169/187`, carry-forward `184/187`, targeted `15/18` **only if CE023 was not part of those 18 eligible failures**. This is a conditional bookkeeping exclusion, not a matched 187-pair rerun. The paper must label both analyses unambiguously.

### Manuscript paragraph (candidate)
The historical register contains 188 reported model–task pairs, with 170 baseline passes and 185 carry-forward successes after focused regeneration. One pair, Gemini CE023, involves a source-snapshot discrepancy: the earliest team import is byte-identical to an inspected upstream Classroom.java, whereas the subsequent experiment implementation changes the Course type and associated API. As the archived generation-time source presented to the model has not been independently reconciled to its execution-time source, the historical inclusion of CE023 is reported as adjusted accounting rather than a matched-input comparison. Excluding the adjusted CE023 pass yields a sensitivity count of 169/187 baseline successes and 184/187 carry-forward successes; these are derived counts, not independent reruns. The 15/18 recovery denominator is maintained only for the documented distinct eligible-failure cohort.
