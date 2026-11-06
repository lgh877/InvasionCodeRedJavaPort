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

import com.sweegy.invasioncodered.client.model.animations.GashslitAnimation;

public class GashslitAnimationIndex {
	public static final AnimationDefinition anims[] = {//
			GashslitAnimation.block, //
			GashslitAnimation.charge, //
			GashslitAnimation.attack, //
			GashslitAnimation.slowattack, //
			GashslitAnimation.flex, //
			GashslitAnimation.prepare, //
			GashslitAnimation.stepback, //
			GashslitAnimation.flew2, //
			GashslitAnimation.shoot, //
			GashslitAnimation.quickat1, //
			GashslitAnimation.quickat2, //
			GashslitAnimation.quickat3, //
			GashslitAnimation.quickat4, //
			GashslitAnimation.quickat5, //
			GashslitAnimation.death, //
			GashslitAnimation.walk, //
			GashslitAnimation.run, //
	};//
	public static final int anims_block = 1, anims_charge = 2, anims_attack = 3, anims_slowattack = 4, anims_flex = 5, anims_prepare = 6, anims_stepback = 7, anims_flew2 = 8, anims_shoot = 9, anims_quickat1 = 10, anims_quickat2 = 11,
			anims_quickat3 = 12, anims_quickat4 = 13, anims_quickat5 = 14, anims_death = 15, anims_walk = 16, anims_run = 17;
}