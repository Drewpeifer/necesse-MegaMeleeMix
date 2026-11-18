# Builds the mod on the Windows PC and puts the jar, alone, in upload/.
# Usage (from the repo root): pwsh tools/build.ps1
# Works for both layouts: Gradle project at the repo root, or in project/ (the Rebuild repos).
# Needs JDK 17 (Gradle 7.3 cannot run on newer JDKs). The system JAVA_HOME points at JDK 8, so it is set here.
$ErrorActionPreference = 'Stop'
$root = Split-Path -Parent $PSScriptRoot
$proj = if (Test-Path (Join-Path $root 'project\build.gradle')) { Join-Path $root 'project' } else { $root }
$jdk = if ($env:NECESSE_JDK) { $env:NECESSE_JDK } else { 'C:\Program Files\Eclipse Adoptium\jdk-17.0.17.10-hotspot' }
$gameDir = if ($env:NECESSE_GAME) { $env:NECESSE_GAME } else { 'E:/games/steam/steamapps/common/Necesse' }

Set-Location $proj
$env:JAVA_HOME = $jdk
Remove-Item -Recurse -Force build\jar -ErrorAction SilentlyContinue
# --no-daemon: a reused background daemon hung builds when this script ran from another shell (2026-11-18).
& .\gradlew.bat --no-daemon -q buildModJar "-PgameDir=$gameDir" 2>&1 | Where-Object { "$_" -notmatch '^Note:' }
if ($LASTEXITCODE -ne 0) { throw "Gradle build failed (exit $LASTEXITCODE); upload/ left unchanged." }

$jar = Get-ChildItem build\jar\*.jar | Select-Object -First 1
if (-not $jar) { throw 'No jar was produced; upload/ left unchanged.' }
$upload = Join-Path $root 'upload'
New-Item -ItemType Directory -Force $upload | Out-Null
# The game's dev-mod folder must hold exactly one jar: drop older builds (e.g. after a version bump).
Get-ChildItem (Join-Path $upload '*.jar') | Where-Object Name -ne $jar.Name | Remove-Item -Force
Copy-Item $jar.FullName $upload -Force
Get-ChildItem $upload | Select-Object Name, Length, LastWriteTime
