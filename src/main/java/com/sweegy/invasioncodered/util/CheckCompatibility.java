package com.sweegy.invasioncodered.util;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@Mod.EventBusSubscriber(modid = "invasioncoderedsweegyport", bus = Mod.EventBusSubscriber.Bus.MOD)
public class CheckCompatibility {
	public static boolean IS_EPIC_FIGHT_LOADED = false;
	@SubscribeEvent
	public static void onCommonSetup(FMLCommonSetupEvent event) {
		IS_EPIC_FIGHT_LOADED = ModList.get().isLoaded("epicfight");
	}
}