/**
 * The code of this mod element is always locked.
 *
 * You can register new events in this class too.
 *
 * If you want to make a plain independent class, create it using
 * Project Browser -> New... and make sure to make the class
 * outside com.sweegy.invasioncoderedjavaport as this package is managed by MCreator.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
 *
 * This class will be added in the mod root package.
*/
package com.sweegy.invasioncodered.util;

import net.minecraft.world.phys.Vec3;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.protocol.game.ClientboundLevelParticlesPacket;
import net.minecraft.core.particles.ParticleOptions;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class ServerLevelRelatedUtils {
	private static final Random RANDOM = new Random();

	private static List<ServerPlayer> getNearbyPlayers(ServerLevel level, Vec3 pos) {
		List<ServerPlayer> validPlayers = new ArrayList<>();
		for (ServerPlayer player : level.players()) {
			if (player.level() == level && player.position().distanceToSqr(pos) < 262144.0D) { // 512^2
				validPlayers.add(player);
			}
		}
		return validPlayers;
	}

	private static void sendPacketsToPlayers(List<ServerPlayer> players, List<ClientboundLevelParticlesPacket> packets) {
		for (ServerPlayer player : players) {
			for (ClientboundLevelParticlesPacket packet : packets) {
				player.connection.send(packet);
			}
		}
	}

	public static <T extends ParticleOptions> void sendRandomSpreadRandomSpeed(ServerLevel level, T particle, Vec3 pos, Vec3 spread, double randSpeed, int count) {
		List<ServerPlayer> players = getNearbyPlayers(level, pos);
		if (players.isEmpty())
			return;
		ClientboundLevelParticlesPacket packet = new ClientboundLevelParticlesPacket(particle, true, pos.x, pos.y, pos.z, (float) spread.x, (float) spread.y, (float) spread.z, (float) randSpeed, count);
		for (ServerPlayer player : players)
			player.connection.send(packet);
	}

	public static <T extends ParticleOptions> void sendRandomSpreadDirectedSpeed(ServerLevel level, T particle, Vec3 pos, Vec3 spread, Vec3 velocity, double speedMult, int count) {
		List<ServerPlayer> players = getNearbyPlayers(level, pos);
		if (players.isEmpty())
			return;
		List<ClientboundLevelParticlesPacket> packets = new ArrayList<>();
		Vec3 normVel = velocity.normalize();
		for (int i = 0; i < count; i++) {
			double rX = pos.x + RANDOM.nextGaussian() * spread.x;
			double rY = pos.y + RANDOM.nextGaussian() * spread.y;
			double rZ = pos.z + RANDOM.nextGaussian() * spread.z;
			packets.add(new ClientboundLevelParticlesPacket(particle, true, rX, rY, rZ, (float) normVel.x, (float) normVel.y, (float) normVel.z, (float) speedMult, 0));
		}
		sendPacketsToPlayers(players, packets);
	}

	public static <T extends ParticleOptions> void sendExactDirectedSpeed(ServerLevel level, T particle, Vec3 pos, Vec3 velocity, double speedMult) {
		List<ServerPlayer> players = getNearbyPlayers(level, pos);
		if (players.isEmpty())
			return;
		Vec3 normVel = velocity.normalize();
		ClientboundLevelParticlesPacket packet = new ClientboundLevelParticlesPacket(particle, true, pos.x, pos.y, pos.z, (float) normVel.x, (float) normVel.y, (float) normVel.z, (float) speedMult, 0);
		for (ServerPlayer player : players)
			player.connection.send(packet);
	}

	public static <T extends ParticleOptions> void sendRayRandomSpreadRandomSpeed(ServerLevel level, T particle, Vec3 startPos, Vec3 rayDir, double length, double stepSpace, Vec3 spread, double randSpeed, int countPerStep) {
		List<ServerPlayer> players = getNearbyPlayers(level, startPos);
		if (players.isEmpty())
			return;
		List<ClientboundLevelParticlesPacket> packets = new ArrayList<>();
		Vec3 normRay = rayDir.normalize();
		for (double d = 0; d < length; d += stepSpace) {
			packets.add(new ClientboundLevelParticlesPacket(particle, true, startPos.x + normRay.x * d, startPos.y + normRay.y * d, startPos.z + normRay.z * d, (float) spread.x, (float) spread.y, (float) spread.z, (float) randSpeed, countPerStep));
		}
		sendPacketsToPlayers(players, packets);
	}

	public static <T extends ParticleOptions> void sendRayRandomSpreadDirectedSpeed(ServerLevel level, T particle, Vec3 startPos, Vec3 rayDir, double length, double stepSpace, Vec3 spread, Vec3 velocity, double speedMult, int countPerStep) {
		List<ServerPlayer> players = getNearbyPlayers(level, startPos);
		if (players.isEmpty())
			return;
		List<ClientboundLevelParticlesPacket> packets = new ArrayList<>();
		Vec3 normRay = rayDir.normalize();
		Vec3 normVel = velocity.normalize();
		for (double d = 0; d < length; d += stepSpace) {
			double pX = startPos.x + normRay.x * d;
			double pY = startPos.y + normRay.y * d;
			double pZ = startPos.z + normRay.z * d;
			for (int i = 0; i < countPerStep; i++) {
				double rX = pX + RANDOM.nextGaussian() * spread.x;
				double rY = pY + RANDOM.nextGaussian() * spread.y;
				double rZ = pZ + RANDOM.nextGaussian() * spread.z;
				packets.add(new ClientboundLevelParticlesPacket(particle, true, rX, rY, rZ, (float) normVel.x, (float) normVel.y, (float) normVel.z, (float) speedMult, 0));
			}
		}
		sendPacketsToPlayers(players, packets);
	}

	public static <T extends ParticleOptions> void sendRayRandomSpread(ServerLevel level, T particle, Vec3 startPos, Vec3 rayDir, double length, double stepSpace, Vec3 spread, int countPerStep) {
		List<ServerPlayer> players = getNearbyPlayers(level, startPos);
		if (players.isEmpty())
			return;
		List<ClientboundLevelParticlesPacket> packets = new ArrayList<>();
		Vec3 normRay = rayDir.normalize();
		for (double d = 0; d < length; d += stepSpace) {
			double pX = startPos.x + normRay.x * d;
			double pY = startPos.y + normRay.y * d;
			double pZ = startPos.z + normRay.z * d;
			for (int i = 0; i < countPerStep; i++) {
				double rX = pX + RANDOM.nextGaussian() * spread.x;
				double rY = pY + RANDOM.nextGaussian() * spread.y;
				double rZ = pZ + RANDOM.nextGaussian() * spread.z;
				packets.add(new ClientboundLevelParticlesPacket(particle, true, rX, rY, rZ, 0, 0, 0, 0, 0));
			}
		}
		sendPacketsToPlayers(players, packets);
	}

	public static <T extends ParticleOptions> void sendRayExactDirectedSpeed(ServerLevel level, T particle, Vec3 startPos, Vec3 rayDir, double length, double stepSpace, Vec3 velocity, double speedMult) {
		List<ServerPlayer> players = getNearbyPlayers(level, startPos);
		if (players.isEmpty())
			return;
		List<ClientboundLevelParticlesPacket> packets = new ArrayList<>();
		Vec3 normRay = rayDir.normalize();
		Vec3 normVel = velocity.normalize();
		for (double d = 0; d < length; d += stepSpace) {
			packets.add(new ClientboundLevelParticlesPacket(particle, true, startPos.x + normRay.x * d, startPos.y + normRay.y * d, startPos.z + normRay.z * d, (float) normVel.x, (float) normVel.y, (float) normVel.z, (float) speedMult, 0));
		}
		sendPacketsToPlayers(players, packets);
	}

	public static <T extends ParticleOptions> void sendParticles(ServerLevel serverLevel, T particleOptions, double posX, double posY, double posZ, int particleCount, double randX, double randY, double randZ, double randSpeed) {
		ClientboundLevelParticlesPacket packet = new ClientboundLevelParticlesPacket(particleOptions, true, posX, posY, posZ, (float) randX, (float) randY, (float) randZ, (float) randSpeed, particleCount);
		Vec3 pos = new Vec3(posX, posY, posZ);
		List<ServerPlayer> nearbyPlayers = getNearbyPlayers(serverLevel, pos);
		for (ServerPlayer player : nearbyPlayers) {
			player.connection.send(packet);
		}
	}
}