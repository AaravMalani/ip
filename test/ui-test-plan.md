# UI Test Plan

## Test case 1: Add and list a todo task

Aim: Verify that a todo task is added and displayed with its number, type icon, and unmarked icon.

Inputs:

```text
todo read book
list
bye
```

Expected output pattern:

```text
(Any Arthur Dent quote)

added:
[T][ ] read book

(Any Arthur Dent quote)

1. [T][ ] read book

So long, and thanks for all the fish.
```

## Test case 2: Mark and unmark a task

Aim: Verify that marking and unmarking update the displayed task state.

Inputs:

```text
todo submit assignment
mark 1
unmark 1
list
bye
```

Expected output pattern:

```text
(Any Arthur Dent quote)

added:
[T][ ] submit assignment

(Any Arthur Dent quote)

marked:
[T][X] submit assignment

(Any Arthur Dent quote)

unmarked:
[T][ ] submit assignment

(Any Arthur Dent quote)

1. [T][ ] submit assignment

So long, and thanks for all the fish.
```

## Test case 3: Add and list deadline and event tasks

Aim: Verify that deadline and event tasks preserve their dates and type icons.

Inputs:

```text
deadline submit report /by Friday
event team meeting /from Monday /to Tuesday
list
bye
```

Expected output pattern:

```text
(Any Arthur Dent quote)

added:
[D][ ] submit report (by Friday)

(Any Arthur Dent quote)

added:
[E][ ] team meeting (from Monday, to Tuesday)

(Any Arthur Dent quote)

1. [D][ ] submit report (by Friday)
2. [E][ ] team meeting (from Monday, to Tuesday)

So long, and thanks for all the fish.
```
