$todoQuoteOutput = '(?s)^.*\r?\n\r?\n'

& .codex/skills/test-ui/scripts/run-ui-tests.ps1 `
    -Commands @('todo read book', 'list', 'bye') `
    -ExpectedOutputs @(
        ($todoQuoteOutput + 'added:\r?\n\[T\]\[ \] read book$'),
        ($todoQuoteOutput + '1\. \[T\]\[ \] read book$'),
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
