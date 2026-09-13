package com.sweegy.invasioncodered.entity.gashslit.actions;

import com.sweegy.invasioncodered.client.model.animations.indices.GashslitAnimationIndex;
import com.sweegy.invasioncodered.entity.gashslit.GashslitEntity;
import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModParticleTypes;
import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModSounds;
import com.sweegy.invasioncodered.util.OtherStuff;
import com.sweegy.invasioncodered.util.ServerLevelRelatedUtils;
import com.sweegy.invasioncodered.util.math.LaserHitbox;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class GashslitDashAction {
    public static void execute(LevelAccessor world, GashslitEntity mob, double x, double y, double z) {
        int actionTicks = mob.actionTicks++;
        if (actionTicks == 0) {
            Entity target = mob.getTarget();
            mob.setNoGravity(true);
            double width = mob.getBbWidth();
            double moveDist = width * 8;
            Vec3 lookAngle = mob.getLookAngle();
            ServerLevelRelatedUtils.sendParticles((ServerLevel) world, ParticleTypes.CAMPFIRE_COSY_SMOKE, x, y + width * 1.666, z, 40, width * 0.5, width * 1.666, width * 0.5, 0.1);

            if (target != null) {
                mob.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(target.getX(), target.getEyeY(), target.getZ()));
                Vec2 moveDirection = new Vec2((float) (target.getX() - x), (float) (target.getZ() - z)).normalized();
                moveDist = Mth.clamp(mob.distanceTo(target), moveDist, moveDist * 3);
                mob.setDeltaMovement(new Vec3(moveDirection.x * moveDist * 0.575, 0, moveDirection.y * moveDist * 0.575));
            } else {
                mob.setDeltaMovement(new Vec3(lookAngle.x() * moveDist * 0.575, lookAngle.y() * moveDist * 0.5153, lookAngle.z() * moveDist * 0.575));
            }

            Vec3 lookVec = mob.getDeltaMovement();
            LaserHitbox hitbox = new LaserHitbox(x, y + width * 2, z, lookVec, lookVec.length(), width * 3);
            float attackDamage = (float) mob.getAttribute(Attributes.ATTACK_DAMAGE).getValue() * (mob.isIn2Phase() ? 1.46f : 3.53f);

            if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(mob.level(), mob)) {
                for (BlockPos blockpos : hitbox.getIntersectingBlocks()) {
                    BlockState blockstate = world.getBlockState(blockpos);
                    if (OtherStuff.canDestroy(blockstate) && net.minecraftforge.event.ForgeEventFactory.onEntityDestroyBlock(mob, blockpos, blockstate))
                        world.destroyBlock(blockpos, true, mob);
                }
            }

            List<LivingEntity> targets = world.getEntitiesOfClass(LivingEntity.class, hitbox.getBoundingAABB(), e -> true);
            for (LivingEntity entityiterator : targets) {
                if (entityiterator != mob && entityiterator.canBeSeenAsEnemy() && hitbox.isColliding(entityiterator.position(), entityiterator.getBbWidth(), entityiterator.getBbHeight())) {
                    entityiterator.hurt(mob.damageSources().mobAttack(mob), attackDamage);
                }
            }

            world.playSound(null, BlockPos.containing(x, y, z), InvasioncoderedsweegyportModSounds.GASH_DASH.get(), SoundSource.HOSTILE, 2, 1);
            ServerLevelRelatedUtils.sendRayRandomSpread((ServerLevel) world, InvasioncoderedsweegyportModParticleTypes.GASHSLIT_SMOKE_PARTICLE.get(), mob.position(), lookVec, lookVec.length(), 0.5,
                    new Vec3(width * 0.5, mob.getBbHeight() * 0.5, width * 0.5), 1);
            ServerLevelRelatedUtils.sendRayRandomSpread((ServerLevel) world, InvasioncoderedsweegyportModParticleTypes.GASHSLIT_SLASH_BARRAGE_PARTICLE.get(), mob.position(), lookVec, lookVec.length(), 3,
                    new Vec3(width * 0.5, mob.getBbHeight() * 0.5, width * 0.5), 1);
        } else if (actionTicks < 5) {
            Vec3 lookVec = mob.getDeltaMovement();
            if (lookVec.length() < 0.1) {
                mob.setActionState(0);
                mob.setInAction(false);
                mob.setSwingType(1, false);
                mob.setNoGravity(false);
            } else {
                double width = mob.getBbWidth();
                Vec3 startPos = new Vec3(x, y + width * 2, z);
                Vec3 maxEndPos = startPos.add(lookVec);
                BlockHitResult blockHitResult = world.clip(new ClipContext(startPos, maxEndPos, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, mob));
                double actualDistance = (blockHitResult.getType() == HitResult.Type.BLOCK) ? startPos.distanceTo(blockHitResult.getLocation()) : lookVec.length();

                LaserHitbox hitbox = new LaserHitbox(startPos, lookVec, actualDistance, width * 3);
                float attackDamage = (float) mob.getAttribute(Attributes.ATTACK_DAMAGE).getValue() * 2;
                List<LivingEntity> targets = world.getEntitiesOfClass(LivingEntity.class, hitbox.getBoundingAABB(), e -> true);
                for (LivingEntity entityiterator : targets) {
                    if (entityiterator != mob && entityiterator.canBeSeenAsEnemy() && hitbox.isColliding(entityiterator.position(), entityiterator.getBbWidth(), entityiterator.getBbHeight())) {
                        entityiterator.hurt(mob.damageSources().mobAttack(mob), attackDamage);
                    }
                }
                ServerLevelRelatedUtils.sendRayRandomSpread((ServerLevel) world, InvasioncoderedsweegyportModParticleTypes.GASHSLIT_SMOKE_PARTICLE.get(), mob.position(), lookVec, lookVec.length(), 0.5,
                        new Vec3(width * 0.5, mob.getBbHeight() * 0.5, width * 0.5), 1);
                ServerLevelRelatedUtils.sendRayRandomSpread((ServerLevel) world, InvasioncoderedsweegyportModParticleTypes.GASHSLIT_SLASH_BARRAGE_PARTICLE.get(), mob.position(), lookVec, lookVec.length(), 3,
                        new Vec3(width * 0.5, mob.getBbHeight() * 0.5, width * 0.5), 1);
            }
        } else {
            mob.setInAction(false);
            mob.setSwingType(1, false);
            mob.setDeltaMovement(mob.getDeltaMovement().scale(0.5));
            mob.setNoGravity(false);
            double anotherAttackChance = Math.random();
            if (anotherAttackChance < 0.2) {
                mob.setActionState(GashslitAnimationIndex.anims_shoot);
                mob.rangeAttackShootCount = (int) (Math.random() * 2) + 2;
            } else if (anotherAttackChance < 0.5) {
                mob.setActionState(GashslitAnimationIndex.anims_prepare);
            } else {
                mob.setActionState(0);
            }
        }
    }
}