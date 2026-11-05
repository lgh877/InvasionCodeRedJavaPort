package com.sweegy.invasioncodered.interfaces;

import javax.annotation.Nullable;

/**
 * Single-previous-animation counterpart to IUsingFadingAnims.
 *
 * <p>InvasionCodeRed/Forge 1.20.1 passes the entity directly to its model:
 * there is no 1.21+/1.26 RenderState requirement.</p>
 *
 * <p>Implementations store ONE SimpleFadingOutAnimation reference,
 * not a list. Use {@link com.sweegy.invasioncodered.util.animation.SimpleFadingAnimationController}
 * to avoid maintaining animation timers by hand.</p>
 */
public interface IUsingSimpleFadingAnims extends IUsingCustomVanillaAnimations {
    @Nullable
    SimpleFadingOutAnimation getFadingOutAnimation();

    void setFadingOutAnimation(@Nullable SimpleFadingOutAnimation animation);

    /** 0..1, interpolated for the current incoming animation. */
    float getFadeInTime(float partialTicks);

    default void triggerFadeOut(float finalTime, float fadeOutTime,
                                int animIdx, float fadeOutSpeed) {
        setFadingOutAnimation(new SimpleFadingOutAnimation(
                finalTime, fadeOutTime, animIdx, fadeOutSpeed));
    }

    default void triggerFadeOut(float finalTime, float fadeOutTime, int animIdx) {
        triggerFadeOut(finalTime, fadeOutTime, animIdx, 1.0F);
    }

    default void triggerFadeOut(float finalTime, int animIdx) {
        triggerFadeOut(finalTime, SimpleFadingOutAnimation.DEFAULT_FADE_OUT, animIdx);
    }

    /** Call once per client tick when manually managing the animation. */
    default void tickFadingAnim() {
        SimpleFadingOutAnimation animation = getFadingOutAnimation();
        if (animation == null) return;
        animation.tick();
        if (animation.isFinished()) {
            setFadingOutAnimation(null);
        }
    }

    default void clearFadingAnim() {
        setFadingOutAnimation(null);
    }
}
