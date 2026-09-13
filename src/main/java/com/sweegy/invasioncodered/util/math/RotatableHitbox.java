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
package com.sweegy.invasioncodered.util.math;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.BlockPos;

import java.util.List;
import java.util.ArrayList;

import it.unimi.dsi.fastutil.longs.LongSet;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;

public class RotatableHitbox {
	private Vec3 center;
	private Vec3 halfExtents;
	private Vec3[] axes = new Vec3[3];
	private static final double EPSILON = 1e-6;

	public RotatableHitbox(Vec3 center, //
			double sizeX, double sizeY, double sizeZ, //
			double yaw, double pitch, double roll) {
		this.center = center;
		this.halfExtents = new Vec3(sizeX / 2.0, sizeY / 2.0, sizeZ / 2.0);
		updateRotation(yaw, pitch, roll);
	}

	public RotatableHitbox(double centerX, double centerY, double centerZ, //
			double sizeX, double sizeY, double sizeZ, //
			double yaw, double pitch, double roll) {
		this(new Vec3(centerX, centerY, centerZ), sizeX, sizeY, sizeZ, yaw, pitch, roll);
	}

	public void updateRotation(double yawDeg, double pitchDeg, double rollDeg) {
		double yaw = Math.toRadians(-yawDeg);
		double pitch = Math.toRadians(-pitchDeg);
		double roll = Math.toRadians(-rollDeg);
		Vec3 worldX = new Vec3(1, 0, 0); // (X+) 동쪽
		Vec3 worldY = new Vec3(0, 1, 0); // (Y+) 위쪽
		Vec3 worldZ = new Vec3(0, 0, 1); // (Z+) 남쪽
		axes[0] = rotateY(worldX, yaw); // 로컬 X축 (붉은색 선)
		axes[1] = worldY; // 로컬 Y축 (위쪽)
		axes[2] = rotateY(worldZ, yaw); // 로컬 Z축 (파란색 선)
		double pitchRad = -pitch;
		Vec3 newY = rotateAxis(axes[1], axes[0], pitchRad);
		Vec3 newZ = rotateAxis(axes[2], axes[0], pitchRad);
		axes[1] = newY;
		axes[2] = newZ;
		Vec3 newX = rotateAxis(axes[0], axes[2], roll);
		Vec3 newY_roll = rotateAxis(axes[1], axes[2], roll);
		axes[0] = newX;
		axes[1] = newY_roll;
	}

	public void updateCenter(double x, double y, double z) {
		this.center = new Vec3(x, y, z);
	}

	public boolean isColliding(AABB aabb) {
		double aabbCX = (aabb.minX + aabb.maxX) * 0.5;
		double aabbCY = (aabb.minY + aabb.maxY) * 0.5;
		double aabbCZ = (aabb.minZ + aabb.maxZ) * 0.5;
		double aabbEX = (aabb.maxX - aabb.minX) * 0.5;
		double aabbEY = (aabb.maxY - aabb.minY) * 0.5;
		double aabbEZ = (aabb.maxZ - aabb.minZ) * 0.5;
		double tx = aabbCX - this.center.x;
		double ty = aabbCY - this.center.y;
		double tz = aabbCZ - this.center.z;
		double obbEX = this.halfExtents.x;
		double obbEY = this.halfExtents.y;
		double obbEZ = this.halfExtents.z;
		double r00 = this.axes[0].x, r01 = this.axes[1].x, r02 = this.axes[2].x;
		double r10 = this.axes[0].y, r11 = this.axes[1].y, r12 = this.axes[2].y;
		double r20 = this.axes[0].z, r21 = this.axes[1].z, r22 = this.axes[2].z;
		double absR00 = Math.abs(r00) + EPSILON;
		double absR01 = Math.abs(r01) + EPSILON;
		double absR02 = Math.abs(r02) + EPSILON;
		double absR10 = Math.abs(r10) + EPSILON;
		double absR11 = Math.abs(r11) + EPSILON;
		double absR12 = Math.abs(r12) + EPSILON;
		double absR20 = Math.abs(r20) + EPSILON;
		double absR21 = Math.abs(r21) + EPSILON;
		double absR22 = Math.abs(r22) + EPSILON;
		double ra, rb;
		ra = aabbEX;
		rb = obbEX * absR00 + obbEY * absR01 + obbEZ * absR02;
		if (Math.abs(tx) > ra + rb)
			return false;
		ra = aabbEY;
		rb = obbEX * absR10 + obbEY * absR11 + obbEZ * absR12;
		if (Math.abs(ty) > ra + rb)
			return false;
		ra = aabbEZ;
		rb = obbEX * absR20 + obbEY * absR21 + obbEZ * absR22;
		if (Math.abs(tz) > ra + rb)
			return false;
		ra = aabbEX * absR00 + aabbEY * absR10 + aabbEZ * absR20;
		rb = obbEX;
		if (Math.abs(tx * r00 + ty * r10 + tz * r20) > ra + rb)
			return false;
		ra = aabbEX * absR01 + aabbEY * absR11 + aabbEZ * absR21;
		rb = obbEY;
		if (Math.abs(tx * r01 + ty * r11 + tz * r21) > ra + rb)
			return false;
		ra = aabbEX * absR02 + aabbEY * absR12 + aabbEZ * absR22;
		rb = obbEZ;
		if (Math.abs(tx * r02 + ty * r12 + tz * r22) > ra + rb)
			return false;
		// AABB X x OBB X
		ra = aabbEY * absR20 + aabbEZ * absR10;
		rb = obbEY * absR02 + obbEZ * absR01;
		if (Math.abs(tz * r10 - ty * r20) > ra + rb)
			return false;
		// AABB X x OBB Y
		ra = aabbEY * absR21 + aabbEZ * absR11;
		rb = obbEX * absR02 + obbEZ * absR00;
		if (Math.abs(tz * r11 - ty * r21) > ra + rb)
			return false;
		// AABB X x OBB Z
		ra = aabbEY * absR22 + aabbEZ * absR12;
		rb = obbEX * absR01 + obbEY * absR00;
		if (Math.abs(tz * r12 - ty * r22) > ra + rb)
			return false;
		// AABB Y x OBB X
		ra = aabbEX * absR20 + aabbEZ * absR00;
		rb = obbEY * absR12 + obbEZ * absR11;
		if (Math.abs(tx * r20 - tz * r00) > ra + rb)
			return false;
		// AABB Y x OBB Y
		ra = aabbEX * absR21 + aabbEZ * absR01;
		rb = obbEX * absR12 + obbEZ * absR10;
		if (Math.abs(tx * r21 - tz * r01) > ra + rb)
			return false;
		// AABB Y x OBB Z
		ra = aabbEX * absR22 + aabbEZ * absR02;
		rb = obbEX * absR11 + obbEY * absR10;
		if (Math.abs(tx * r22 - tz * r02) > ra + rb)
			return false;
		// AABB Z x OBB X
		ra = aabbEX * absR10 + aabbEY * absR00;
		rb = obbEY * absR22 + obbEZ * absR21;
		if (Math.abs(ty * r00 - tx * r10) > ra + rb)
			return false;
		// AABB Z x OBB Y
		ra = aabbEX * absR11 + aabbEY * absR01;
		rb = obbEX * absR22 + obbEZ * absR20;
		if (Math.abs(ty * r01 - tx * r11) > ra + rb)
			return false;
		// AABB Z x OBB Z
		ra = aabbEX * absR12 + aabbEY * absR02;
		rb = obbEX * absR21 + obbEY * absR20;
		if (Math.abs(ty * r02 - tx * r12) > ra + rb)
			return false;
		return true;
	}

	public List<Vec3> getVertices() {
		List<Vec3> vertices = new ArrayList<>();
		double[] xM = {-1, 1};
		double[] yM = {-1, 1};
		double[] zM = {-1, 1};
		for (double xMult : xM) {
			for (double yMult : yM) {
				for (double zMult : zM) {
					Vec3 corner = this.center.add(this.axes[0].scale(this.halfExtents.x * xMult)).add(this.axes[1].scale(this.halfExtents.y * yMult)).add(this.axes[2].scale(this.halfExtents.z * zMult));
					vertices.add(corner);
				}
			}
		}
		return vertices;
	}

	public boolean contains(Vec3 point) {
		Vec3 dir = point.subtract(this.center);
		double dotX = dotProduct(dir, this.axes[0]);
		double dotY = dotProduct(dir, this.axes[1]);
		double dotZ = dotProduct(dir, this.axes[2]);
		return Math.abs(dotX) <= this.halfExtents.x && Math.abs(dotY) <= this.halfExtents.y && Math.abs(dotZ) <= this.halfExtents.z;
	}

	public AABB getBoundingAABB() {
		double extentsX = Math.abs(this.axes[0].x * this.halfExtents.x) + Math.abs(this.axes[1].x * this.halfExtents.y) + Math.abs(this.axes[2].x * this.halfExtents.z);
		double extentsY = Math.abs(this.axes[0].y * this.halfExtents.x) + Math.abs(this.axes[1].y * this.halfExtents.y) + Math.abs(this.axes[2].y * this.halfExtents.z);
		double extentsZ = Math.abs(this.axes[0].z * this.halfExtents.x) + Math.abs(this.axes[1].z * this.halfExtents.y) + Math.abs(this.axes[2].z * this.halfExtents.z);
		return new AABB(this.center.x - extentsX, this.center.y - extentsY, this.center.z - extentsZ, this.center.x + extentsX, this.center.y + extentsY, this.center.z + extentsZ);
	}

	/*public List<BlockPos> getIntersectingBlocks() {
		Set<BlockPos> blocks = new HashSet<>();
		double step = 0.5;
		int stepsX = (int) Math.ceil((this.halfExtents.x * 2) / step) + 1;
		int stepsY = (int) Math.ceil((this.halfExtents.y * 2) / step) + 1;
		int stepsZ = (int) Math.ceil((this.halfExtents.z * 2) / step) + 1;
		for (int i = 0; i < stepsX; i++) {
			double lerpX = (stepsX == 0) ? 0.0 : ((double) i / stepsX) * 2.0 - 1.0;
			Vec3 offsetX = this.axes[0].scale(this.halfExtents.x * lerpX);
			for (int j = 0; j < stepsY; j++) {
				double lerpY = (stepsY == 0) ? 0.0 : ((double) j / stepsY) * 2.0 - 1.0;
				Vec3 offsetY = this.axes[1].scale(this.halfExtents.y * lerpY);
				for (int k = 0; k < stepsZ; k++) {
					double lerpZ = (stepsZ == 0) ? 0.0 : ((double) k / stepsZ) * 2.0 - 1.0;
					Vec3 offsetZ = this.axes[2].scale(this.halfExtents.z * lerpZ);
					Vec3 samplePoint = this.center.add(offsetX).add(offsetY).add(offsetZ);
					blocks.add(new BlockPos(Mth.floor(samplePoint.x), Mth.floor(samplePoint.y), Mth.floor(samplePoint.z)));
				}
			}
		}
		return new ArrayList<>(blocks);
	}
	*/
	public List<BlockPos> getIntersectingBlocks() {
		LongSet blockSet = new LongOpenHashSet();
		double step = 0.5;
		double cx = this.center.x, cy = this.center.y, cz = this.center.z;
		double hx = this.halfExtents.x, hy = this.halfExtents.y, hz = this.halfExtents.z;
		double ax0x = this.axes[0].x, ax0y = this.axes[0].y, ax0z = this.axes[0].z;
		double ax1x = this.axes[1].x, ax1y = this.axes[1].y, ax1z = this.axes[1].z;
		double ax2x = this.axes[2].x, ax2y = this.axes[2].y, ax2z = this.axes[2].z;
		int stepsX = (int) Math.ceil((hx * 2.0) / step) + 1;
		int stepsY = (int) Math.ceil((hy * 2.0) / step) + 1;
		int stepsZ = (int) Math.ceil((hz * 2.0) / step) + 1;
		double mulX = 2.0 / stepsX;
		double mulY = 2.0 / stepsY;
		double mulZ = 2.0 / stepsZ;
		double sax0x = ax0x * hx, sax0y = ax0y * hx, sax0z = ax0z * hx;
		double sax1x = ax1x * hy, sax1y = ax1y * hy, sax1z = ax1z * hy;
		double sax2x = ax2x * hz, sax2y = ax2y * hz, sax2z = ax2z * hz;
		for (int i = 0; i < stepsX; i++) {
			double lerpX = (i * mulX) - 1.0;
			double offXx = sax0x * lerpX;
			double offXy = sax0y * lerpX;
			double offXz = sax0z * lerpX;
			for (int j = 0; j < stepsY; j++) {
				double lerpY = (j * mulY) - 1.0;
				double baseX = cx + offXx + (sax1x * lerpY);
				double baseY = cy + offXy + (sax1y * lerpY);
				double baseZ = cz + offXz + (sax1z * lerpY);
				for (int k = 0; k < stepsZ; k++) {
					double lerpZ = (k * mulZ) - 1.0;
					double sampleX = baseX + (sax2x * lerpZ);
					double sampleY = baseY + (sax2y * lerpZ);
					double sampleZ = baseZ + (sax2z * lerpZ);
					blockSet.add(BlockPos.asLong(Mth.floor(sampleX), Mth.floor(sampleY), Mth.floor(sampleZ)));
				}
			}
		}
		List<BlockPos> result = new ArrayList<>(blockSet.size());
		for (long posLong : blockSet) {
			result.add(BlockPos.of(posLong));
		}
		return result;
	}

	public void spawnParticles(ServerLevel level, ParticleOptions particleOption, double density, boolean fillVolume) {
		double step = 1.0 / density;
		if (fillVolume) {
			for (double sx : getLocalSteps(this.halfExtents.x, step)) {
				for (double sy : getLocalSteps(this.halfExtents.y, step)) {
					for (double sz : getLocalSteps(this.halfExtents.z, step)) {
						Vec3 pos = calculateWorldPos(sx, sy, sz);
						spawnSingleParticle(level, particleOption, pos);
					}
				}
			}
		} else {
			Vec3[] v = new Vec3[8];
			int idx = 0;
			for (int x = -1; x <= 1; x += 2) {
				for (int y = -1; y <= 1; y += 2) {
					for (int z = -1; z <= 1; z += 2) {
						v[idx++] = calculateWorldPos(this.halfExtents.x * x, this.halfExtents.y * y, this.halfExtents.z * z);
					}
				}
			}
			// X
			drawLine(level, particleOption, v[0], v[4], step);
			drawLine(level, particleOption, v[1], v[5], step);
			drawLine(level, particleOption, v[2], v[6], step);
			drawLine(level, particleOption, v[3], v[7], step);
			// Y
			drawLine(level, particleOption, v[0], v[2], step);
			drawLine(level, particleOption, v[1], v[3], step);
			drawLine(level, particleOption, v[4], v[6], step);
			drawLine(level, particleOption, v[5], v[7], step);
			// Z
			drawLine(level, particleOption, v[0], v[1], step);
			drawLine(level, particleOption, v[2], v[3], step);
			drawLine(level, particleOption, v[4], v[5], step);
			drawLine(level, particleOption, v[6], v[7], step);
		}
	}

	private void drawLine(ServerLevel level, ParticleOptions particleOption, Vec3 start, Vec3 end, double step) {
		double distance = start.distanceTo(end);
		if (distance <= 0) {
			spawnSingleParticle(level, particleOption, start);
			return;
		}
		Vec3 direction = end.subtract(start).normalize();
		for (double d = 0; d <= distance; d += step) {
			Vec3 pos = start.add(direction.scale(d));
			spawnSingleParticle(level, particleOption, pos);
		}
	}

	private Vec3 calculateWorldPos(double offsetX, double offsetY, double offsetZ) {
		return this.center.add(this.axes[0].scale(offsetX)).add(this.axes[1].scale(offsetY)).add(this.axes[2].scale(offsetZ));
	}

	private List<Double> getLocalSteps(double halfExtent, double step) {
		List<Double> steps = new ArrayList<>();
		if (halfExtent <= 0) {
			steps.add(0.0);
			return steps;
		}
		for (double d = -halfExtent; d < halfExtent; d += step) {
			steps.add(d);
		}
		steps.add(halfExtent);
		return steps;
	}

	private void spawnSingleParticle(ServerLevel level, ParticleOptions particleOption, Vec3 pos) {
		level.sendParticles(particleOption, pos.x, pos.y, pos.z, 1, 0, 0, 0, 0.0);
	}

	private Vec3 rotateY(Vec3 v, double yaw) {
		double cosY = Math.cos(yaw), sinY = Math.sin(yaw);
		return new Vec3(v.x * cosY + v.z * sinY, v.y, -v.x * sinY + v.z * cosY);
	}

	private Vec3 rotateAxis(Vec3 v, Vec3 axis, double angle) {
		double cosA = Math.cos(angle);
		double sinA = Math.sin(angle);
		Vec3 k = axis.normalize();
		return v.scale(cosA).add(crossProduct(k, v).scale(sinA)).add(k.scale(dotProduct(k, v)).scale(1.0 - cosA));
	}

	private double projectRadius(Vec3 halfExtents, Vec3[] localAxes, Vec3 testAxis) {
		return halfExtents.x * Math.abs(dotProduct(localAxes[0], testAxis)) + halfExtents.y * Math.abs(dotProduct(localAxes[1], testAxis)) + halfExtents.z * Math.abs(dotProduct(localAxes[2], testAxis));
	}

	private double dotProduct(Vec3 a, Vec3 b) {
		return a.x * b.x + a.y * b.y + a.z * b.z;
	}

	private Vec3 crossProduct(Vec3 a, Vec3 b) {
		return new Vec3(a.y * b.z - a.z * b.y, a.z * b.x - a.x * b.z, a.x * b.y - a.y * b.x);
	}
}