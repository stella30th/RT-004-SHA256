# Change log and methodological decisions needed

## Applied consistently to every A/B/C prompt
1. Removed the draft's explanatory preamble and entire 94-task catalog, evaluation instructions, model labels and same-chat/CONTINUE control protocol; these belong in experiment documentation, not the Gemini request.
2. Kept all baseline behavioral-test rules relevant to a single task, except the explicit P0-versus-P1 methodological prohibition line (which contradicted adding the B/C checklist).
3. Retained the task metadata, API hints, task specification and verbatim Java TARGET_SOURCE from the uploaded draft.
4. Standardized task block Java 11 requirement to Java 17, consistent with the archived global Java 17 environment, across all arms.
5. Defined one single-task, exact BEGIN/END test-class output protocol, shared across all arms.
6. Converted accidental literal backslash-n sequences in B/C checklist additions to real newlines, without changing the checklist's nine item statements.
7. Used the draft's proposed task focus for C only, not a corrected expected value. Focus statements require independent human verification against P0 failure logs.
8. New per-task baseline is designated A_baseline rather than 'exact P0 resample'.

## Scientific caveats / mandatory group decisions before first formal run
- The historical P0 was 94 tasks in one continuing chat. The new one-task baseline removes previous-task context, so comparison A/B/C is prospective and internally comparable but is NOT byte-accurate reproduction of historical P0. Record this deviation in protocol_v2 and paper; do not claim exact historical baseline matching.
- Verify freeze of production source SHA-256, Maven pom.xml, Java 17/JUnit 4.13.2 harness; verify all task focus texts.
- Freeze model identity/interface/time, fresh-session policy, order randomization/rotation, identical output budget, invalid/truncated output rules, extraction algorithm, retry/infra-error policy.
- Obtain agreement from protocol owner and team before data collection. If historical exact-resample is mandatory under a reviewer requirement, reject this adapted design and replay the original full-master protocol instead.
- The original master requirements say no explanation between files; adapted output requires one Java class with BEGIN/END markers and no Markdown. Check local parser supports these exact markers before pilot.
- 6 pilot generations (1 per arm × 2 tasks) is an OPTIONAL pilot suggestion, not a mandated protocol sample size. Keep their outputs separate unless inclusion was declared in advance.
