# Week 7 Next Step — Pilot 5 Tasks

## Models
- ChatGPT: GPT-5.6 Sol, Medium, Web UI
- Gemini: Gemini 3.8 Flash, Web UI

## Rule
Each task = brand-new chat. Same prompt content for both models. One compile-repair maximum.

## Step A — HE000 only first
1. Open `01_prompts_chatgpt/HE000_generation_prompt.txt`.
2. Open a NEW ChatGPT chat.
3. Select GPT-5.6 Sol + Medium.
4. Paste the prompt exactly.
5. Save the complete raw response.
6. Save the returned Java code without editing.

Then repeat with:
`02_prompts_gemini/HE000_generation_prompt.txt`
in a NEW Gemini 3.8 Flash chat.

## Step B — Compile
Put the generated test into your HumanEval-Java project under:

`src/test/java/humaneval/generated/GeneratedTest_HE000.java`

Run:

```powershell
mvn "-Dtest=humaneval.generated.GeneratedTest_HE000" test
```

Save the entire console output.

## Step C — If compile fails
Use the matching:
`HE000_repair_prompt_TEMPLATE.txt`

Replace:
- `{PASTE_COMPILER_OUTPUT_HERE}` with the exact compiler output
- `{PASTE_GENERATED_TEST_HERE}` with the exact initial generated Java

Send this once in the SAME HE000 chat.
Compile the repaired code once.

Do not repair again.

## Step D — If compile passes but tests fail
Do not repair.
Save the JUnit output.

## Step E — Send HE000 back
Follow `RETURN_TO_CHATGPT.md`.

Only after HE000 is checked should you repeat HE001–HE004.
