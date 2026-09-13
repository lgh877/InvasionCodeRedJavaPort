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

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.core.BlockPos;

import java.util.EnumSet;

public class VexLikeRandomMoveGoal extends Goal {
	protected final PathfinderMob mob;

	public VexLikeRandomMoveGoal(PathfinderMob mob) {
		this.mob = mob;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE));
	}

	public boolean canUse() {
		return !mob.getMoveControl().hasWanted() && mob.getRandom().nextInt(reducedTickDelay(7)) == 0;
	}

	public boolean canContinueToUse() {
		return false;
	}

	public void tick() {
		BlockPos blockpos = getBlockPosition();
		if (blockpos == null) {
			blockpos = getBlockPosition();
		}
		for (int i = 0; i < 3; ++i) {
			BlockPos blockpos1 = blockpos.offset(mob.getRandom().nextInt(15) - 7, mob.getRandom().nextInt(11) - 5, mob.getRandom().nextInt(15) - 7);
			if (mob.level().isEmptyBlock(blockpos1)) {
				mob.getMoveControl().setWantedPosition((double) blockpos1.getX() + 0.5D, (double) blockpos1.getY() + 0.5D, (double) blockpos1.getZ() + 0.5D, 0.25D);
				if (mob.getTarget() == null) {
					mob.getLookControl().setLookAt((double) blockpos1.getX() + 0.5D, (double) blockpos1.getY() + 0.5D, (double) blockpos1.getZ() + 0.5D, 180.0F, 20.0F);
				}
				break;
			}
		}
	}

	public BlockPos getBlockPosition() {
		return mob.getTarget() == null ? mob.blockPosition() : mob.getTarget().blockPosition();
	}
}