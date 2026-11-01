package megameleemix.buffs;

import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.entity.mobs.buffs.BuffEventSubscriber;
import necesse.entity.mobs.buffs.BuffModifiers;
import necesse.entity.mobs.buffs.staticBuffs.Buff;

// Paralyzes the target. Applied by the Tranquilizer for 3 seconds.
public class Stun extends Buff {

    public Stun() {
        canCancel = true;
        isVisible = true;
        shouldSave = false;
    }

    @Override
    public void init(ActiveBuff activeBuff, BuffEventSubscriber buffEventSubscriber) {
        activeBuff.setModifier(BuffModifiers.PARALYZED, true);
    }

}
