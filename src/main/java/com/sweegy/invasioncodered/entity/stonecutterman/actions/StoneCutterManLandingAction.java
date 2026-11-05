package com.sweegy.invasioncodered.entity.stonecutterman.actions;
import com.sweegy.invasioncodered.entity.stonecutterman.StoneCutterManEntity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class StoneCutterManLandingAction {

    private static final int LANDING_DURATION = 6;

    private static final double ATTACK_RADIUS = 1.0D;
    private static final float ATTACK_DAMAGE = 23.0F;

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

            Vec3 movement = mob.getDeltaMovement();

            mob.setDeltaMovement(
                    movement.x * 0.1D,
                    movement.y,
                    movement.z * 0.1D
            );

            mob.hasImpulse = true;
        }

        if (actionTicks < LANDING_DURATION) {
            attack(mob);
        } else {
            finish(mob);
        }
    }

    private static void attack(StoneCutterManEntity mob) {
        AABB area = mob.getBoundingBox().inflate(
                ATTACK_RADIUS,
                0.25D,
                ATTACK_RADIUS
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

            if (dx * dx + dz * dz
                    > ATTACK_RADIUS * ATTACK_RADIUS) {
                continue;
            }

            // 원본의 damage_per_tick 동작에 가깝게 처리
            target.invulnerableTime = 0;

            target.hurt(
                    mob.damageSources().mobAttack(mob),
                    ATTACK_DAMAGE
            );
        }
    }

    private static void finish(StoneCutterManEntity mob) {
        mob.setInAction(false);
        mob.setActionState(0);
    }
}