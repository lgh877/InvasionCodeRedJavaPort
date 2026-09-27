package com.sweegy.invasioncodered.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import com.sweegy.invasioncodered.client.model.*;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class InvasioncoderedsweegyportModModels {
	@SubscribeEvent
	public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(Modelgashslit_boots.LAYER_LOCATION, Modelgashslit_boots::createBodyLayer);
		event.registerLayerDefinition(Modelgashslit_leggings.LAYER_LOCATION, Modelgashslit_leggings::createBodyLayer);
		event.registerLayerDefinition(Modelgashslit.LAYER_LOCATION, Modelgashslit::createBodyLayer);
		event.registerLayerDefinition(Modelsusanogashslit.LAYER_LOCATION, Modelsusanogashslit::createBodyLayer);
		event.registerLayerDefinition(Modelgashslit_slash.LAYER_LOCATION, Modelgashslit_slash::createBodyLayer);
		event.registerLayerDefinition(Modelgashslitdragon.LAYER_LOCATION, Modelgashslitdragon::createBodyLayer);
		event.registerLayerDefinition(Modelgashslit_head_armor.LAYER_LOCATION, Modelgashslit_head_armor::createBodyLayer);
		event.registerLayerDefinition(Modelgashslit_chestplate.LAYER_LOCATION, Modelgashslit_chestplate::createBodyLayer);
        event.registerLayerDefinition(GashslitLeggingsAnimatedModel.LAYER_LOCATION, GashslitLeggingsAnimatedModel::createBodyLayer);
	}
}