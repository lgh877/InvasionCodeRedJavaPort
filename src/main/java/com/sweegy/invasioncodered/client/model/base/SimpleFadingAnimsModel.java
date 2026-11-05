package com.sweegy.invasioncodered.client.model.base;

import com.sweegy.invasioncodered.interfaces.IUsingSimpleFadingAnims;
import com.sweegy.invasioncodered.interfaces.SimpleFadingOutAnimation;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

import java.util.function.Function;

/**
 * Forge 1.20.1 counterpart of CrimsonSteveAPI's SimpleFadingAnimsModel.
 *
 * <p>Uses the project's existing BetterAnimsModel/HierarchicalModel path,
 * directly receiving an entity rather than a RenderState. It blends the
 * incoming animation with only the immediately previous animation.</p>
 *
 * <p>Call applyFadingAnims() after resetting ModelPart poses and before
 * any procedural adjustments. The animation indices must index the same
 * AnimationDefinition[] used by the entity.</p>
 */
public abstract class SimpleFadingAnimsModel<E extends Entity & IUsingSimpleFadingAnims>
        extends BetterAnimsModel<E> {

    public SimpleFadingAnimsModel() {
        super();
    }

    public SimpleFadingAnimsModel(Function<ResourceLocation, RenderType> renderType) {
        super(renderType);
    }

    public void applyFadingAnims(E entity, float partialTicks,
                                 AnimationDefinition[] animations) {
        if (animations == null || animations.length == 0) return;

        int incoming = entity.getAnimIndex();
        if (validAnimation(animations, incoming)) {
            float fadeIn = Mth.clamp(entity.getFadeInTime(partialTicks), 0.0F, 1.0F);
            if (fadeIn > 0.0F) {
                animateWalk(animations[incoming], entity.getAnimTicks(partialTicks),
                        fadeIn, 1.0F, 1.0F);
            }
        }

        SimpleFadingOutAnimation outgoing = entity.getFadingOutAnimation();
        if (outgoing != null && !outgoing.isFinished()
                && validAnimation(animations, outgoing.animIdx)) {
            float fadeOut = outgoing.getFadeProgress(partialTicks);
            if (fadeOut > 0.0F) {
                animateWalk(animations[outgoing.animIdx], outgoing.finalTime,
                        fadeOut, 1.0F, 1.0F);
            }
        }
    }

    private static boolean validAnimation(AnimationDefinition[] definitions, int index) {
        return index >= 0 && index < definitions.length && definitions[index] != null;
    }
}
