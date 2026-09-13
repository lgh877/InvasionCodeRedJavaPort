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

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;

import java.util.Set;
import java.util.Iterator;

import it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet;

public class RecentAttackerTracker {
	private final Mob owner;
	private final int maxCapacity;
	private final Set<LivingEntity> recentAttackers = new ReferenceOpenHashSet<>();

	public RecentAttackerTracker(Mob owner, int maxCapacity) {
		this.owner = owner;
		this.maxCapacity = maxCapacity;
	}

	public void onAttackedBy(LivingEntity attacker) {
		if (!isValidTarget(attacker))
			return;
		if (recentAttackers.add(attacker)) {
			if (recentAttackers.size() > maxCapacity) {
				Iterator<LivingEntity> iterator = recentAttackers.iterator();
				iterator.next();
				iterator.remove();
			}
		}
	}

	public void tick() {
		if ((owner.tickCount & 31) == 0) {
			evaluateAndSetClosestTarget();
		}
	}

	private void evaluateAndSetClosestTarget() {
		recentAttackers.removeIf(entity -> !isValidTarget(entity));
		if (recentAttackers.isEmpty())
			return;
		LivingEntity closestAttacker = null;
		double minDistanceSq = Double.MAX_VALUE;
		for (LivingEntity attacker : recentAttackers) {
			double distSq = owner.distanceToSqr(attacker);
			if (distSq < minDistanceSq) {
				minDistanceSq = distSq;
				closestAttacker = attacker;
			}
		}
		if (closestAttacker != null && owner.getTarget() != closestAttacker) {
			owner.setTarget(closestAttacker);
		}
	}

	private boolean isValidTarget(LivingEntity target) {
		if (target == null || !target.isAlive() || target.isRemoved())
			return false;
		if (target == owner)
			return false;
		if (owner.isAlliedTo(target))
			return false;
		if (target instanceof Player player && (player.isCreative() || player.isSpectator())) {
			return false;
		}
		return true;
	}
}