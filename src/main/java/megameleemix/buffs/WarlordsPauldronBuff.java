package megameleemix.buffs;

import necesse.engine.localization.Localization;
import necesse.entity.mobs.PlayerMob;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.buffs.BuffEventSubscriber;
import necesse.entity.mobs.buffs.BuffModifiers;
import necesse.entity.mobs.buffs.staticBuffs.armorBuffs.trinketBuffs.TrinketBuff;
import necesse.gfx.gameTooltips.ListGameTooltips;
import necesse.inventory.InventoryItem;
import necesse.inventory.item.trinketItem.TrinketItem;

// Percentage modifiers are fractions: 0.5F = +50%, -0.15F = -15%.
// Trinket tooltips don't list modifiers automatically, so keep itemtooltip.warlordspauldrontip in en.lang in sync with these values.
public class WarlordsPauldronBuff extends TrinketBuff {

    @Override
    public void init(ActiveBuff buff, BuffEventSubscriber eventSubscriber) {
        buff.setModifier(BuffModifiers.RESILIENCE_GAIN, 0.5F);
        buff.setModifier(BuffModifiers.MAX_RESILIENCE_FLAT, 100);
        buff.setModifier(BuffModifiers.MAX_HEALTH_FLAT, 100);
        buff.setModifier(BuffModifiers.ATTACK_SPEED, -0.15F);
        buff.setModifier(BuffModifiers.CRIT_CHANCE, -0.15F);
        buff.setModifier(BuffModifiers.COMBAT_HEALTH_REGEN, 0.25F);
        buff.setModifier(BuffModifiers.MAX_FOOD_BUFFS, 1);
    }

    @Override
    public ListGameTooltips getTrinketTooltip(TrinketItem trinketItem, InventoryItem item, PlayerMob perspective) {
        ListGameTooltips tooltips = super.getTrinketTooltip(trinketItem, item, perspective);
        tooltips.add(Localization.translate("itemtooltip", "warlordspauldrontip"));
        return tooltips;
    }

}
