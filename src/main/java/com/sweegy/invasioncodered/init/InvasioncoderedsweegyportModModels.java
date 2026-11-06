package com.sweegy.invasioncodered.init;

import com.sweegy.invasioncodered.client.model.unprocessed.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import com.sweegy.invasioncodered.client.model.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class InvasioncoderedsweegyportModModels {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(ModelGashslitBoots.LAYER_LOCATION, ModelGashslitBoots::createBodyLayer);
		event.registerLayerDefinition(ModelGashslitLeggings.LAYER_LOCATION, ModelGashslitLeggings::createBodyLayer);
		event.registerLayerDefinition(ModelGashslit.LAYER_LOCATION, ModelGashslit::createBodyLayer);
		event.registerLayerDefinition(ModelStonecutterMan.LAYER_LOCATION, ModelStonecutterMan::createBodyLayer);
		event.registerLayerDefinition(ModelRavagerHunter.LAYER_LOCATION, ModelRavagerHunter::createBodyLayer);
		event.registerLayerDefinition(ModelSusanoGashslit.LAYER_LOCATION, ModelSusanoGashslit::createBodyLayer);
		event.registerLayerDefinition(ModelGashslitSlash.LAYER_LOCATION, ModelGashslitSlash::createBodyLayer);
		event.registerLayerDefinition(ModelGashslitDragon.LAYER_LOCATION, ModelGashslitDragon::createBodyLayer);
		event.registerLayerDefinition(ModelgashslitHeadArmor.LAYER_LOCATION, ModelgashslitHeadArmor::createBodyLayer);
		event.registerLayerDefinition(ModelGashslitChestplate.LAYER_LOCATION, ModelGashslitChestplate::createBodyLayer);
        event.registerLayerDefinition(GashslitLeggingsAnimatedModel.LAYER_LOCATION, GashslitLeggingsAnimatedModel::createBodyLayer);
	}
}