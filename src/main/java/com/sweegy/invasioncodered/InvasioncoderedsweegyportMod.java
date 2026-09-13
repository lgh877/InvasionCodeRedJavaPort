package com.sweegy.invasioncodered;

import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;

import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.BiConsumer;

import com.sweegy.invasioncodered.init.*;

@Mod("invasioncodered")
public class InvasioncoderedsweegyportMod {
	//public static final Logger LOGGER = LogManager.getLogger(InvasioncoderedsweegyportMod.class);
	public static final String MODID = "invasioncodered";

	public InvasioncoderedsweegyportMod(FMLJavaModLoadingContext context) {
		MinecraftForge.EVENT_BUS.register(this);
		IEventBus bus = context.getModEventBus();
		InvasioncoderedsweegyportModSounds.REGISTRY.register(bus);
		InvasioncoderedsweegyportModItems.REGISTRY.register(bus);
		InvasioncoderedsweegyportModEntities.REGISTRY.register(bus);
		InvasioncoderedsweegyportModTabs.REGISTRY.register(bus);
		InvasioncoderedsweegyportModParticleTypes.REGISTRY.register(bus);
		InvasioncoderedsweegyportModAttributes.REGISTRY.register(bus);
        InvasioncoderedsweegyportModMobEffects.REGISTRY.register(bus);
	}

	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(ResourceLocation.fromNamespaceAndPath(MODID, MODID), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
	private static int messageID = 0;

	public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
		PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
		messageID++;
	}
}