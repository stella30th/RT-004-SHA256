# CE023: resolved snapshot identity, not generation-time provenance

Official upstream inspected: `https://github.com/wLinHoo/ClassEval-T`, commit `fd41d22e6ee2e7497087f4724d4d0a8292ec9491`.

| Source | SHA-256 | Interpretation |
|---|---|---|
| Upstream `ClassEval_T/java/solutuon/Classroom.java` | `df08e31ecd6bf6d8cf66c60b126b3fc7b92b3837c04353dde0af2daabfd09ed8` | Byte-identical to team's earliest import at `34e1d57` |
| Upstream `BatchTestTool/java/java automated tester/Test/compile_passed/solutions/Classroom.java` | `3a41626e411b53a534e6e1db07a7aa3c53e556d185bdf155b3a82f17f4bccc7b` | Second upstream candidate; do not conflate |
| Team history adjusted `solution_human` at `74b4d0f` | `2a3593e97b4707d841754caadc29035e8d43c85d2eb7d89e410784a76fe29095` | Material API adjustment (`Classroom.Course`, `equals`, `hashCode`) |
| User's present working-tree check | `78784d46edd39fa8df3ecfe0714f0641ce0765375bd7a5741b87ee3796240c61` | Separate snapshot, must not replace commit hashes |

**Validated claim:** upstream commit snapshot matches early team import, and the study's adjusted SUT differs in Java API. **Unvalidated claim:** this upstream commit was necessarily the model's generation-time input. An adjusted-input P1 pass cannot be counted as a matched-input P0/P1 recovery without an original matched-input P0 execution. Exclude CE023 from the matched-source 187-pair analysis; keep 188-pair historical adjusted figures labeled sensitivity arithmetic, not newly observed data.
