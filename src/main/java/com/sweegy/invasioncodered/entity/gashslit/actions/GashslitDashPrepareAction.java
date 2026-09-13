package com.sweegy.invasioncodered.entity.gashslit.actions;

import com.sweegy.invasioncodered.client.model.animations.indices.GashslitAnimationIndex;
import com.sweegy.invasioncodered.entity.gashslit.GashslitEntity;
import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModParticleTypes;
import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModSounds;
import com.sweegy.invasioncodered.util.ServerLevelRelatedUtils;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

public class GashslitDashPrepareAction {
    public static void execute(LevelAccessor world, GashslitEntity mob, double x, double y, double z) {
        int actionTicks = mob.actionTicks++;
        if (actionTicks == 0) {
            world.playSound(null, BlockPos.containing(x, y, z), InvasioncoderedsweegyportModSounds.GASH_PREPARES_DASH.get(), SoundSource.HOSTILE, 2, 1);
            mob.setInAction(true);
        } else if (actionTicks < 10) {
            Entity target = mob.getTarget();
            if (target != null) {
                mob.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(target.getX(), target.getEyeY(), target.getZ()));
            }
        } else if (actionTicks > 10) {
            double width = mob.getBbWidth();
            ServerLevelRelatedUtils.sendParticles((ServerLevel) world, InvasioncoderedsweegyportModParticleTypes.GASHSLIT_SMOKE_PARTICLE.get(), x, y + width * 1.666, z, 10, width * 0.5, width * 1.666, width * 0.5, 0);
            Entity target = mob.getTarget();
            if (target != null && target.isAlive()) {
                mob.setActionState(GashslitAnimationIndex.anims_flew2);
                mob.setSwingType(1, true);
                mob.setDeltaMovement(new Vec3(0, 0.01, 0));
            } else {
                mob.setActionState(0);
                mob.setInAction(false);
            }
        }
    }
}
