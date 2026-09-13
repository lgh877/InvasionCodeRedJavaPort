package com.sweegy.invasioncodered.entity.gashslit.actions;

import com.sweegy.invasioncodered.entity.gashslit.GashslitEntity;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;

public class GashslitBlockAction {
    public static void execute(LevelAccessor world, GashslitEntity mob, double x, double y, double z) {
        int actionTicks = mob.actionTicks++;
        if (actionTicks == 0) {
            mob.setInAction(true);
            Vec3 delta = mob.getDeltaMovement();
            mob.setDeltaMovement(delta.x() * 0.5, delta.y(), delta.z() * 0.5);
        } else if (actionTicks < mob.blockDuration) {
            Vec3 delta = mob.getDeltaMovement();
            mob.setDeltaMovement(delta.x() * 0.5, delta.y(), delta.z() * 0.5);
            Entity target = mob.getTarget();
            if (target != null) {
                mob.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(target.getX(), target.getEyeY(), target.getZ()));
            }
        } else {
            mob.setActionState(0);
            mob.setInAction(false);
        }
    }
}