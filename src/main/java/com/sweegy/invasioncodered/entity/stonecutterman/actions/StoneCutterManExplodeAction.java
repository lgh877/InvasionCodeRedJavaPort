package com.sweegy.invasioncodered.entity.stonecutterman.actions;

import com.sweegy.invasioncodered.client.model.animations.indices.StoneCutterManAnimationIndex;
import com.sweegy.invasioncodered.entity.stonecutterman.StoneCutterManEntity;
import com.sweegy.invasioncodered.util.ServerLevelRelatedUtils;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;

public class StoneCutterManExplodeAction {
    public static void execute(LevelAccessor world, StoneCutterManEntity mob,
                               double x, double y, double z) {
        int actionTicks = mob.actionTicks++;
        if(actionTicks == 0){
            mob.isKnocked = true;
            mob.setInAction(true);
        }
        else if(actionTicks < 40){
            ServerLevelRelatedUtils.sendParticles(
                    (ServerLevel) world,
                    ParticleTypes.CAMPFIRE_COSY_SMOKE,
                    x, y + mob.getBbHeight() * 0.5, z,
                    (actionTicks + 5)/10, 0, 0, 0, (double) (actionTicks + 5) * 0.01);
            mob.setDeltaMovement(Vec3.ZERO);
        }else{
            mob.level().explode(mob, x,y,z, (float)4, Level.ExplosionInteraction.MOB);
            mob.setActionState(StoneCutterManAnimationIndex.anims_dead);
        }
    }
}
