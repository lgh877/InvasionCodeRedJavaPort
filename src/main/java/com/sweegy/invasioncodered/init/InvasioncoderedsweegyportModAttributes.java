package com.sweegy.invasioncodered.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.entity.ai.attributes.Attribute;

import com.sweegy.invasioncodered.InvasioncoderedsweegyportMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class InvasioncoderedsweegyportModAttributes {
	public static final DeferredRegister<Attribute> REGISTRY = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, InvasioncoderedsweegyportMod.MODID);
	public static final RegistryObject<Attribute> GASHSLIT_EQUITMENT_STATE_CHECKER = REGISTRY.register("gashslit_equitment_state_checker",
			() -> new RangedAttribute("attribute.invasioncodered.gashslit_equitment_state_checker", 0, 0, 10).setSyncable(true));

	@SubscribeEvent
	public static void addAttributes(EntityAttributeModificationEvent event) {
		event.getTypes().forEach(entity -> event.add(entity, GASHSLIT_EQUITMENT_STATE_CHECKER.get()));
	}

	@Mod.EventBusSubscriber
	public static class PlayerAttributesSync {
		@SubscribeEvent
		public static void playerClone(PlayerEvent.Clone event) {
			Player oldPlayer = event.getOriginal();
			Player newPlayer = event.getEntity();
			newPlayer.getAttribute(GASHSLIT_EQUITMENT_STATE_CHECKER.get()).setBaseValue(oldPlayer.getAttribute(GASHSLIT_EQUITMENT_STATE_CHECKER.get()).getBaseValue());
		}
	}
}