package megameleemix.items;

import megameleemix.events.BoomyExplosion;
import necesse.engine.localization.Localization;
import necesse.engine.network.gameNetworkData.GNDItemMap;
import necesse.engine.util.GameBlackboard;
import necesse.entity.levelEvent.mobAbilityLevelEvent.ToolItemMobAbilityEvent;
import necesse.entity.mobs.Mob;
import necesse.entity.mobs.PlayerMob;
import necesse.entity.mobs.itemAttacker.ItemAttackSlot;
import necesse.entity.mobs.itemAttacker.ItemAttackerMob;
import necesse.gfx.gameTooltips.ListGameTooltips;
import necesse.inventory.InventoryItem;
import necesse.inventory.item.toolItem.spearToolItem.SpearToolItem;
import necesse.inventory.lootTable.presets.SpearWeaponsLootTable;
import necesse.level.maps.Level;

public class BoomyWhackerSpear extends SpearToolItem
{
    public BoomyWhackerSpear() {
      super(1400, SpearWeaponsLootTable.spearWeapons);
      this.rarity = Rarity.LEGENDARY;
      this.attackAnimTime.setBaseValue(400);
      this.attackDamage.setBaseValue(100)
                .setUpgradedValue(1,125)
                .setUpgradedValue(2,150)
                .setUpgradedValue(3,175)
                .setUpgradedValue(4,200)
                .setUpgradedValue(5,225)
                .setUpgradedValue(6,250)
                .setUpgradedValue(7,275)
                .setUpgradedValue(8,300)
                .setUpgradedValue(9,325)
                .setUpgradedValue(10,350);
      this.attackRange.setBaseValue(140);
      this.knockback.setBaseValue(50);
   }

   public ListGameTooltips getPreEnchantmentTooltips(InventoryItem item, PlayerMob perspective, GameBlackboard blackboard) {
      ListGameTooltips tooltips = super.getPreEnchantmentTooltips(item, perspective, blackboard);
      tooltips.add(Localization.translate("itemtooltip", "boomywhackerspeartip"));
      return tooltips;
   }

   public void hitMob(InventoryItem item, ToolItemMobAbilityEvent event, Level level, Mob target, Mob attacker)
   {
        if (target != null)
        {
            // damage target mob
            super.hitMob(item, event, level, target, attacker);
        }

   }

    public InventoryItem onAttack(Level level, int x, int y, ItemAttackerMob attackerMob, int attackHeight, InventoryItem item, ItemAttackSlot slot, int animAttack, int seed, GNDItemMap mapContent) {
         // create explosion at target location
         level.entityManager.events.add(new BoomyExplosion((float)x, (float)y, 60, new necesse.entity.mobs.GameDamage(this.getAttackDamageValue(item, attackerMob) * 2), true, true, 5.0F, attackerMob));
         return super.onAttack(level, x, y, attackerMob, attackHeight, item, slot, animAttack, seed, mapContent);

   }
}
