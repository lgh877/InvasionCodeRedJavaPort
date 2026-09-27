package com.sweegy.invasioncodered.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import com.sweegy.invasioncodered.entity.gashslit.GashslitDragonEntity;
import com.sweegy.invasioncodered.client.model.animations.gashslitdragonAnimation;
import com.sweegy.invasioncodered.client.model.Modelgashslitdragon;

public class GashslitDragonRenderer extends MobRenderer<GashslitDragonEntity, Modelgashslitdragon<GashslitDragonEntity>> {
    private final ResourceLocation entityTexture = ResourceLocation.tryParse("invasioncodered:textures/entities/gashslitdragon.png");

	public GashslitDragonRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelgashslitdragon.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(GashslitDragonEntity entity) {
		return entityTexture;
	}

	private static final class AnimatedModel extends Modelgashslitdragon<GashslitDragonEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<GashslitDragonEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(GashslitDragonEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				float partialTicks = ageInTicks - entity.tickCount;
				this.animateWalk(gashslitdragonAnimation.fly, entity.getAnimationTicks(partialTicks), 0.5f, 1f, 1f);
				this.animateWalk(gashslitdragonAnimation.shoot, 1f, entity.getShootAnimation(partialTicks), 1f, 1f);
			}
		};

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(GashslitDragonEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}