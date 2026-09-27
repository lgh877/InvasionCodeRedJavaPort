package com.sweegy.invasioncodered.client.gui;

import com.sweegy.invasioncodered.InvasioncoderedsweegyportMod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import java.util.concurrent.ConcurrentHashMap;
import java.util.UUID;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = InvasioncoderedsweegyportMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BossBarClientSetup {
	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		MinecraftForge.EVENT_BUS.register(new BossBarClientEvent());
	}

	public static final Map<UUID, Integer> bossBarRenderTypes = new ConcurrentHashMap<>();

	public static void removeBossBarRender(UUID bossBar) {
		bossBarRenderTypes.remove(bossBar);
	}

	public static void setBossBarRender(UUID bossBar, int renderType) {
		bossBarRenderTypes.put(bossBar, renderType);
	}

	public static Integer getBossBarRenderType(UUID bossBar) {
		return bossBarRenderTypes.get(bossBar);
	}

	public static boolean hasBossBarRenderType(UUID bossBar) {
		return bossBarRenderTypes.containsKey(bossBar);
	}
}