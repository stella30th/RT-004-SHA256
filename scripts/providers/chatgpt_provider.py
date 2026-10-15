import os
import json
import urllib.request
import urllib.error
from .base import LLMProvider


class ChatGPTProvider(LLMProvider):
    def __init__(self, model: str = "gpt-4o", temperature: float = 0.0):
        self.model = model
        self.temperature = temperature
        self.api_key = os.environ.get("OPENAI_API_KEY")

    def generate(self, prompt: str) -> str:
        """
        Sends the prompt to OpenAI ChatGPT API and returns the verbatim text response.
        Does not perform silent repairs or modifications.
        """
        if not self.api_key:
            raise ValueError(
                "OPENAI_API_KEY environment variable is not set. "
                "Please set OPENAI_API_KEY before running ChatGPT experiment condition."
            )

        url = "https://api.openai.com/v1/chat/completions"
        
        payload = {
            "model": self.model,
            "temperature": self.temperature,
            "messages": [
                {"role": "user", "content": prompt}
            ]
        }

        req = urllib.request.Request(
            url,
            data=json.dumps(payload).encode("utf-8"),
            headers={
                "Content-Type": "application/json",
                "Authorization": f"Bearer {self.api_key}"
            }
        )

        try:
            with urllib.request.urlopen(req, timeout=45) as resp:
                data = json.loads(resp.read().decode("utf-8"))
                choices = data.get("choices", [])
                if not choices:
                    return ""
                return choices[0].get("message", {}).get("content", "")
        except urllib.error.HTTPError as e:
            err_msg = e.read().decode("utf-8", errors="replace")
            raise RuntimeError(f"OpenAI API request failed ({e.code}): {err_msg}")
        except Exception as e:
            raise RuntimeError(f"Error connecting to OpenAI API: {e}")
