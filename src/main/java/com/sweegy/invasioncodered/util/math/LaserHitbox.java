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

public class LaserHitbox {
	private final Vec3 startPos;
	private final Vec3 endPos;
	private final Vec3 dir;
	private final double dist;
	private final double radius;

	public LaserHitbox(Vec3 startPos, Vec3 dir, double dist, double width) {
		this.startPos = startPos;
		this.dir = dir.normalize();
		this.dist = dist;
		this.radius = width / 2.0;
		this.endPos = this.startPos.add(this.dir.scale(dist));
	}

	public LaserHitbox(double x, double y, double z, Vec3 dir, double dist, double width) {
		this(new Vec3(x, y, z), dir, dist, width);
	}

	public boolean isColliding(AABB aabb) {
		double minX = aabb.minX - this.radius;
		double minY = aabb.minY - this.radius;
		double minZ = aabb.minZ - this.radius;
		double maxX = aabb.maxX + this.radius;
		double maxY = aabb.maxY + this.radius;
		double maxZ = aabb.maxZ + this.radius;
		double p0x = this.startPos.x, p0y = this.startPos.y, p0z = this.startPos.z;
		double dx = this.dir.x * this.dist;
		double dy = this.dir.y * this.dist;
		double dz = this.dir.z * this.dist;
		double tmin = 0.0;
		double tmax = 1.0;
		if (Math.abs(dx) < 1e-6) {
			if (p0x < minX || p0x > maxX)
				return false;
		} else {
			double invD = 1.0 / dx;
			double t1 = (minX - p0x) * invD;
			double t2 = (maxX - p0x) * invD;
			if (t1 > t2) {
				double temp = t1;
				t1 = t2;
				t2 = temp;
			}
			tmin = Math.max(tmin, t1);
			tmax = Math.min(tmax, t2);
			if (tmin > tmax)
				return false;
		}
		if (Math.abs(dy) < 1e-6) {
			if (p0y < minY || p0y > maxY)
				return false;
		} else {
			double invD = 1.0 / dy;
			double t1 = (minY - p0y) * invD;
			double t2 = (maxY - p0y) * invD;
			if (t1 > t2) {
				double temp = t1;
				t1 = t2;
				t2 = temp;
			}
			tmin = Math.max(tmin, t1);
			tmax = Math.min(tmax, t2);
			if (tmin > tmax)
				return false;
		}
		if (Math.abs(dz) < 1e-6) {
			if (p0z < minZ || p0z > maxZ)
				return false;
		} else {
			double invD = 1.0 / dz;
			double t1 = (minZ - p0z) * invD;
			double t2 = (maxZ - p0z) * invD;
			if (t1 > t2) {
				double temp = t1;
				t1 = t2;
				t2 = temp;
			}
			tmin = Math.max(tmin, t1);
			tmax = Math.min(tmax, t2);
			if (tmin > tmax)
				return false;
		}
		return true;
	}

	public boolean isColliding(Vec3 pos, double targetWidth, double height) {
		double targetRadius = targetWidth * 0.5;
		double rSum = this.radius + targetRadius;
		double dx = this.endPos.x - this.startPos.x;
		double dz = this.endPos.z - this.startPos.z;
		double px = pos.x - this.startPos.x;
		double pz = pos.z - this.startPos.z;
		double lenSqXZ = dx * dx + dz * dz;
		double t;
		if (lenSqXZ < 1e-6) {
			double distSqXZ = px * px + pz * pz;
			if (distSqXZ > rSum * rSum)
				return false;
			double minY = Math.min(this.startPos.y, this.endPos.y) - this.radius;
			double maxY = Math.max(this.startPos.y, this.endPos.y) + this.radius;
			return (maxY >= pos.y) && (minY <= pos.y + height);
		} else {
			t = (px * dx + pz * dz) / lenSqXZ;
			t = Math.max(0.0, Math.min(1.0, t));
		}
		double closestX = t * dx;
		double closestZ = t * dz;
		double distSq = (px - closestX) * (px - closestX) + (pz - closestZ) * (pz - closestZ);
		if (distSq > rSum * rSum)
			return false;
		double laserYAtT = this.startPos.y + t * (this.endPos.y - this.startPos.y);
		return laserYAtT >= (pos.y - this.radius) && laserYAtT <= (pos.y + height + this.radius);
	}

	public List<BlockPos> getIntersectingBlocks() {
		LongSet blockSet = new LongOpenHashSet();
		double step = 0.5;
		int steps = (int) Math.ceil(this.dist / step);
		double mul = (steps == 0) ? 0 : 1.0 / steps;
		double dx = this.endPos.x - this.startPos.x;
		double dy = this.endPos.y - this.startPos.y;
		double dz = this.endPos.z - this.startPos.z;
		int radCells = (int) Math.ceil(this.radius);
		for (int i = 0; i <= steps; i++) {
			double t = i * mul;
			double cx = this.startPos.x + t * dx;
			double cy = this.startPos.y + t * dy;
			double cz = this.startPos.z + t * dz;
			int baseX = Mth.floor(cx);
			int baseY = Mth.floor(cy);
			int baseZ = Mth.floor(cz);
			for (int x = baseX - radCells; x <= baseX + radCells; x++) {
				for (int y = baseY - radCells; y <= baseY + radCells; y++) {
					for (int z = baseZ - radCells; z <= baseZ + radCells; z++) {
						long posLong = BlockPos.asLong(x, y, z);
						if (blockSet.contains(posLong))
							continue;
						AABB blockAABB = new AABB(x, y, z, x + 1, y + 1, z + 1);
						if (isColliding(blockAABB)) {
							blockSet.add(posLong);
						}
					}
				}
			}
		}
		List<BlockPos> result = new ArrayList<>(blockSet.size());
		for (long posLong : blockSet) {
			result.add(BlockPos.of(posLong));
		}
		return result;
	}

	public void spawnParticles(ServerLevel level, ParticleOptions particleOption, double stepDistance, boolean drawRings) {
		if (stepDistance <= 0)
			stepDistance = 0.5;
		int ringSegments = 12;
		Vec3[] ringOffsets = null;
		if (drawRings && this.radius > 0) {
			ringOffsets = new Vec3[ringSegments];
			Vec3 up = new Vec3(0, 1, 0);
			if (Math.abs(this.dir.dot(up)) > 0.99) {
				up = new Vec3(1, 0, 0);
			}
			Vec3 u = this.dir.cross(up).normalize();
			Vec3 v = this.dir.cross(u).normalize();
			for (int i = 0; i < ringSegments; i++) {
				double angle = 2.0 * Math.PI * i / ringSegments;
				ringOffsets[i] = u.scale(Math.cos(angle) * this.radius).add(v.scale(Math.sin(angle) * this.radius));
			}
		}
		for (double d = 0; d < this.dist + 1; d += stepDistance) {
			Vec3 centerAtD = this.startPos.add(this.dir.scale(d));
			level.sendParticles(particleOption, centerAtD.x, centerAtD.y, centerAtD.z, 1, 0, 0, 0, 0.0);
			if (ringOffsets != null) {
				for (Vec3 offset : ringOffsets) {
					Vec3 ringPos = centerAtD.add(offset);
					level.sendParticles(particleOption, ringPos.x, ringPos.y, ringPos.z, 1, 0, 0, 0, 0.0);
				}
			}
		}
	}

	public AABB getBoundingAABB() {
		return new AABB(Math.min(this.startPos.x, this.endPos.x) - this.radius, Math.min(this.startPos.y, this.endPos.y) - this.radius, Math.min(this.startPos.z, this.endPos.z) - this.radius, Math.max(this.startPos.x, this.endPos.x) + this.radius,
				Math.max(this.startPos.y, this.endPos.y) + this.radius, Math.max(this.startPos.z, this.endPos.z) + this.radius);
	}
}