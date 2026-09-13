package com.sweegy.invasioncodered.util;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.tags.BlockTags;

public class OtherStuff {
	public static boolean canDestroy(BlockState p_31492_) {
		return !p_31492_.isAir() && !p_31492_.is(BlockTags.WITHER_IMMUNE);
	}
}