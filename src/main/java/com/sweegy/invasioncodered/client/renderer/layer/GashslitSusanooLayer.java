package com.sweegy.invasioncodered.client.renderer.layer;

import com.sweegy.invasioncodered.client.renderer.CustomRenderTypes;
import com.sweegy.invasioncodered.client.model.GashslitAnimatedModel;
import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.model.geom.ModelPart;

import com.sweegy.invasioncodered.entity.gashslit.GashslitEntity;
import com.sweegy.invasioncodered.client.model.Modelsusanogashslit;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class GashslitSusanooLayer extends RenderLayer<GashslitEntity, GashslitAnimatedModel> {
    private static final ResourceLocation LAYER_TEXTURE = ResourceLocation.tryParse("invasioncodered:textures/entities/rage_glint4.png");
    private static final ResourceLocation LAYER_TEXTURE2 = ResourceLocation.tryParse("invasioncodered:textures/entities/rage_glint.png");
	private final Modelsusanogashslit<GashslitEntity> model;
	private final GashslitAnimatedModel parentModel = getParentModel();

	public GashslitSusanooLayer(RenderLayerParent<GashslitEntity, GashslitAnimatedModel> p_117346_, EntityRendererProvider.Context context) {
		super(p_117346_);
		model = new Modelsusanogashslit(context.bakeLayer(Modelsusanogashslit.LAYER_LOCATION));
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, GashslitEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		float progress = Mth.lerp(partialTicks, (float) entity.rageTicksO, (float) entity.rageTicks);
		if (progress > 0) {
			float offset = ageInTicks * 0.03f % 1;
			progress *= 0.00375f;
			setRotation(model.trueHead, parentModel.trueHead);
			setRotation(model.head, parentModel.head);
			setRotation(model.torso, parentModel.torso);
			setRotation(model.rightArm, parentModel.rightArm);
			setRotation(model.leftArm, parentModel.leftArm);
			setRotation(model.whole, parentModel.whole);
			VertexConsumer vertexConsumer2 = bufferSource.getBuffer(CustomRenderTypes.customEnergySwirl2(LAYER_TEXTURE2, offset, offset));
			parentModel.renderToBuffer(poseStack, vertexConsumer2, 15728640, OverlayTexture.NO_OVERLAY, progress, progress, progress, 1);
			VertexConsumer vertexConsumer = bufferSource.getBuffer(CustomRenderTypes.customEnergySwirl(LAYER_TEXTURE, offset, offset));
			model.renderToBuffer(poseStack, vertexConsumer, 15728640, OverlayTexture.NO_OVERLAY, progress, progress, progress, 1);
		}
	}

	private void setRotation(ModelPart target, ModelPart goal) {
		target.xRot = goal.xRot;
		target.yRot = goal.yRot;
		target.zRot = goal.zRot;
	}
}