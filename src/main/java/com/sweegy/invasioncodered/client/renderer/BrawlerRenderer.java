package com.sweegy.invasioncodered.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.sweegy.invasioncodered.client.model.BrawlerAnimatedModel;
import com.sweegy.invasioncodered.client.model.Modelbrawler;
import com.sweegy.invasioncodered.client.renderer.layer.HierachicalHeadLayer;
import com.sweegy.invasioncodered.entity.brawler.BrawlerEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;

public class BrawlerRenderer extends MobRenderer<BrawlerEntity, BrawlerAnimatedModel> {
    private static final ResourceLocation entityTexture = ResourceLocation.tryParse("invasioncodered:textures/entities/brawler.png");

    public BrawlerRenderer(EntityRendererProvider.Context context) {
        super(context, new BrawlerAnimatedModel(context.bakeLayer(Modelbrawler.LAYER_LOCATION)), 0.5f);
        addLayer(new HierachicalHeadLayer<>(this, context.getModelSet(), context.getItemInHandRenderer()));
        addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
    }

    public void render(BrawlerEntity mob, float p_115977_, float partialTicks, PoseStack poseStack, MultiBufferSource p_115980_, int p_115981_) {
        if (!mob.isAlive())
            shadowRadius = 0;
        super.render(mob, p_115977_, partialTicks, poseStack, p_115980_, p_115981_);
    }

    @Override
    public ResourceLocation getTextureLocation(BrawlerEntity entity) {
        return entityTexture;
    }
}
