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
        attackAnimTime.setBaseValue(300);
        attackDamage.setBaseValue(200)
                .setUpgradedValue(1,225)
                .setUpgradedValue(2,250)
                .setUpgradedValue(3,275)
                .setUpgradedValue(4,300)
                .setUpgradedValue(5,325)
                .setUpgradedValue(6,350)
                .setUpgradedValue(7,375)
                .setUpgradedValue(8,400)
                .setUpgradedValue(9,425)
                .setUpgradedValue(10,475);
        resilienceGain.setBaseValue(10.0F)
                .setUpgradedValue(1, 11.0F)
                .setUpgradedValue(5, 12.0F)
                .setUpgradedValue(10, 14.0F);
        attackRange.setBaseValue(90);
        knockback.setBaseValue(400);
        
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


