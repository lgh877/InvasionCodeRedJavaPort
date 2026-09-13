package com.sweegy.invasioncodered.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import com.sweegy.invasioncodered.entity.gashslit.GashslitEntity;
import com.sweegy.invasioncodered.client.model.Modelgashslit;
import com.sweegy.invasioncodered.client.renderer.layer.HierachicalHeadLayer;
import com.sweegy.invasioncodered.client.renderer.layer.GashslitSwordParticleLayer;
import com.sweegy.invasioncodered.client.renderer.layer.GashslitSusanooLayer;
import com.sweegy.invasioncodered.client.renderer.layer.GashslitDeathVisualLayer;
import com.sweegy.invasioncodered.client.model.GashslitAnimatedModel;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class GashslitRenderer extends MobRenderer<GashslitEntity, GashslitAnimatedModel> {
    private static final ResourceLocation entityTexture = ResourceLocation.tryParse("invasioncodered:textures/entities/gashslit.png");
    private static final ResourceLocation entityTexture2 = ResourceLocation.tryParse("invasioncodered:textures/entities/nothing.png");

	public GashslitRenderer(EntityRendererProvider.Context context) {
		super(context, new GashslitAnimatedModel(context.bakeLayer(Modelgashslit.LAYER_LOCATION)), 0.5f);
		this.addLayer(new RenderLayer<GashslitEntity, GashslitAnimatedModel>(this) {
            static final ResourceLocation LAYER_TEXTURE = ResourceLocation.tryParse("invasioncodered:textures/entities/gashslit_glow.png");

			@Override
			public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, GashslitEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
				float transparency = 1;
				if (entity.isInDeathAction()) {
					transparency = Math.max(0, 80 - (float) entity.customDeathTicks + partialTicks) / 80f;
				}
				VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityTranslucentEmissive(LAYER_TEXTURE));
				this.getParentModel().renderToBuffer(poseStack, vertexConsumer, 15728640, OverlayTexture.NO_OVERLAY, 1, 1, 1, transparency);
			}
		});
		addLayer(new GashslitSusanooLayer(this, context));
		addLayer(new GashslitDeathVisualLayer(this));
		addLayer(new ItemInHandLayer<>(this, context.getItemInHandRenderer()));
		addLayer(new HierachicalHeadLayer<>(this, context.getModelSet(), context.getItemInHandRenderer()));
		addLayer(new GashslitSwordParticleLayer(this));
	}

	public void render(GashslitEntity mob, float p_115977_, float partialTicks, PoseStack poseStack, MultiBufferSource p_115980_, int p_115981_) {
		if (!mob.isAlive())
			shadowRadius = 0;
		super.render(mob, p_115977_, partialTicks, poseStack, p_115980_, p_115981_);
	}

	@Override
	public ResourceLocation getTextureLocation(GashslitEntity entity) {
		return !entity.isInDeathAction() ? entityTexture : entityTexture2;
	}
}