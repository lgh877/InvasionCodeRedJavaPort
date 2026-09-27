package com.sweegy.invasioncodered.entity.gashslit.actions;

import com.sweegy.invasioncodered.client.model.animations.indices.GashslitAnimationIndex;
import com.sweegy.invasioncodered.entity.gashslit.GashslitEntity;
import com.sweegy.invasioncodered.util.math.VectorHelper;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;

public class GashslitStepbackAction {
    public static void execute(LevelAccessor world, GashslitEntity mob, double x, double y, double z) {
        int actionTicks = mob.actionTicks++;
        Entity target = mob.getTarget();
        if (target != null) {
            mob.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(target.getX(), target.getEyeY(), target.getZ()));
        }
        if (actionTicks == 0) {
            Vec3 lookVec = VectorHelper.calculateFlatViewVector(mob.yBodyRot);
            mob.setInAction(true);
            mob.setDeltaMovement(new Vec3(-lookVec.x() * 2, 0.4533, -lookVec.z() * 2));
        } else if (actionTicks > 7) {
            mob.setActionState(GashslitAnimationIndex.anims_shoot);
        }
    }
}