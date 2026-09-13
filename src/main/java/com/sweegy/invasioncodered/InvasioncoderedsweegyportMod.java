package com.sweegy.invasioncodered;

import net.minecraft.world.entity.raid.Raid;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.util.thread.SidedThreadGroups;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.server.TickTask;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;

import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.BiConsumer;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Comparator;

import it.unimi.dsi.fastutil.ints.IntObjectPair;
import it.unimi.dsi.fastutil.ints.IntObjectImmutablePair;

import com.sweegy.invasioncodered.init.*;

@Mod("invasioncodered")
public class InvasioncoderedsweegyportMod {
	//public static final Logger LOGGER = LogManager.getLogger(InvasioncoderedsweegyportMod.class);
	public static final String MODID = "invasioncodered";

	public InvasioncoderedsweegyportMod(FMLJavaModLoadingContext context) {
		// Start of user code block mod constructor
		// End of user code block mod constructor
		MinecraftForge.EVENT_BUS.register(this);
		IEventBus bus = context.getModEventBus();
		InvasioncoderedsweegyportModSounds.REGISTRY.register(bus);
		InvasioncoderedsweegyportModItems.REGISTRY.register(bus);
		InvasioncoderedsweegyportModEntities.REGISTRY.register(bus);
		InvasioncoderedsweegyportModTabs.REGISTRY.register(bus);
		InvasioncoderedsweegyportModParticleTypes.REGISTRY.register(bus);
		InvasioncoderedsweegyportModAttributes.REGISTRY.register(bus);
        InvasioncoderedsweegyportModMobEffects.REGISTRY.register(bus);
		// Start of user code block mod init
		// End of user code block mod init
	}

	// Start of user code block mod methods
	// End of user code block mod methods
	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(ResourceLocation.fromNamespaceAndPath(MODID, MODID), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
	private static int messageID = 0;

	public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
		PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
		messageID++;
	}
}