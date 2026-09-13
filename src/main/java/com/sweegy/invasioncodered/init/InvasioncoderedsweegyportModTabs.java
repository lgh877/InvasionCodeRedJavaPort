package com.sweegy.invasioncodered.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import com.sweegy.invasioncodered.InvasioncoderedsweegyportMod;

public class InvasioncoderedsweegyportModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, InvasioncoderedsweegyportMod.MODID);
	public static final RegistryObject<CreativeModeTab> ICR = REGISTRY.register("invasion_code_red_mod_tab",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.invasioncodered.icr_mobs")).icon(() -> new ItemStack(InvasioncoderedsweegyportModItems.ICR_TAB_ITEM.get())).displayItems((parameters, tabData) -> {
				//ingredients
                tabData.accept(InvasioncoderedsweegyportModItems.GASH_INGOT.get());
				tabData.accept(InvasioncoderedsweegyportModItems.GASH_UPGRADE_SMITHING_TEMPLATE.get());
				tabData.accept(InvasioncoderedsweegyportModItems.RED_COIN.get());
                //weapons
				tabData.accept(InvasioncoderedsweegyportModItems.ROSE_DIAMOND_SWORD.get());
                //armors
				tabData.accept(InvasioncoderedsweegyportModItems.CURSED_ARMOR_HELMET.get());
				tabData.accept(InvasioncoderedsweegyportModItems.CURSED_ARMOR_CHESTPLATE.get());
				tabData.accept(InvasioncoderedsweegyportModItems.CURSED_ARMOR_LEGGINGS.get());
				tabData.accept(InvasioncoderedsweegyportModItems.CURSED_ARMOR_BOOTS.get());
                //spawneggs
				tabData.accept(InvasioncoderedsweegyportModItems.GASHSLIT_SPAWN_EGG.get());
				tabData.accept(InvasioncoderedsweegyportModItems.GASHSLIT_DRAGON_SPAWN_EGG.get());
			}).build());
}