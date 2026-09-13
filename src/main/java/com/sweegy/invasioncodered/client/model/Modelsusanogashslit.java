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
public class Modelsusanogashslit<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.tryParse("invasioncodered:modelsusanogashslit"), "main");
	public final ModelPart whole;
	public final ModelPart torso;
	public final ModelPart robes;
	public final ModelPart robe;
	public final ModelPart robe2;
	public final ModelPart robe3;
	public final ModelPart w2;
	public final ModelPart trueHead;
	public final ModelPart head;
	public final ModelPart collar2;
	public final ModelPart collar;
	public final ModelPart collar3;
	public final ModelPart rightArm;
	public final ModelPart rightItemLayer;
	public final ModelPart leftArm;
	public final ModelPart leftItemLayer;

	public Modelsusanogashslit(ModelPart root) {
		this.whole = root.getChild("whole");
		this.torso = this.whole.getChild("torso");
		this.robes = this.torso.getChild("robes");
		this.robe = this.robes.getChild("robe");
		this.robe2 = this.robes.getChild("robe2");
		this.robe3 = this.robes.getChild("robe3");
		this.w2 = this.robe3.getChild("w2");
		this.trueHead = this.torso.getChild("trueHead");
		this.head = this.trueHead.getChild("head");
		this.collar2 = this.head.getChild("collar2");
		this.collar = this.collar2.getChild("collar");
		this.collar3 = this.collar2.getChild("collar3");
		this.rightArm = this.torso.getChild("rightArm");
		this.rightItemLayer = this.rightArm.getChild("rightItemLayer");
		this.leftArm = this.torso.getChild("leftArm");
		this.leftItemLayer = this.leftArm.getChild("leftItemLayer");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition whole = partdefinition.addOrReplaceChild("whole", CubeListBuilder.create(), PartPose.offset(0.0F, -3.0F, 16.0F));
		PartDefinition torso = whole.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(44, 96).addBox(-8.0F, -15.0F, -6.0F, 16.0F, 24.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 2.0F));
		PartDefinition w_r1 = torso.addOrReplaceChild("w_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-10.25F, -8.25F, -10.25F, 20.5F, 18.5F, 18.5F, new CubeDeformation(0.25F)),
				PartPose.offsetAndRotation(0.0F, -7.0F, -2.0F, 0.2618F, 0.0F, 0.0F));
		PartDefinition robes = torso.addOrReplaceChild("robes", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -15.0F, 10.0F, 0.0436F, 0.0F, 0.0F));
		PartDefinition robe = robes.addOrReplaceChild("robe", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 4.0F, 0.0F, 0.0F, 0.1309F));
		PartDefinition robe2 = robes.addOrReplaceChild("robe2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 4.0F, 0.0F, 0.0F, -0.1309F));
		PartDefinition robe3 = robes.addOrReplaceChild("robe3", CubeListBuilder.create().texOffs(64, 64).addBox(-10.551F, 0.249F, -5.251F, 21.002F, 22.502F, 10.502F, new CubeDeformation(0.251F)), PartPose.offset(0.0F, 23.0F, -11.0F));
		PartDefinition w2 = robe3.addOrReplaceChild("w2", CubeListBuilder.create(), PartPose.offset(0.0F, -23.0F, 7.0F));
		PartDefinition w2_r1 = w2.addOrReplaceChild("w2_r1", CubeListBuilder.create().texOffs(100, 140).addBox(-6.3F, -2.5F, 0.0F, 12.5F, 18.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 24.0F, -16.0F, -0.2618F, 0.0F, 0.0F));
		PartDefinition trueHead = torso.addOrReplaceChild("trueHead", CubeListBuilder.create(), PartPose.offset(0.0F, -17.0F, 2.0F));
		PartDefinition head = trueHead.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(128, 144).addBox(-2.0F, -6.0F, -16.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(0, 72).addBox(-2.0F, -28.0F, -14.0F, 4.0F, 20.0F, 18.0F, new CubeDeformation(0.0F)).texOffs(0, 36)
						.addBox(-8.0F, -18.0F, -12.0F, 16.0F, 20.0F, 16.0F, new CubeDeformation(0.0F)).texOffs(126, 26).addBox(-10.0F, -10.0F, -18.0F, 20.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).texOffs(64, 36)
						.addBox(-8.5F, -18.5F, -12.5F, 17.0F, 11.0F, 17.0F, new CubeDeformation(0.5F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition Head_r1 = head.addOrReplaceChild("Head_r1", CubeListBuilder.create().texOffs(54, 72).addBox(1.7F, -17.9F, -6.3F, 3.6F, 21.6F, 2.6F, new CubeDeformation(0.3F)).texOffs(108, 31)
				.addBox(5.7F, 1.1F, -6.3F, 7.6F, 2.6F, 2.6F, new CubeDeformation(0.3F)).texOffs(44, 72).addBox(13.7F, -17.9F, -6.3F, 3.6F, 21.6F, 2.6F, new CubeDeformation(0.3F)),
				PartPose.offsetAndRotation(-9.0F, -14.0F, -10.0F, -0.2182F, 0.0F, 0.0F));
		PartDefinition Head_r2 = head.addOrReplaceChild("Head_r2", CubeListBuilder.create().texOffs(126, 0).addBox(1.0F, -3.0F, -8.0F, 2.0F, 10.0F, 16.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(8.0F, -6.0F, -4.0F, 0.0F, 0.0F, -0.5672F));
		PartDefinition Head_r3 = head.addOrReplaceChild("Head_r3", CubeListBuilder.create().texOffs(0, 110).addBox(2.0F, -6.0F, -8.0F, 2.0F, 10.0F, 16.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-14.0F, -6.0F, -4.0F, 0.0F, 0.0F, 0.5672F));
		PartDefinition Head_r4 = head.addOrReplaceChild("Head_r4", CubeListBuilder.create().texOffs(140, 96).addBox(-9.2F, 0.0F, -2.0F, 18.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -10.0F, 6.0F, 0.2967F, 0.0F, 0.0F));
		PartDefinition collar2 = head.addOrReplaceChild("collar2", CubeListBuilder.create(), PartPose.offset(0.0F, -21.5F, 14.5F));
		PartDefinition collar = collar2.addOrReplaceChild("collar", CubeListBuilder.create(), PartPose.offsetAndRotation(8.0F, -0.5F, 0.0F, 0.0F, 0.0F, 0.2182F));
		PartDefinition collar3 = collar2.addOrReplaceChild("collar3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition collar3_r1 = collar3.addOrReplaceChild("collar3_r1", CubeListBuilder.create().texOffs(132, 34).addBox(-0.001F, -0.001F, -8.001F, 0.002F, 4.002F, 17.002F, new CubeDeformation(0.001F)),
				PartPose.offsetAndRotation(-0.5F, 23.5F, -10.0F, -1.5708F, 0.0F, -1.5708F));
		PartDefinition collar3_r2 = collar3.addOrReplaceChild("collar3_r2", CubeListBuilder.create().texOffs(76, 0).addBox(0.0F, -4.0F, -8.9F, 6.0F, 12.0F, 19.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.5F, 23.5F, -10.0F, -1.5708F, 1.309F, -1.5708F));
		PartDefinition rightArm = torso.addOrReplaceChild("rightArm",
				CubeListBuilder.create().texOffs(126, 64).addBox(-10.949F, -2.3217F, -1.7615F, 6.0F, 24.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(0, 136).addBox(-13.45F, 10.1773F, -2.2625F, 9.002F, 8.002F, 9.002F, new CubeDeformation(0.001F)),
				PartPose.offset(-10.5F, -12.0F, -7.0F));
		PartDefinition RightArm_r1 = rightArm.addOrReplaceChild("RightArm_r1",
				CubeListBuilder.create().texOffs(140, 108).addBox(-16.1433F, -12.8961F, 8.9F, 11.2F, 16.2F, 2.2F, new CubeDeformation(0.5F)).texOffs(92, 96).addBox(-14.1433F, -8.8961F, 4.9F, 14.2F, 12.2F, 10.2F, new CubeDeformation(0.2F)),
				PartPose.offsetAndRotation(-2.449F, 1.6783F, -7.7615F, 0.0F, 0.0F, -0.3927F));
		PartDefinition rightItemLayer = rightArm.addOrReplaceChild("rightItemLayer",
				CubeListBuilder.create().texOffs(64, 132).addBox(-40.6113F, -11.9053F, -18.22F, 11.2652F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)).texOffs(64, 136)
						.addBox(-38.6113F, -9.8389F, -18.22F, 11.3315F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)).texOffs(154, 63).addBox(-33.547F, -5.7747F, -18.22F, 10.3315F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)).texOffs(154, 67)
						.addBox(-31.4807F, -3.7084F, -18.22F, 10.3315F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)).texOffs(144, 144).addBox(-36.5655F, -7.7932F, -18.22F, 11.3315F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)).texOffs(154, 71)
						.addBox(-29.4144F, -1.6421F, -18.22F, 10.3315F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)).texOffs(154, 75).addBox(-27.3481F, 0.4242F, -18.22F, 10.3315F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)).texOffs(154, 79)
						.addBox(-25.2818F, 2.4905F, -18.22F, 10.3315F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)).texOffs(154, 83).addBox(-23.2156F, 4.5568F, -18.22F, 10.3315F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)).texOffs(154, 87)
						.addBox(-21.1493F, 6.6231F, -18.22F, 10.3315F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)).texOffs(36, 110).addBox(-6.6852F, 8.6894F, -18.22F, 2.0663F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)).texOffs(154, 91)
						.addBox(-19.083F, 8.6894F, -18.22F, 10.3315F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)).texOffs(76, 31).addBox(-17.0167F, 10.7557F, -18.22F, 14.464F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)).texOffs(144, 156)
						.addBox(-14.9504F, 12.8219F, -18.22F, 10.3315F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)).texOffs(48, 161).addBox(-12.8841F, 14.8882F, -18.22F, 8.2652F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)).texOffs(64, 128)
						.addBox(-14.9504F, 16.9545F, -18.22F, 12.3977F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)).texOffs(162, 8).addBox(-6.6852F, 19.0208F, -18.22F, 6.1989F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)).texOffs(36, 114)
						.addBox(-12.8841F, 19.0208F, -18.22F, 2.0663F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)).texOffs(68, 161).addBox(-4.6189F, 21.0871F, -18.22F, 8.2652F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)).texOffs(162, 12)
						.addBox(-2.5526F, 23.1534F, -18.22F, 6.1989F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)).texOffs(162, 16).addBox(-2.5526F, 25.2197F, -18.22F, 6.1989F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(9.551F, 34.6783F, -8.7615F, 1.5708F, -0.7854F, 1.5708F));
		PartDefinition leftArm = torso.addOrReplaceChild("leftArm", CubeListBuilder.create(), PartPose.offset(11.5F, -15.0F, -4.0F));
		PartDefinition LeftArm_r1 = leftArm.addOrReplaceChild("LeftArm_r1",
				CubeListBuilder.create().texOffs(140, 126).addBox(-0.9F, -11.66F, -1.1F, 11.2F, 16.2F, 2.2F, new CubeDeformation(0.2F)).texOffs(92, 118).addBox(-5.9F, -6.66F, -5.1F, 14.2F, 12.2F, 10.2F, new CubeDeformation(0.5F)),
				PartPose.offsetAndRotation(7.7277F, 4.8571F, 1.88F, 0.0F, 0.0F, 0.3927F));
		PartDefinition LeftArm_r2 = leftArm.addOrReplaceChild("LeftArm_r2",
				CubeListBuilder.create().texOffs(64, 140).addBox(-16.501F, -35.501F, -4.501F, 9.002F, 8.002F, 9.002F, new CubeDeformation(0.001F)).texOffs(36, 128).addBox(-14.0F, -48.0F, -4.0F, 6.0F, 24.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-3.7724F, 48.8571F, 1.88F, 0.0F, 3.1416F, 0.0F));
		PartDefinition leftItemLayer = leftArm.addOrReplaceChild("leftItemLayer",
				CubeListBuilder.create().texOffs(144, 148).addBox(-40.6113F, -11.9053F, -18.22F, 11.2652F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)).texOffs(144, 152)
						.addBox(-38.6113F, -9.8389F, -18.22F, 11.3315F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)).texOffs(0, 157).addBox(-33.547F, -5.7747F, -18.22F, 10.3315F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)).texOffs(64, 157)
						.addBox(-31.4807F, -3.7084F, -18.22F, 10.3315F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)).texOffs(0, 153).addBox(-36.5655F, -7.7932F, -18.22F, 11.3315F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)).texOffs(24, 160)
						.addBox(-29.4144F, -1.6421F, -18.22F, 10.3315F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)).texOffs(160, 59).addBox(-27.3481F, 0.4242F, -18.22F, 10.3315F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)).texOffs(88, 160)
						.addBox(-25.2818F, 2.4905F, -18.22F, 10.3315F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)).texOffs(112, 160).addBox(-23.2156F, 4.5568F, -18.22F, 10.3315F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)).texOffs(136, 160)
						.addBox(-21.1493F, 6.6231F, -18.22F, 10.3315F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)).texOffs(36, 118).addBox(-6.6852F, 8.6894F, -18.22F, 2.0663F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)).texOffs(160, 160)
						.addBox(-19.083F, 8.6894F, -18.22F, 10.3315F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)).texOffs(132, 55).addBox(-17.0167F, 10.7557F, -18.22F, 14.464F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)).texOffs(0, 161)
						.addBox(-14.9504F, 12.8219F, -18.22F, 10.3315F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)).texOffs(162, 0).addBox(-12.8841F, 14.8882F, -18.22F, 8.2652F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)).texOffs(132, 59)
						.addBox(-14.9504F, 16.9545F, -18.22F, 12.3977F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)).texOffs(162, 20).addBox(-6.6852F, 19.0208F, -18.22F, 6.1989F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)).texOffs(36, 122)
						.addBox(-12.8841F, 19.0208F, -18.22F, 2.0663F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)).texOffs(162, 4).addBox(-4.6189F, 21.0871F, -18.22F, 8.2652F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)).texOffs(24, 164)
						.addBox(-2.5526F, 23.1534F, -18.22F, 6.1989F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)).texOffs(164, 55).addBox(-2.5526F, 25.2197F, -18.22F, 6.1989F, 2.0663F, 2.0663F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(23.7276F, 37.8571F, -10.12F, 1.5708F, -0.7854F, 1.5708F));
		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		whole.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
}