package com.sweegy.invasioncodered.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SmithingTemplateItem;

import com.sweegy.invasioncodered.init.ComponentsRegistry;

public class GashUpgradeSmithingTemplateItem extends SmithingTemplateItem {
	public GashUpgradeSmithingTemplateItem() {
		super(//
				ComponentsRegistry.GASHSLIT_UPGRADE_APPLIES_TO, //
				ComponentsRegistry.GASHSLIT_UPGRADE_INGREDIENTS, //
				ComponentsRegistry.GASHSLIT_UPGRADE, //
				ComponentsRegistry.GASHSLIT_UPGRADE_BASE_SLOT_DESCRIPTION, //
				ComponentsRegistry.GASHSLIT_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, //
				ComponentsRegistry.createUpgradeIconListArmorOnly(), //
				ComponentsRegistry.createUpgradeMaterialListIngotOnly()//
		);
	}

    @Override
    public boolean canBeHurtBy(DamageSource p_41387_) {
        return false;
    }

    @Override
    public Component getName(ItemStack stack) {
        return ((MutableComponent) super.getName(stack)).withStyle(ChatFormatting.RED);
    }
}