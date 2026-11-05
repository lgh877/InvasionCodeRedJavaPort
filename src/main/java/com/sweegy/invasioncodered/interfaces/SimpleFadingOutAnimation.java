package com.sweegy.invasioncodered.interfaces;

import net.minecraft.util.Mth;

/**
 * A snapshot of exactly one outgoing AnimationDefinition animation.
 *
 * <p>Unlike the existing {@link FadingOutAnimation}, this class is not
 * owned by a List. Replacing the reference immediately discards any older
 * outgoing animation. It is intended for simple mobs.</p>
 *
 * <p>All times are in animation ticks, as used by
 * HierarchicalModel.animateWalk in Minecraft/Forge 1.20.1.</p>
 */
public final class SimpleFadingOutAnimation {
    public static final float DEFAULT_FADE_OUT = 5.0F;

    public final int animIdx;
    public final float finalTime;
    public final float maxFadeOutTime;
    public final float fadeOutSpeed;

    private float remainingFadeOutTime;

    public SimpleFadingOutAnimation(float finalTime, float fadeOutTime,
                                    int animIdx, float fadeOutSpeed) {
        if (!Float.isFinite(finalTime) || !Float.isFinite(fadeOutTime)
                || !Float.isFinite(fadeOutSpeed) || fadeOutSpeed <= 0.0F) {
            throw new IllegalArgumentException("Animation times must be finite; fadeOutSpeed must be positive");
        }
        this.finalTime = Math.max(0.0F, finalTime);
        this.animIdx = animIdx;
        this.maxFadeOutTime = Math.max(0.0F, fadeOutTime);
        this.remainingFadeOutTime = this.maxFadeOutTime;
        this.fadeOutSpeed = fadeOutSpeed;
    }

    public SimpleFadingOutAnimation(float finalTime, float fadeOutTime, int animIdx) {
        this(finalTime, fadeOutTime, animIdx, 1.0F);
    }

    public SimpleFadingOutAnimation(float finalTime, int animIdx) {
        this(finalTime, DEFAULT_FADE_OUT, animIdx);
    }

    /** Call once per client tick, not once per render frame. */
    public void tick() {
        remainingFadeOutTime = Math.max(0.0F, remainingFadeOutTime - fadeOutSpeed);
    }

    public boolean isFinished() {
        return remainingFadeOutTime <= 0.0F;
    }

    /**
     * Render-time fade-out weight, including partial-tick interpolation.
     * The interpolation factor also uses fadeOutSpeed, rather than assuming 1.
     */
    public float getFadeProgress(float partialTicks) {
        if (maxFadeOutTime <= 0.0F) return 0.0F;
        float remaining = remainingFadeOutTime - Mth.clamp(partialTicks, 0.0F, 1.0F) * fadeOutSpeed;
        return Mth.clamp(remaining / maxFadeOutTime, 0.0F, 1.0F);
    }

    public float getRemainingFadeOutTime() {
        return remainingFadeOutTime;
    }
}
