package com.sweegy.invasioncodered.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.sweegy.invasioncodered.client.model.Modelstonecutterman;
import com.sweegy.invasioncodered.client.model.StoneCutterManAnimatedModel;
import com.sweegy.invasioncodered.client.renderer.layer.HierachicalHeadLayer;
import com.sweegy.invasioncodered.entity.stonecutterman.StoneCutterManEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class StoneCutterManRenderer extends MobRenderer<StoneCutterManEntity, StoneCutterManAnimatedModel> {
    private static final ResourceLocation entityTexture = ResourceLocation.tryParse("invasioncodered:textures/entities/stonecutterman_saw1.png");
    private static final ResourceLocation entityTexture2 = ResourceLocation.tryParse("invasioncodered:textures/entities/stonecutterman_saw2.png");

    public StoneCutterManRenderer(EntityRendererProvider.Context context) {
        super(context, new StoneCutterManAnimatedModel(context.bakeLayer(Modelstonecutterman.LAYER_LOCATION)), 0.5f);
        addLayer(new HierachicalHeadLayer<>(this, context.getModelSet(), context.getItemInHandRenderer()));
    }

    public void render(StoneCutterManEntity mob, float p_115977_, float partialTicks, PoseStack poseStack, MultiBufferSource p_115980_, int p_115981_) {
        if (!mob.isAlive())
            shadowRadius = 0;
        super.render(mob, p_115977_, partialTicks, poseStack, p_115980_, p_115981_);
    }

    @Override
    public ResourceLocation getTextureLocation(StoneCutterManEntity entity) {
        return ((entity.tickCount / 2) & 1) == 0 ? entityTexture : entityTexture2;
    }
}
