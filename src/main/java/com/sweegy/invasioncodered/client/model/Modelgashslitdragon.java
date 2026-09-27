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
public class Modelgashslitdragon<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.tryParse("invasioncodered:modelgashslitdragon"), "main");
	public final ModelPart whole;
	public final ModelPart trueHead;
	public final ModelPart head;
	public final ModelPart bone2;
	public final ModelPart bone4;
	public final ModelPart bone3;
	public final ModelPart bone5;
	public final ModelPart bone6;

	public Modelgashslitdragon(ModelPart root) {
		this.whole = root.getChild("whole");
		this.trueHead = this.whole.getChild("trueHead");
		this.head = this.trueHead.getChild("head");
		this.bone2 = this.whole.getChild("bone2");
		this.bone4 = this.whole.getChild("bone4");
		this.bone3 = this.whole.getChild("bone3");
		this.bone5 = this.whole.getChild("bone5");
		this.bone6 = this.whole.getChild("bone6");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition whole = partdefinition.addOrReplaceChild("whole", CubeListBuilder.create().texOffs(0, 14).addBox(-3.0F, -1.0F, -4.0F, 6.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 19.0F, 0.0F));
		PartDefinition trueHead = whole.addOrReplaceChild("trueHead", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -4.0F));
		PartDefinition head = trueHead.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(25, 0).addBox(-3.0F, -3.5211F, -2.3552F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(9, 24).addBox(-2.0F, -1.0211F, -8.3552F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(19, 8)
						.addBox(-2.0F, -3.0211F, -5.3552F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(25, 0).mirror().addBox(1.0F, -3.5211F, -2.3552F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition bone2 = whole.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(20, 18).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 3.0F));
		PartDefinition bone4 = whole.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(0, 7).mirror().addBox(-9.0F, -0.1F, -4.0F, 9.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, -1.0F, 0.0F));
		PartDefinition bone3 = whole.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(0, 7).addBox(0.0F, -0.1F, -4.0F, 9.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -1.0F, 0.0F));
		PartDefinition bone5 = whole.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(14, 30).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, -3.0F));
		PartDefinition bone6 = whole.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(14, 30).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 2.0F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		whole.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.trueHead.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.trueHead.xRot = headPitch / (180F / (float) Math.PI);
	}
}