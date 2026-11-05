package com.sweegy.invasioncodered.entity.ai;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.LivingEntity;

import java.util.EnumSet;

public class AvoidTargetGoal extends Goal {
    protected final PathfinderMob mob;
    protected LivingEntity toAvoid;
    protected final double walkSpeedModifier;
    protected final float maxDist;
    protected double stopDist;
    protected Path path;
    protected final PathNavigation pathNav;

    public AvoidTargetGoal(PathfinderMob mob, float maxDistance, double walkSpeed) {
        this.mob = mob;
        this.maxDist = maxDistance;
        this.walkSpeedModifier = walkSpeed;
        this.pathNav = mob.getNavigation();
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        LivingEntity target = this.mob.getTarget();
        if (target == null || !target.isAlive()) {
            return false;
        }
        if (this.mob.distanceToSqr(target) > (float) Math.pow(maxDist + target.getBbWidth() / 2d, 2)) {
            return false;
        }
        this.toAvoid = target;
        Vec3 vec3 = DefaultRandomPos.getPosAway(this.mob, 16, 7, this.toAvoid.position());
        if (vec3 == null) {
            return false;
        } else if (this.toAvoid.distanceToSqr(vec3.x, vec3.y, vec3.z) < this.toAvoid.distanceToSqr(this.mob)) {
            return false;
        }
        this.path = this.pathNav.createPath(vec3.x, vec3.y, vec3.z, 0);
        stopDist = Math.pow((maxDist + toAvoid.getBbWidth() / 2d) * 1.5, 2);
        return this.path != null;
    }

    @Override
    public boolean canContinueToUse() {
        if (this.toAvoid == null || !this.toAvoid.isAlive() || this.mob.hurtTime > 0) {
            return false;
        }
        if (this.mob.distanceToSqr(this.toAvoid) > stopDist) {
            return false;
        }
        return !this.pathNav.isDone();
    }

    @Override
    public void start() {
        this.pathNav.moveTo(this.path, this.walkSpeedModifier);
    }

    @Override
    public void tick() {
        mob.getLookControl().setLookAt(toAvoid);
    }

    @Override
    public void stop() {
        pathNav.stop();
        this.toAvoid = null;
    }
}
