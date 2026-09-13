package com.sweegy.invasioncodered.network;

import com.sweegy.invasioncodered.client.gui.BossBarClientSetup;
import com.sweegy.invasioncodered.InvasioncoderedsweegyportMod;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.entity.Mob;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.network.FriendlyByteBuf;

import java.util.function.Supplier;
import java.util.UUID;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SyncBossBarRenderPacket {
	private final UUID bossEventId;
	private final int renderType;

	public SyncBossBarRenderPacket(UUID bossEventId, int renderType) {
		this.bossEventId = bossEventId;
		this.renderType = renderType;
	}

	public SyncBossBarRenderPacket(FriendlyByteBuf buf) {
		this.bossEventId = buf.readUUID();
		this.renderType = buf.readInt();
	}

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeUUID(this.bossEventId);
		buf.writeInt(this.renderType);
	}

	public void handle(Supplier<NetworkEvent.Context> supplier) {
		NetworkEvent.Context context = supplier.get();
		context.enqueueWork(() -> {
			if (this.renderType == -1) {
				BossBarClientSetup.removeBossBarRender(this.bossEventId);
			} else {
				BossBarClientSetup.setBossBarRender(this.bossEventId, this.renderType);
			}
		});
		context.setPacketHandled(true);
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		InvasioncoderedsweegyportMod.addNetworkMessage(//
				SyncBossBarRenderPacket.class, //
				SyncBossBarRenderPacket::toBytes, //
				SyncBossBarRenderPacket::new, //
				SyncBossBarRenderPacket::handle//
		);
	}

	public static void setBossbarType(ServerPlayer player, ServerBossEvent bossInfo, int currentRenderType) {
		InvasioncoderedsweegyportMod.PACKET_HANDLER.//
				send(PacketDistributor.PLAYER.with(() -> player), //
						new SyncBossBarRenderPacket(bossInfo.getId(), currentRenderType));
	}

	public static void setBossbarType(Mob mob, ServerBossEvent bossInfo, int currentRenderType) {
		InvasioncoderedsweegyportMod.PACKET_HANDLER.//
				send(PacketDistributor.TRACKING_ENTITY.with(() -> mob), //
						new SyncBossBarRenderPacket(bossInfo.getId(), currentRenderType));
	}

	public static void removeBossbarType(ServerPlayer player, ServerBossEvent bossInfo) {
		InvasioncoderedsweegyportMod.PACKET_HANDLER.//
				send(PacketDistributor.PLAYER.with(() -> player), //
						new SyncBossBarRenderPacket(bossInfo.getId(), -1));
	}

	public static void removeBossbarType(Mob mob, ServerBossEvent bossInfo) {
		InvasioncoderedsweegyportMod.PACKET_HANDLER.//
				send(PacketDistributor.TRACKING_ENTITY.with(() -> mob), //
						new SyncBossBarRenderPacket(bossInfo.getId(), -1));
	}
}