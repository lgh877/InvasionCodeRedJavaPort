package com.sweegy.invasioncodered.client.model;

import com.sweegy.invasioncodered.client.model.base.FadingAnimsModel;
import com.sweegy.invasioncodered.util.animation.AnimUtil;
import com.sweegy.invasioncodered.interfaces.FadingOutAnimation;
import com.sweegy.invasioncodered.client.renderer.layer.IHierachicalHeadedModel;
import com.sweegy.invasioncodered.client.model.animations.indices.GashslitAnimationIndex;
import com.sweegy.invasioncodered.util.animation.EntityModelCache;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.util.Mth;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.animation.AnimationDefinition;

import java.util.List;

import com.sweegy.invasioncodered.entity.gashslit.GashslitEntity;
import com.sweegy.invasioncodered.client.model.animations.gashslitAnimation;

import com.mojang.blaze3d.vertex.PoseStack;

public class GashslitAnimatedModel extends Modelgashslit<GashslitEntity> implements ArmedModel, IHierachicalHeadedModel {
    public final ModelPart root;
    private final ModelPart[] allPartsFlat;
    private final FadingAnimsModel<GashslitEntity> animator = new FadingAnimsModel<>() {
        @Override
        public ModelPart root() {
            return root;
        }

        @Override
        public void setupAnim(GashslitEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
            float partialTicks = ageInTicks - entity.tickCount;
            int currentStep = (int) (ageInTicks * 3);

            EntityModelCache cache = entity.getAnimCache();
            List<ModelPart> allParts = this.root().getAllParts().toList();

            if (currentStep != cache.lastAnimStep) {
                allParts.forEach(ModelPart::resetPose);
                for (ModelPart modelPart : allPartsFlat) {
                    modelPart.resetPose();
                }
                float legAnimTicks = entity.getLegAnimTicks(partialTicks) * 0.2f;
                this.applyFadingAnims(entity, partialTicks, GashslitAnimationIndex.anims);
                AnimUtil.animateWalkAmplitude(this, gashslitAnimation.walk, limbSwing, 1f, (1f - legAnimTicks) * limbSwingAmount * 1.5f);
                AnimUtil.animateWalkAmplitude(this, gashslitAnimation.run, limbSwing, 1.1f, legAnimTicks * limbSwingAmount * 1.5f);

                cache.capture(allParts);
                cache.lastAnimStep = currentStep;
            } else {
                cache.apply(allParts);
            }
        }

        @Override
        public void applyFadingAnims(GashslitEntity entity, float partialTicks, AnimationDefinition[] animations) {
            List<FadingOutAnimation> list = entity.getFadingAnims();
            float animTicks = entity.getAnimTicks(partialTicks);
            float progress = Mth.lerp(partialTicks, (float) entity.rageTicksO, (float) entity.rageTicks);
            if (progress > 0) {
                progress *= 0.025;
                animateWalk(gashslitAnimation.ragepose, 1, progress, 1f, 1f);
            }
            if (entity.getAnimIndex() != -1)
                this.animateWalk(animations[entity.getAnimIndex()], animTicks, entity.getFadeInTime(partialTicks), 1f, 1f);
            for (int i = list.size() - 1; i >= 0; i--) {
                FadingOutAnimation anim = list.get(i);
                if (anim.shouldBeRemoved) {
                    anim.cleanup();
                    continue;
                }
                this.animateWalk(animations[anim.animIdx], anim.finalTime, (float) Math.max(anim.remainingFadeOutTime - partialTicks * anim.fadeOutSpeed, 0) / anim.maxFadeOutTime, 1f, 1f);
            }
        }
    };

    public GashslitAnimatedModel(ModelPart root) {
        super(root);
        this.root = root;
        this.allPartsFlat = root.getAllParts().toArray(ModelPart[]::new);
        rightItemLayer.visible = false;
        leftItemLayer.visible = false;
    }

    public void translateLeftHand(PoseStack poseStack) {
        root2.translateAndRotate(poseStack);
        whole.translateAndRotate(poseStack);
        torso.translateAndRotate(poseStack);
        leftArm.translateAndRotate(poseStack);
        leftItemLayer.translateAndRotate(poseStack);
        leftItemParticlePos.translateAndRotate(poseStack);
    }

    public void translateRightHand(PoseStack poseStack) {
        root2.translateAndRotate(poseStack);
        whole.translateAndRotate(poseStack);
        torso.translateAndRotate(poseStack);
        rightArm.translateAndRotate(poseStack);
        rightItemLayer.translateAndRotate(poseStack);
        rightItemParticlePos.translateAndRotate(poseStack);
    }

    public void translateToHand(HumanoidArm arm, PoseStack poseStack) {
        root2.translateAndRotate(poseStack);
        whole.translateAndRotate(poseStack);
        torso.translateAndRotate(poseStack);
        if (arm == HumanoidArm.RIGHT) {
            rightArm.translateAndRotate(poseStack);
            rightItemLayer.translateAndRotate(poseStack);
            poseStack.translate(0, -0.5, 0);
        } else {
            leftArm.translateAndRotate(poseStack);
            leftItemLayer.translateAndRotate(poseStack);
            poseStack.translate(0, -0.5, 0);
        }
    }

    public void translateToHead(PoseStack poseStack) {
        root2.translateAndRotate(poseStack);
        whole.translateAndRotate(poseStack);
        torso.translateAndRotate(poseStack);
        head.translateAndRotate(poseStack);
        trueHead.translateAndRotate(poseStack);
    }

    @Override
    public void setupAnim(GashslitEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
    }
}