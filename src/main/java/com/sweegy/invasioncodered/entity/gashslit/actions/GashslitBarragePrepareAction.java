package com.sweegy.invasioncodered.entity.gashslit.actions;

import com.sweegy.invasioncodered.client.model.animations.indices.GashslitAnimationIndex;
import com.sweegy.invasioncodered.entity.gashslit.GashslitEntity;
import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModSounds;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

public class GashslitBarragePrepareAction {
    public static void execute(LevelAccessor world, GashslitEntity mob, double x, double y, double z) {
        int actionTicks = mob.actionTicks++;
        if (actionTicks == 0) {
            mob.setInAction(true);
            world.playSound(null, BlockPos.containing(x, y, z), InvasioncoderedsweegyportModSounds.GASH_BARRAGE_PREPARE.get(), SoundSource.HOSTILE, 2, 1);
        } else if (actionTicks < 10) {
            Entity target = mob.getTarget();
            if (target != null) {
                mob.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(target.getX(), target.getEyeY(), target.getZ()));
            }
        } else {
            mob.prevPos = mob.position();
            mob.setActionState(GashslitAnimationIndex.anims_charge);
            mob.setAnimSpeed((int) (Math.random() * 5) + 4);
        }
    }
}