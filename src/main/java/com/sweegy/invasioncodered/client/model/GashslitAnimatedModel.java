package com.sweegy.invasioncodered.client.model;

import com.sweegy.invasioncodered.client.model.base.FadingAnimsModel;
import com.sweegy.invasioncodered.client.model.unprocessed.Modelgashslit;
import com.sweegy.invasioncodered.config.InvasionCodeRedConfig;
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
            for (ModelPart part : allPartsFlat) {
                part.resetPose();
            }

            float legAnimTicks = entity.getLegAnimTicks(partialTicks) * 0.2f;
            applyFadingAnims(entity, partialTicks, GashslitAnimationIndex.anims);

            AnimUtil.animateWalkAmplitude(this, gashslitAnimation.walk, limbSwing, 1f, (1f - legAnimTicks) * limbSwingAmount * 1.5f);
            AnimUtil.animateWalkAmplitude(this, gashslitAnimation.run, limbSwing, 1.1f, legAnimTicks * limbSwingAmount * 1.5f);
        }

        @Override
        public void applyFadingAnims(GashslitEntity entity, float partialTicks, AnimationDefinition[] animations) {
            List<FadingOutAnimation> list = entity.getFadingAnims();
            float animTicks = entity.getAnimTicks(partialTicks);
            float progress = Mth.lerp(partialTicks, (float) entity.rageTicksO, (float) entity.rageTicks);

            if (progress > 0) {
                progress *= 0.025f;
                animateWalk(gashslitAnimation.ragepose, 1, progress, 1f, 1f);
            }

            int index = entity.getAnimIndex();
            if (index >= 0 && index < animations.length) {
                animateWalk(animations[index], animTicks, entity.getFadeInTime(partialTicks), 1f, 1f);
            }

            for (int i = list.size() - 1; i >= 0; i--) {
                FadingOutAnimation anim = list.get(i);
                if (anim.shouldBeRemoved) {
                    anim.cleanup();
                    continue;
                }
                if (anim.animIdx < 0 || anim.animIdx >= animations.length) continue;
                if (anim.maxFadeOutTime <= 0) continue;

                float weight = Math.max(anim.remainingFadeOutTime - partialTicks * anim.fadeOutSpeed, 0) / anim.maxFadeOutTime;
                animateWalk(animations[anim.animIdx], anim.finalTime, weight, 1f, 1f);
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

    @Override
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

    @Override
    public void translateToHead(PoseStack poseStack) {
        root2.translateAndRotate(poseStack);
        whole.translateAndRotate(poseStack);
        torso.translateAndRotate(poseStack);
        head.translateAndRotate(poseStack);
        trueHead.translateAndRotate(poseStack);
    }

    @Override
    public void setupAnim(GashslitEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        int currentStep = (int) (ageInTicks * InvasionCodeRedConfig.GASHSLIT_ANIM_FRAME.get() * 0.05f);

        EntityModelCache cache = entity.getAnimCache();
        if (!cache.canReuse(currentStep)) {
            animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            if (entity.isCelebrating()) {
                float t = ageInTicks / 30.0f;
                this.head.x += Mth.sin(t * 10.0f);
                this.head.y += Mth.sin(t * 40.0f) + 0.4f;
                this.rightArm.zRot += ((float) Math.PI / 180f) * (70.0f + Mth.cos(t * 40.0f) * 10.0f);
                this.rightArm.xRot += ((float) Math.PI / 180f) * (Mth.cos(t * 10.0f) * 20.0f);
                this.leftArm.zRot += this.rightArm.zRot * -1.0f;
                this.leftArm.xRot += this.rightArm.xRot * -1.0f;
                this.rightArm.y += Mth.sin(t * 40.0f) * 0.5f + 1.5f;
                this.leftArm.y += Mth.sin(t * 40.0f) * 0.5f + 1.5f;
                this.torso.zRot += ((float) Math.PI / 360f) * (Mth.cos(t * 10.0f) * 10.0f);
                this.torso.y += Mth.sin(t * 40.0f) * 0.35f;
            }
            if (this.riding) {
                this.rightLeg.xRot = -1.4137167F;
                this.rightLeg.yRot = ((float)Math.PI / 10F);
                this.leftLeg.xRot = -1.4137167F;
                this.leftLeg.yRot = -((float)Math.PI / 10F);
            }
            super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            cache.capture(allPartsFlat);
            cache.markCaptured(currentStep);
        } else {
            cache.apply(allPartsFlat);
        }
    }
}