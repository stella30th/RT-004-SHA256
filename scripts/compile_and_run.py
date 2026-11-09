from dataclasses import dataclass
from pathlib import Path
import subprocess


@dataclass
class CommandResult:
    success: bool
    returncode: int
    stdout: str
    stderr: str


def run_command(command: list[str], cwd: str | Path, timeout: int) -> CommandResult:
    try:
        completed = subprocess.run(
            command,
            cwd=str(cwd),
            capture_output=True,
            text=True,
            timeout=timeout,
            check=False,
            shell=True,
        )
        return CommandResult(
            success=completed.returncode == 0,
            returncode=completed.returncode,
            stdout=completed.stdout,
            stderr=completed.stderr,
        )
    except subprocess.TimeoutExpired as exc:
        return CommandResult(
            success=False,
            returncode=-1,
            stdout=exc.stdout or "",
            stderr=f"TIMEOUT after {timeout}s\n{exc.stderr or ''}",
        )


def compile_project(project_dir: str | Path, timeout: int = 30) -> CommandResult:
    return run_command(["mvn", "-q", "-DskipTests", "test-compile"], project_dir, timeout)


def run_tests(project_dir: str | Path, timeout: int = 30) -> CommandResult:
    return run_command(["mvn", "-q", "test"], project_dir, timeout)
