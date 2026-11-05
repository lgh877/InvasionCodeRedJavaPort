package com.sweegy.invasioncodered.entity.stonecutterman.control;

import com.sweegy.invasioncodered.client.model.animations.indices.StoneCutterManAnimationIndex;
import com.sweegy.invasioncodered.entity.stonecutterman.StoneCutterManEntity;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public class StoneCutterManBodyRotationControl extends BodyRotationControl {

    private final StoneCutterManEntity mob;

    public StoneCutterManBodyRotationControl(StoneCutterManEntity mob) {
        super(mob);
        this.mob = mob;
    }

    @Override
    public void clientTick() {
        if (mob.getActionState()
                == StoneCutterManAnimationIndex.anims_spin) {

            Vec3 velocity = mob.getDeltaMovement();
            double horizontalSpeedSqr =
                    velocity.x * velocity.x + velocity.z * velocity.z;

            if (horizontalSpeedSqr > 1.0E-6D) {
                float yaw = (float) (
                        Mth.atan2(velocity.z, velocity.x)
                                * Mth.RAD_TO_DEG
                ) - 90.0F;

                mob.setYRot(yaw);
                mob.yBodyRot = yaw;
                mob.yHeadRot = yaw;
            }

            return;
        }

        super.clientTick();
    }
}