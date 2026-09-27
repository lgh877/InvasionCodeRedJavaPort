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
import net.minecraft.world.entity.Mob;

import java.util.EnumSet;

public class DoNothingGoalUniversal extends Goal {
	protected final Mob mob;

	public DoNothingGoalUniversal(Mob mob) {
		this.mob = mob;
		this.setFlags(EnumSet.of(//
				Goal.Flag.MOVE, //
				Goal.Flag.LOOK, //
				Goal.Flag.JUMP//
		));
	}

	public void start() {
		mob.getNavigation().stop();
	}

	public boolean canUse() {
		return isInAction();
	}

	public boolean canContinueToUse() {
		return isInAction();
	}

	protected boolean isInAction() {
		return true;
	}
}