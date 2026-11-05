package com.sweegy.invasioncodered.util.animation;

import com.sweegy.invasioncodered.interfaces.SimpleFadingOutAnimation;
import net.minecraft.util.Mth;

import javax.annotation.Nullable;

/**
 * Optional, client-side timer/state helper for IUsingSimpleFadingAnims.
 *
 * <p>Call transitionTo() and tick() on the CLIENT each entity tick. The
 * controller holds the current animation and a single outgoing animation.
 * It does not synchronize an action state or select combat actions; those
 * remain the responsibility of the owning entity.</p>
 *
 * <p>Index -1 means no current AnimationDefinition (useful for IDLE).
 * A transition to -1 still fades out the outgoing animation.</p>
 */
public final class SimpleFadingAnimationController {
    private final float fadeInDuration;
    private final float fadeOutDuration;

    private int animIndex = -1;
    private float animTicks;
    private float animTicksO;
    private float fadeInTicks;
    private float fadeInTicksO;

    @Nullable
    private SimpleFadingOutAnimation fadingOutAnimation;

    public SimpleFadingAnimationController() {
        this(5.0F, 5.0F);
    }

    public SimpleFadingAnimationController(float fadeInDuration, float fadeOutDuration) {
        if (!Float.isFinite(fadeInDuration) || fadeInDuration < 0.0F
                || !Float.isFinite(fadeOutDuration) || fadeOutDuration < 0.0F) {
            throw new IllegalArgumentException("Fade durations must be finite and nonnegative");
        }
        this.fadeInDuration = fadeInDuration;
        this.fadeOutDuration = fadeOutDuration;
    }

    /**
     * Call with the model's ZERO-BASED index, not necessarily the networked
     * action-state ID. Repeated calls with the same index do nothing.
     */
    public void transitionTo(int newIndex) {
        if (newIndex == animIndex) return;
        startNewAnimation(newIndex);
    }

    /** Replays the same animation too, useful for repeated action events. */
    public void restart(int newIndex) {
        startNewAnimation(newIndex);
    }

    private void startNewAnimation(int newIndex) {
        // The old fading reference is ALWAYS replaced: never accumulate a list.
        if (animIndex >= 0 && animTicks > 0.0F && fadeOutDuration > 0.0F) {
            fadingOutAnimation = new SimpleFadingOutAnimation(
                    animTicks, Math.min(animTicks, fadeOutDuration), animIndex);
        } else {
            fadingOutAnimation = null;
        }
        animIndex = newIndex;
        animTicks = animTicksO = 0.0F;
        fadeInTicks = fadeInTicksO = 0.0F;
    }

    /** Call once per CLIENT entity tick, AFTER transitionTo()/restart(). */
    public void tick() {
        animTicksO = animTicks;
        animTicks += 1.0F;
        fadeInTicksO = fadeInTicks;
        fadeInTicks = Math.min(fadeInDuration, fadeInTicks + 1.0F);

        if (fadingOutAnimation != null) {
            fadingOutAnimation.tick();
            if (fadingOutAnimation.isFinished()) {
                fadingOutAnimation = null;
            }
        }
    }

    public int getAnimIndex() {
        return animIndex;
    }

    public float getAnimTicks(float partialTicks) {
        return Mth.lerp(Mth.clamp(partialTicks, 0.0F, 1.0F), animTicksO, animTicks);
    }

    public float getFadeInTime(float partialTicks) {
        if (fadeInDuration <= 0.0F) return 1.0F;
        float progress = Mth.lerp(Mth.clamp(partialTicks, 0.0F, 1.0F), fadeInTicksO, fadeInTicks);
        return Mth.clamp(progress / fadeInDuration, 0.0F, 1.0F);
    }

    @Nullable
    public SimpleFadingOutAnimation getFadingOutAnimation() {
        return fadingOutAnimation;
    }

    public void setFadingOutAnimation(@Nullable SimpleFadingOutAnimation animation) {
        fadingOutAnimation = animation;
    }

    /** For entity removal/reinitialization, not normal animation transitions. */
    public void reset() {
        animIndex = -1;
        animTicks = animTicksO = fadeInTicks = fadeInTicksO = 0.0F;
        fadingOutAnimation = null;
    }
}
