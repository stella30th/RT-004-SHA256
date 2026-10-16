# What to send back after each task

For HE000 first, send:

## ChatGPT
- complete raw response text
- generated Java code exactly as returned
- compile console output
- if compile failed: repaired response + second compile output
- if compile passed: JUnit output

## Gemini
- same five items

Do NOT:
- manually fix the Java code
- add imports
- rename classes
- change assertions
- retry more than once after compile failure

You can either upload the saved files or paste into chat using:

```text
===== HE000 CHATGPT RAW RESPONSE =====
...

===== HE000 CHATGPT COMPILE OUTPUT =====
...

===== HE000 CHATGPT REPAIR RESPONSE =====
... only if repair was needed ...

===== HE000 CHATGPT JUNIT OUTPUT =====
...

===== HE000 GEMINI RAW RESPONSE =====
...

===== HE000 GEMINI COMPILE OUTPUT =====
...

===== HE000 GEMINI REPAIR RESPONSE =====
... only if repair was needed ...

===== HE000 GEMINI JUNIT OUTPUT =====
...
```

After HE000 is audited, repeat HE001–HE004.
