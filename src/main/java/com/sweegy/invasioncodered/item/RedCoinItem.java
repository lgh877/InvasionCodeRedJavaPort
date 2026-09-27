package com.sweegy.invasioncodered.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class RedCoinItem extends Item {
    public RedCoinItem(){
        super(new Item.Properties());
    }

    @Override
    public Component getName(ItemStack stack) {
        return ((MutableComponent) super.getName(stack)).withStyle(ChatFormatting.RED);
    }

    @Override
    public boolean canBeHurtBy(DamageSource p_41387_) {
        return false;
    }
}
