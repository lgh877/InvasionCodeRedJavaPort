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
public class Modelgashslit_slash<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.tryParse("invasioncodered:modelgashslit_slash"), "main");
	public final ModelPart whole;
	public final ModelPart Slash;

	public Modelgashslit_slash(ModelPart root) {
		this.whole = root.getChild("whole");
		this.Slash = this.whole.getChild("Slash");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition whole = partdefinition.addOrReplaceChild("whole", CubeListBuilder.create(), PartPose.offset(0.0F, 20.0F, 0.0F));
		PartDefinition Slash = whole.addOrReplaceChild("Slash",
				CubeListBuilder.create().texOffs(21, 6).addBox(-0.5F, -6.2088F, -6.5637F, 1.0F, 14.0F, 15.0F, new CubeDeformation(0.0F)).texOffs(34, 36).addBox(-0.5F, 6.7912F, -6.5637F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 36)
						.addBox(-0.5F, 0.7912F, -3.5637F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 36).addBox(-0.5F, -0.2088F, -5.5637F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 36)
						.addBox(-0.5F, 4.7912F, -5.5637F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 36).addBox(-0.5F, 3.7912F, -5.5637F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 36)
						.addBox(-0.5F, 2.7912F, -4.5637F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 36).addBox(-0.5F, 2.7912F, -5.5637F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 36)
						.addBox(-0.5F, 1.7912F, -5.5637F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 36).addBox(-0.5F, 5.7912F, -6.5637F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 36)
						.addBox(-0.5F, 4.7912F, -6.5637F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 36).addBox(-0.5F, 3.7912F, -6.5637F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 36)
						.addBox(-0.5F, 2.7912F, -6.5637F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 36).addBox(-0.5F, 1.7912F, -6.5637F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 38)
						.addBox(-0.5F, 0.7912F, -5.5637F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 38).addBox(-0.5F, 0.7912F, -4.5637F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 38)
						.addBox(-0.5F, 1.7912F, -4.5637F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 38).addBox(-0.5F, -0.2088F, -4.5637F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 38)
						.addBox(-0.5F, -1.2088F, -4.5637F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 38).addBox(-0.5F, -2.2088F, -4.5637F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 38)
						.addBox(-0.5F, -0.2088F, -3.5637F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 38).addBox(-0.5F, -0.2088F, -2.5637F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 38)
						.addBox(-0.5F, -1.2088F, -2.5637F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 38).addBox(-0.5F, -2.2088F, -1.5637F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 38)
						.addBox(-0.5F, -3.2088F, -3.5637F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 40).addBox(-0.5F, -1.2088F, -3.5637F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 40)
						.addBox(-0.5F, -1.2088F, -1.5637F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 40).addBox(-0.5F, -2.2088F, -2.5637F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 40)
						.addBox(-0.5F, -3.2088F, -2.5637F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 40).addBox(-0.5F, -3.2088F, -1.5637F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 40)
						.addBox(-0.5F, -3.2088F, -0.5637F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 40).addBox(-0.5F, -3.2088F, 0.4363F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 40)
						.addBox(-0.5F, -4.2088F, -0.5637F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 40).addBox(-0.5F, -4.2088F, 0.4363F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 40)
						.addBox(-0.5F, -4.2088F, 1.4363F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 40).addBox(-0.5F, -2.2088F, -0.5637F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 42)
						.addBox(-0.5F, -4.2088F, -2.5637F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 42).addBox(-0.5F, -4.2088F, -1.5637F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 42)
						.addBox(-0.5F, -5.2088F, -0.5637F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 42).addBox(-0.5F, -3.2088F, 1.4363F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 42)
						.addBox(-0.5F, -5.2088F, 0.4363F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 42).addBox(-0.5F, -5.2088F, 1.4363F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 42)
						.addBox(-0.5F, -4.2088F, 2.4363F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 42).addBox(-0.5F, -4.2088F, 3.4363F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 42)
						.addBox(-0.5F, -5.2088F, 2.4363F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 42).addBox(-0.5F, -5.2088F, 5.4363F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 42)
						.addBox(-0.5F, -5.2088F, 3.4363F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 42).addBox(-0.5F, -5.2088F, 4.4363F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 44)
						.addBox(-0.5F, -6.2088F, 7.4363F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 44).addBox(-0.5F, -6.2088F, 2.4363F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 44)
						.addBox(-0.5F, -6.2088F, 3.4363F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 44).addBox(-0.5F, -6.2088F, 4.4363F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 44)
						.addBox(-0.5F, -6.2088F, 5.4363F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 44).addBox(-0.5F, -6.2088F, 6.4363F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 44)
						.addBox(-0.5F, -5.2088F, 6.4363F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(34, 46).addBox(-0.5F, -1.2088F, -5.5637F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.6981F, 0.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		whole.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}