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
package com.sweegy.invasioncodered.client.model.base;

import com.sweegy.invasioncodered.interfaces.IUsingCustomVanillaAnimations;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.animation.AnimationDefinition;

import java.util.function.Function;

public abstract class BetterAnimsModel<E extends Entity & IUsingCustomVanillaAnimations> extends HierarchicalModel<E> {
	public BetterAnimsModel() {
		super(RenderType::entityCutoutNoCull);
	}

	public BetterAnimsModel(Function<ResourceLocation, RenderType> p_170623_) {
		super(p_170623_);
	}

	public void applyAnims(E entity, float partialTicks, AnimationDefinition[] animations) {
		float animTicks = entity.getAnimTicks(partialTicks);
		this.animateWalk(animations[entity.getAnimIndex()], animTicks, 1f, 1f, 1f);
	}
}