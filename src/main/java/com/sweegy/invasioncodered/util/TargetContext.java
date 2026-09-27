package com.sweegy.invasioncodered.util;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;

public class TargetContext {
    public final Mob mob;
    public final Entity target;
    public final double mobX, mobY, mobZ;
    public final double targetX, targetY, targetZ;
    public final double dx, dy, dz;
    public final double distFlatSq;
    public final double distFlat;
    public final double dist3DSq;
    public final double dist3D;
    public final double yDiff;
    public final double minDist;
    public final double mobWidth;
    public final double mobHeight;
    public final double targetWidth;
    public final double targetHeight;

    public TargetContext(Mob mob, Entity target, double x, double y, double z) {
        this.mob = mob;
        this.target = target;
        this.mobX = x;
        this.mobY = y;
        this.mobZ = z;
        this.targetX = target.getX();
        this.targetY = target.getY();
        this.targetZ = target.getZ();

        this.dx = targetX - x;
        this.dy = targetY - y;
        this.dz = targetZ - z;

        this.distFlatSq = dx * dx + dz * dz;
        this.distFlat = Math.sqrt(distFlatSq);
        this.dist3DSq = distFlatSq + dy * dy;
        this.dist3D = Math.sqrt(dist3DSq);

        this.yDiff = targetY - y;
        this.mobWidth = mob.getBbWidth();
        this.mobHeight = mob.getBbHeight();
        this.targetWidth = target.getBbWidth();
        this.targetHeight = target.getBbHeight();
        this.minDist = (mobWidth + targetWidth) * 0.5D;
    }

    public boolean isWithinFlatRange(double range) {
        return distFlatSq < range * range;
    }

    public boolean isWithin3DRange(double range) {
        return dist3DSq < range * range;
    }

    public boolean isYWithin(double maxY, double minY) {
        return yDiff < maxY && yDiff > minY;
    }
}