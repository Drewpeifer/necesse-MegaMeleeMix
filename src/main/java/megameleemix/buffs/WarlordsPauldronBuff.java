package megameleemix.buffs;

import necesse.engine.localization.Localization;
import necesse.engine.network.Packet;
import necesse.entity.mobs.PlayerMob;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.buffs.BuffAbility;
import necesse.entity.mobs.buffs.BuffEventSubscriber;
import necesse.entity.mobs.buffs.BuffModifiers;
import necesse.entity.mobs.buffs.staticBuffs.armorBuffs.trinketBuffs.TrinketBuff;
import necesse.gfx.gameTooltips.ListGameTooltips;
import necesse.inventory.InventoryItem;
import necesse.inventory.item.trinketItem.TrinketItem;


public class WarlordsPauldronBuff extends TrinketBuff implements BuffAbility
{
    public WarlordsPauldronBuff(){}

    public void init(ActiveBuff buff, BuffEventSubscriber eventSubscriber)
    {
        buff.setModifier(BuffModifiers.RESILIENCE_REGEN, 50.0F);
        buff.setModifier(BuffModifiers.MAX_RESILIENCE_FLAT, 100);
        buff.setModifier(BuffModifiers.MAX_HEALTH_FLAT, 100);
        buff.setModifier(BuffModifiers.ATTACK_SPEED, -0.15F);
        buff.setModifier(BuffModifiers.CRIT_CHANCE, -0.15F);
        buff.setModifier(BuffModifiers.COMBAT_HEALTH_REGEN, 0.25F);
        buff.setModifier(BuffModifiers.MAX_FOOD_BUFFS, 1);
    }

    public void runAbility(PlayerMob player, ActiveBuff buff, Packet content) {}

    public boolean canRunAbility(PlayerMob player, ActiveBuff buff, Packet content) {
        return true;
    }

    public ListGameTooltips getTrinketTooltip(TrinketItem trinketItem, InventoryItem item, PlayerMob perspective) 
    {
        ListGameTooltips tooltips = super.getTrinketTooltip(trinketItem, item, perspective);
        tooltips.add(Localization.translate("itemtooltip", "warlordspauldrontip"));
        return tooltips;
    }
}