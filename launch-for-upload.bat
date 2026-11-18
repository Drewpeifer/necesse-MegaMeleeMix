@echo off
rem Launches Necesse with this mod loaded as a development mod, so it can be tested or uploaded to the
rem Steam Workshop from the game's Mods menu. The upload\ folder must contain only the mod's jar
rem (build it with tools\build.ps1). Windows PC version; launch-for-upload.command is the obsolete macOS one.
setlocal
if "%NECESSE_GAME%"=="" set "NECESSE_GAME=E:\games\steam\steamapps\common\Necesse"
rem Tell the Steam API which game this is. Without it the game asks Steam to relaunch it, and Steam shows its
rem launch-options popup instead of starting the dev build ("Could not load platform" in the log).
set "SteamAppId=1169040"
set "SteamGameId=1169040"
set "MOD_DIR=%~dp0upload"
if "%MOD_DIR:~-1%"=="\" set "MOD_DIR=%MOD_DIR:~0,-1%"

powershell -NoProfile -Command "if (Get-CimInstance Win32_Process -Filter \"Name='java.exe' OR Name='javaw.exe' OR Name='Necesse.exe'\" | Where-Object { $_.CommandLine -match 'Necesse' }) { exit 1 }"
if errorlevel 1 (
  echo Necesse is already running. Quit it first, then run this again.
  pause
  exit /b 1
)
tasklist /FI "IMAGENAME eq steam.exe" | find /I "steam.exe" >nul
if errorlevel 1 (
  echo Steam does not seem to be running. Start Steam first so the Workshop upload works.
  pause
  exit /b 1
)

rem Explorer can drop hidden Thumbs.db / desktop.ini files into the folder, which the game rejects.
del /A /F /Q "%MOD_DIR%\Thumbs.db" "%MOD_DIR%\desktop.ini" 2>nul
echo Launching Necesse with dev mod:
dir /B "%MOD_DIR%\*.jar"

cd /d "%NECESSE_GAME%" || exit /b 1
start "Necesse (dev mod)" "%NECESSE_GAME%\jre\bin\javaw.exe" -XX:+UnlockExperimentalVMOptions -XX:+UseG1GC -XX:+ExplicitGCInvokesConcurrent ^
  -XX:G1NewSizePercent=20 -XX:G1ReservePercent=20 -XX:MaxGCPauseMillis=50 -XX:G1HeapRegionSize=32M ^
  -Xms512m -Xmx4G -jar Necesse.jar -mod "%MOD_DIR%"
