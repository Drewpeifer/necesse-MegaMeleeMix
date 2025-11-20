package megameleemix.items;

import necesse.engine.localization.Localization;
import necesse.engine.util.GameBlackboard;
import necesse.entity.levelEvent.mobAbilityLevelEvent.ToolItemMobAbilityEvent;
import necesse.entity.mobs.Mob;
import necesse.entity.mobs.PlayerMob;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.gfx.gameTooltips.ListGameTooltips;
import necesse.inventory.InventoryItem;
import necesse.inventory.item.Item;
import necesse.inventory.item.toolItem.swordToolItem.SwordToolItem;
import necesse.level.maps.Level;


// Extends SwordToolItem
public class TranquilizerHammer extends SwordToolItem  {

    // Weapon attack textures are loaded from resources/player/weapons/<itemStringID>

    public TranquilizerHammer() {
        super(400, null);
        rarity = Item.Rarity.EPIC;
        attackAnimTime.setBaseValue(500);
        attackDamage.setBaseValue(225)
                .setUpgradedValue(1,250)
                .setUpgradedValue(2,275)
                .setUpgradedValue(3,300)
                .setUpgradedValue(4,325)
                .setUpgradedValue(5,350)
                .setUpgradedValue(6,375)
                .setUpgradedValue(7,400)
                .setUpgradedValue(8,425)
                .setUpgradedValue(9,450)
                .setUpgradedValue(10,475);
        resilienceGain.setBaseValue(15.0F);
        attackRange.setBaseValue(50)
                .setUpgradedValue(5, 75)
                .setUpgradedValue(10, 100);
        knockback.setBaseValue(800)
                .setUpgradedValue(1,850)
                .setUpgradedValue(3,900)
                .setUpgradedValue(5,950)
                .setUpgradedValue(7,1000)
                .setUpgradedValue(9,1050)
                .setUpgradedValue(10,1500);
        
    }

    public ListGameTooltips getPreEnchantmentTooltips(InventoryItem item, PlayerMob perspective, GameBlackboard blackboard) {
      ListGameTooltips tooltips = super.getPreEnchantmentTooltips(item, perspective, blackboard);
      tooltips.add(Localization.translate("itemtooltip", "tranquilizerhammertip"), 400);
      return tooltips;
   }

   public void hitMob(InventoryItem item, ToolItemMobAbilityEvent event, Level level, Mob target, Mob attacker) {
        if (target != null) {
            // damage target mob
            super.hitMob(item, event, level, target, attacker);
            // stun for 3 sec
            target.buffManager.addBuff(new ActiveBuff("stun", target, 3000, null), true);
        }
   }

}


