# necesse-MegaMeleeMix

A mod for the game Necesse that adds a little something for the melee crowd. Built for Necesse 1.3.3.

## Weapons

- The Exsanguinator (sword) - 50% chance to heal for 30% of your armor on hit
- The Cauterizer (greatsword) - Sets the target ablaze for 10 seconds, burning for 50% of your current health each second. More health means more damage.
- The Tranquilizer (hammer) - Paralyzes target for 3 seconds and delivers huge knockback
- The Boomy Whacker (spear) - Creates an explosion on EVERY swing at cursor location, scaling with weapon damage (Don't use indoors!)

## Trinkets

- The Warlord's Pauldron
  - +50% Resilience Gain
  - +100 Max Resilience
  - +100 Max Health
  - +25% Health Regen
  - +1 Max Food Buffs
  - -15% Attack Speed
  - -15% Crit Chance

## Recipes (Iron Anvil)

| Item | Ingredients |
|---|---|
| The Exsanguinator | 15 Tungsten Bar |
| The Cauterizer | 35 Tungsten Bar |
| The Tranquilizer | 25 Mycelium Bar |
| The Boomy Whacker | 20 each of Any Log, Copper, Iron, Gold and Tungsten Bar, plus 1 Dynamite Stick |
| Warlord's Pauldron | 5 Challenger's Pauldron |

## Building

Requires JDK 16 or 17 (the game's bundled Java can run the game but can't compile). `build.gradle` points at the default macOS Steam install.

    ./gradlew buildModJar     # -> build/jar/MegaMeleeMix-1.3.3-1.1.jar
    ./gradlew runClient       # launches the game with the mod

The build writes `mod.info` itself instead of using the game's `CreateModInfoFile` tool, whose argument parser treats any `-` inside a value as a new option.
