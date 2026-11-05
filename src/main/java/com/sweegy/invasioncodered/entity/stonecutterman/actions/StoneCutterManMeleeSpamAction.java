package com.sweegy.invasioncodered.entity.stonecutterman.actions;

import com.sweegy.invasioncodered.client.model.animations.indices.StoneCutterManAnimationIndex;
import com.sweegy.invasioncodered.entity.stonecutterman.StoneCutterManEntity;
import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModSounds;
import com.sweegy.invasioncodered.util.EntityUtil;
import com.sweegy.invasioncodered.util.math.RotatableHitbox;
import com.sweegy.invasioncodered.util.math.VectorHelper;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class StoneCutterManMeleeSpamAction {

    public static void execute(LevelAccessor world, StoneCutterManEntity mob,
                               double x, double y, double z) {
        int actionTicks = mob.actionTicks++;
        if (actionTicks < 4) {
            Entity target = mob.getTarget();
            if (target != null) {
                EntityUtil.lookAt(mob, target.getEyePosition(), 60);
            }
        } else {
            if((actionTicks & 1) == 0) mob.playSound(InvasioncoderedsweegyportModSounds.STONECUTTERMAN_ATTACK.get());
            attack(world, mob, x, y, z);
            Entity target = mob.getTarget();
            if (target != null) {
                EntityUtil.lookAt(mob, target.getEyePosition(), 60);
            }
            Vec3 flatLookVec =
                    VectorHelper.calculateFlatViewVector(mob.yBodyRot)
                            .scale(mob.getAttributeValue(Attributes.MOVEMENT_SPEED) * 5);
            mob.setDeltaMovement(flatLookVec.x, mob.getDeltaMovement().y(), flatLookVec.z);
            if (mob.spamComboCount++ > 20 || mob.spamComboSucceedCount > 3) {
                mob.setInAction(false);
                mob.setActionState(0);
            }
//            int processedActionTicks = (actionTicks & 3);
//            if (processedActionTicks == 0) {
//                Entity target = mob.getTarget();
//                if (target != null) {
//                    EntityUtil.lookAt(mob, target.getEyePosition(), 60);
//                }
//                Vec3 flatLookVec =
//                        VectorHelper.calculateFlatViewVector(mob.yBodyRot)
//                                .scale(mob.getAttributeValue(Attributes.MOVEMENT_SPEED) * 5);
//                mob.setDeltaMovement(flatLookVec.x, mob.getDeltaMovement().y(), flatLookVec.z);
//            } else if (processedActionTicks == 1) {
//                attack(world, mob, x, y, z);
//            } else if (processedActionTicks == 2) {
//                Entity target = mob.getTarget();
//                if (target != null) {
//                    EntityUtil.lookAt(mob, target.getEyePosition(), 60);
//                }
//                Vec3 flatLookVec =
//                        VectorHelper.calculateFlatViewVector(mob.yBodyRot)
//                                .scale(mob.getAttributeValue(Attributes.MOVEMENT_SPEED) * 5);
//                mob.setDeltaMovement(flatLookVec.x, mob.getDeltaMovement().y(), flatLookVec.z);
//            } else {
//                attack(world, mob, x, y, z);
//                if (mob.spamComboCount++ > 6 || mob.spamComboSucceedCount > 3) {
//                    mob.setInAction(false);
//                    mob.setActionState(0);
//                }
//            }
        }
    }

    private static void attack(LevelAccessor world, StoneCutterManEntity mob,
                               double x, double y, double z) {
        double width = mob.getBbWidth();
        double height = mob.getBbHeight();
        boolean isHit = false;

        Vec3 flatLookVec =
                VectorHelper.calculateFlatViewVector(mob.yBodyRot);

        Vec3 hitboxPos = new Vec3(
                x + flatLookVec.x() * width,
                y + height * 0.5D,
                z + flatLookVec.z() * width
        );

        RotatableHitbox hitbox = new RotatableHitbox(
                hitboxPos,
                width * 2.0D,
                height,
                width * 2.0D,
                mob.yBodyRot,
                0,
                0
        );

        float attackDamage =
                (float) mob.getAttributeValue(Attributes.ATTACK_DAMAGE) * 0.4736f * mob.getDamageMultiplier();

        List<LivingEntity> targets = world.getEntitiesOfClass(
                LivingEntity.class,
                hitbox.getBoundingAABB(),
                e -> true
        );

        for (LivingEntity target : targets) {
            if (target != mob
                    && target.canBeSeenAsEnemy()
                    && hitbox.isColliding(target.getBoundingBox())) {
                isHit = true;
                target.invulnerableTime = 0;
                target.hurt(
                        mob.damageSources().mobAttack(mob),
                        attackDamage
                );
            }
        }
        if (isHit) mob.spamComboSucceedCount++;
    }
}