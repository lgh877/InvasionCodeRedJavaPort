package com.sweegy.invasioncodered.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class ICRTabItemItem extends Item {
	public ICRTabItemItem() {
		super(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC));
	}
}