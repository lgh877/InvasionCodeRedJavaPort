package com.sweegy.invasioncodered.event;

import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModMobEffects;
import com.sweegy.invasioncodered.item.RoseDiamondSwordItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import net.minecraft.world.entity.LivingEntity;

import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModAttributes;

@Mod.EventBusSubscriber
public class AttackDamageReductionEvent {
    @SubscribeEvent
    public static void onEntityAttacked(LivingHurtEvent event) {
        if (event != null && event.getEntity() != null) {
            LivingEntity entity = event.getEntity();
            float initialDamage = 0;
            initialDamage = event.getAmount();
            initialDamage = processGashslitEquipmentBonus(entity, initialDamage);
            initialDamage = processRoseDiamondSwordBlock(entity, initialDamage);
            event.setAmount(initialDamage);
        }
    }

    private static float processGashslitEquipmentBonus(LivingEntity entity, float amount) {
        AttributeInstance stateChecker = entity.getAttribute(InvasioncoderedsweegyportModAttributes.GASHSLIT_EQUITMENT_STATE_CHECKER.get());
        if (stateChecker != null && stateChecker.getValue() > 9) {
            amount = amount * 0.4f;
        }
        return amount;
    }

    private static float processRoseDiamondSwordBlock(LivingEntity entity, float amount) {
        if (!(entity instanceof Player player)) {
            return amount;
        }

        if (!RoseDiamondSwordItem.isBlocking(player)) {
            return amount;
        }

        if (amount < 0.0F) {
            return 0;
        }

        ItemStack mainHand = player.getMainHandItem();
        ItemStack offHand = player.getOffhandItem();

        int durabilityDamage = 1 + Mth.floor(amount) / 2;

        mainHand.hurtAndBreak(durabilityDamage, player, p -> p.broadcastBreakEvent(InteractionHand.MAIN_HAND));
        offHand.hurtAndBreak(durabilityDamage, player, p -> p.broadcastBreakEvent(InteractionHand.OFF_HAND));

        return 0;
    }
}