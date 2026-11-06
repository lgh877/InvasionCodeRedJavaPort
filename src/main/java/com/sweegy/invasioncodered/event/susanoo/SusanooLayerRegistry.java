package com.sweegy.invasioncodered.event.susanoo;

import com.mojang.logging.LogUtils;
import com.sweegy.invasioncodered.InvasioncoderedsweegyportMod;
import com.sweegy.invasioncodered.client.model.unprocessed.ModelSusanoGashslit;
import com.sweegy.invasioncodered.client.renderer.layer.GlobalSusanooLayer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;

@Mod.EventBusSubscriber(modid = InvasioncoderedsweegyportMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SusanooLayerRegistry {
    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void onAddLayers(EntityRenderersEvent.AddLayers event) {
        ModelSusanoGashslit<LivingEntity> sharedModel = new ModelSusanoGashslit<>(event.getContext().bakeLayer(ModelSusanoGashslit.LAYER_LOCATION));
        Set<LivingEntityRenderer<?, ?>> processed = Collections.newSetFromMap(new IdentityHashMap<>());
        for (EntityType<?> type : ForgeRegistries.ENTITY_TYPES) {
            LivingEntityRenderer renderer;
            try {
                renderer = event.getRenderer((EntityType) type);
            } catch (ClassCastException nonLivingRenderer) {
                continue;
            } catch (Exception exception) {
                LOGGER.warn("Failed to resolve renderer for entity type {}", ForgeRegistries.ENTITY_TYPES.getKey(type), exception);
                continue;
            }
            addLayer(renderer, sharedModel, processed, String.valueOf(ForgeRegistries.ENTITY_TYPES.getKey(type)));
        }
        for (String skinType : event.getSkins()) {
            LivingEntityRenderer<?, ?> renderer = event.getSkin(skinType);
            addLayer(renderer, sharedModel, processed, "player/" + skinType);
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static void addLayer(LivingEntityRenderer renderer, ModelSusanoGashslit<LivingEntity> sharedModel, Set<LivingEntityRenderer<?, ?>> processed, String key) {
        if (renderer == null || !processed.add(renderer)) {
            return;
        }
        try {
            renderer.addLayer(new GlobalSusanooLayer(renderer, sharedModel));
        } catch (Exception exception) {
            LOGGER.warn("Failed to add Susanoo layer to {}", key, exception);
        }
    }
}
