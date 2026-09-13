package com.sweegy.invasioncodered.entity.gashslit.actions;

import com.sweegy.invasioncodered.client.model.animations.indices.GashslitAnimationIndex;
import com.sweegy.invasioncodered.entity.gashslit.GashslitEntity;
import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModParticleTypes;
import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModSounds;
import com.sweegy.invasioncodered.util.ServerLevelRelatedUtils;
import com.sweegy.invasioncodered.util.math.LaserHitbox;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class GashslitBarrageChargeAction {
    public static void execute(LevelAccessor world, GashslitEntity mob, double x, double y, double z) {
        if (mob.barrageAtCooltime < 36) {
            Entity target = mob.getTarget();
            if (target != null) {
                mob.lookAt(target.getEyePosition(), mob.barrageAtCooltime * 5);
            }
            mob.barrageAtCooltime++;
            mob.setActionState(GashslitAnimationIndex.anims_charge);
            Vec3 lookVec = mob.getLookAngle();
            mob.setAnimSpeed((int) (Math.random() * 5) + 4);
            mob.setDeltaMovement(new Vec3(lookVec.x() * 3, lookVec.y() * 0.5, lookVec.z() * 3));
            lookVec = mob.position().subtract(mob.prevPos);
            double width = mob.getBbWidth();
            LaserHitbox hitbox = new LaserHitbox(x, y + width * 2, z, lookVec, lookVec.length(), width * 3);

            float attackDamage = (float) mob.getAttribute(Attributes.ATTACK_DAMAGE).getValue() * 1.53f;
            List<LivingEntity> targets = world.getEntitiesOfClass(LivingEntity.class, hitbox.getBoundingAABB(), e -> true);
            for (LivingEntity entityiterator : targets) {
                if (entityiterator != mob && entityiterator.canBeSeenAsEnemy() && hitbox.isColliding(entityiterator.position(), entityiterator.getBbWidth(), entityiterator.getBbHeight())) {
                    entityiterator.invulnerableTime = 0;
                    entityiterator.hurt(mob.damageSources().mobAttack(mob), attackDamage);
                }
            }
            world.playSound(null, BlockPos.containing(x, y, z), InvasioncoderedsweegyportModSounds.GASH_SLASH_BARRAGE.get(), SoundSource.HOSTILE, 2, (float) (0.5 + Math.random()));
            ServerLevelRelatedUtils.sendRayRandomSpread((ServerLevel) world, InvasioncoderedsweegyportModParticleTypes.GASHSLIT_SLASH_BARRAGE_PARTICLE.get(), mob.position(), lookVec, lookVec.length(), 1.5,
                    new Vec3(width * 0.5, mob.getBbHeight() * 0.5, width * 0.5), 1);
            mob.prevPos = mob.position();
        } else {
            mob.setActionState(0);
            mob.setInAction(false);
            mob.setAnimSpeed(1);
            mob.barrageAtCooltime = 0;
            mob.setDeltaMovement(mob.getDeltaMovement().scale(0.3));
        }
    }
}
