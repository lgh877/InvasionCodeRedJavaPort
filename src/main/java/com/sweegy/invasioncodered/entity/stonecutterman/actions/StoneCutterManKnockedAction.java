package com.sweegy.invasioncodered.entity.stonecutterman.actions;

import com.sweegy.invasioncodered.entity.stonecutterman.StoneCutterManEntity;
import com.sweegy.invasioncodered.util.ServerLevelRelatedUtils;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.LevelAccessor;

public class StoneCutterManKnockedAction {
    public static void execute(LevelAccessor world, StoneCutterManEntity mob,
                               double x, double y, double z) {
        int actionTicks = mob.actionTicks++;
        if (actionTicks == 0) {
            mob.setInAction(true);
        } else if (actionTicks < 50) {
            ServerLevelRelatedUtils.sendParticles(
                    (ServerLevel) world,
                    ParticleTypes.CAMPFIRE_COSY_SMOKE,
                    mob.getX(), mob.getEyeY(), mob.getZ(),
                    1, 0,0, 0, 0);
        } else {
            mob.setInAction(false);
            mob.setActionState(0);
            mob.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 80, 1));
        }
    }
}
