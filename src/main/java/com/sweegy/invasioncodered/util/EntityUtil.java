package com.sweegy.invasioncodered.util;

import com.sweegy.invasioncodered.entity.stonecutterman.StoneCutterManEntity;
import com.sweegy.invasioncodered.util.math.VectorHelper;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.phys.Vec3;

import java.util.UUID;

public class EntityUtil {
	public static boolean canDestroy(BlockState p_31492_) {
		return !p_31492_.isAir() && !p_31492_.is(BlockTags.WITHER_IMMUNE);
	}

    //source from : https://github.com/lender544/new1.20.1/blob/master/src/main/java/com/github/L_Ender/cataclysm/entity/Pet/AnimationPet.java#L67
    public static void setConfigattribute(LivingEntity entity, double hpconfig, double dmgconfig) {
        AttributeInstance maxHealthAttr = entity.getAttribute(Attributes.MAX_HEALTH);
        if (maxHealthAttr != null) {
            double difference = maxHealthAttr.getBaseValue() * hpconfig - maxHealthAttr.getBaseValue();
            maxHealthAttr.addTransientModifier(new AttributeModifier(UUID.fromString("36b1441b-4dd7-4ba3-90a0-0618bb37dede"), "Health config multiplier", difference, AttributeModifier.Operation.ADDITION));
            entity.setHealth(entity.getMaxHealth());
        }
        AttributeInstance attackDamageAttr = entity.getAttribute(Attributes.ATTACK_DAMAGE);
        if (attackDamageAttr != null) {
            double difference = attackDamageAttr.getBaseValue() * dmgconfig - attackDamageAttr.getBaseValue();
            attackDamageAttr.addTransientModifier(new AttributeModifier(UUID.fromString("6920e51b-c80a-4482-831c-f630a35fa2d7"), "Attack config multiplier", difference, AttributeModifier.Operation.ADDITION));
        }
    }

    public static void setConfigattribute(LivingEntity entity, double hpconfig) {
        AttributeInstance maxHealthAttr = entity.getAttribute(Attributes.MAX_HEALTH);
        if (maxHealthAttr != null) {
            double difference = maxHealthAttr.getBaseValue() * hpconfig - maxHealthAttr.getBaseValue();
            maxHealthAttr.addTransientModifier(new AttributeModifier(UUID.fromString("36b1441b-4dd7-4ba3-90a0-0618bb37dede"), "Health config multiplier", difference, AttributeModifier.Operation.ADDITION));
            entity.setHealth(entity.getMaxHealth());
        }
    }

    public static void lookAt(PathfinderMob mob, Vec3 p_20034_, int limit) {
        Vec3 vec3 = mob.getEyePosition();
        double d0 = p_20034_.x - vec3.x;
        double d1 = p_20034_.y - vec3.y;
        double d2 = p_20034_.z - vec3.z;
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);
        mob.setXRot(rotlerp(mob.getXRot(), Mth.wrapDegrees((float) (-(Mth.atan2(d1, d3) * (double) (180F / (float) Math.PI)))), limit));
        mob.setYRot(rotlerp(mob.getYRot(), Mth.wrapDegrees((float) (Mth.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F), limit));
        mob.setYHeadRot(mob.getYRot());
        mob.xRotO = mob.getXRot();
        mob.yRotO = mob.getYRot();
        mob.yHeadRotO = mob.yHeadRot;
        mob.yBodyRot = mob.yHeadRot;
        mob.yBodyRotO = mob.yBodyRot;
    }

    public static float rotlerp(float p_24992_, float p_24993_, float p_24994_) {
        float f = Mth.wrapDegrees(p_24993_ - p_24992_);
        if (f > p_24994_) {
            f = p_24994_;
        }
        if (f < -p_24994_) {
            f = -p_24994_;
        }
        float f1 = p_24992_ + f;
        if (f1 < 0.0F) {
            f1 += 360.0F;
        } else if (f1 > 360.0F) {
            f1 -= 360.0F;
        }
        return f1;
    }

    public static void applyKnockback(LivingEntity livingEntity,
                                      LivingEntity target, double knockback) {
        Vec3 flatLookVec =
                VectorHelper.calculateFlatViewVector(livingEntity.yBodyRot);

        target.knockback(
                knockback,
                -flatLookVec.x(),
                -flatLookVec.z()
        );
    }
}