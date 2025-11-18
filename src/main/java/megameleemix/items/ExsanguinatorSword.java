package megameleemix.items;

import necesse.engine.localization.Localization;
import necesse.engine.util.GameBlackboard;
import necesse.engine.util.GameRandom;
import necesse.entity.levelEvent.LevelEvent;
import necesse.entity.levelEvent.mobAbilityLevelEvent.MobHealthChangeEvent;
import necesse.entity.levelEvent.mobAbilityLevelEvent.ToolItemMobAbilityEvent;
import necesse.entity.mobs.Mob;
import necesse.entity.mobs.PlayerMob;
import necesse.gfx.gameTooltips.ListGameTooltips;
import necesse.inventory.InventoryItem;
import necesse.inventory.item.Item;
import necesse.inventory.item.toolItem.swordToolItem.SwordToolItem;
import necesse.level.maps.Level;


// Extends SwordToolItem
public class ExsanguinatorSword extends SwordToolItem {

    // Weapon attack textures are loaded from resources/player/weapons/<itemStringID>

    public ExsanguinatorSword() {
        super(400, null);
        rarity = Item.Rarity.EPIC;
        attackAnimTime.setBaseValue(400);
        attackDamage.setBaseValue(175)
                .setUpgradedValue(1,200)
                .setUpgradedValue(2,225)
                .setUpgradedValue(3,250)
                .setUpgradedValue(4,300)
                .setUpgradedValue(5,350)
                .setUpgradedValue(6,400)
                .setUpgradedValue(7,450)
                .setUpgradedValue(8,500)
                .setUpgradedValue(9,550)
                .setUpgradedValue(10,650);
        resilienceGain.setBaseValue(8.0F);
        attackRange.setBaseValue(60);
        knockback.setBaseValue(200);
        
    }

    public ListGameTooltips getPreEnchantmentTooltips(InventoryItem item, PlayerMob perspective, GameBlackboard blackboard) {
      ListGameTooltips tooltips = super.getPreEnchantmentTooltips(item, perspective, blackboard);
      tooltips.add(Localization.translate("itemtooltip", "exsanguinatorswordtip"), 400);
      return tooltips;
   }

   public void hitMob(InventoryItem item, ToolItemMobAbilityEvent event, Level level, Mob target, Mob attacker) {
        if (target != null) {
            // damage target mob
            super.hitMob(item, event, level, target, attacker);
            // 50% chance to heal attacker for 1% of max health on hit
            if (GameRandom.globalRandom.getChance(0.5F)) {
                int AttackerHealthChange = attacker.getHealth() / 100;
                attacker.getLevel().entityManager.events.add((LevelEvent) new MobHealthChangeEvent(attacker, AttackerHealthChange));
            }
        }
   }

}


