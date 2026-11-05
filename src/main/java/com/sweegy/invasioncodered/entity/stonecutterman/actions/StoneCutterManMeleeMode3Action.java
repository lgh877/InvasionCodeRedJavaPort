package com.sweegy.invasioncodered.entity.stonecutterman.actions;

import com.sweegy.invasioncodered.client.model.animations.indices.StoneCutterManAnimationIndex;
import com.sweegy.invasioncodered.config.InvasionCodeRedConfig;
import com.sweegy.invasioncodered.entity.stonecutterman.StoneCutterManEntity;
import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModSounds;
import com.sweegy.invasioncodered.util.EntityUtil;
import com.sweegy.invasioncodered.util.math.RotatableHitbox;
import com.sweegy.invasioncodered.util.math.VectorHelper;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class StoneCutterManMeleeMode3Action {

    public static void execute(LevelAccessor world, StoneCutterManEntity mob,
                               double x, double y, double z) {
        int actionTicks = mob.actionTicks++;

        double width = mob.getBbWidth();
        double height = mob.getBbHeight();

        if (actionTicks == 2) {
            mob.setInAction(true);

            Entity target = mob.getTarget();
            if (target != null) {
                mob.lookAt(
                        EntityAnchorArgument.Anchor.EYES,
                        new Vec3(target.getX(), target.getEyeY(), target.getZ())
                );
            }

        } else if (actionTicks == 3) {
            mob.setNoGravity(true);
            mob.setDeltaMovement(new Vec3(0, 0.02, 0));
            Entity target = mob.getTarget();
            if (target != null) {
                mob.lookAt(
                        EntityAnchorArgument.Anchor.EYES,
                        new Vec3(target.getX(), target.getEyeY(), target.getZ())
                );
            }
        } else if (actionTicks == 4) {
            Entity target = mob.getTarget();

            if (target != null) {
                Vec2 diffVec = new Vec2(
                        (float) (target.getX() - x),
                        (float) (target.getZ() - z)
                );

                double flatDist = diffVec.length();
                double dashDist = Math.min(flatDist, width * 0.5);

                Vec3 flatLookVec =
                        VectorHelper.calculateFlatViewVector(mob.yBodyRot);

                mob.setDeltaMovement(new Vec3(
                        flatLookVec.x() * dashDist * 0.57534089D,
                        0,
                        flatLookVec.z() * dashDist * 0.57534089D
                ));
            }

        } else if (actionTicks == 6) {
            attack(world, mob, x, y, z, width, height);

            mob.setNoGravity(false);
            mob.setInAction(false);

        } else if (actionTicks == 8) {
            if (mob.getRandom().nextDouble()
                    < InvasionCodeRedConfig.STONE_CUTTER_MAN_MELEE_COMBO_CHANCE.get()) {
                tryMeleeCombo(mob);
            }
        } else if (actionTicks > 11) {
            finish(mob);
        }
    }

    private static void attack(LevelAccessor world, StoneCutterManEntity mob,
                               double x, double y, double z,
                               double width, double height) {
        mob.playSound(InvasioncoderedsweegyportModSounds.STONECUTTERMAN_ATTACK.get());
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
                (float) mob.getAttributeValue(Attributes.ATTACK_DAMAGE) * 1.21F * mob.getDamageMultiplier();;
        double knockback =
                mob.getAttributeValue(Attributes.ATTACK_KNOCKBACK) * 2.0D;

        List<LivingEntity> targets = world.getEntitiesOfClass(
                LivingEntity.class,
                hitbox.getBoundingAABB(),
                e -> true
        );

        boolean didHitTarget = false;

        for (LivingEntity target : targets) {
            if (target != mob
                    && target.canBeSeenAsEnemy()
                    && hitbox.isColliding(target.getBoundingBox())) {
                target.invulnerableTime = 0;
                if (target.hurt(
                        mob.damageSources().mobAttack(mob),
                        attackDamage
                )) {
                    EntityUtil.applyKnockback(mob, target, knockback);
                    if(target == mob.getTarget()) didHitTarget = true;
                }
            }
        }

        if(didHitTarget) {
            mob.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 20, 5, false, false));
            mob.avoidTicks = 3;
        }
    }

    private static boolean tryMeleeCombo(StoneCutterManEntity mob) {
        LivingEntity target = mob.getTarget();
        if(target!=null) {
            double yDiff = target.getY() - mob.getY();
            boolean isYValid = yDiff < mob.getBbHeight() && yDiff > -target.getBbHeight();
            boolean canAttackFlat = Math.sqrt(Math.pow(target.getX() - mob.getX(),2) + Math.pow(target.getZ() - mob.getZ(),2)) < target.getBbWidth() / 2 + mob.getBbWidth() * 4;
            boolean succeed = isYValid && canAttackFlat;
            if(succeed) {
                mob.setActionState(mob.getRandom().nextFloat() > 0.5 ? StoneCutterManAnimationIndex.anims_attack : StoneCutterManAnimationIndex.anims_attack2);
            }
            return succeed;
        }
        return false;
    }

    private static void finish(StoneCutterManEntity mob) {
        mob.setNoGravity(false);
        mob.setInAction(false);
        mob.setActionState(0);
    }
}