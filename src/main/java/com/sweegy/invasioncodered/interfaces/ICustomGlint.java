package com.sweegy.invasioncodered.interfaces;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public interface ICustomGlint {
    ResourceLocation getCustomGlintTexture(ItemStack stack);

    default boolean hasCustomGlint(ItemStack stack) {
        // isEnchanted()
        return stack.hasFoil();
    }

    ThreadLocal<ItemStack> CURRENT_ITEM = ThreadLocal.withInitial(() -> null);
}