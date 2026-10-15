import os
import json
import urllib.request
import urllib.error
from .base import LLMProvider


class GeminiProvider(LLMProvider):
    def __init__(self, model: str = "models/gemini-3.8-flash", temperature: float = 0.0):
        if not model.startswith("models/"):
            model = f"models/{model}"
        self.model = model
        self.temperature = temperature
        self.api_key = os.environ.get("GEMINI_API_KEY")

    def generate(self, prompt: str) -> str:
        """
        Sends the prompt to Google Gemini API and returns the verbatim text response.
        Does not perform silent repairs or modifications.
        """
        if not self.api_key:
            raise ValueError(
                "GEMINI_API_KEY environment variable is not set. "
                "Please set GEMINI_API_KEY before running the experiment."
            )

        url = f"https://generativelanguage.googleapis.com/v1beta/{self.model}:generateContent?key={self.api_key}"
        
        payload = {
            "contents": [
                {
                    "parts": [{"text": prompt}]
                }
            ],
            "generationConfig": {
                "temperature": self.temperature,
            }
        }

        req = urllib.request.Request(
            url,
            data=json.dumps(payload).encode("utf-8"),
            headers={"Content-Type": "application/json"}
        )

        try:
            with urllib.request.urlopen(req, timeout=45) as resp:
                data = json.loads(resp.read().decode("utf-8"))
                candidates = data.get("candidates", [])
                if not candidates:
                    return ""
                parts = candidates[0].get("content", {}).get("parts", [])
                if not parts:
                    return ""
                return parts[0].get("text", "")
        except urllib.error.HTTPError as e:
            err_msg = e.read().decode("utf-8", errors="replace")
            raise RuntimeError(f"Gemini API request failed ({e.code}): {err_msg}")
        except Exception as e:
            raise RuntimeError(f"Error connecting to Gemini API: {e}")
