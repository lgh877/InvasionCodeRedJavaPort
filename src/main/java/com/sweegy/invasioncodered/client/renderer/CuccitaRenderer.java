package com.sweegy.invasioncodered.client.renderer;

import com.sweegy.invasioncodered.client.model.CuccitaAnimatedModel;
import com.sweegy.invasioncodered.client.model.unprocessed.ModelCuccita;
import com.sweegy.invasioncodered.client.model.unprocessed.ModelRavagerHunter;
import com.sweegy.invasioncodered.entity.cuccita.CuccitaEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CuccitaRenderer extends MobRenderer<CuccitaEntity, CuccitaAnimatedModel> {
    private static final ResourceLocation entityTexture = ResourceLocation.tryParse("invasioncodered:textures/entities/cuccita.png");

    public CuccitaRenderer(EntityRendererProvider.Context context) {
        super(context, new CuccitaAnimatedModel(context.bakeLayer(ModelCuccita.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(CuccitaEntity entity) {
        return entityTexture;
    }
}
