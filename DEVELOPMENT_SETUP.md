# Development Setup

This is optional but mostly for local development.

To speed up the development process and quality assurance.

## Pre commit Setup

- https://pre-commit.com/

Create a virtual environment for the project in the root directory.

Install the pre-commit package.

Set up pre-commit hooks.

`pre-commit install`

Create a `.pre-commit-config.yaml` file in the root directory.

Change `.pre-commit-config.yaml` to run the maven wrapper after the OS-specific commands.

This is an example of the file for *Unix env:

```yaml
fail_fast: false
repos:
  - repo: local
    hooks:
      - id: lib-dir-test
        name: Test ensure4j
        entry: ./mvnw
        args: [ "-f", "lib/pom.xml", "clean", "test" ]
        language: system
        files: ^lib/src
        require_serial: true
        pass_filenames: false
```

## Agent Setup

There is support for JetBrains agent Junie to do some vibe coding for the project.

The `.junie/guidelines.md` file contains the instructions for the agent context.

It is recommended to use the JetBrains agent for the project.