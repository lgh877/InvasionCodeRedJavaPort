package com.sweegy.invasioncodered.entity.brawler.actions;

import com.sweegy.invasioncodered.client.model.animations.indices.BrawlerAnimationIndex;
import com.sweegy.invasioncodered.entity.brawler.BrawlerEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;

public class BrawlerBlockAction {
    private static final int DURATION_TICKS = 100;
    private BrawlerBlockAction() {}

    public static void execute(LevelAccessor world, BrawlerEntity mob, double x, double y, double z) {
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
            mob.setActionState(BrawlerAnimationIndex.anims_aggressive);
        }
    }
}
