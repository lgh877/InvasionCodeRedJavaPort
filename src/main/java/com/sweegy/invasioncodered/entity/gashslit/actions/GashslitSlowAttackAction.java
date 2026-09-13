package com.sweegy.invasioncodered.entity.gashslit.actions;

import com.sweegy.invasioncodered.entity.gashslit.GashslitEntity;
import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModSounds;
import com.sweegy.invasioncodered.util.math.RotatableHitbox;
import com.sweegy.invasioncodered.util.math.VectorHelper;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

import static com.sweegy.invasioncodered.entity.gashslit.GashslitEntity.getAttackDamageScale;

public class GashslitSlowAttackAction {
    public static void handleSlowAttack(LevelAccessor world, GashslitEntity mob, double x, double y, double z) {
        int actionTicks = mob.actionTicks++;
        Entity target;
        if (actionTicks == 17) {
            mob.setDeltaMovement(new Vec3(0, 0.02, 0));
            mob.setSwingType(0, true);
            mob.setInAction(true);
        } else if (actionTicks == 18) {
            target = mob.getTarget();
            mob.setNoGravity(true);
            double width = mob.getBbWidth();
            if (target != null) {
                mob.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(target.getX(), target.getEyeY(), target.getZ()));
                Vec2 diffVec = new Vec2((float) (target.getX() - x), (float) (target.getZ() - z));
                double flatDist = Math.min((double) diffVec.length(), width * 6);
                diffVec = diffVec.normalized();
                mob.setDeltaMovement(new Vec3(diffVec.x * flatDist * 0.575, 0, diffVec.y * flatDist * 0.575));
            } else {
                Vec3 lookAngle = VectorHelper.calculateFlatViewVector(mob.yBodyRot);
                mob.setDeltaMovement(new Vec3(lookAngle.x() * width, 0, lookAngle.z() * width));
            }
        } else if (actionTicks == 20) {
            mob.setNoGravity(false);
            mob.setDeltaMovement(mob.getDeltaMovement().scale(0.5));
            mob.setInAction(false);
            Vec3 lookVec = VectorHelper.calculateFlatViewVector(mob.yBodyRot);
            double width = mob.getBbWidth();
            Vec3 hitboxPos = new Vec3(x + lookVec.x() * width * 1.25, y + width * 2.083, z + lookVec.z() * width * 1.25);
            RotatableHitbox hitbox = new RotatableHitbox(hitboxPos, width * 2.5, width * 1.6667, width * 2.5, mob.yBodyRot, 0, 0);

            float attackDamage = (float) mob.getAttributeValue(Attributes.ATTACK_DAMAGE) * getAttackDamageScale(mob);
            List<LivingEntity> targets = world.getEntitiesOfClass(LivingEntity.class, hitbox.getBoundingAABB(), e -> true);
            for (LivingEntity entityiterator : targets) {
                if (entityiterator != mob && entityiterator.canBeSeenAsEnemy() && hitbox.isColliding(entityiterator.getBoundingBox())) {
                    entityiterator.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.MOB_ATTACK), mob), attackDamage);
                }
            }
            world.playSound(null, BlockPos.containing(hitboxPos.x(), hitboxPos.y(), hitboxPos.z()), InvasioncoderedsweegyportModSounds.GASH_SLOWATTACK.get(), SoundSource.HOSTILE, 2,
                    1);
        } else if (actionTicks == 25) {
            mob.setSwingType(0, false);
        } else if (actionTicks > 30) {
            mob.setNoGravity(false);
            mob.setActionState(0);
            target = mob.getTarget();
            if (target != null) GashslitActionSelector.INSTANCE.evaluateAndChoose(mob, target, x, y, z);
        }
    }
}
