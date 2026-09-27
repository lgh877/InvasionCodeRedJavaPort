package com.sweegy.invasioncodered.entity.gashslit.actions;

import com.sweegy.invasioncodered.entity.gashslit.GashslitEntity;
import com.sweegy.invasioncodered.util.math.RotatableHitbox;
import com.sweegy.invasioncodered.util.math.VectorHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class GashslitQuickAttackAction {
    public static void execute(LevelAccessor world, GashslitEntity mob, double x, double y, double z) {
        int actionTicks = mob.actionTicks++;
        if (actionTicks == 1) {
            mob.setAnimSpeed(1);
            mob.setSwingType((mob.quickAttackCount & 1), false);
            Vec3 lookVec = VectorHelper.calculateFlatViewVector(mob.yBodyRot);
            double width = mob.getBbWidth();
            Vec3 hitboxPos = new Vec3(x + lookVec.x() * width * 1.25, y + width * 2.083, z + lookVec.z() * width * 1.25);
            RotatableHitbox hitbox = new RotatableHitbox(hitboxPos, width * 2.5, width * 1.6667, width * 2.5, mob.yBodyRot, 0, 0);

            float attackDamage = (float) mob.getAttributeValue(Attributes.ATTACK_DAMAGE) * GashslitEntity.getAttackDamageScale(mob);
            List<LivingEntity> targets = world.getEntitiesOfClass(LivingEntity.class, hitbox.getBoundingAABB(), e -> true);
            for (LivingEntity entityiterator : targets) {
                if (entityiterator != mob && entityiterator.canBeSeenAsEnemy() && hitbox.isColliding(entityiterator.getBoundingBox())) {
                    entityiterator.invulnerableTime = 0;
                    entityiterator.hurt(mob.damageSources().mobAttack(mob), attackDamage);
                }
            }
            world.playSound(null, BlockPos.containing(hitboxPos.x(), hitboxPos.y(), hitboxPos.z()), SoundEvents.GLASS_BREAK, SoundSource.HOSTILE, 2, 0.16F);
            mob.push(lookVec.x() * 0.1, 0, lookVec.z() * 0.1);
        } else if (actionTicks > 3) {
            mob.setActionState(0);
            mob.setAnimSpeed(1);
            mob.setSwingType((mob.quickAttackCount & 1), false);
        }
    }
}