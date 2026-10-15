import os
import json
import time
import urllib.request
import urllib.error
from .base import LLMProvider


class GeminiProvider(LLMProvider):
    def __init__(self, model: str = "models/gemini-3.8-flash", temperature: float = 0.0, max_retries: int = 3, timeout: int = 60):
        if not model.startswith("models/"):
            model = f"models/{model}"
        self.model = model
        self.temperature = temperature
        self.max_retries = max_retries
        self.timeout = timeout
        self.api_key = os.environ.get("GEMINI_API_KEY")

    def generate(self, prompt: str) -> str:
        """
        Sends the prompt to Google Gemini API and returns the verbatim text response.
        Includes automatic retries for transient 503 Service Unavailable or network timeouts.
        Does not perform silent repairs or modifications of test logic.
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

        req_data = json.dumps(payload).encode("utf-8")
        headers = {
            "Content-Type": "application/json",
            "Connection": "close"
        }

        last_error = None
        for attempt in range(1, self.max_retries + 1):
            req = urllib.request.Request(url, data=req_data, headers=headers)
            try:
                with urllib.request.urlopen(req, timeout=self.timeout) as resp:
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
                last_error = f"HTTP {e.code}: {err_msg}"
                if e.code in (429, 500, 502, 503, 504) and attempt < self.max_retries:
                    wait_time = attempt * 3
                    print(f"      [!] Gemini API returned {e.code} (Service Unavailable / Rate Limited). Retrying in {wait_time}s (Attempt {attempt}/{self.max_retries})...")
                    time.sleep(wait_time)
                    continue
                raise RuntimeError(f"Gemini API request failed ({e.code}): {err_msg}")
            except (TimeoutError, urllib.error.URLError) as e:
                last_error = str(e)
                if attempt < self.max_retries:
                    wait_time = attempt * 3
                    print(f"      [!] Gemini API connection timed out. Retrying in {wait_time}s (Attempt {attempt}/{self.max_retries})...")
                    time.sleep(wait_time)
                    continue
                raise RuntimeError(
                    f"Gemini API network timeout after {self.max_retries} attempts: {e}. "
                    "Google Gemini server is currently experiencing high load or connectivity issues. Please try again in a few moments."
                )
            except Exception as e:
                raise RuntimeError(f"Error connecting to Gemini API: {e}")

        raise RuntimeError(f"Failed to get response from Gemini after {self.max_retries} attempts. Last error: {last_error}")
