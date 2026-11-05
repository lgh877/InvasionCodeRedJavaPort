package com.sweegy.invasioncodered.entity.stonecutterman.actions;

import com.sweegy.invasioncodered.client.model.animations.indices.StoneCutterManAnimationIndex;
import com.sweegy.invasioncodered.entity.stonecutterman.StoneCutterManEntity;
import com.sweegy.invasioncodered.util.EntityUtil;
import com.sweegy.invasioncodered.util.math.VectorHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class StoneCutterManJumpAction {

    private static final int PREPARE_TICKS = 2;
    private static final int MAX_TICKS = 100;

    private static final double JUMP_Y_SPEED = 0.85D;
    private static final double JUMP_XZ_SPEED = 1.15D;

    private static final double ROAR_RADIUS = 2.0D;
    private static final double ROAR_HORIZONTAL_POWER = 1.3D;
    private static final double ROAR_VERTICAL_POWER = 0.55D;

    public static void execute(
            LevelAccessor world,
            StoneCutterManEntity mob,
            double x, double y, double z
    ) {
        int actionTicks = mob.actionTicks++;

        if (actionTicks == 0) {
            mob.setInAction(true);
            mob.setStopLegAnim(true);
            mob.setAnimSpeed(3);

            mob.addEffect(new MobEffectInstance(
                    MobEffects.DAMAGE_RESISTANCE,
                    80,
                    1,
                    false,
                    false
            ));
        }

        if (actionTicks < PREPARE_TICKS) {
            LivingEntity target = mob.getTarget();

            if (target != null && target.isAlive()) {
                EntityUtil.lookAt(
                        mob,
                        target.getEyePosition(),
                        60
                );
            }
            return;
        }

        if (mob.actionProgress == 0) {
            launch(mob);
            applyJumpRoar(mob);
            mob.actionProgress = 1;
            return;
        }

        mob.getNavigation().stop();

        if (mob.actionProgress == 1) {
            if (!mob.onGround()) {
                mob.actionProgress = 2;
            }
        } else if (mob.actionProgress == 2) {
            if (mob.onGround()) {
                beginLanding(mob);
                return;
            }
        }

        if (mob.isInWaterOrBubble()
                || mob.isInLava()
                || actionTicks >= MAX_TICKS) {
            if (mob.onGround() || mob.isInWaterOrBubble()
                    || mob.isInLava()) {
                beginLanding(mob);
            } else {
                finish(mob);
            }
        }
    }

    private static void launch(StoneCutterManEntity mob) {
        LivingEntity target = mob.getTarget();

        Vec3 direction = VectorHelper.calculateFlatViewVector(
                mob.yBodyRot
        );

        if (target != null && target.isAlive()) {
            Vec3 difference = new Vec3(
                    target.getX() - mob.getX(),
                    0,
                    target.getZ() - mob.getZ()
            );

            if (difference.lengthSqr() > 1.0E-6D) {
                direction = difference.normalize();
            }
        }

        direction = new Vec3(
                direction.x,
                0,
                direction.z
        ).normalize();

        mob.setDeltaMovement(
                direction.x * JUMP_XZ_SPEED,
                JUMP_Y_SPEED,
                direction.z * JUMP_XZ_SPEED
        );

        mob.hasImpulse = true;
    }

    private static void applyJumpRoar(StoneCutterManEntity mob) {
        AABB area = mob.getBoundingBox().inflate(
                ROAR_RADIUS,
                1.0D,
                ROAR_RADIUS
        );

        List<LivingEntity> targets =
                mob.level().getEntitiesOfClass(
                        LivingEntity.class,
                        area,
                        entity -> entity != mob && entity.isAlive()
                );

        for (LivingEntity target : targets) {
            double dx = target.getX() - mob.getX();
            double dz = target.getZ() - mob.getZ();

            double distanceSqr = dx * dx + dz * dz;

            if (distanceSqr > ROAR_RADIUS * ROAR_RADIUS
                    || distanceSqr < 1.0E-6D) {
                continue;
            }

            double distance = Math.sqrt(distanceSqr);

            target.push(
                    dx / distance * ROAR_HORIZONTAL_POWER,
                    ROAR_VERTICAL_POWER,
                    dz / distance * ROAR_HORIZONTAL_POWER
            );

            target.hurtMarked = true;
        }
    }

    private static void beginLanding(StoneCutterManEntity mob) {
        mob.actionProgress = 0;
        // 실제 프로젝트의 착지 애니메이션 인덱스로 교체
        mob.setActionState(
                StoneCutterManAnimationIndex.anims_airland
        );
    }

    private static void finish(StoneCutterManEntity mob) {
        mob.setNoGravity(false);
        mob.setInAction(false);
        mob.actionProgress = 0;
        mob.setActionState(0);
    }
}