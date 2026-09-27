package com.sweegy.invasioncodered.client.model;

import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.EntityModel;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

// Made with Blockbench 5.1.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class Modelgashslit_chestplate<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.tryParse("invasioncodered:modelgashslit_chestplate"), "main");
	public final ModelPart LeftArm;
	public final ModelPart RightArm;
	public final ModelPart Body;

	public Modelgashslit_chestplate(ModelPart root) {
		this.LeftArm = root.getChild("LeftArm");
		this.RightArm = root.getChild("RightArm");
		this.Body = root.getChild("Body");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create(), PartPose.offset(6.25F, 2.0F, 0.0F));
		PartDefinition LeftArm_r1 = LeftArm.addOrReplaceChild("LeftArm_r1", CubeListBuilder.create().texOffs(19, 47).mirror().addBox(-2.9F, -3.28F, -2.5F, 7.0F, 6.0F, 5.0F, new CubeDeformation(0.1F)).mirror(false),
				PartPose.offsetAndRotation(0.75F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3927F));
		PartDefinition LeftArm_r2 = LeftArm.addOrReplaceChild("LeftArm_r2",
				CubeListBuilder.create().texOffs(60, 58).addBox(-7.75F, -17.75F, -2.25F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.5F)).texOffs(57, 23).addBox(-7.0F, -24.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.4F)),
				PartPose.offsetAndRotation(-5.0F, 22.0F, 0.0F, 0.0F, 3.1416F, 0.0F));
		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm",
				CubeListBuilder.create().texOffs(57, 23).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.4F)).texOffs(60, 58).addBox(-2.75F, 4.25F, -2.25F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.5F)),
				PartPose.offset(-6.25F, 2.0F, 0.0F));
		PartDefinition RightArm_r1 = RightArm.addOrReplaceChild("RightArm_r1", CubeListBuilder.create().texOffs(19, 47).addBox(-4.25F, -3.25F, -2.5F, 7.0F, 6.0F, 5.0F, new CubeDeformation(0.3F)),
				PartPose.offsetAndRotation(-0.75F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3927F));
		PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition Body_r1 = Body.addOrReplaceChild("Body_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -4.0F, -5.0F, 10.0F, 9.0F, 9.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, 0.2618F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		LeftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		RightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
}