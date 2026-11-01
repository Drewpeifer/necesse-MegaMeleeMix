#!/bin/bash
# Launches Necesse with this mod loaded as a development mod, so it can be uploaded to the
# Steam Workshop from the game's Mods menu. The upload/ folder must contain only the mod's jar
# (the game adds preview.png itself).

GAME="$HOME/Library/Application Support/Steam/steamapps/common/Necesse/Necesse.app/Contents/Resources"
MOD_DIR="$(cd "$(dirname "$0")" && pwd)/upload"

if pgrep -f "Necesse.jar" >/dev/null; then
  echo "Necesse is already running. Quit it first, then run this again."
  exit 1
fi
if ! pgrep -x steam_osx >/dev/null; then
  echo "Steam does not seem to be running. Start Steam first so the Workshop upload works."
  exit 1
fi

# Finder can drop a hidden .DS_Store file into the folder, which the game rejects.
rm -f "$MOD_DIR/.DS_Store"
echo "Launching Necesse with dev mod: $(ls "$MOD_DIR"/*.jar)"

cd "$GAME" || exit 1
exec ./jre/bin/java -XX:+UnlockExperimentalVMOptions -XX:+UseG1GC -XX:+ExplicitGCInvokesConcurrent \
  -XX:G1NewSizePercent=20 -XX:G1ReservePercent=20 -XX:MaxGCPauseMillis=50 -XX:G1HeapRegionSize=32M \
  -Xms512m -Xmx4G -jar Necesse.jar -mod "$MOD_DIR"
