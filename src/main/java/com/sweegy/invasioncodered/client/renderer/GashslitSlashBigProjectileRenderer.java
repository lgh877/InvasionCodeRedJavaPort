package com.sweegy.invasioncodered.client.renderer;

import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import com.sweegy.invasioncodered.entity.projectile.GashslitSlashBigProjectileEntity;
import com.sweegy.invasioncodered.client.model.Modelgashslit_slash;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class GashslitSlashBigProjectileRenderer extends EntityRenderer<GashslitSlashBigProjectileEntity> {
    private static final ResourceLocation texture = ResourceLocation.tryParse("invasioncodered:textures/entities/gashslit_slash.png");
	private final Modelgashslit_slash model;

	protected int getBlockLightLevel(Blaze p_113910_, BlockPos p_113911_) {
		return 15;
	}

	public GashslitSlashBigProjectileRenderer(EntityRendererProvider.Context context) {
		super(context);
		model = new Modelgashslit_slash(context.bakeLayer(Modelgashslit_slash.LAYER_LOCATION));
	}

	@Override
	public void render(GashslitSlashBigProjectileEntity entityIn, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int packedLightIn) {
		VertexConsumer vb = bufferIn.getBuffer(RenderType.eyes(this.getTextureLocation(entityIn)));
		poseStack.pushPose();
		poseStack.scale(-2.0F, -2.0F, 2.0F);
		poseStack.translate(0.0F, -1.501F, 0.0F);
		model.whole.xRot = (-Mth.lerp(partialTicks, entityIn.xRotO, entityIn.getXRot())) * Mth.DEG_TO_RAD;
		model.whole.yRot = (-Mth.lerp(partialTicks, entityIn.yRotO, entityIn.getYRot()) + 180) * Mth.DEG_TO_RAD;
		model.renderToBuffer(poseStack, vb, 15728640, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
		poseStack.popPose();
		super.render(entityIn, entityYaw, partialTicks, poseStack, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getTextureLocation(GashslitSlashBigProjectileEntity entity) {
		return texture;
	}
}