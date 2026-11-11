# CE023: upstream, team versions and denominator policy

- Official upstream repository: https://github.com/wLinHoo/ClassEval-T
- Inspected upstream revision: `fd41d22e6ee2e7497087f4724d4d0a8292ec9491` (NOT verified as generation-time revision).
- `ClassEval_T/java/solutuon/Classroom.java`: SHA-256 `df08e31ecd6bf6d8cf66c60b126b3fc7b92b3837c04353dde0af2daabfd09ed8`; 1337 bytes. MATCHES team's initial import `34e1d57`.
- Other upstream candidate `BatchTestTool/java/java automated tester/Test/compile_passed/solutions/Classroom.java`: SHA-256 `3a41626e411b53a534e6e1db07a7aa3c53e556d185bdf155b3a82f17f4bccc7b`; 3578 bytes.
- Team adjusted `solution_human` in Git commit `74b4d0f`: SHA-256 `2a3593e97b4707d841754caadc29035e8d43c85d2eb7d89e410784a76fe29095`; 2174 bytes. API changed from `ClassroomManagementTest.Course` to nested `Classroom.Course`; equality/hashCode added.
- Working copy reported by user: SHA-256 `78784d46edd39fa8df3ecfe0714f0641ce0765375bd7a5741b87ee3796240c61`; it is a DISTINCT snapshot that must not be silently equated with the historical version.

## Analysis bookkeeping

Author choice: retain 188 model-task pairs in the *historical adjusted register*, with an explicit warning that Gemini CE023 was normalized/edited, and show 187 matched-source pairs in the sensitivity cohort. Report the historical 170/188 baseline and 185/188 carry-forward only as an **adjusted accounting scenario**, not 188 observed same-input comparisons. The defensible matched-source sensitivity removes CE023: 169/187 baseline PASS and 184/187 carry-forward; focused 15/18 recovery presumes CE023 is outside 18 eligible pairs.

Any claim that the exact upstream commit was used at generation time requires original session/prompt provenance. Avoid treating the difference as just line-ending normalization.
