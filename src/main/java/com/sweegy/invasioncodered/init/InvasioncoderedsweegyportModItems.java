package com.sweegy.invasioncodered.init;

import com.sweegy.invasioncodered.item.*;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.ForgeSpawnEggItem;

import net.minecraft.world.item.Item;

import com.sweegy.invasioncodered.InvasioncoderedsweegyportMod;

public class InvasioncoderedsweegyportModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, InvasioncoderedsweegyportMod.MODID);
	public static final RegistryObject<Item> GASHSLIT_SPAWN_EGG;
	public static final RegistryObject<Item> ROSE_DIAMOND_SWORD;
	public static final RegistryObject<Item> ICR_TAB_ITEM;
	public static final RegistryObject<Item> GASHSLIT_DRAGON_SPAWN_EGG;
	public static final RegistryObject<Item> GASH_INGOT;
	public static final RegistryObject<Item> GASH_UPGRADE_SMITHING_TEMPLATE;
	public static final RegistryObject<Item> CURSED_ARMOR_HELMET;
	public static final RegistryObject<Item> CURSED_ARMOR_CHESTPLATE;
	public static final RegistryObject<Item> CURSED_ARMOR_LEGGINGS;
	public static final RegistryObject<Item> CURSED_ARMOR_BOOTS;
	public static final RegistryObject<Item> RED_COIN;
	static {
		GASHSLIT_SPAWN_EGG = REGISTRY.register("gashslit_spawn_egg", () -> new ForgeSpawnEggItem(InvasioncoderedsweegyportModEntities.GASHSLIT, -1, -1, new Item.Properties()));
		ROSE_DIAMOND_SWORD = REGISTRY.register("rose_diamond_sword", RoseDiamondSwordItem::new);
		ICR_TAB_ITEM = REGISTRY.register("icr_tab_item", ICRTabItemItem::new);
		GASHSLIT_DRAGON_SPAWN_EGG = REGISTRY.register("gashslit_dragon_spawn_egg", () -> new ForgeSpawnEggItem(InvasioncoderedsweegyportModEntities.GASHSLIT_DRAGON, -1, -1, new Item.Properties()));
		GASH_INGOT = REGISTRY.register("gash_ingot", GashIngotItem::new);
		GASH_UPGRADE_SMITHING_TEMPLATE = REGISTRY.register("gash_upgrade_smithing_template", GashUpgradeSmithingTemplateItem::new);
		CURSED_ARMOR_HELMET = REGISTRY.register("cursed_armor_helmet", CursedArmorItem.Helmet::new);
		CURSED_ARMOR_CHESTPLATE = REGISTRY.register("cursed_armor_chestplate", CursedArmorItem.Chestplate::new);
		CURSED_ARMOR_LEGGINGS = REGISTRY.register("cursed_armor_leggings", CursedArmorItem.Leggings::new);
		CURSED_ARMOR_BOOTS = REGISTRY.register("cursed_armor_boots", CursedArmorItem.Boots::new);
		RED_COIN = REGISTRY.register("red_coin", RedCoinItem::new);
	}
}