package com.sweegy.invasioncodered.event.susanoo;

import com.sweegy.invasioncodered.InvasioncoderedsweegyportMod;
import com.sweegy.invasioncodered.client.model.Modelsusanogashslit;
import com.sweegy.invasioncodered.client.renderer.layer.GlobalSusanooLayer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = InvasioncoderedsweegyportMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SusanooLayerRegistry {

    @SubscribeEvent
    public static void onAddLayers(EntityRenderersEvent.AddLayers event) {
        Modelsusanogashslit<LivingEntity> sharedModel = new Modelsusanogashslit<>(event.getContext().bakeLayer(Modelsusanogashslit.LAYER_LOCATION));

        for (EntityType<?> type : ForgeRegistries.ENTITY_TYPES) {
            try {
                EntityRenderer renderer = event.getRenderer((EntityType) type);
                if (renderer instanceof LivingEntityRenderer livingRenderer) {
                    livingRenderer.addLayer(new GlobalSusanooLayer<>(livingRenderer, sharedModel));
                }
            } catch (Exception ignored) {}
        }

        for (String skinType : event.getSkins()) {
            LivingEntityRenderer<?, ?> renderer = event.getSkin(skinType);
            if (renderer != null) {
                renderer.addLayer(new GlobalSusanooLayer(renderer, sharedModel));
            }
        }
    }
}
