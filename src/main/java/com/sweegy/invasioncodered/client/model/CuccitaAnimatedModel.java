package com.sweegy.invasioncodered.client.model;

import com.sweegy.invasioncodered.client.model.animations.indices.CuccitaAnimationIndex;
import com.sweegy.invasioncodered.client.model.animations.indices.RavagerHunterAnimationIndex;
import com.sweegy.invasioncodered.client.model.base.SimpleFadingAnimsModel;
import com.sweegy.invasioncodered.client.model.unprocessed.ModelCuccita;
import com.sweegy.invasioncodered.config.InvasionCodeRedConfig;
import com.sweegy.invasioncodered.entity.cuccita.CuccitaEntity;
import com.sweegy.invasioncodered.entity.ravager_hunter.RavagerHunterEntity;
import com.sweegy.invasioncodered.util.animation.EntityModelCache;
import net.minecraft.client.model.geom.ModelPart;

public class CuccitaAnimatedModel extends ModelCuccita<CuccitaEntity> {
    private final ModelPart root;
    private final ModelPart[] allPartsFlat;

    private final SimpleFadingAnimsModel<CuccitaEntity> animator = new SimpleFadingAnimsModel<>() {
        @Override
        public ModelPart root() { return root; }

        @Override
        public void setupAnim(CuccitaEntity entity, float limbSwing, float limbSwingAmount,
                              float ageInTicks, float netHeadYaw, float headPitch) {
            float partialTicks = ageInTicks - entity.tickCount;
            for (ModelPart part : allPartsFlat) {
                part.resetPose();
            }
            applyFadingAnims(entity, partialTicks, CuccitaAnimationIndex.anims);
        }

    };

    public CuccitaAnimatedModel(ModelPart root) {
        super(root);
        this.root = root;
        this.allPartsFlat = root.getAllParts().toArray(ModelPart[]::new);
    }

    @Override
    public void setupAnim(CuccitaEntity entity, float limbSwing, float limbSwingAmount,
                          float ageInTicks, float netHeadYaw, float headPitch) {
        int currentStep = (int) (ageInTicks * InvasionCodeRedConfig.RAVAGER_HUNTER_ANIM_FRAME.get() * 0.05F);
        EntityModelCache cache = entity.getAnimCache();
        if (!cache.canReuse(currentStep)) {
            animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            cache.capture(allPartsFlat);
            cache.markCaptured(currentStep);
        } else {
            cache.apply(allPartsFlat);
        }
    }
}
