package com.sweegy.invasioncodered.event.susanoo;

import com.sweegy.invasioncodered.InvasioncoderedsweegyportMod;
import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModAttributes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;

@Mod.EventBusSubscriber(modid = InvasioncoderedsweegyportMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class SusanooAlphaManager {
    private static final float MAX_ALPHA = 0.15f;
    private static final float FADE_SPEED = 0.0075f;

    private static final boolean FADE_OUT_ENABLED = true;

    public static class AlphaState {
        public float prevAlpha = 0.0f;
        public float currentAlpha = 0.0f;
    }

    private static final Map<LivingEntity, AlphaState> ALPHA_MAP = new IdentityHashMap<>();
    private static ClientLevel trackedLevel;

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }
        Minecraft mc = Minecraft.getInstance();
        ClientLevel level = synchronizeLevel(mc);
        if (level == null || mc.isPaused()) {
            return;
        }
        if (mc.player != null && isCurrentEntity(mc.player, level, mc) && checkEntityCondition(mc.player)) {
            ALPHA_MAP.computeIfAbsent(mc.player, entity -> new AlphaState());
        }
        Iterator<Map.Entry<LivingEntity, AlphaState>> iterator = ALPHA_MAP.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<LivingEntity, AlphaState> entry = iterator.next();
            LivingEntity entity = entry.getKey();
            if (!isCurrentEntity(entity, level, mc)) {
                iterator.remove();
                continue;
            }
            boolean active = checkEntityCondition(entity);
            AlphaState state = entry.getValue();
            tickState(state, active);
            if (!active && state.currentAlpha <= 0.0f) {
                iterator.remove();
            }
        }
    }

    private static boolean checkEntityCondition(LivingEntity entity) {
        if (!entity.isAlive()) {
            return false;
        }
        AttributeInstance attribute = entity.getAttribute(InvasioncoderedsweegyportModAttributes.GASHSLIT_EQUITMENT_STATE_CHECKER.get());
        if (attribute == null) {
            return false;
        }
        double value = attribute.getValue();
        return value > 9.0D;
    }

    private static void tickState(AlphaState state, boolean active) {
        state.prevAlpha = state.currentAlpha;
        if (active) {
            state.currentAlpha = Math.min(MAX_ALPHA, state.currentAlpha + FADE_SPEED);
        } else if (FADE_OUT_ENABLED) {
            state.currentAlpha = Math.max(0.0f, state.currentAlpha - FADE_SPEED);
        } else {
            state.prevAlpha = 0.0f;
            state.currentAlpha = 0.0f;
        }
    }

    public static float getInterpolatedAlpha(LivingEntity entity, float partialTicks) {
        Minecraft mc = Minecraft.getInstance();
        ClientLevel level = synchronizeLevel(mc);
        if (level == null || !isCurrentEntity(entity, level, mc)) {
            ALPHA_MAP.remove(entity);
            return 0.0f;
        }
        boolean active = checkEntityCondition(entity);
        if (!active && !FADE_OUT_ENABLED) {
            ALPHA_MAP.remove(entity);
            return 0.0f;
        }
        AlphaState state = ALPHA_MAP.get(entity);
        if (state == null) {
            if (!active) {
                return 0.0f;
            }
            state = new AlphaState();
            ALPHA_MAP.put(entity, state);
        }
        if (mc.isPaused()) {
            return state.currentAlpha;
        }
        float interpolation = Float.isFinite(partialTicks) ? Mth.clamp(partialTicks, 0.0f, 1.0f) : 0.0f;
        return Mth.lerp(interpolation, state.prevAlpha, state.currentAlpha);
    }

    private static ClientLevel synchronizeLevel(Minecraft mc) {
        ClientLevel level = mc.level;
        if (trackedLevel != level) {
            ALPHA_MAP.clear();
            trackedLevel = level;
        }
        return level;
    }

    private static boolean isCurrentEntity(LivingEntity entity, ClientLevel level, Minecraft mc) {
        if (entity.isRemoved() || entity.level() != level) {
            return false;
        }
        return entity == mc.player || level.getEntity(entity.getId()) == entity;
    }
}
