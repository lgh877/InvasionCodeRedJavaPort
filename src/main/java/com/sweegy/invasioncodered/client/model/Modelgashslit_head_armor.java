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
public class Modelgashslit_head_armor<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.tryParse("invasioncodered:modelgashslit_head_armor"), "main");
	public final ModelPart waist;
	public final ModelPart body;
	public final ModelPart head;
	public final ModelPart hat;

	public Modelgashslit_head_armor(ModelPart root) {
		this.waist = root.getChild("waist");
		this.body = this.waist.getChild("body");
		this.head = this.body.getChild("head");
		this.hat = this.head.getChild("hat");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition waist = partdefinition.addOrReplaceChild("waist", CubeListBuilder.create(), PartPose.offset(0.0F, 12.0F, 0.0F));
		PartDefinition body = waist.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, -12.0F, 0.0F));
		PartDefinition head = body.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(20, 40).addBox(-0.5F, -3.0F, -5.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.5F)).texOffs(0, 0).addBox(-4.0F, -11.0F, -4.0F, 8.0F, 11.0F, 8.0F, new CubeDeformation(0.3F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition hat = head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(0, 19).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 4.0F, 8.0F, new CubeDeformation(0.6F)).texOffs(32, 28)
				.addBox(-4.0F, -5.0F, -6.0F, 8.0F, 0.0F, 1.0F, new CubeDeformation(0.6F)).texOffs(0, 31).addBox(-0.5F, -15.0F, -5.5F, 1.0F, 10.0F, 9.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition hat_r1 = hat.addOrReplaceChild("hat_r1", CubeListBuilder.create().texOffs(32, 12).addBox(0.5F, 0.3F, -4.0F, 0.0F, 4.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(4.0F, -5.0F, 0.0F, 0.0F, 0.0F, -0.48F));
		PartDefinition hat_r2 = hat.addOrReplaceChild("hat_r2", CubeListBuilder.create().texOffs(32, 0).addBox(-0.5F, 0.3F, -4.0F, 0.0F, 4.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(-4.0F, -5.0F, 0.0F, 0.0F, 0.0F, 0.48F));
		PartDefinition hat_r3 = hat.addOrReplaceChild("hat_r3", CubeListBuilder.create().texOffs(32, 24).addBox(-4.0F, 0.8F, -1.0F, 8.0F, 4.0F, 0.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, -6.0F, 5.0F, 0.4363F, 0.0F, 0.0F));
		PartDefinition hat_r4 = hat.addOrReplaceChild("hat_r4", CubeListBuilder.create().texOffs(20, 31).addBox(-5.0F, -8.5F, 0.1F, 10.0F, 9.0F, 0.0F, new CubeDeformation(0.6F)), PartPose.offsetAndRotation(0.0F, -6.0F, -5.0F, -0.0873F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		waist.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
}