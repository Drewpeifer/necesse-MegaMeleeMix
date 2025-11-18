package megameleemix.events;

import necesse.engine.sound.SoundEffect;
import necesse.engine.sound.SoundManager;
import necesse.entity.levelEvent.explosionEvent.ExplosionEvent;
import necesse.entity.mobs.GameDamage;
import necesse.entity.mobs.Mob;
import necesse.entity.mobs.buffs.ActiveBuff;
import necesse.gfx.GameResources;

public class BoomyExplosion extends ExplosionEvent
{
   public BoomyExplosion() {
      this(0.0F, 0.0F, 40, new GameDamage(200.0F), true, true, 5.0F, (Mob)null);
    }
    
    // public BoomyExplosion(float x, float y, int range, GameDamage damage, boolean destructive, float toolTier, Mob owner) {
    //   super(x, y, range, damage, destructive, toolTier, owner);
    // }
    
    public BoomyExplosion(float x, float y, int range, GameDamage damage, boolean destroysObjects, boolean destroysTiles, float toolTier, Mob owner) {
      super(x, y, range, damage, destroysObjects, destroysTiles, toolTier, owner);
    }
    
    protected GameDamage getTotalObjectDamage(float mod) {
      float objectMod = (float)Math.pow(mod, 0.699999988079071D) * 10.0F;
      return super.getTotalObjectDamage(mod).modDamage(objectMod);
    }
    
    protected void playExplosionEffects() {
      SoundManager.playSound(GameResources.explosionHeavy, (SoundEffect)SoundEffect.effect(this.x, this.y).volume(1.5F).pitch(1.5F));
    }

    protected void onMobWasHit(Mob mob, float distance) {
      float mod = this.getDistanceMod(distance);
      GameDamage damage = this.getTotalMobDamage(mod);
      float knockback = (float)this.knockback * mod;
      mob.isServerHit(damage, (float)mob.getX() - this.x, (float)mob.getY() - this.y, knockback, this);
      mob.buffManager.addBuff(new ActiveBuff("ablaze", mob, 10000, this), true);
   }
}