from pathlib import Path
import shutil
import argparse


ROOT = Path(__file__).resolve().parents[1]

TEMPLATE_DIR = ROOT / "workspace-template"
WORKSPACES_DIR = ROOT / "workspaces"


def create_workspace(problem_id, solution_file, test_file):
    solution_file = Path(solution_file).resolve()
    test_file = Path(test_file).resolve()

    if not TEMPLATE_DIR.exists():
        raise FileNotFoundError(
            f"Workspace template not found: {TEMPLATE_DIR}"
        )

    if not solution_file.exists():
        raise FileNotFoundError(
            f"Solution file not found: {solution_file}"
        )

    if not test_file.exists():
        raise FileNotFoundError(
            f"Test file not found: {test_file}"
        )

    workspace = WORKSPACES_DIR / problem_id

    if workspace.exists():
        shutil.rmtree(workspace)

    shutil.copytree(
        TEMPLATE_DIR,
        workspace
    )

    main_java = (
        workspace
        / "src"
        / "main"
        / "java"
        / problem_id
    )

    test_java = (
        workspace
        / "src"
        / "test"
        / "java"
        / problem_id
    )

    main_java.mkdir(
        parents=True,
        exist_ok=True
    )

    test_java.mkdir(
        parents=True,
        exist_ok=True
    )

    shutil.copy2(
        solution_file,
        main_java / "Solution.java"
    )

    shutil.copy2(
        test_file,
        test_java / "SolutionTest.java"
    )

    print()
    print("[OK] Workspace created")
    print(f"Problem   : {problem_id}")
    print(f"Workspace : {workspace}")
    print(f"Solution  : {solution_file}")
    print(f"Test      : {test_file}")


def main():
    parser = argparse.ArgumentParser()

    parser.add_argument(
        "--problem",
        required=True
    )

    parser.add_argument(
        "--solution",
        required=True
    )

    parser.add_argument(
        "--test",
        required=True
    )

    args = parser.parse_args()

    create_workspace(
        args.problem,
        args.solution,
        args.test
    )


if __name__ == "__main__":
    main()