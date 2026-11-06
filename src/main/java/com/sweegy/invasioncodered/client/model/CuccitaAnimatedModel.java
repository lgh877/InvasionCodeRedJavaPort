package com.sweegy.invasioncodered.client.model;

import com.sweegy.invasioncodered.client.model.animations.indices.RavagerHunterAnimationIndex;
import com.sweegy.invasioncodered.client.model.base.SimpleFadingAnimsModel;
import com.sweegy.invasioncodered.client.model.unprocessed.ModelCuccita;
import com.sweegy.invasioncodered.entity.cuccita.CuccitaEntity;
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
            applyFadingAnims(entity, partialTicks, RavagerHunterAnimationIndex.anims);
        }

    };

    public CuccitaAnimatedModel(ModelPart root) {
        super(root);
        this.root = root;
        this.allPartsFlat = root.getAllParts().toArray(ModelPart[]::new);
    }
}
