# Experiment Protocol

## Experimental Unit
One HumanEval problem.

## Conditions
1. Human baseline
2. ChatGPT
3. Gemini

## Frozen Inputs
- Dataset/version
- 30 selected problem IDs
- Java 17, JUnit Jupiter 5.10.2 and Maven Surefire 3.2.5 environment
- Prompt version
- Model identifiers
- Temperature and generation parameters
- Compile/run timeout
- Repair policy
- Category taxonomy

## AI Generation Procedure
For each problem and model:

1. Load problem statement and target method signature.
2. Send the frozen structured prompt.
3. Save raw response verbatim.
4. Extract generated JUnit test code.
5. Compile.
6. If compilation fails:
   - save compiler error;
   - send exactly one repair prompt containing compiler feedback;
   - save repair response;
   - compile again.
7. If compilation succeeds, execute tests.
8. Record all outcomes.

## Prohibited Actions
- Manual editing of AI-generated tests.
- Multiple repair retries.
- Per-problem prompt changes.
- Deleting failed/empty outputs.
- Re-running until a preferred answer appears.
- Changing metrics/thresholds after observing full results.

## Primary Outcome
```text
success = 1 if generated JUnit suite compiles and executes successfully
success = 0 otherwise
```

## Secondary Outcomes
- compile_success
- execution_success
- repair_needed
- repair_success
- failure_stage
- task_category

## Pilot
Use 5-10 problems only to validate scripts, environment, schema and thresholds.

## Full Experiment
Run only after the protocol is frozen.
