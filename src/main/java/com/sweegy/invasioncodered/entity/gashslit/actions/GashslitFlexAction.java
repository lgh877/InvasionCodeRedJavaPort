package com.sweegy.invasioncodered.entity.gashslit.actions;

import com.sweegy.invasioncodered.entity.gashslit.GashslitDragonEntity;
import com.sweegy.invasioncodered.entity.gashslit.GashslitEntity;
import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModEntities;
import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModParticleTypes;
import com.sweegy.invasioncodered.util.ServerLevelRelatedUtils;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;

public class GashslitFlexAction {
    public static void execute(LevelAccessor world, GashslitEntity mob, double x, double y, double z) {
        int actionTicks = mob.actionTicks++;
        if (actionTicks == 0) {
            mob.setInAction(true);
        } else if (actionTicks < 29) {
            Entity target = mob.getTarget();
            if (target != null) {
                mob.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(target.getX(), target.getEyeY(), target.getZ()));
            }
        } else if (actionTicks == 30) {
            double width = mob.getBbWidth();
            ServerLevelRelatedUtils.sendParticles((ServerLevel) world, InvasioncoderedsweegyportModParticleTypes.GASHSLIT_SMOKE_PARTICLE.get(), x, y + width * 3, z, 40, width * 0.5, width * 0.5, width * 0.5, 0.02);
            for (int i = 0; i < 4; i++) {
                if (world instanceof ServerLevel level) {
                    GashslitDragonEntity dragon = InvasioncoderedsweegyportModEntities.GASHSLIT_DRAGON.get().spawn(level,
                            BlockPos.containing(x + width * (Math.random() - 0.5), y + width * 3, z + width * (Math.random() - 0.5)), MobSpawnType.MOB_SUMMONED);
                    if (dragon != null) {
                        dragon.setYRot(world.getRandom().nextFloat() * 360F);
                        dragon.setOwner(mob);
                        dragon.setLimitedLife(200);
                    }
                }
            }
            mob.setActionState(0);
            mob.setInAction(false);
        } else if (actionTicks > 30) {
            mob.setActionState(0);
            mob.setInAction(false);
        }
    }
}