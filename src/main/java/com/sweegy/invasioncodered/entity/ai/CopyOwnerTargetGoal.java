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
package com.sweegy.invasioncodered.entity.ai;

import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.TraceableEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Entity;

public class CopyOwnerTargetGoal<E extends PathfinderMob & TraceableEntity> extends TargetGoal {
	private final TargetingConditions copyOwnerTargeting = TargetingConditions.forNonCombat().ignoreLineOfSight().ignoreInvisibilityTesting();
	final E traceableEntity;

	public CopyOwnerTargetGoal(E p_34056_) {
		super(p_34056_, false);
		traceableEntity = p_34056_;
	}

	public boolean canUse() {
		Entity ownerEntity = traceableEntity.getOwner();
		if (ownerEntity != null && ownerEntity instanceof Mob owner)
			return owner.getTarget() != null && this.canAttack(owner.getTarget(), this.copyOwnerTargeting);
		else
			return false;
	}

	public void start() {
		traceableEntity.setTarget(((Mob) traceableEntity.getOwner()).getTarget());
		super.start();
	}
}