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
package com.sweegy.invasioncodered.client.model.animations.indices;

import net.minecraft.client.animation.AnimationDefinition;

import com.sweegy.invasioncodered.client.model.animations.gashslitAnimation;

public class GashslitAnimationIndex {
	public static final AnimationDefinition anims[] = {//
			gashslitAnimation.block, //
			gashslitAnimation.charge, //
			gashslitAnimation.attack, //
			gashslitAnimation.slowattack, //
			gashslitAnimation.flex, //
			gashslitAnimation.prepare, //
			gashslitAnimation.stepback, //
			gashslitAnimation.flew2, //
			gashslitAnimation.shoot, //
			gashslitAnimation.quickat1, //
			gashslitAnimation.quickat2, //
			gashslitAnimation.quickat3, //
			gashslitAnimation.quickat4, //
			gashslitAnimation.quickat5, //
			gashslitAnimation.death, //
			gashslitAnimation.walk, //
			gashslitAnimation.run, //
	};//
	public static final int anims_block = 1, anims_charge = 2, anims_attack = 3, anims_slowattack = 4, anims_flex = 5, anims_prepare = 6, anims_stepback = 7, anims_flew2 = 8, anims_shoot = 9, anims_quickat1 = 10, anims_quickat2 = 11,
			anims_quickat3 = 12, anims_quickat4 = 13, anims_quickat5 = 14, anims_death = 15, anims_walk = 16, anims_run = 17;
}