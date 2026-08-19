$storageDirectory = Join-Path $PSScriptRoot '..\data'
$storageFile = Join-Path $storageDirectory 'arthur.bin'
$storageBackup = Join-Path $env:TEMP ('arthur-bin-backup-' + [guid]::NewGuid())
$storagePreviouslyExisted = Test-Path -LiteralPath $storageFile

if ($storagePreviouslyExisted) {
    Copy-Item -LiteralPath $storageFile -Destination $storageBackup
}

function Reset-TestStorage {
    if (Test-Path -LiteralPath $storageFile) {
        Remove-Item -LiteralPath $storageFile -Force
    }
}

function Invoke-UiTest {
    param(
        [string[]]$Commands,
        [string[]]$ExpectedOutputs
    )

    & .codex/skills/test-ui/scripts/run-ui-tests.ps1 -Commands $Commands -ExpectedOutputs $ExpectedOutputs
    if ($LASTEXITCODE -ne 0) {
        exit $LASTEXITCODE
    }
}

function Invoke-IsolatedUiTest {
    param(
        [string[]]$Commands,
        [string[]]$ExpectedOutputs
    )

    Reset-TestStorage
    Invoke-UiTest -Commands $Commands -ExpectedOutputs $ExpectedOutputs
}

try {
    $quoteOutput = '(?s)^.*\r?\n\r?\n'
    $errorOutput = '(?s)^'

    Invoke-IsolatedUiTest -Commands @('todo read book', 'list', 'bye') -ExpectedOutputs @(
        ($quoteOutput + 'added:\r?\n\[T\]\[ \] read book$'),
        ($quoteOutput + '1\. \[T\]\[ \] read book$'),
        '^So long, and thanks for all the fish\.$'
    )

    Invoke-IsolatedUiTest -Commands @('list', 'bye') -ExpectedOutputs @(
        ($quoteOutput + 'No tasks to display\.$'),
        '^So long, and thanks for all the fish\.$'
    )

    Invoke-IsolatedUiTest -Commands @('mark', 'bye') -ExpectedOutputs @(
        ($errorOutput + 'Missing argument from mark: index\r?\n.*$'),
        '^So long, and thanks for all the fish\.$'
    )

    Invoke-IsolatedUiTest -Commands @('unmark first', 'bye') -ExpectedOutputs @(
        ($errorOutput + 'An invalid argument was passed to the command: first\r?\n.*$'),
        '^So long, and thanks for all the fish\.$'
    )

    Invoke-IsolatedUiTest -Commands @('todo read book', 'mark 2', 'list', 'bye') -ExpectedOutputs @(
        ($quoteOutput + 'added:\r?\n\[T\]\[ \] read book$'),
        ($errorOutput + 'An invalid argument was passed to the command: 2\r?\n.*$'),
        ($quoteOutput + '1\. \[T\]\[ \] read book$'),
        '^So long, and thanks for all the fish\.$'
    )

    Invoke-IsolatedUiTest -Commands @('unknown', 'bye') -ExpectedOutputs @(
        ($errorOutput + 'The called command does not exist: unknown\r?\n.*$'),
        '^So long, and thanks for all the fish\.$'
    )

    Invoke-IsolatedUiTest -Commands @('deadline submit report /by Friday', 'list', 'bye') -ExpectedOutputs @(
        ($quoteOutput + 'added:\r?\n\[D\]\[ \] submit report \(by Friday\)$'),
        ($quoteOutput + '1\. \[D\]\[ \] submit report \(by Friday\)$'),
        '^So long, and thanks for all the fish\.$'
    )

    Invoke-IsolatedUiTest -Commands @('event team meeting /from Monday /to Tuesday', 'list', 'bye') -ExpectedOutputs @(
        ($quoteOutput + 'added:\r?\n\[E\]\[ \] team meeting \(from Monday, to Tuesday\)$'),
        ($quoteOutput + '1\. \[E\]\[ \] team meeting \(from Monday, to Tuesday\)$'),
        '^So long, and thanks for all the fish\.$'
    )

    Invoke-IsolatedUiTest -Commands @('todo submit assignment', 'mark 1', 'unmark 1', 'list', 'bye') -ExpectedOutputs @(
        ($quoteOutput + 'added:\r?\n\[T\]\[ \] submit assignment$'),
        ($quoteOutput + 'marked:\r?\n\[T\]\[X\] submit assignment$'),
        ($quoteOutput + 'unmarked:\r?\n\[T\]\[ \] submit assignment$'),
        ($quoteOutput + '1\. \[T\]\[ \] submit assignment$'),
        '^So long, and thanks for all the fish\.$'
    )

    Invoke-IsolatedUiTest -Commands @('todo write report', 'todo revise notes', 'remove 1', 'list', 'bye') -ExpectedOutputs @(
        ($quoteOutput + 'added:\r?\n\[T\]\[ \] write report$'),
        ($quoteOutput + 'added:\r?\n\[T\]\[ \] revise notes$'),
        ($quoteOutput + 'removed:\r?\n\[T\]\[ \] write report$'),
        ($quoteOutput + '1\. \[T\]\[ \] revise notes$'),
        '^So long, and thanks for all the fish\.$'
    )

    Invoke-IsolatedUiTest -Commands @('remove', 'bye') -ExpectedOutputs @(
        ($errorOutput + 'Missing argument from remove: index\r?\n.*$'),
        '^So long, and thanks for all the fish\.$'
    )

    Invoke-IsolatedUiTest -Commands @('todo read book', 'remove one', 'remove 0', 'remove 2', 'list', 'bye') -ExpectedOutputs @(
        ($quoteOutput + 'added:\r?\n\[T\]\[ \] read book$'),
        ($errorOutput + 'An invalid argument was passed to the command: one\r?\n.*$'),
        ($errorOutput + 'An invalid argument was passed to the command: 0\r?\n.*$'),
        ($errorOutput + 'An invalid argument was passed to the command: 2\r?\n.*$'),
        ($quoteOutput + '1\. \[T\]\[ \] read book$'),
        '^So long, and thanks for all the fish\.$'
    )

    Reset-TestStorage
    Invoke-UiTest -Commands @('todo read book', 'bye') -ExpectedOutputs @(
        ($quoteOutput + 'added:\r?\n\[T\]\[ \] read book$'),
        '^So long, and thanks for all the fish\.$'
    )
    Invoke-UiTest -Commands @('list', 'bye') -ExpectedOutputs @(
        ($quoteOutput + '1\. \[T\]\[ \] read book$'),
        '^So long, and thanks for all the fish\.$'
    )
} finally {
    Reset-TestStorage
    if ($storagePreviouslyExisted) {
        New-Item -ItemType Directory -Path $storageDirectory -Force | Out-Null
        Move-Item -LiteralPath $storageBackup -Destination $storageFile
    }
}
