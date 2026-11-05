package com.sweegy.invasioncodered.entity.stonecutterman.actions;

import com.sweegy.invasioncodered.client.model.animations.indices.StoneCutterManAnimationIndex;
import com.sweegy.invasioncodered.entity.stonecutterman.StoneCutterManEntity;
import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModSounds;
import com.sweegy.invasioncodered.util.math.VectorHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class StoneCutterManRamMode2Action {

    public static final double BASE_SPEED = 0.4D;
    public static final double BOUNCE_Y_SPEED = 2.3;
    private static final double FLUID_ACCELERATION = 0.12D;
    private static final double MIN_SPEED_SQR = 1.0E-6D;

    public static void execute(
            LevelAccessor world,
            StoneCutterManEntity mob,
            double x, double y, double z
    ) {
        int actionTicks = mob.actionTicks++;

        if (actionTicks < 100) {
            mob.playSound(InvasioncoderedsweegyportModSounds.STONECUTTERMAN_ATTACK.get(), 1, mob.getRandom().nextFloat() * 1.5f + 0.5f);
            attack(mob);
            updateMovement(mob, actionTicks);
        } else {
            finish(mob);
        }
    }

    private static void attack(StoneCutterManEntity mob) {
        AABB hitbox = mob.getBoundingBox().inflate(0.5D);

        float damage = (float) mob.getAttributeValue(
                Attributes.ATTACK_DAMAGE
        ) * 0.4736f * mob.getDamageMultiplier();

        List<LivingEntity> targets = mob.level().getEntitiesOfClass(
                LivingEntity.class,
                hitbox,
                entity -> entity != mob
                        && entity.canBeSeenAsEnemy()
        );

        if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(mob.level(), mob))
            for (BlockPos blockpos : BlockPos.betweenClosed(Mth.floor(hitbox.minX), Mth.floor(hitbox.minY), Mth.floor(hitbox.minZ), Mth.floor(hitbox.maxX), Mth.floor(hitbox.maxY), Mth.floor(hitbox.maxZ))) {
                BlockState blockstate = mob.level().getBlockState(blockpos);
                Block block = blockstate.getBlock();
                if (block instanceof LeavesBlock) {
                    mob.level().destroyBlock(blockpos, true, mob);
                }
            }

        for (LivingEntity target : targets) {
            target.invulnerableTime = 0;

            target.hurt(
                    mob.damageSources().mobAttack(mob),
                    damage
            );
        }
    }

    private static void updateMovement(StoneCutterManEntity mob, int actionTicks) {
        Vec3 velocity = mob.getDeltaMovement();
        Vec3 direction = getTargetDirection(mob);

        if (mob.isInWaterOrBubble() || mob.isInLava()) {
            Vec3 horizontalVelocity = new Vec3(
                    velocity.x, 0, velocity.z
            ).add(direction.scale(FLUID_ACCELERATION));

            if (horizontalVelocity.lengthSqr()
                    > BASE_SPEED * BASE_SPEED) {
                horizontalVelocity = horizontalVelocity
                        .normalize()
                        .scale(BASE_SPEED);
            }

            mob.setDeltaMovement(
                    horizontalVelocity.x,
                    velocity.y,
                    horizontalVelocity.z
            );

            mob.hasImpulse = true;
            return;
        }

        Vec3 newVelocity = velocity;

        boolean groundCollision =
                mob.verticalCollisionBelow || mob.onGround();

        if (groundCollision) {
            LivingEntity target = mob.getTarget();
            if(target != null && isCloseEnough(mob, target)) {
                newVelocity = new Vec3(
                        direction.x * BASE_SPEED,
                        BOUNCE_Y_SPEED,
                        direction.z * BASE_SPEED
                );
            }else{
                convertAction(mob, actionTicks);
                return;
            }
        } else if (mob.verticalCollision) {
            LivingEntity target = mob.getTarget();
            if(target != null && isCloseEnough(mob, target)) {
                newVelocity = new Vec3(
                        velocity.x,
                        -Math.max(
                                Math.abs(velocity.y),
                                BOUNCE_Y_SPEED
                        ),
                        velocity.z
                );
            }else{
                convertAction(mob, actionTicks);
                return;
            }
        }

        if (mob.horizontalCollision) {
            Vec3 horizontal = new Vec3(
                    velocity.x,
                    0,
                    velocity.z
            );

            if (horizontal.lengthSqr() > MIN_SPEED_SQR) {
                horizontal = horizontal.scale(-1);
            } else {
                horizontal = direction.scale(-1);
            }

            double speed = Math.max(
                    horizontal.length(),
                    BASE_SPEED
            );

            horizontal = horizontal.normalize().scale(speed);

            newVelocity = new Vec3(
                    horizontal.x,
                    newVelocity.y,
                    horizontal.z
            );
        }

        mob.setDeltaMovement(newVelocity.add(0, -0.32, 0));
        mob.hasImpulse = true;
    }

    private static boolean isCloseEnough(StoneCutterManEntity mob, LivingEntity target){
        return mob.distanceTo(target) - (mob.getBbWidth() + target.getBbWidth()) / 2 < 2.857 * mob.getBbWidth();
    }

    private static void convertAction(StoneCutterManEntity mob, int actionTicks){
        mob.setActionState(StoneCutterManAnimationIndex.anims_ram);
        mob.actionProgress = 1;
        mob.actionTicks = actionTicks;
        mob.setInAction(false);
        mob.stoneCutterManMoveControl.rotationLimit = 20;
        mob.setSprinting(true);
    }

    public static Vec3 getTargetDirection(
            StoneCutterManEntity mob
    ) {
        LivingEntity target = mob.getTarget();

        Vec3 look = VectorHelper.calculateFlatViewVector(
                mob.yBodyRot
        );

        if (target != null && target.isAlive()) {
            Vec3 difference = new Vec3(
                    target.getX() - mob.getX(),
                    0,
                    target.getZ() - mob.getZ()
            );

            if (difference.lengthSqr() > MIN_SPEED_SQR) {
                return difference.normalize().add(look.scale(0.5));
            }
        }

        Vec3 direction = new Vec3(
                look.x,
                0,
                look.z
        );

        if (direction.lengthSqr() > MIN_SPEED_SQR) {
            return direction.normalize();
        }

        return new Vec3(0, 0, 1);
    }

    private static void finish(StoneCutterManEntity mob) {
        mob.setInAction(false);
        mob.setStopLegAnim(false);
        mob.setActionState(0);
    }
}