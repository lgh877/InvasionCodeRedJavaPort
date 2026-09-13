package com.sweegy.invasioncodered.client.renderer.layer;

import com.sweegy.invasioncodered.client.renderer.CustomRenderTypes;
import com.sweegy.invasioncodered.client.model.GashslitAnimatedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import com.sweegy.invasioncodered.entity.gashslit.GashslitEntity;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class GashslitDeathVisualLayer extends RenderLayer<GashslitEntity, GashslitAnimatedModel> {
    private static final ResourceLocation entityTexture = ResourceLocation.tryParse("invasioncodered:textures/entities/gashslit.png");
    private static final ResourceLocation entityTexture2 = ResourceLocation.tryParse("invasioncodered:textures/entities/gashslit_death_texture.png");
	private static final RenderType DECAL = CustomRenderTypes.entityTranslucentDecal(entityTexture);

	public GashslitDeathVisualLayer(RenderLayerParent<GashslitEntity, GashslitAnimatedModel> p_117346_) {
		super(p_117346_);
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, GashslitEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		if (entity.isInDeathAction()) {
			float progress = (float) entity.customDeathTicks + partialTicks;
			if (progress > 191) {
				float rawProgress = (progress - 190f) / 70f;
				float clampedProgress = Math.max(0, Math.min(1.0f, rawProgress));//Math.max(0.0001f, Math.min(1.0f, rawProgress));
				float explodeAlpha = 1.0f - clampedProgress;
				VertexConsumer vertexConsumerExplosion = bufferSource.getBuffer(CustomRenderTypes.customExplosionAlpha(entityTexture2));
				getParentModel().renderToBuffer(poseStack, vertexConsumerExplosion, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1.0f, 1.0f, 1.0f, clampedProgress);
				VertexConsumer vertexConsumerDecal = bufferSource.getBuffer(DECAL);
				getParentModel().renderToBuffer(poseStack, vertexConsumerDecal, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 0.0f, 0.0f, 0.0f, explodeAlpha);
			} else if (progress > 120) {
				float blackProgress = (progress - 120) / 70f;
				blackProgress = Math.max(0.0f, Math.min(1.0f, blackProgress));
				float rgbValues = 1.0f - blackProgress;
				VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(entityTexture));
				getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), rgbValues, rgbValues, rgbValues, 1.0f);
			} else {
				VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutoutNoCull(entityTexture));
				getParentModel().renderToBuffer(poseStack, vertexConsumer, light, LivingEntityRenderer.getOverlayCoords(entity, 0), 1.0f, 1.0f, 1.0f, 1.0f);
			}
		}
	}
}