---
name: pr-creation
description: Creates a PR and writes pull request descriptions. Use when creating a PR, writing a PR, or when the user asks to summarize changes for a pull request.
---

When writing a PR description:

1. Run `git diff main...HEAD` to see all changes on this branch
2. Write a description following this format:

## What
One sentence explaining what this PR does.

## Why
Brief context on why this change is needed

## Changes
- Bullet points of specific changes made
- Group related changes together
- Mention any files deleted or renamed

The description should not be longer than 5 lines. 

3. The PR should diff against the branch "claude-code" and not against "main" or "master". This is because the "claude-code" branch is where all code changes are merged before being merged into main.