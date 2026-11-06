package com.sweegy.invasioncodered.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.sweegy.invasioncodered.client.model.animations.indices.RavagerHunterAnimationIndex;
import com.sweegy.invasioncodered.client.model.base.SimpleFadingAnimsModel;
import com.sweegy.invasioncodered.client.model.unprocessed.ModelRavagerHunter;
import com.sweegy.invasioncodered.client.renderer.layer.IHierachicalHeadedModel;
import com.sweegy.invasioncodered.config.InvasionCodeRedConfig;
import com.sweegy.invasioncodered.entity.ravager_hunter.RavagerHunterEntity;
import com.sweegy.invasioncodered.util.animation.EntityModelCache;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;


public class RavagerHunterAnimatedModel extends ModelRavagerHunter<RavagerHunterEntity> implements IHierachicalHeadedModel, ArmedModel {
    private final ModelPart root;
    private final ModelPart[] allPartsFlat;

    private final SimpleFadingAnimsModel<RavagerHunterEntity> animator = new SimpleFadingAnimsModel<>() {
        @Override
        public ModelPart root() { return root; }

        @Override
        public void setupAnim(RavagerHunterEntity entity, float limbSwing, float limbSwingAmount,
                              float ageInTicks, float netHeadYaw, float headPitch) {
            float partialTicks = ageInTicks - entity.tickCount;
            for (ModelPart part : allPartsFlat) {
                part.resetPose();
            }
            applyFadingAnims(entity, partialTicks, RavagerHunterAnimationIndex.anims);
        }

    };

    public RavagerHunterAnimatedModel(ModelPart root) {
        super(root);
        this.root = root;
        this.allPartsFlat = root.getAllParts().toArray(ModelPart[]::new);
    }

    public ModelPart root() { return root; }

    @Override
    public void setupAnim(RavagerHunterEntity entity, float limbSwing, float limbSwingAmount,
                          float ageInTicks, float netHeadYaw, float headPitch) {
        int currentStep = (int) (ageInTicks * InvasionCodeRedConfig.RAVAGER_HUNTER_ANIM_FRAME.get() * 0.05F);
        float partialTicks = ageInTicks - entity.tickCount;
        EntityModelCache cache = entity.getAnimCache();
        if (!cache.canReuse(currentStep)) {
            animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            if (!this.riding) {
                float walkAnim = Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
                float armAnimAmp = entity.getArmAnimAmp(partialTicks);
                float legAnimAmp = entity.getLegAnimAmp(partialTicks);
                arms.xRot = (1 - armAnimAmp) * headPitch / (180F / (float) Math.PI);
                whole.yRot = trueHead.yRot * (1-armAnimAmp);
                trueHead.yRot *= armAnimAmp;
                LeftArm.xRot += walkAnim * armAnimAmp;
                RightArm.xRot -= walkAnim * armAnimAmp;
                LeftLeg.xRot -= walkAnim * 0.7f * legAnimAmp;
                RightLeg.xRot += walkAnim * 0.7f * legAnimAmp;
            }else {
                this.RightLeg.xRot = -1.4137167F;
                this.RightLeg.yRot = ((float)Math.PI / 10F);
                this.LeftLeg.xRot = -1.4137167F;
                this.LeftLeg.yRot = -((float)Math.PI / 10F);
            }

            if(attackTime > 0){
                float f = Mth.sin(attackTime * (float)Math.PI);
                float f1 = Mth.sin((1.0F - (1.0F - attackTime) * (1.0F - attackTime)) * (float)Math.PI);
                RightArm.xRot += f * 2.2F - f1 * 0.4F;;
                LeftArm.xRot += f * 1.2F - f1 * 0.4F;;
            }
            cache.capture(allPartsFlat);
            cache.markCaptured(currentStep);
        } else {
            cache.apply(allPartsFlat);
        }
    }

    @Override
    public void translateToHead(PoseStack poseStack) {
        whole.translateAndRotate(poseStack);
        bone3.translateAndRotate(poseStack);
        head.translateAndRotate(poseStack);
        trueHead.translateAndRotate(poseStack);
    }

    @Override
    public void translateToHand(HumanoidArm humanoidArm, PoseStack poseStack){
        whole.translateAndRotate(poseStack);
        bone3.translateAndRotate(poseStack);
        arms.translateAndRotate(poseStack);
        if(humanoidArm == HumanoidArm.RIGHT){
            RightArm.translateAndRotate(poseStack);
        }else{
            LeftArm.translateAndRotate(poseStack);
        }
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer,
                               int packedLight, int packedOverlay,
                               float red, float green, float blue, float alpha) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
