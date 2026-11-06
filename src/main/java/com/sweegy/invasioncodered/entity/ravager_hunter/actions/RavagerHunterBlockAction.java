package com.sweegy.invasioncodered.entity.ravager_hunter.actions;

import com.sweegy.invasioncodered.client.model.animations.indices.RavagerHunterAnimationIndex;
import com.sweegy.invasioncodered.entity.ravager_hunter.RavagerHunterEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.LevelAccessor;

public class RavagerHunterBlockAction {
    private static final int DURATION_TICKS = 100;
    private RavagerHunterBlockAction() {}

    public static void execute(LevelAccessor world, RavagerHunterEntity mob, double x, double y, double z) {
        int actionTicks = mob.actionTicks++;
        mob.setDeltaMovement(
                mob.getDeltaMovement().x * 0.5,
                mob.getDeltaMovement().y > 0 ? mob.getDeltaMovement().y * 0.5 : mob.getDeltaMovement().y,
                mob.getDeltaMovement().z * 0.5
        );
        mob.setYHeadRot(mob.yBodyRot);
        if (actionTicks == 0) {
            mob.setInAction(true);
            mob.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, mob.nextBlockDuration, 1, false, false));
        } else if (actionTicks > mob.nextBlockDuration) {
            mob.setInAction(false);
            mob.setActionState(RavagerHunterAnimationIndex.anims_aggressive);
        }
    }
}
