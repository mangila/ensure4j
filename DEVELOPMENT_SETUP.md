# Development Setup

## Mise Setup

Install <https://mise.jdx.dev/getting-started.html>

Trust this directory

```bash
mise trust
```

Install mise stuffs

```bash
mise install
```

## Pre commit Setup

- <https://pre-commit.com/>

Create a virtual environment in the root of this project (for convenience)

Install the `requirements.txt` file.

```text
pip install -r requirements.txt
```

Set up pre-commit hooks.

```bash
pre-commit install
```

## Agent Setup

There is support for JetBrains agent Junie to do some vibe coding for the project.

The `.junie/guidelines.md` file contains the instructions for the agent context.
