package com.sweegy.invasioncodered.event;

import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModAttributes;

@Mod.EventBusSubscriber
public class AttackDamageBonusEvent {

    @SubscribeEvent
    public static void onEntityAttacked(LivingHurtEvent event) {
        if (event != null && event.getSource() != null && event.getSource().getEntity() != null) {
            Entity sourceEntity = event.getSource().getEntity();

            if (sourceEntity instanceof LivingEntity attacker) {
                float initialDamage = event.getAmount();

                initialDamage = processGashslitAttackBonus(attacker, initialDamage);

                event.setAmount(initialDamage);
            }
        }
    }

    private static float processGashslitAttackBonus(LivingEntity attacker, float amount) {
        AttributeInstance stateChecker = attacker.getAttribute(InvasioncoderedsweegyportModAttributes.GASHSLIT_EQUITMENT_STATE_CHECKER.get());
        if (stateChecker != null && stateChecker.getValue() > 9) {
            amount = amount * 1.2f;
        }
        return amount;
    }
}