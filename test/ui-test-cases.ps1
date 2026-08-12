$todoQuoteOutput = '(?s)^.*\r?\n\r?\n'

& .codex/skills/test-ui/scripts/run-ui-tests.ps1 `
    -Commands @('todo read book', 'list', 'bye') `
    -ExpectedOutputs @(
        ($todoQuoteOutput + 'added:\r?\n\[T\]\[ \] read book$'),
        ($todoQuoteOutput + '1\. \[T\]\[ \] read book$'),
        '^So long, and thanks for all the fish\.$'
    )

$errorQuoteOutput = '(?s)^'

& .codex/skills/test-ui/scripts/run-ui-tests.ps1 `
    -Commands @('list', 'bye') `
    -ExpectedOutputs @(
        ($todoQuoteOutput + 'No tasks to display\.$'),
        '^So long, and thanks for all the fish\.$'
    )

& .codex/skills/test-ui/scripts/run-ui-tests.ps1 `
    -Commands @('mark', 'bye') `
    -ExpectedOutputs @(
        ($errorQuoteOutput + 'Missing argument from mark: index\r?\n.*$'),
        '^So long, and thanks for all the fish\.$'
    )

& .codex/skills/test-ui/scripts/run-ui-tests.ps1 `
    -Commands @('unmark first', 'bye') `
    -ExpectedOutputs @(
        ($errorQuoteOutput + 'An invalid argument was passed to the command: first\r?\n.*$'),
        '^So long, and thanks for all the fish\.$'
    )

& .codex/skills/test-ui/scripts/run-ui-tests.ps1 `
    -Commands @('todo read book', 'mark 2', 'list', 'bye') `
    -ExpectedOutputs @(
        ($todoQuoteOutput + 'added:\r?\n\[T\]\[ \] read book$'),
        ($errorQuoteOutput + 'An invalid argument was passed to the command: 2\r?\n.*$'),
        ($todoQuoteOutput + '1\. \[T\]\[ \] read book$'),
        '^So long, and thanks for all the fish\.$'
    )

& .codex/skills/test-ui/scripts/run-ui-tests.ps1 `
    -Commands @('unknown', 'bye') `
    -ExpectedOutputs @(
        ($errorQuoteOutput + 'The called command does not exist: unknown\r?\n.*$'),
        '^So long, and thanks for all the fish\.$'
    )

& .codex/skills/test-ui/scripts/run-ui-tests.ps1 `
    -Commands @('deadline submit report /by Friday', 'list', 'bye') `
    -ExpectedOutputs @(
        ($todoQuoteOutput + 'added:\r?\n\[D\]\[ \] submit report \(by Friday\)$'),
        ($todoQuoteOutput + '1\. \[D\]\[ \] submit report \(by Friday\)$'),
        '^So long, and thanks for all the fish\.$'
    )

& .codex/skills/test-ui/scripts/run-ui-tests.ps1 `
    -Commands @('event team meeting /from Monday /to Tuesday', 'list', 'bye') `
    -ExpectedOutputs @(
        ($todoQuoteOutput + 'added:\r?\n\[E\]\[ \] team meeting \(from Monday, to Tuesday\)$'),
        ($todoQuoteOutput + '1\. \[E\]\[ \] team meeting \(from Monday, to Tuesday\)$'),
        '^So long, and thanks for all the fish\.$'
    )

& .codex/skills/test-ui/scripts/run-ui-tests.ps1 `
    -Commands @('todo submit assignment', 'mark 1', 'unmark 1', 'list', 'bye') `
    -ExpectedOutputs @(
        ($todoQuoteOutput + 'added:\r?\n\[T\]\[ \] submit assignment$'),
        ($todoQuoteOutput + 'marked:\r?\n\[T\]\[X\] submit assignment$'),
        ($todoQuoteOutput + 'unmarked:\r?\n\[T\]\[ \] submit assignment$'),
        ($todoQuoteOutput + '1\. \[T\]\[ \] submit assignment$'),
        '^So long, and thanks for all the fish\.$'
    )

& .codex/skills/test-ui/scripts/run-ui-tests.ps1 `
    -Commands @('todo write report', 'todo revise notes', 'remove 1', 'list', 'bye') `
    -ExpectedOutputs @(
        ($todoQuoteOutput + 'added:\r?\n\[T\]\[ \] write report$'),
        ($todoQuoteOutput + 'added:\r?\n\[T\]\[ \] revise notes$'),
        ($todoQuoteOutput + 'removed:\r?\n\[T\]\[ \] write report$'),
        ($todoQuoteOutput + '1\. \[T\]\[ \] revise notes$'),
        '^So long, and thanks for all the fish\.$'
    )

& .codex/skills/test-ui/scripts/run-ui-tests.ps1 `
    -Commands @('remove', 'bye') `
    -ExpectedOutputs @(
        ($errorQuoteOutput + 'Missing argument from remove: index\r?\n.*$'),
        '^So long, and thanks for all the fish\.$'
    )

& .codex/skills/test-ui/scripts/run-ui-tests.ps1 `
    -Commands @('todo read book', 'remove one', 'remove 0', 'remove 2', 'list', 'bye') `
    -ExpectedOutputs @(
        ($todoQuoteOutput + 'added:\r?\n\[T\]\[ \] read book$'),
        ($errorQuoteOutput + 'An invalid argument was passed to the command: one\r?\n.*$'),
        ($errorQuoteOutput + 'An invalid argument was passed to the command: 0\r?\n.*$'),
        ($errorQuoteOutput + 'An invalid argument was passed to the command: 2\r?\n.*$'),
        ($todoQuoteOutput + '1\. \[T\]\[ \] read book$'),
        '^So long, and thanks for all the fish\.$'
    )
