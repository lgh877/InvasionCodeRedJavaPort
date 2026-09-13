package com.sweegy.invasioncodered.event;

import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModAttributes;
import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModMobEffects;
import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModParticleTypes;
import com.sweegy.invasioncodered.util.ServerLevelRelatedUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class HpBasedEvent {

    private static final int SUSANOO_COOLDOWN_TICKS = 600;

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        LivingEntity entity = event.getEntity();

        if (entity.level().isClientSide()) {
            return;
        }

        float currentHealth = entity.getHealth();
        float finalDamage = event.getAmount();
        float predictedHealth = currentHealth - finalDamage;
        float maxHealth = entity.getMaxHealth();

        if (predictedHealth <= 0) {
            return;
        }

        long currentTime = entity.level().getGameTime();
        CompoundTag persistentData = entity.getPersistentData();

        activateSusanooState(event, entity, predictedHealth, maxHealth, currentTime, persistentData);
        // activateAnotherState(entity, predictedHealth, maxHealth, currentTime, persistentData);
    }

    private static void activateSusanooState(LivingDamageEvent event, LivingEntity entity, float predictedHealth, float maxHealth, long currentTime, CompoundTag persistentData) {
        if (predictedHealth < maxHealth * 0.5f) {
            AttributeInstance stateChecker = entity.getAttribute(InvasioncoderedsweegyportModAttributes.GASHSLIT_EQUITMENT_STATE_CHECKER.get());

            if (stateChecker != null && stateChecker.getValue() > 3.5) {
                long cooldownEndTime = persistentData.getLong("SusanooStateCooldown");

                if (currentTime > cooldownEndTime) {
                    event.setAmount(event.getAmount() * 0.1f);

                    entity.addEffect(new MobEffectInstance(InvasioncoderedsweegyportModMobEffects.CURSED_RAGE.get(), 300, 1, true, false));
                    persistentData.putLong("SusanooStateCooldown", currentTime + SUSANOO_COOLDOWN_TICKS);
                    ServerLevelRelatedUtils.sendParticles(
                            (ServerLevel) entity.level(),
                            InvasioncoderedsweegyportModParticleTypes.GASHSLIT_POPFLAME_PARTICLE.get(),
                            entity.getRandomX(entity.getBbWidth() * 0.5),
                            entity.getRandomY(),
                            entity.getRandomZ(entity.getBbWidth() * 0.5),
                            (int) (entity.getBbWidth() * entity.getBbHeight() * 20),
                            0, 0, 0, 0.2D);
                }
            }
        }
    }
}