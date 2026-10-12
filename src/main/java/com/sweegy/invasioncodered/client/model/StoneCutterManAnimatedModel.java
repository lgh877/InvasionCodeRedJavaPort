package com.sweegy.invasioncodered.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.sweegy.invasioncodered.client.model.animations.gashslitAnimation;
import com.sweegy.invasioncodered.client.model.animations.indices.GashslitAnimationIndex;
import com.sweegy.invasioncodered.client.model.animations.indices.StoneCutterManAnimationIndex;
import com.sweegy.invasioncodered.client.model.animations.stonecuttermanAnimation;
import com.sweegy.invasioncodered.client.model.base.FadingAnimsModel;
import com.sweegy.invasioncodered.client.renderer.layer.IHierachicalHeadedModel;
import com.sweegy.invasioncodered.config.InvasionCodeRedConfig;
import com.sweegy.invasioncodered.entity.stonecutterman.StoneCutterManEntity;
import com.sweegy.invasioncodered.interfaces.FadingOutAnimation;
import com.sweegy.invasioncodered.util.animation.EntityModelCache;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;

import java.util.List;

public class StoneCutterManAnimatedModel extends Modelstonecutterman<StoneCutterManEntity> implements IHierachicalHeadedModel {
    public final ModelPart root;
    private final ModelPart[] allPartsFlat;

    private final FadingAnimsModel<StoneCutterManEntity> animator = new FadingAnimsModel<>() {
        @Override
        public ModelPart root() {
            return root;
        }

        @Override
        public void setupAnim(StoneCutterManEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
            float partialTicks = ageInTicks - entity.tickCount;
            for (ModelPart part : allPartsFlat) {
                part.resetPose();
            }

            //float legAnimTicks = entity.getLegAnimTicks(partialTicks) * 0.2f;
            animateWalk(stonecuttermanAnimation.runningpose, limbSwing, limbSwingAmount, 1f, 1f);
            applyFadingAnims(entity, partialTicks, StoneCutterManAnimationIndex.anims);


            //AnimUtil.animateWalkAmplitude(this, gashslitAnimation.walk, limbSwing, 1f, (1f - legAnimTicks) * limbSwingAmount * 1.5f);
            //AnimUtil.animateWalkAmplitude(this, gashslitAnimation.run, limbSwing, 1.1f, legAnimTicks * limbSwingAmount * 1.5f);
        }

        @Override
        public void applyFadingAnims(StoneCutterManEntity entity, float partialTicks, AnimationDefinition[] animations) {
            List<FadingOutAnimation> list = entity.getFadingAnims();
            float animTicks = entity.getAnimTicks(partialTicks);
//            float progress = Mth.lerp(partialTicks, (float) entity.rageTicksO, (float) entity.rageTicks);
//
//            if (progress > 0) {
//                progress *= 0.025f;
//                animateWalk(gashslitAnimation.ragepose, 1, progress, 1f, 1f);
//            }

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

    public StoneCutterManAnimatedModel(ModelPart root) {
        super(root);
        this.root = root;
        this.allPartsFlat = root.getAllParts().toArray(ModelPart[]::new);
    }

    @Override
    public void translateToHead(PoseStack poseStack) {
        whole.translateAndRotate(poseStack);
        bone3.translateAndRotate(poseStack);
        trueHead.translateAndRotate(poseStack);
        head.translateAndRotate(poseStack);
    }

    @Override
    public void setupAnim(StoneCutterManEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        int currentStep = (int) (ageInTicks * InvasionCodeRedConfig.GASHSLIT_ANIM_FRAME.get() * 0.05f);

        EntityModelCache cache = entity.getAnimCache();
        if (!cache.canReuse(currentStep)) {
            animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            this.trueHead.yRot = netHeadYaw / (180F / (float) Math.PI);
            this.trueHead.xRot = headPitch / (180F / (float) Math.PI);

            float limbAmplitude = 1f + Mth.lerp(ageInTicks - entity.tickCount, entity.sprintTickO, entity.sprintTick) * 0.1f;
            float limbAnim = Mth.cos(limbSwing * 0.6662F) * limbAmplitude * limbSwingAmount;

            LeftArm.xRot += limbAnim;
            RightArm.xRot += -limbAnim;
            LeftLeg.xRot += -limbAnim;
            RightLeg.xRot += limbAnim;

            if (limbAmplitude > 1.0001f) {
                float rand1 = entity.getRandom().nextFloat() * (limbAmplitude - 1f) * 2
                        , rand2 = entity.getRandom().nextFloat() * (limbAmplitude - 1f) * 2;
                head.x += rand1;
                head.z += rand2;
                head.xRot += rand1 * 0.1f;
                head.yRot += rand2 * 0.1f;
                head.zRot += rand1 * 0.1f;
            }

            cache.capture(allPartsFlat);
            cache.lastAnimStep = currentStep;
            cache.markCaptured(currentStep);
        } else {
            cache.apply(allPartsFlat);
        }
    }
}
