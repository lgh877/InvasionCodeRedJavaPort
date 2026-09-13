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
package com.sweegy.invasioncodered.entity.gashslit;

import java.util.stream.Stream;
import java.util.List;
import java.util.Comparator;

import com.google.common.collect.ImmutableList;

public enum GashslitCurrentStates {
	NONE(1.0F), //
	FIRST(0.76F), //
	SECOND(0.44F), //
	THIRD(0.22F), //
	FINAL(0.02f);

	private static final List<GashslitCurrentStates> BY_DAMAGE = Stream.of(values()).sorted(Comparator.comparingDouble((p_28904_) -> {
		return (double) p_28904_.fraction;
	})).collect(ImmutableList.toImmutableList());
	private final float fraction;

	private GashslitCurrentStates(float p_28900_) {
		this.fraction = p_28900_;
	}

	public static GashslitCurrentStates byFraction(float p_28902_) {
		for (GashslitCurrentStates irongolem$crackiness : BY_DAMAGE) {
			if (p_28902_ < irongolem$crackiness.fraction) {
				return irongolem$crackiness;
			}
		}
		return NONE;
	}
}