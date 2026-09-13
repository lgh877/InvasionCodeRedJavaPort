package com.sweegy.invasioncodered.event.susanoo;

import com.sweegy.invasioncodered.InvasioncoderedsweegyportMod;
import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModAttributes;
import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModMobEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = InvasioncoderedsweegyportMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class SusanooAlphaManager {
    private static final float MAX_ALPHA = 0.15f;
    private static final float FADE_SPEED = 0.0075f;

    public static class AlphaState {
        public float prevAlpha = 0f;
        public float currentAlpha = 0f;
    }

    private static final Map<UUID, AlphaState> ALPHA_MAP = new HashMap<>();

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getInstance();
        ClientLevel level = mc.level;

        if (level == null) {
            ALPHA_MAP.clear();
            return;
        }

        if (mc.isPaused()) {
            return;
        }

        LocalPlayer localPlayer = mc.player;

        if (localPlayer != null) {
            boolean active = checkEntityCondition(localPlayer);
            if (active || ALPHA_MAP.containsKey(localPlayer.getUUID())) {
                AlphaState state = ALPHA_MAP.computeIfAbsent(localPlayer.getUUID(), k -> new AlphaState());
                tickState(state, active);
                if (!active && state.currentAlpha < 0f) {
                    ALPHA_MAP.remove(localPlayer.getUUID());
                }
            }
        }

        Iterator<Map.Entry<UUID, AlphaState>> iterator = ALPHA_MAP.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<UUID, AlphaState> entry = iterator.next();
            UUID uuid = entry.getKey();

            if (localPlayer != null && localPlayer.getUUID().equals(uuid)) {
                continue;
            }

            AlphaState state = entry.getValue();
            Entity entity = findEntityByUUID(level, uuid);
            boolean shouldBeActive = false;

            if (entity instanceof LivingEntity living && living.isAlive()) {
                shouldBeActive = checkEntityCondition(living);
            }

            tickState(state, shouldBeActive);

            if (state.currentAlpha < 0f && (entity == null || !entity.isAlive())) {
                iterator.remove();
            }
        }
    }

    private static boolean checkEntityCondition(LivingEntity living) {
        AttributeInstance attr = living.getAttribute(InvasioncoderedsweegyportModAttributes.GASHSLIT_EQUITMENT_STATE_CHECKER.get());
        return attr != null && attr.getValue() > 9;
    }

    private static void tickState(AlphaState state, boolean shouldBeActive) {
        state.prevAlpha = state.currentAlpha;
        if (shouldBeActive) {
            state.currentAlpha = Math.min(MAX_ALPHA, state.currentAlpha + FADE_SPEED);
        } else {
            state.currentAlpha = Math.max(0f, state.currentAlpha - FADE_SPEED);
        }
    }

    public static float getInterpolatedAlpha(LivingEntity entity, float partialTicks) {
        UUID uuid = entity.getUUID();
        AlphaState state = ALPHA_MAP.get(uuid);

        if (state == null) {
            if (checkEntityCondition(entity)) {
                state = new AlphaState();
                ALPHA_MAP.put(uuid, state);
            } else {
                return 0f;
            }
        }

        if (Minecraft.getInstance().isPaused()) {
            return state.currentAlpha;
        }

        return Mth.lerp(partialTicks, state.prevAlpha, state.currentAlpha);
    }

    private static Entity findEntityByUUID(ClientLevel level, UUID uuid) {
        Entity player = level.getPlayerByUUID(uuid);
        if (player != null) return player;

        for (Entity entity : level.entitiesForRendering()) {
            if (entity.getUUID().equals(uuid)) {
                return entity;
            }
        }
        return null;
    }
}