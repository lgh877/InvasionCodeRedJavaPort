package com.sweegy.invasioncodered.client.gui;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.Minecraft;

import java.util.function.Supplier;
import java.util.UUID;
import java.util.Map;
import java.util.HashMap;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class BossBarClientEvent {
	private static class BossBarState {
		CustomBossBar bossBar;
		int renderType;
		int lastSeenTick;

		BossBarState(CustomBossBar bossBar, int renderType, int lastSeenTick) {
			this.bossBar = bossBar;
			this.renderType = renderType;
			this.lastSeenTick = lastSeenTick;
		}
	}

	private static final Map<UUID, BossBarState> ACTIVE_BOSS_MAP = new HashMap<>();
	private static int clientTickCount = 0;

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void renderBossOverlay(CustomizeGuiOverlayEvent.BossEventProgress event) {
		UUID bossId = event.getBossEvent().getId();
		if (BossBarClientSetup.hasBossBarRenderType(bossId)) {
			int targetType = BossBarClientSetup.getBossBarRenderType(bossId);
			BossBarState state = ACTIVE_BOSS_MAP.get(bossId);
			if (state == null || state.renderType != targetType) {
				Supplier<CustomBossBar> supplier = CustomBossBar.customBossBars.get(targetType);
				if (supplier != null) {
					state = new BossBarState(supplier.get(), targetType, clientTickCount);
					ACTIVE_BOSS_MAP.put(bossId, state);
				}
			}
			if (state != null && state.bossBar != null) {
				event.setCanceled(true);
				state.bossBar.renderBossBar(event);
				state.lastSeenTick = clientTickCount;
			}
		}
	}

	@SubscribeEvent
	public static void onClientTick(TickEvent.ClientTickEvent event) {
		if (event.phase == TickEvent.Phase.END && Minecraft.getInstance().level != null) {
			clientTickCount++;
			ACTIVE_BOSS_MAP.values().removeIf(state -> (clientTickCount - state.lastSeenTick) > 100);
		}
	}

	@SubscribeEvent
	public static void onClientLogOut(ClientPlayerNetworkEvent.LoggingOut event) {
		ACTIVE_BOSS_MAP.clear();
		clientTickCount = 0;
	}
}