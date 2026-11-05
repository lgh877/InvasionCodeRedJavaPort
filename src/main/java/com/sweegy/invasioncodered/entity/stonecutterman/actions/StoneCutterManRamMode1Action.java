package com.sweegy.invasioncodered.entity.stonecutterman.actions;

import com.sweegy.invasioncodered.client.model.animations.indices.StoneCutterManAnimationIndex;
import com.sweegy.invasioncodered.entity.stonecutterman.StoneCutterManEntity;
import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModSounds;
import com.sweegy.invasioncodered.util.math.VectorHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class StoneCutterManRamMode1Action {

    public static void execute(
            LevelAccessor world,
            StoneCutterManEntity mob,
            double x, double y, double z
    ) {
        int actionTicks = mob.actionTicks++;

        if (mob.actionProgress == 0) {
            if (actionTicks > 4) {
                mob.setSprinting(true);
                mob.actionProgress = 1;
                mob.stoneCutterManMoveControl.rotationLimit = 40;
            }

        } else if (mob.actionProgress == 1) {
            if (actionTicks < 100) {
                mob.playSound(InvasioncoderedsweegyportModSounds.STONECUTTERMAN_ATTACK.get(), 1, mob.getRandom().nextFloat() * 1.5f + 0.5f);
                attack(mob);
                spawnParticles(mob);

                LivingEntity target = mob.getTarget();
                if (target != null) {
                    if(mob.distanceTo(target) - (mob.getBbWidth() + target.getBbWidth()) / 2 < 2.857 * mob.getBbWidth()) {
                        if (mob.canBounce) {
                            mob.setActionState(StoneCutterManAnimationIndex.anims_spin);
                            mob.actionTicks = actionTicks;
                            mob.setStopLegAnim(true);

                            mob.getNavigation().stop();

                            Vec3 direction = StoneCutterManRamMode2Action.getTargetDirection(mob);

                            mob.setDeltaMovement(
                                    direction.x * StoneCutterManRamMode2Action.BASE_SPEED,
                                    StoneCutterManRamMode2Action.BOUNCE_Y_SPEED,
                                    direction.z * StoneCutterManRamMode2Action.BASE_SPEED
                            );
                            mob.hasImpulse = true;
                            mob.getNavigation().stop();
                        } else {
                            Vec3 flatLookVec = VectorHelper.calculateFlatViewVector(mob.yBodyRot).scale(mob.getBbWidth() * 3);
                            mob.getNavigation().moveTo(x - flatLookVec.x, y, z - flatLookVec.z, 2.4);
                        }
                    }else{
                        mob.getNavigation().moveTo(target, 2.4);
                    }
                }
            } else {
                finish(mob);
            }

        } else {
            finish(mob);
        }

        if (actionTicks >= 100) {
            finish(mob);
        }
    }

    private static void attack(StoneCutterManEntity mob) {
        double width = mob.getBbWidth();

        Vec3 flatLookVec = VectorHelper.calculateFlatViewVector(mob.yBodyRot);

        AABB hitbox = mob.getBoundingBox().inflate(0.5).move(
                width * 0.75D * flatLookVec.x(),
                0,
                width * 0.75D * flatLookVec.z()
        );

        float damage = (float) mob.getAttributeValue(
                Attributes.ATTACK_DAMAGE
        ) * 0.4736f * mob.getDamageMultiplier();

        if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(mob.level(), mob))
            for (BlockPos blockpos : BlockPos.betweenClosed(Mth.floor(hitbox.minX), Mth.floor(hitbox.minY), Mth.floor(hitbox.minZ), Mth.floor(hitbox.maxX), Mth.floor(hitbox.maxY), Mth.floor(hitbox.maxZ))) {
                BlockState blockstate = mob.level().getBlockState(blockpos);
                Block block = blockstate.getBlock();
                if (block instanceof LeavesBlock) {
                    mob.level().destroyBlock(blockpos, true, mob);
                }
            }

        List<LivingEntity> targets = mob.level().getEntitiesOfClass(
                LivingEntity.class,
                hitbox,
                entity -> entity != mob
                        && entity.canBeSeenAsEnemy()
        );

        for (LivingEntity target : targets) {
            target.invulnerableTime = 0;

            target.hurt(
                    mob.damageSources().mobAttack(mob),
                    damage
            );
        }
    }

    private static void spawnParticles(StoneCutterManEntity mob) {
        if (!(mob.level() instanceof ServerLevel serverLevel)) {
            return;
        }

        double width = mob.getBbWidth();

        serverLevel.sendParticles(
                ParticleTypes.CAMPFIRE_COSY_SMOKE,
                mob.getX(),
                mob.getEyeY(),
                mob.getZ(),
                3,
                width * 0.5D,
                width * 0.25D,
                width * 0.5D,
                0.01D
        );
    }
    
    private static void finish(StoneCutterManEntity mob){
        mob.setActionState(0);
        mob.getNavigation().stop();
    }
}