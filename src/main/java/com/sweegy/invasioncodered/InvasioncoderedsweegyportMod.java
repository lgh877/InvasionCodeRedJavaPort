package com.sweegy.invasioncodered;

import com.sweegy.invasioncodered.config.InvasionCodeRedConfig;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.entity.raid.Raider;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;

import java.util.List;
import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.BiConsumer;

import com.sweegy.invasioncodered.init.*;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("invasioncodered")
public class InvasioncoderedsweegyportMod {
	public static final Logger LOGGER = LogManager.getLogger(InvasioncoderedsweegyportMod.class);
	public static final String MODID = "invasioncodered";

	public InvasioncoderedsweegyportMod(FMLJavaModLoadingContext context) {
        context.registerConfig(ModConfig.Type.COMMON, InvasionCodeRedConfig.SPEC, "InvasionCodeRedConfig.toml");
		MinecraftForge.EVENT_BUS.register(this);
		IEventBus bus = context.getModEventBus();
		InvasioncoderedsweegyportModSounds.REGISTRY.register(bus);
		InvasioncoderedsweegyportModItems.REGISTRY.register(bus);
		InvasioncoderedsweegyportModEntities.REGISTRY.register(bus);
		InvasioncoderedsweegyportModTabs.REGISTRY.register(bus);
		InvasioncoderedsweegyportModParticleTypes.REGISTRY.register(bus);
		InvasioncoderedsweegyportModAttributes.REGISTRY.register(bus);
        InvasioncoderedsweegyportModMobEffects.REGISTRY.register(bus);
        bus.addListener(this::setupCommon);
    }

    private void setupCommon(final FMLCommonSetupEvent event) {
        event.enqueueWork(this::registerRaiders);
    }

    private void registerRaiders() {
        List<? extends String> raiders = InvasionCodeRedConfig.CUSTOM_RAIDER_LIST.get();

        for (String entry : raiders) {
            try {
                String[] parts = entry.split("\\|");
                if (parts.length != 2) {
                    LOGGER.warn("[InvasionCodeRed] Wrong uniform detacted: {}", entry);
                    continue;
                }

                String entityId = parts[0].trim();
                String[] waveCounts = parts[1].split(",");

                // 3. 숫자(int) 배열 생성
                int[] waves = new int[waveCounts.length];
                for (int i = 0; i < waveCounts.length; i++) {
                    waves[i] = Integer.parseInt(waveCounts[i].trim());
                }

                ResourceLocation resLoc = ResourceLocation.parse(entityId);

                EntityType<?> rawEntityType = ForgeRegistries.ENTITY_TYPES.getValue(resLoc);

                if (rawEntityType != null) {
                    String enumName = "INVASIONCODERED_" + resLoc.getPath().toUpperCase();

                    @SuppressWarnings("unchecked")
                    EntityType<? extends Raider> raiderEntityType = (EntityType<? extends Raider>) rawEntityType;

                    Raid.RaiderType.create(enumName, raiderEntityType, waves);
                } else {
                    LOGGER.error("[InvasionCodeRed] skipped this entity because couldn't find it: " + entityId);
                }
            } catch (Exception e) {
                LOGGER.error("[InvasionCodeRed] error occurred while parsing raider regisry name: " + entry);
                e.printStackTrace();
            }
        }
    }

	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(ResourceLocation.fromNamespaceAndPath(MODID, MODID), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
	private static int messageID = 0;

	public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
		PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
		messageID++;
	}
}