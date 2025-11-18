package megameleemix.items;

import necesse.engine.localization.Localization;
import necesse.engine.util.GameBlackboard;
import necesse.entity.levelEvent.mobAbilityLevelEvent.ToolItemMobAbilityEvent;
import necesse.entity.mobs.Mob;
import necesse.entity.mobs.PlayerMob;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.buffs.BuffModifiers;
import necesse.gfx.gameTooltips.ListGameTooltips;
import necesse.inventory.InventoryItem;
import necesse.inventory.item.Item;
import necesse.inventory.item.toolItem.swordToolItem.SwordToolItem;
import necesse.level.maps.Level;


// Extends SwordToolItem
public class CauterizerGreatsword extends SwordToolItem {

    // Weapon attack textures are loaded from resources/player/weapons/<itemStringID>

    public CauterizerGreatsword() {
        super(400, null);
        rarity = Item.Rarity.EPIC;
        attackAnimTime.setBaseValue(300); // 300 ms attack time
        attackDamage.setBaseValue(215) // Base sword damage
                .setUpgradedValue(1,240)
                .setUpgradedValue(2,275)
                .setUpgradedValue(3,325)
                .setUpgradedValue(4,375)
                .setUpgradedValue(5,425)
                .setUpgradedValue(6,475)
                .setUpgradedValue(7,525)
                .setUpgradedValue(8,575)
                .setUpgradedValue(9,625)
                .setUpgradedValue(10,675); // Upgraded damage
        resilienceGain.setBaseValue(12.0F); // 12 resilience per hit
        attackRange.setBaseValue(90); // 90 range
        knockback.setBaseValue(400); // 400 knockback
        
    }

    public ListGameTooltips getPreEnchantmentTooltips(InventoryItem item, PlayerMob perspective, GameBlackboard blackboard) {
      ListGameTooltips tooltips = super.getPreEnchantmentTooltips(item, perspective, blackboard);
      tooltips.add(Localization.translate("itemtooltip", "cauterizergreatswordtip"), 400);
      return tooltips;
   }

   public void hitMob(InventoryItem item, ToolItemMobAbilityEvent event, Level level, Mob target, Mob attacker) {
        if (target != null) {
            // damage target mob
            super.hitMob(item, event, level, target, attacker);
            // burn target for 50% of total health, for 10 sec
            float attackerHealth = attacker.getHealth();
            target.buffManager.addBuff(new ActiveBuff("ablaze", target, 10000, null), true, true).setModifier(BuffModifiers.FIRE_DAMAGE_FLAT, attackerHealth * 0.5F);
        }
   }

}


