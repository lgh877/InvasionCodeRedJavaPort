package com.sweegy.invasioncodered.util;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.tags.BlockTags;

import java.util.UUID;

public class OtherStuff {
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
}