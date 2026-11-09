from pathlib import Path


def load_prompt_template(path: str) -> str:
    return Path(path).read_text(encoding="utf-8")


def build_generation_prompt(template: str, problem: dict) -> str:
    return (
        template
        .replace("{{problem_id}}", problem.get("problem_id", ""))
        .replace("{{prompt}}", problem.get("prompt", ""))
        .replace("{{target_signature}}", problem.get("target_signature", ""))
    )


def build_repair_prompt(original_prompt: str, generated_code: str, compiler_error: str) -> str:
    return f"""
The previously generated JUnit test did not compile.

Original task:
{original_prompt}

Generated test:
{generated_code}

Compiler feedback:
{compiler_error}

Repair the JUnit test using the compiler feedback.
Return the corrected JUnit test code only.
""".strip()
