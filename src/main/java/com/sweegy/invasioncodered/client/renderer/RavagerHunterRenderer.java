package com.sweegy.invasioncodered.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.sweegy.invasioncodered.client.model.RavagerHunterAnimatedModel;
import com.sweegy.invasioncodered.client.model.unprocessed.ModelRavagerHunter;
import com.sweegy.invasioncodered.client.renderer.layer.HierachicalHeadLayer;
import com.sweegy.invasioncodered.entity.ravager_hunter.RavagerHunterEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;

public class RavagerHunterRenderer extends MobRenderer<RavagerHunterEntity, RavagerHunterAnimatedModel> {
    private static final ResourceLocation entityTexture = ResourceLocation.tryParse("invasioncodered:textures/entities/brawler.png");

    public RavagerHunterRenderer(EntityRendererProvider.Context context) {
        super(context, new RavagerHunterAnimatedModel(context.bakeLayer(ModelRavagerHunter.LAYER_LOCATION)), 0.5f);
        addLayer(new HierachicalHeadLayer<>(this, context.getModelSet(), context.getItemInHandRenderer()));
        addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
    }

    public void render(RavagerHunterEntity mob, float p_115977_, float partialTicks, PoseStack poseStack, MultiBufferSource p_115980_, int p_115981_) {
        if (!mob.isAlive())
            shadowRadius = 0;
        super.render(mob, p_115977_, partialTicks, poseStack, p_115980_, p_115981_);
    }

    @Override
    public ResourceLocation getTextureLocation(RavagerHunterEntity entity) {
        return entityTexture;
    }
}
