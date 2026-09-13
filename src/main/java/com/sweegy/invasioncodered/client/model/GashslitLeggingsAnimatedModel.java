/**
 * The code of this mod element is always locked.
 *
 * You can register new events in this class too.
 *
 * If you want to make a plain independent class, create it using
 * Project Browser -> New... and make sure to make the class
 * outside com.sweegy.invasioncoderedjavaport as this package is managed by MCreator.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
 *
 * This class will be added in the mod root package.
*/
package com.sweegy.invasioncodered.client.model;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.HumanoidModel;

public class GashslitLeggingsAnimatedModel<T extends LivingEntity> extends HumanoidModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.tryParse("invasioncodered:modelgashslit_leggings_animated_model"), "main");
	public final ModelPart robecollection;
	public final ModelPart robe3;
	public final ModelPart bone;

	public GashslitLeggingsAnimatedModel(ModelPart root) {
		super(root);
		this.robecollection = this.body.getChild("robecollection");
		this.robe3 = this.robecollection.getChild("robe3");
		this.bone = this.robe3.getChild("bone");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
		partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
		//partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.ZERO);
		partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);
		partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);
		partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
		partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 40).addBox(-4.0F, 4.0F, -2.0F, 8.0F, 8.0F, 4.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition robecollection = body.addOrReplaceChild("robecollection", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 12.0F, -2.0F, 0.0436F, 0.0F, 0.0F));
		PartDefinition robe3 = robecollection.addOrReplaceChild("robe3", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 2.0F));
		PartDefinition robe3_r1 = robe3.addOrReplaceChild("robe3_r1", CubeListBuilder.create().texOffs(27, 31).addBox(-5.0F, -1.25F, -2.0F, 10.0F, 11.0F, 5.0F, new CubeDeformation(0.251F)),
				PartPose.offsetAndRotation(0.0F, 2.0F, 0.0F, 0.2182F, 0.0F, 0.0F));
		PartDefinition bone = robe3.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, -2.0F));
		PartDefinition bone_r1 = bone.addOrReplaceChild("bone_r1", CubeListBuilder.create().texOffs(74, 40).addBox(-3.15F, -1.25F, 1.0F, 6.25F, 9.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, -2.0F, -0.2618F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	public void setupCustomAnim(T entity, float partialTicks) {
		float limbSwing = entity.walkAnimation.position(partialTicks);
		float f = 1.0F;
		if (entity.getFallFlyingTicks() > 4) {
			f = (float) entity.getDeltaMovement().lengthSqr();
			f /= 0.2F;
			f *= f * f;
		}
		if (f < 1.0F) {
			f = 1.0F;
		}
		float limbSwingAmount = entity.walkAnimation.speed(partialTicks) / f;
		if (!this.riding) {
			this.robecollection.xRot = (Mth.cos(limbSwing * 0.6662F * 2) * 0.2F + 1.1f) * limbSwingAmount;
			this.bone.xRot = -this.robecollection.xRot * 1.85f;
			this.bone.yRot = -Mth.cos(limbSwing * 0.6662F) * 0.4F * limbSwingAmount;
			this.bone.zRot = this.bone.yRot;
			if (this.crouching) {
				robecollection.xRot -= 0.55f;
				robecollection.z = -4;
				robecollection.y = 11;
			} else {
				robecollection.z = -2;
				robecollection.y = 12;
			}
		} else {
			this.robecollection.xRot = 0.0F;
			this.bone.xRot = 0.0F;
			this.bone.yRot = 0;
			this.bone.zRot = 0;
		}
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.robecollection.xRot = 0.0F;
		this.bone.xRot = 0.0F;
		this.bone.yRot = 0;
		this.bone.zRot = 0;
	}
}