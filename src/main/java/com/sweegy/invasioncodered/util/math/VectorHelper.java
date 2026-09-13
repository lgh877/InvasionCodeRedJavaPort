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
import net.minecraft.util.Mth;

public class VectorHelper {
	public static Vec3 calculateViewVector(float xRot, float yRot) {
		float f = xRot * (Mth.PI / 180F);
		float f1 = -yRot * (Mth.PI / 180F);
		float f2 = Mth.cos(f1);
		float f3 = Mth.sin(f1);
		float f4 = Mth.cos(f);
		float f5 = Mth.sin(f);
		return new Vec3((double) (f3 * f4), (double) (-f5), (double) (f2 * f4));
	}

	public static Vec3 calculateFlatViewVector(float yRot) {
		float f1 = -yRot * (Mth.PI / 180F);
		float f2 = Mth.cos(f1);
		float f3 = Mth.sin(f1);
		return new Vec3((double) f3, 0, (double) f2);
	}

	/**
	 * Calculates the required initial horizontal velocity to travel a specific distance 
	 * within a set number of ticks, considering horizontal drag.
	 *
	 * @param d  The target horizontal distance to travel (in blocks).
	 * @param n  The total flight time (in ticks).
	 * @param f  The horizontal drag coefficient (e.g., 0.91 for LivingEntities, 0.99 for arrows).
	 * @return   The required initial horizontal velocity component.
	 */
	public static double getRequiredVelocityFlat(double d, int n, double f) {
		double S = (1 - Math.pow(f, n)) / (1 - f);
		return d / S;
	}

	/**
	 * Calculates the required initial vertical velocity to reach a target height 
	 * within a set number of ticks, considering both vertical drag and gravity.
	 *
	 * @param d  The target vertical displacement (Δy) to reach (in blocks).
	 * @param n  The total flight time (in ticks).
	 * @param f  The vertical drag coefficient (e.g., 0.98 for LivingEntities, 0.99 for arrows).
	 * @param g  The gravity acceleration per tick (e.g., 0.08 for players, 0.05 for arrows).
	 * @return   The required initial vertical velocity component.
	 */
	public static double getRequiredVelocity(double d, int n, double f, double g) {
		double S = (1 - Math.pow(f, n)) / (1 - f);
		double v_term = (-g * f) / (1 - f);
		double gravityDist = v_term * (n - S / f);
		return (d - gravityDist) / S;
	}

	/**
	 * Calculates the optimal launch velocity to reach a target at a given distance and height 
	 * while attempting to match a specific initial speed.
	 *
	 * @param d        Horizontal distance to the target (in blocks).
	 * @param dy       Height difference to the target (target Y - source Y).
	 * @param targetS  The desired initial launch speed (magnitude of the velocity vector).
	 * @param f        The drag coefficient (e.g., 0.91 for players, 0.99 for arrows).
	 * @param g        The gravity acceleration per tick (e.g., 0.08 for players, 0.05 for arrows).
	 * @param maxIter  The maximum number of ticks (flight time) to simulate for searching the trajectory.
	 * @return         A Vec3 containing the calculated horizontal (x) and vertical (y) velocity components.
	 */
	public static Vec3 getLightweightVelocity(double d, double dy, double targetS, double f, double g, int maxIter) {
		double Sn = 0;
		double Gn = 0;
		double fn = 1.0;
		double bestVx = 0;
		double bestVy = 0;
		double minDiff = Double.MAX_VALUE;
		for (int n = 1; n < maxIter; n++) {
			fn *= f;
			Sn += fn;
			if (n > 1) {
				Gn += (1 - fn / f) / (1 - f) * g;
			}
			double vx = d / Sn;
			double vy = (dy + Gn) / Sn;
			double currentS = Math.sqrt(vx * vx + vy * vy);
			double diff = Math.abs(currentS - targetS);
			if (diff < minDiff) {
				minDiff = diff;
				bestVx = vx;
				bestVy = vy;
			}
			if (currentS < targetS) {
				break;
			}
		}
		return new Vec3(bestVx, bestVy, 0);
	}

	public static Vec3 getRequiredAcceleration(int accelTime, Vec3 target, int n, double f) {
		double simX = 0, simY = 0, simZ = 0;
		double divisor = 0;
		double unitDist = 0;
		double unitVel = 0;
		for (int i = 0; i < n; i++) {
			unitVel *= f;
			if (i < accelTime)
				unitVel += 1.0;
			unitDist += unitVel;
		}
		//divisor = unitDist;
		//if (divisor == 0)
		//	return new Vec3(0, 0, 0);
		double reqX = target.x - simX;
		double reqY = target.y - simY;
		double reqZ = target.z - simZ;
		return new Vec3(reqX / unitDist, reqY / unitDist, reqZ / unitDist);
	}

	public static Vec3 getRequiredAcceleration(int accelTime, Vec3 target, int n, double f, Vec3 currentVel) {
		double simX = 0, simY = 0, simZ = 0;
		double vX = currentVel.x, vY = currentVel.y, vZ = currentVel.z;
		double divisor = 0;
		for (int i = 0; i < n; i++) {
			vX *= f;
			vY *= f;
			vZ *= f;
			simX += vX;
			simY += vY;
			simZ += vZ;
		}
		double unitDist = 0;
		double unitVel = 0;
		for (int i = 0; i < n; i++) {
			unitVel *= f;
			if (i < accelTime)
				unitVel += 1.0;
			unitDist += unitVel;
		}
		//divisor = unitDist;
		//if (divisor == 0)
		//	return new Vec3(0, 0, 0);
		double reqX = target.x - simX;
		double reqY = target.y - simY;
		double reqZ = target.z - simZ;
		return new Vec3(reqX / unitDist, reqY / unitDist, reqZ / unitDist);
	}

	public static Vec3 getRequiredAcceleration(int accelTime, Vec3 target, int n, double f1, double f2) {
		double simX = 0, simY = 0, simZ = 0;
		double unitDistXZ = 0;
		double unitVelXZ = 0;
		for (int i = 0; i < n; i++) {
			unitVelXZ *= f1;
			if (i < accelTime)
				unitVelXZ += 1.0;
			unitDistXZ += unitVelXZ;
		}
		//double divisorXZ = unitDistXZ;
		double unitDistY = 0;
		double unitVelY = 0;
		for (int i = 0; i < n; i++) {
			unitVelY *= f2;
			if (i < accelTime)
				unitVelY += 1.0;
			unitDistY += unitVelY;
		}
		//double divisorY = unitDistY;
		//if (divisorXZ == 0 || divisorY == 0) {
		//	return new Vec3(0, 0, 0);
		//}
		double reqX = target.x - simX;
		double reqY = target.y - simY;
		double reqZ = target.z - simZ;
		return new Vec3(reqX / unitDistXZ, reqY / unitDistY, reqZ / unitDistXZ);
	}

	public static Vec3 getRequiredAcceleration(int accelTime, Vec3 target, int n, double f1, double f2, Vec3 currentVel) {
		double simX = 0, simY = 0, simZ = 0;
		double vX = currentVel.x, vY = currentVel.y, vZ = currentVel.z;
		for (int i = 0; i < n; i++) {
			vX *= f1;
			vY *= f2;
			vZ *= f1;
			simX += vX;
			simY += vY;
			simZ += vZ;
		}
		double unitDistXZ = 0;
		double unitVelXZ = 0;
		for (int i = 0; i < n; i++) {
			unitVelXZ *= f1;
			if (i < accelTime)
				unitVelXZ += 1.0;
			unitDistXZ += unitVelXZ;
		}
		//double divisorXZ = unitDistXZ;
		double unitDistY = 0;
		double unitVelY = 0;
		for (int i = 0; i < n; i++) {
			unitVelY *= f2;
			if (i < accelTime)
				unitVelY += 1.0;
			unitDistY += unitVelY;
		}
		//double divisorY = unitDistY;
		//if (divisorXZ == 0 || divisorY == 0) {
		//	return new Vec3(0, 0, 0);
		//}
		double reqX = target.x - simX;
		double reqY = target.y - simY;
		double reqZ = target.z - simZ;
		return new Vec3(reqX / unitDistXZ, reqY / unitDistY, reqZ / unitDistXZ);
	}
}