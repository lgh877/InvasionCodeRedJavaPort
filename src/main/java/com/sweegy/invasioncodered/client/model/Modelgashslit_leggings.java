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
public class Modelgashslit_leggings<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.tryParse("invasioncodered:modelgashslit_leggings"), "main");
	public final ModelPart body;
	public final ModelPart robecollection;
	public final ModelPart robe3;
	public final ModelPart bone;

	public Modelgashslit_leggings(ModelPart root) {
		this.body = root.getChild("body");
		this.robecollection = this.body.getChild("robecollection");
		this.robe3 = this.robecollection.getChild("robe3");
		this.bone = this.robe3.getChild("bone");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
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

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
}