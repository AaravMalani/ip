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

## Test case 4: List an empty task list

Aim: Verify that `list` gives a helpful response before any task has been added.

Inputs:

```text
list
bye
```

Expected output pattern:

```text
(Any Arthur Dent quote)

No tasks to display.

So long, and thanks for all the fish.
```

## Test case 5: Mark without an index

Aim: Verify that `mark` rejects a missing task index and keeps the session running.

Inputs:

```text
mark
bye
```

Expected output pattern:

```text
Missing argument from mark: index
(Any Arthur Dent quote)

So long, and thanks for all the fish.
```

## Test case 6: Unmark with an invalid index

Aim: Verify that `unmark` rejects a non-numeric index.

Inputs:

```text
unmark first
bye
```

Expected output pattern:

```text
An invalid argument was passed to the command: first
(Any Arthur Dent quote)

So long, and thanks for all the fish.
```

## Test case 7: Mark an out-of-range task index

Aim: Verify that `mark` rejects an index that does not identify a stored task.

Inputs:

```text
todo read book
mark 2
list
bye
```

Expected output pattern:

```text
(Any Arthur Dent quote)

added:
[T][ ] read book

An invalid argument was passed to the command: 2
(Any Arthur Dent quote)

(Any Arthur Dent quote)

1. [T][ ] read book

So long, and thanks for all the fish.
```

## Test case 8: Use an unknown command

Aim: Verify that an unknown command returns an error and does not terminate the session.

Inputs:

```text
unknown
bye
```

Expected output pattern:

```text
The called command does not exist: unknown
(Any Arthur Dent quote)

So long, and thanks for all the fish.
```

## Test case 9: Remove a task and renumber the remaining tasks

Aim: Verify that `remove` confirms the removed task and that `list` renumbers
the remaining tasks.

Inputs:

```text
todo write report
todo revise notes
remove 1
list
bye
```

Expected output pattern:

```text
(Any Arthur Dent quote)

added:
[T][ ] write report

(Any Arthur Dent quote)

added:
[T][ ] revise notes

(Any Arthur Dent quote)

removed:
[T][ ] write report

(Any Arthur Dent quote)

1. [T][ ] revise notes

So long, and thanks for all the fish.
```

## Test case 10: Remove without an index

Aim: Verify that `remove` rejects a missing task index.

Inputs:

```text
remove
bye
```

Expected output pattern:

```text
Missing argument from remove: index
(Any Arthur Dent quote)

So long, and thanks for all the fish.
```

## Test case 11: Remove with invalid indices

Aim: Verify that `remove` rejects non-numeric, zero, and out-of-range indices
without changing the task list.

Inputs:

```text
todo read book
remove one
remove 0
remove 2
list
bye
```

Expected output pattern:

```text
(Any Arthur Dent quote)

added:
[T][ ] read book

An invalid argument was passed to the command: one
(Any Arthur Dent quote)

An invalid argument was passed to the command: 0
(Any Arthur Dent quote)

An invalid argument was passed to the command: 2
(Any Arthur Dent quote)

(Any Arthur Dent quote)

1. [T][ ] read book

So long, and thanks for all the fish.
```
