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
package com.sweegy.invasioncodered.interfaces;

import java.util.List;

public class FadingOutAnimation {
	private final List<FadingOutAnimation> ownerList;
	public static final int DEFAULT_FADE_OUT = 5;
	public final int animIdx;
	public final float finalTime;
	public float remainingFadeOutTime, fadeOutSpeed, maxFadeOutTime;
	public boolean shouldBeRemoved = false;

	public FadingOutAnimation(List<FadingOutAnimation> ownerList, float finalTime, float fadeOutTime, int animIdx, float fadeOutSpeed) {
		this.ownerList = ownerList;
		this.finalTime = finalTime;
		this.animIdx = animIdx;
		remainingFadeOutTime = maxFadeOutTime = fadeOutTime;
		this.fadeOutSpeed = fadeOutSpeed;
	}

	public FadingOutAnimation(List<FadingOutAnimation> ownerList, float finalTime, float fadeOutTime, int animIdx) {
		this(ownerList, finalTime, fadeOutTime, animIdx, 1);
	}

	public FadingOutAnimation(List<FadingOutAnimation> ownerList, float finalTime, int animIdx) {
		this(ownerList, finalTime, DEFAULT_FADE_OUT, animIdx, 1);
	}

	public void tick() {
		remainingFadeOutTime = Math.max(remainingFadeOutTime - fadeOutSpeed, 0);
		shouldBeRemoved = remainingFadeOutTime == 0;
	}

	public void cleanup() {
		this.ownerList.remove(this);
	}
}