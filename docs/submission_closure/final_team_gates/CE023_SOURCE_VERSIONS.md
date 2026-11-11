# CE023 source identity — evidence and limitations

Three **distinct** snapshots appear in submitted evidence:

| Snapshot | SHA-256 | Evidence meaning |
|---|---|---|
| Team first import, commit `34e1d57` | `df08e31ecd6bf6d8cf66c60b126b3fc7b92b3837c04353dde0af2daabfd09ed8` | Earlier team Git snapshot |
| Adjusted team Git snapshot, commit `74b4d0f`, reproduced at `74665e4` and Round 2 `c9542f8` | `2a3593e97b4707d841754caadc29035e8d43c85d2eb7d89e410784a76fe29095` | Git object, not necessarily same as current worktree |
| Uploaded/current working tree `solution_human/Classroom.java` (11 Nov 2026) | `78784d46edd39fa8df3ecfe0714f0641ce0765375bd7a5741b87ee3796240c61` | A third source snapshot, 2,261 bytes in uploaded ZIP |

First import referenced `ClassroomManagementTest.Course`; adjusted Git source defines a static nested `Classroom.Course` with constructor, `equals` and `hashCode`. This is a **material API difference**. The later current-worktree source differs in bytes even from the adjusted Git version; determine semantic differences by diff, not hash alone.

Authoritative upstream project: https://github.com/wLinHoo/ClassEval-T. Its actual source path and immutable revision must be located and compared. Even a byte-match to **current upstream** cannot prove that revision was supplied in the original LLM generation sessions. The actual historical generation-time input remains UNVERIFIED without session/prompt evidence.

For sensitivity, exclusion of one historically counted CE023 PASS gives 169/187 baseline and 184/187 carried-forward **only under the explicitly stated conditional assumptions**. The 15/18 recovery figure is unchanged only if CE023 was excluded from the 18 repair cohort. These are bookkeeping calculations, not re-execution.
