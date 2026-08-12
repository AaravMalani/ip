param(
    [Parameter(Mandatory = $true)] [string[]]$Commands,
    [Parameter(Mandatory = $true)] [string[]]$ExpectedOutputs
)

$ErrorActionPreference = 'Stop'
if ($Commands.Count -ne $ExpectedOutputs.Count) {
    throw 'Commands and ExpectedOutputs must contain the same number of items.'
}

$jdk = Get-ChildItem 'C:\Users\aarav\.jdks' -Directory | Where-Object Name -Match '25' | Select-Object -First 1
if ($null -eq $jdk) { throw 'Java 25 was not found under C:\Users\aarav\.jdks.' }
$outputDirectory = Join-Path $env:TEMP ('test-ui-' + [guid]::NewGuid())
New-Item -ItemType Directory -Path $outputDirectory | Out-Null

try {
    $javac = Join-Path $jdk.FullName 'bin\javac.exe'
    $java = Join-Path $jdk.FullName 'bin\java.exe'
    $sourceFiles = Get-ChildItem src/main/java -Recurse -Filter *.java | ForEach-Object FullName
    $compilerOutput = & $javac -d $outputDirectory $sourceFiles 2>&1
    if ($LASTEXITCODE -ne 0) {
        Write-Host 'UI test failed: compilation failed.'
        $compilerOutput | ForEach-Object { Write-Host $_ }
        exit 1
    }
    $processInfo = [System.Diagnostics.ProcessStartInfo]::new()
    $processInfo.FileName = $java
    $processInfo.Arguments = "-cp `"$outputDirectory`" ArthurDent"
    $processInfo.UseShellExecute = $false
    $processInfo.RedirectStandardInput = $true
    $processInfo.RedirectStandardOutput = $true
    $processInfo.RedirectStandardError = $true
    $process = [System.Diagnostics.Process]::new()
    $process.StartInfo = $processInfo
    $process.Start() | Out-Null
    $Commands | ForEach-Object { $process.StandardInput.WriteLine($_) }
    $process.StandardInput.Close()
    $transcriptText = $process.StandardOutput.ReadToEnd().TrimEnd()
    $standardError = $process.StandardError.ReadToEnd().TrimEnd()
    $process.WaitForExit()
    if ($standardError) { $transcriptText += [Environment]::NewLine + $standardError }
    Write-Host '--- Console session ---'
    $Commands | ForEach-Object { Write-Host "> $_" }
    Write-Host '--- Console output ---'
    Write-Host $transcriptText

    $line = [regex]::Escape('_' * 60)
    $responses = @([regex]::Matches($transcriptText, "(?s)$line\r?\n(.*?)\r?\n$line") | ForEach-Object { $_.Groups[1].Value } | Select-Object -Skip 1)
    for ($index = 0; $index -lt $ExpectedOutputs.Count; $index++) {
        $actual = if ($index -lt $responses.Count) { $responses[$index] } else { '<no output>' }
        if ($actual -notmatch $ExpectedOutputs[$index]) {
            Write-Host "UI test failed at command $($index + 1): $($Commands[$index])"
            Write-Host "Expected pattern: $($ExpectedOutputs[$index])"
            Write-Host "Actual: $actual"
            exit 1
        }
    }
    Write-Host "UI test passed: $($Commands.Count) command(s) matched."
} finally {
    Remove-Item -Recurse -Force -LiteralPath $outputDirectory
}
