package com.sweegy.invasioncodered.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.sweegy.invasioncodered.client.model.Modelsusanogashslit;
import com.sweegy.invasioncodered.client.renderer.CustomRenderTypes;
import com.sweegy.invasioncodered.event.susanoo.SusanooAlphaManager;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class GlobalSusanooLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {
    private static final ResourceLocation LAYER_TEXTURE = ResourceLocation.tryParse("invasioncodered:textures/entities/rage_glint4.png");
    private static final ResourceLocation LAYER_TEXTURE2 = ResourceLocation.tryParse("invasioncodered:textures/entities/rage_glint.png");

    private final Modelsusanogashslit<T> sharedModel;

    private final ModelPart[] sharedModelPartsFlat;

    public GlobalSusanooLayer(RenderLayerParent<T, M> parent, Modelsusanogashslit<T> sharedModel) {
        super(parent);
        this.sharedModel = sharedModel;
        this.sharedModelPartsFlat = sharedModel.whole.getAllParts().toArray(ModelPart[]::new);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        float alpha = SusanooAlphaManager.getInterpolatedAlpha(entity, partialTicks);
        if (alpha < 0.001f) return;

        float offset = ageInTicks * 0.03f % 1.0f;

        VertexConsumer vertexConsumer2 = bufferSource.getBuffer(CustomRenderTypes.customEnergySwirl2(LAYER_TEXTURE2, offset, offset));
        this.getParentModel().renderToBuffer(poseStack, vertexConsumer2, 15728640, OverlayTexture.NO_OVERLAY, alpha, alpha, alpha, 1.0f);

        poseStack.pushPose();
        float scale = entity.getBbHeight() / 2.0f;
        poseStack.scale(scale, scale, scale);

        for (ModelPart modelPart : sharedModelPartsFlat) {
            modelPart.resetPose();
        }

        sharedModel.whole.getAllParts().forEach(ModelPart::resetPose);
        if(this.getParentModel() instanceof HumanoidModel<?> hm){
            setRotation(sharedModel.trueHead, hm.head);
            setRotation(sharedModel.torso, hm.body);
            setRotation(sharedModel.rightArm, hm.rightArm);
            setRotation(sharedModel.leftArm, hm.leftArm);
        }else if(this.getParentModel() instanceof IllagerModel<?> im){
            setRotation(sharedModel.trueHead, im.head);
            setRotation(sharedModel.rightArm, im.rightArm);
            setRotation(sharedModel.leftArm, im.leftArm);
        }
        VertexConsumer vertexConsumer = bufferSource.getBuffer(CustomRenderTypes.customEnergySwirl(LAYER_TEXTURE, offset, offset));
        sharedModel.renderToBuffer(poseStack, vertexConsumer, 15728640, OverlayTexture.NO_OVERLAY, alpha, alpha, alpha, 1.0f);

        poseStack.popPose();
    }

    private void setRotation(ModelPart target, ModelPart source) {
        if (target != null && source != null) {
            target.xRot = source.xRot;
            target.yRot = source.yRot;
            target.zRot = source.zRot;
        }
    }
}
