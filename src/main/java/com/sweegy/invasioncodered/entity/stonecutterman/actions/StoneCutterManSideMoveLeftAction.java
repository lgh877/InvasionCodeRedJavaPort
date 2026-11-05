package com.sweegy.invasioncodered.entity.stonecutterman.actions;

import com.sweegy.invasioncodered.entity.stonecutterman.StoneCutterManEntity;
import com.sweegy.invasioncodered.util.ServerLevelRelatedUtils;
import com.sweegy.invasioncodered.util.math.VectorHelper;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;

public class StoneCutterManSideMoveLeftAction {
    public static void execute(LevelAccessor world, StoneCutterManEntity mob,
                               double x, double y, double z) {
        int actionTicks = mob.actionTicks++;
        if (actionTicks == 0) {
            mob.isKnocked = true;
            mob.push(0,0.3,0);
            mob.hasImpulse = true;
            mob.setInAction(true);
            mob.setAnimSpeed(2);
            mob.setStopLegAnim(true);
        } else if (actionTicks < 10) {
            mob.setXRot(0);
            Vec3 lookVec = VectorHelper.calculateFlatViewVector(mob.yBodyRot - 90).scale(0.3 * mob.getBbWidth());
            mob.push(lookVec.x, 0, lookVec.z);
            ServerLevelRelatedUtils.sendParticles(
                    (ServerLevel) world,
                    ParticleTypes.CAMPFIRE_COSY_SMOKE,
                    mob.getX(), mob.getY() + 0.2, mob.getZ(),
                    1, 0, 0, 0, 0);
        } else {
            mob.setInAction(false);
            mob.setActionState(0);
            mob.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 80, 1, false, false));
        }
    }
}
