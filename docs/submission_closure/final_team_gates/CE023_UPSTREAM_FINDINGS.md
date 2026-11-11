# CE023 upstream verification — 2026-11-11

## Verified comparison

The official `wLinHoo/ClassEval-T` repository was cloned by the local audit. At inspected upstream commit `fd41d22e6ee2e7497087f4724d4d0a8292ec9491`:

| Source | SHA-256 | Bytes |
|---|---|---:|
| `ClassEval_T/java/solutuon/Classroom.java` | `df08e31ecd6bf6d8cf66c60b126b3fc7b92b3837c04353dde0af2daabfd09ed8` | 1337 |
| `BatchTestTool/java/java automated tester/Test/compile_passed/solutions/Classroom.java` | `3a41626e411b53a534e6e1db07a7aa3c53e556d185bdf155b3a82f17f4bccc7b` | 3578 |

The first file's SHA-256 is identical to the earliest team import `34e1d57:data/ClassEval-T/solution/Classroom.java`. The adjusted team history snapshot (`74b4d0f`, P0/P1 repro, Round 2) has hash `2a3593e97b4707d841754caadc29035e8d43c85d2eb7d89e410784a76fe29095`; the separately observed current working-tree copy has hash `78784d46edd39fa8df3ecfe0714f0641ce0765375bd7a5741b87ee3796240c61`.

The original imported implementation references `ClassroomManagementTest.Course`; the adjusted team version defines a nested `Classroom.Course` and implements `equals`/`hashCode`. These are materially different source snapshots. The upstream commit is an *inspected* revision and has not been established as the generation-time revision. The historical experiment must not be described as using a single unchanged SUT across these mismatched versions.

## Outcome

- Official upstream repository located: **VERIFIED**.
- SHA match of initial team import against one upstream source at the inspected revision: **VERIFIED**.
- Equivalence of modified team SUT to the upstream baseline: **FALSE (different bytes/API)**.
- Actual generation-time upstream revision, prompt session SUT, and matched rerun outcome: **UNVERIFIED**.
- CE023 must be disclosed as an exception; 169/187 and 184/187 are conditional sensitivity arithmetic, not observed full reruns.

Source: local upstream check output and Git-history audit. Final approval of treatment and inclusion criteria is the authors' responsibility.
