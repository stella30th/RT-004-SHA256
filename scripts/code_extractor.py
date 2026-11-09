import re


def extract_java_code(raw_response: str) -> str:
    """
    Extracts Java code from LLM response.
    Handles markdown code blocks (```java ... ``` or ``` ... ```) or returns raw response.
    Preserves code verbatim without manual modification of test logic.
    """
    if not raw_response:
        return ""

    # Check for ```java ... ``` block
    java_block = re.search(r"```(?:java)?\s*(.*?)```", raw_response, re.DOTALL | re.IGNORECASE)
    if java_block:
        return java_block.group(1).strip()

    # If no markdown fences found, return trimmed raw output
    return raw_response.strip()
