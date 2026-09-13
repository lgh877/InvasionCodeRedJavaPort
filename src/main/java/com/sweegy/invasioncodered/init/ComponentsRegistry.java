package com.sweegy.invasioncodered.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.Util;
import net.minecraft.ChatFormatting;

import java.util.List;

public class ComponentsRegistry {
    private static final ResourceLocation EMPTY_SLOT_HELMET = ResourceLocation.tryParse("item/empty_armor_slot_helmet");
    private static final ResourceLocation EMPTY_SLOT_CHESTPLATE = ResourceLocation.tryParse("item/empty_armor_slot_chestplate");
    private static final ResourceLocation EMPTY_SLOT_LEGGINGS = ResourceLocation.tryParse("item/empty_armor_slot_leggings");
    private static final ResourceLocation EMPTY_SLOT_BOOTS = ResourceLocation.tryParse("item/empty_armor_slot_boots");
    private static final ResourceLocation EMPTY_SLOT_HOE = ResourceLocation.tryParse("item/empty_slot_hoe");
    private static final ResourceLocation EMPTY_SLOT_AXE = ResourceLocation.tryParse("item/empty_slot_axe");
    private static final ResourceLocation EMPTY_SLOT_SWORD = ResourceLocation.tryParse("item/empty_slot_sword");
    private static final ResourceLocation EMPTY_SLOT_SHOVEL = ResourceLocation.tryParse("item/empty_slot_shovel");
    private static final ResourceLocation EMPTY_SLOT_PICKAXE = ResourceLocation.tryParse("item/empty_slot_pickaxe");
    private static final ResourceLocation EMPTY_SLOT_INGOT = ResourceLocation.tryParse("item/empty_slot_ingot");
    private static final ResourceLocation EMPTY_SLOT_REDSTONE_DUST = ResourceLocation.tryParse("item/empty_slot_redstone_dust");
    private static final ResourceLocation EMPTY_SLOT_QUARTZ = ResourceLocation.tryParse("item/empty_slot_quartz");
    private static final ResourceLocation EMPTY_SLOT_EMERALD = ResourceLocation.tryParse("item/empty_slot_emerald");
    private static final ResourceLocation EMPTY_SLOT_DIAMOND = ResourceLocation.tryParse("item/empty_slot_diamond");
    private static final ResourceLocation EMPTY_SLOT_LAPIS_LAZULI = ResourceLocation.tryParse("item/empty_slot_lapis_lazuli");
    private static final ResourceLocation EMPTY_SLOT_AMETHYST_SHARD = ResourceLocation.tryParse("item/empty_slot_amethyst_shard");
	//
	private static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
	private static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;
	public static final Component GASHSLIT_UPGRADE_APPLIES_TO = Component.translatable(//
			Util.makeDescriptionId("item", ResourceLocation.tryParse("invasioncodered:smithing_template.gashslit_upgrade.applies_to"))//
	).withStyle(DESCRIPTION_FORMAT);
	public static final Component GASHSLIT_UPGRADE_INGREDIENTS = Component.translatable(//
			Util.makeDescriptionId("item", ResourceLocation.tryParse("invasioncodered:gash_ingot"))//
	).withStyle(DESCRIPTION_FORMAT);
	public static final Component GASHSLIT_UPGRADE = Component.translatable(//
			Util.makeDescriptionId("upgrade", ResourceLocation.tryParse("invasioncodered:gashslit_upgrade"))//
	).withStyle(TITLE_FORMAT);
	public static final Component GASHSLIT_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(//
			Util.makeDescriptionId("item", ResourceLocation.tryParse("invasioncodered:smithing_template.gashslit_upgrade.base_slot_description"))//
	);
	public static final Component GASHSLIT_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(//
			Util.makeDescriptionId("item", ResourceLocation.tryParse("invasioncodered:smithing_template.gashslit_upgrade.additions_slot_description"))//
	);

	public static List<ResourceLocation> createUpgradeIconListArmorOnly() {
		return List.of(EMPTY_SLOT_HELMET, EMPTY_SLOT_CHESTPLATE, EMPTY_SLOT_LEGGINGS, EMPTY_SLOT_BOOTS);
	}

	public static List<ResourceLocation> createUpgradeMaterialListIngotOnly() {
		return List.of(EMPTY_SLOT_INGOT);
	}
}