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
public class Modelgashslit<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.tryParse("invasioncodered:modelgashslit"), "main");
	public final ModelPart root2;
	public final ModelPart whole;
	public final ModelPart torso;
	public final ModelPart robes;
	public final ModelPart robe;
	public final ModelPart robe2;
	public final ModelPart robe3;
	public final ModelPart bone;
	public final ModelPart trueHead;
	public final ModelPart head;
	public final ModelPart helmetDeco;
	public final ModelPart collar2;
	public final ModelPart collar;
	public final ModelPart collar3;
	public final ModelPart rightArm;
	public final ModelPart rightItemLayer;
	public final ModelPart rightItemParticlePos;
	public final ModelPart leftArm;
	public final ModelPart leftItemLayer;
	public final ModelPart leftItemParticlePos;
	public final ModelPart rightLeg;
	public final ModelPart leftLeg;

	public Modelgashslit(ModelPart root) {
		this.root2 = root.getChild("root2");
		this.whole = this.root2.getChild("whole");
		this.torso = this.whole.getChild("torso");
		this.robes = this.torso.getChild("robes");
		this.robe = this.robes.getChild("robe");
		this.robe2 = this.robes.getChild("robe2");
		this.robe3 = this.robes.getChild("robe3");
		this.bone = this.robe3.getChild("bone");
		this.trueHead = this.torso.getChild("trueHead");
		this.head = this.trueHead.getChild("head");
		this.helmetDeco = this.head.getChild("helmetDeco");
		this.collar2 = this.head.getChild("collar2");
		this.collar = this.collar2.getChild("collar");
		this.collar3 = this.collar2.getChild("collar3");
		this.rightArm = this.torso.getChild("rightArm");
		this.rightItemLayer = this.rightArm.getChild("rightItemLayer");
		this.rightItemParticlePos = this.rightItemLayer.getChild("rightItemParticlePos");
		this.leftArm = this.torso.getChild("leftArm");
		this.leftItemLayer = this.leftArm.getChild("leftItemLayer");
		this.leftItemParticlePos = this.leftItemLayer.getChild("leftItemParticlePos");
		this.rightLeg = this.whole.getChild("rightLeg");
		this.leftLeg = this.whole.getChild("leftLeg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition root2 = partdefinition.addOrReplaceChild("root2", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
		PartDefinition whole = root2.addOrReplaceChild("whole", CubeListBuilder.create(), PartPose.offset(0.0F, -24.0F, 0.0F));
		PartDefinition torso = whole.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 36).addBox(-4.0F, -11.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 11.0F, 0.0F));
		PartDefinition w_r1 = torso.addOrReplaceChild("w_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -4.0F, -5.0F, 10.0F, 9.0F, 9.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(0.0F, -7.0F, 0.0F, 0.2618F, 0.0F, 0.0F));
		PartDefinition robes = torso.addOrReplaceChild("robes", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 1.0F, -2.0F, 0.0436F, 0.0F, 0.0F));
		PartDefinition robe = robes.addOrReplaceChild("robe", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 2.0F, 0.0F, 0.0F, 0.1309F));
		PartDefinition robe2 = robes.addOrReplaceChild("robe2", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 2.0F, 0.0F, 0.0F, -0.1309F));
		PartDefinition robe3 = robes.addOrReplaceChild("robe3", CubeListBuilder.create().texOffs(27, 31).addBox(-5.15F, 0.75F, -2.0F, 10.25F, 11.0F, 5.0F, new CubeDeformation(0.251F)), PartPose.offset(0.0F, -1.0F, 2.0F));
		PartDefinition bone = robe3.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, -4.0F));
		PartDefinition bone_r1 = bone.addOrReplaceChild("bone_r1", CubeListBuilder.create().texOffs(74, 40).addBox(-3.15F, -1.25F, 0.0F, 6.25F, 9.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2618F, 0.0F, 0.0F));
		PartDefinition trueHead = torso.addOrReplaceChild("trueHead", CubeListBuilder.create(), PartPose.offset(0.0F, -11.0F, 0.0F));
		PartDefinition head = trueHead.addOrReplaceChild("head",
				CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -4.0F, -6.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(51, 66).addBox(-1.0F, -15.0F, -5.0F, 2.0F, 10.0F, 9.0F, new CubeDeformation(0.0F)).texOffs(0, 18)
						.addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(77, 21).addBox(-5.0F, -6.0F, -7.0F, 10.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).texOffs(30, 10)
						.addBox(-4.0F, -10.0F, -4.0F, 8.0F, 5.0F, 8.0F, new CubeDeformation(0.5F)).texOffs(40, 23).addBox(-4.0F, -11.3F, -4.0F, 8.0F, 1.0F, 2.0F, new CubeDeformation(0.3F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition Head_r1 = head.addOrReplaceChild("Head_r1", CubeListBuilder.create().texOffs(62, 10).mirror().addBox(0.5F, -1.5F, -4.0F, 1.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(4.0F, -4.0F, 0.0F, 0.0F, 0.0F, -0.5672F));
		PartDefinition Head_r2 = head.addOrReplaceChild("Head_r2", CubeListBuilder.create().texOffs(62, 10).addBox(1.0F, -3.0F, -4.0F, 1.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -4.0F, 0.0F, 0.0F, 0.0F, 0.5672F));
		PartDefinition Head_r3 = head.addOrReplaceChild("Head_r3", CubeListBuilder.create().texOffs(67, 50).addBox(-4.6F, 0.0F, -1.0F, 9.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, 5.0F, 0.2967F, 0.0F, 0.0F));
		PartDefinition helmetDeco = head.addOrReplaceChild("helmetDeco", CubeListBuilder.create(), PartPose.offset(0.0F, -9.0F, -5.0F));
		PartDefinition Head_r4 = helmetDeco.addOrReplaceChild("Head_r4", CubeListBuilder.create().texOffs(106, 0).addBox(-6.0F, -7.0F, 0.9F, 12.0F, 10.0F, -1.0F, new CubeDeformation(0.5F)),
				PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.0F));
		PartDefinition collar2 = head.addOrReplaceChild("collar2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.25F, 1.25F));
		PartDefinition collar = collar2.addOrReplaceChild("collar", CubeListBuilder.create(), PartPose.offsetAndRotation(4.0F, -0.25F, 0.0F, 0.0F, 0.0F, 0.2182F));
		PartDefinition collar3 = collar2.addOrReplaceChild("collar3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition collar3_r1 = collar3.addOrReplaceChild("collar3_r1", CubeListBuilder.create().texOffs(32, 21).addBox(0.0F, 0.0F, -4.0F, 0.0F, 2.0F, 8.5F, new CubeDeformation(0.001F)),
				PartPose.offsetAndRotation(-0.25F, -0.25F, 3.0F, -1.5708F, 0.0F, -1.5708F));
		PartDefinition collar3_r2 = collar3.addOrReplaceChild("collar3_r2", CubeListBuilder.create().texOffs(0, 68).mirror().addBox(0.0F, -2.0F, -4.45F, 3.0F, 6.0F, 9.5F, new CubeDeformation(0.0F)).mirror(false),
				PartPose.offsetAndRotation(-0.25F, -0.25F, 3.0F, -1.5708F, 1.309F, -1.5708F));
		PartDefinition rightArm = torso.addOrReplaceChild("rightArm",
				CubeListBuilder.create().texOffs(57, 23).addBox(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(60, 58).addBox(-2.75F, 4.25F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.001F)),
				PartPose.offset(-6.25F, -9.0F, 0.0F));
		PartDefinition RightArm_r1 = rightArm.addOrReplaceChild("RightArm_r1", CubeListBuilder.create().texOffs(19, 47).addBox(-4.25F, -3.25F, -2.5F, 7.0F, 6.0F, 5.0F, new CubeDeformation(0.1F)),
				PartPose.offsetAndRotation(-0.75F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3927F));
		PartDefinition rightItemLayer = rightArm.addOrReplaceChild("rightItemLayer", CubeListBuilder.create(), PartPose.offset(0.25F, 8.0F, 0.0F));
		PartDefinition s_r1 = rightItemLayer.addOrReplaceChild("s_r1",
				CubeListBuilder.create().texOffs(89, 0).addBox(13.6731F, -17.9527F, 0.89F, 4.1326F, 1.0332F, 1.0332F, new CubeDeformation(0.0F)).texOffs(88, 1).addBox(12.6399F, -16.9195F, 0.89F, 5.1657F, 1.0331F, 1.0332F, new CubeDeformation(0.0F))
						.texOffs(87, 2).addBox(10.6078F, -14.8873F, 0.89F, 5.1657F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).texOffs(86, 3).addBox(9.5746F, -13.8542F, 0.89F, 5.1657F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).texOffs(86, 3)
						.addBox(11.617F, -15.8966F, 0.89F, 5.1657F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).texOffs(85, 4).addBox(8.5415F, -12.821F, 0.89F, 5.1657F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).texOffs(84, 5)
						.addBox(7.5083F, -11.7879F, 0.89F, 5.1657F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).texOffs(83, 6).addBox(6.4752F, -10.7548F, 0.89F, 5.1657F, 1.0332F, 1.0332F, new CubeDeformation(0.0F)).texOffs(82, 7)
						.addBox(5.4421F, -9.7216F, 0.89F, 5.1657F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).texOffs(81, 8).addBox(4.4089F, -8.6885F, 0.89F, 5.1657F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).texOffs(77, 9).mirror()
						.addBox(1.3095F, -7.6553F, 0.89F, 1.0332F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).mirror(false).texOffs(80, 9).addBox(3.3758F, -7.6553F, 0.89F, 5.1657F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).texOffs(77, 10)
						.addBox(0.2763F, -6.6222F, 0.89F, 7.232F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).texOffs(78, 11).addBox(1.3095F, -5.589F, 0.89F, 5.1657F, 1.0332F, 1.0332F, new CubeDeformation(0.0F)).texOffs(78, 12)
						.addBox(1.3095F, -4.5559F, 0.89F, 4.1326F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).texOffs(77, 13).addBox(0.2763F, -3.5227F, 0.89F, 6.1989F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).texOffs(76, 14)
						.addBox(-0.7568F, -2.4896F, 0.89F, 3.0994F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).texOffs(80, 14).mirror().addBox(4.4089F, -2.4896F, 0.89F, 1.0332F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).mirror(false)
						.texOffs(74, 15).addBox(-2.8231F, -1.4564F, 0.89F, 4.1326F, 1.0332F, 1.0332F, new CubeDeformation(0.0F)).texOffs(74, 16).addBox(-2.8231F, -0.4233F, 0.89F, 3.0994F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).texOffs(74, 17)
						.addBox(-2.8231F, 0.6098F, 0.89F, 3.0994F, 1.0332F, 1.0332F, new CubeDeformation(0.0F)).texOffs(89, 0).addBox(13.6731F, -17.9527F, 0.89F, 4.1326F, 1.0332F, 1.0332F, new CubeDeformation(0.0F)).texOffs(88, 1)
						.addBox(12.6399F, -16.9195F, 0.89F, 5.1657F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).texOffs(87, 2).addBox(10.6078F, -14.8873F, 0.89F, 5.1657F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).texOffs(86, 3)
						.addBox(9.5746F, -13.8542F, 0.89F, 5.1657F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).texOffs(86, 3).addBox(11.617F, -15.8966F, 0.89F, 5.1657F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).texOffs(85, 4)
						.addBox(8.5415F, -12.821F, 0.89F, 5.1657F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).texOffs(84, 5).addBox(7.5083F, -11.7879F, 0.89F, 5.1657F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).texOffs(83, 6)
						.addBox(6.4752F, -10.7548F, 0.89F, 5.1657F, 1.0332F, 1.0332F, new CubeDeformation(0.0F)).texOffs(82, 7).addBox(5.4421F, -9.7216F, 0.89F, 5.1657F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).texOffs(81, 8)
						.addBox(4.4089F, -8.6885F, 0.89F, 5.1657F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).texOffs(77, 9).mirror().addBox(1.3095F, -7.6553F, 0.89F, 1.0332F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).mirror(false)
						.texOffs(80, 9).addBox(3.3758F, -7.6553F, 0.89F, 5.1657F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).texOffs(77, 10).addBox(0.2763F, -6.6222F, 0.89F, 7.232F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).texOffs(78, 11)
						.addBox(1.3095F, -5.589F, 0.89F, 5.1657F, 1.0332F, 1.0332F, new CubeDeformation(0.0F)).texOffs(78, 12).addBox(1.3095F, -4.5559F, 0.89F, 4.1326F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).texOffs(77, 13)
						.addBox(0.2763F, -3.5227F, 0.89F, 6.1989F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).texOffs(76, 14).addBox(-0.7568F, -2.4896F, 0.89F, 3.0994F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).texOffs(80, 14).mirror()
						.addBox(4.4089F, -2.4896F, 0.89F, 1.0332F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).mirror(false).texOffs(74, 15).addBox(-2.8231F, -1.4564F, 0.89F, 4.1326F, 1.0332F, 1.0332F, new CubeDeformation(0.0F)).texOffs(74, 16)
						.addBox(-2.8231F, -0.4233F, 0.89F, 3.0994F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).texOffs(74, 17).addBox(-2.8231F, 0.6098F, 0.89F, 3.0994F, 1.0332F, 1.0332F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.0F, 0.0F, 0.0F, 1.5708F, 0.7854F, -1.5708F));
		PartDefinition rightItemParticlePos = rightItemLayer.addOrReplaceChild("rightItemParticlePos", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -22.0F));
		PartDefinition leftArm = torso.addOrReplaceChild("leftArm", CubeListBuilder.create(), PartPose.offset(6.25F, -9.0F, 0.0F));
		PartDefinition LeftArm_r1 = leftArm.addOrReplaceChild("LeftArm_r1", CubeListBuilder.create().texOffs(19, 47).mirror().addBox(-2.9F, -3.28F, -2.5F, 7.0F, 6.0F, 5.0F, new CubeDeformation(0.1F)).mirror(false),
				PartPose.offsetAndRotation(0.75F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3927F));
		PartDefinition LeftArm_r2 = leftArm.addOrReplaceChild("LeftArm_r2", CubeListBuilder.create().texOffs(60, 58).addBox(-7.75F, -17.75F, 0.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.001F)),
				PartPose.offsetAndRotation(-5.0F, 22.0F, 2.0F, 0.0F, 3.1416F, 0.0F));
		PartDefinition LeftArm_r3 = leftArm.addOrReplaceChild("LeftArm_r3", CubeListBuilder.create().texOffs(57, 23).addBox(-7.0F, -24.0F, -2.0F, 3.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-5.0F, 22.0F, 0.0F, 0.0F, 3.1416F, 0.0F));
		PartDefinition leftItemLayer = leftArm.addOrReplaceChild("leftItemLayer", CubeListBuilder.create(), PartPose.offset(-0.25F, 8.0F, 0.0F));
		PartDefinition s_r2 = leftItemLayer.addOrReplaceChild("s_r2", CubeListBuilder.create().texOffs(74, 17).mirror().addBox(-0.2763F, 0.6098F, 0.89F, 3.0994F, 1.0332F, 1.0332F, new CubeDeformation(0.0F)).mirror(false).texOffs(74, 16).mirror()
				.addBox(-0.2763F, -0.4233F, 0.89F, 3.0994F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).mirror(false).texOffs(74, 15).mirror().addBox(-1.3095F, -1.4564F, 0.89F, 4.1326F, 1.0332F, 1.0332F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(80, 14).addBox(-5.4421F, -2.4896F, 0.89F, 1.0332F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).texOffs(76, 14).mirror().addBox(-2.3426F, -2.4896F, 0.89F, 3.0994F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(77, 13).mirror().addBox(-6.4752F, -3.5227F, 0.89F, 6.1989F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).mirror(false).texOffs(78, 12).mirror()
				.addBox(-5.4421F, -4.5559F, 0.89F, 4.1326F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).mirror(false).texOffs(78, 11).mirror().addBox(-6.4752F, -5.589F, 0.89F, 5.1657F, 1.0332F, 1.0332F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(77, 10).mirror().addBox(-7.5083F, -6.6222F, 0.89F, 7.232F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).mirror(false).texOffs(80, 9).mirror()
				.addBox(-8.5415F, -7.6553F, 0.89F, 5.1657F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).mirror(false).texOffs(77, 9).addBox(-2.3426F, -7.6553F, 0.89F, 1.0332F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).texOffs(81, 8).mirror()
				.addBox(-9.5746F, -8.6885F, 0.89F, 5.1657F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).mirror(false).texOffs(82, 7).mirror().addBox(-10.6078F, -9.7216F, 0.89F, 5.1657F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(83, 6).mirror().addBox(-11.6409F, -10.7548F, 0.89F, 5.1657F, 1.0332F, 1.0332F, new CubeDeformation(0.0F)).mirror(false).texOffs(84, 5).mirror()
				.addBox(-12.6741F, -11.7879F, 0.89F, 5.1657F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).mirror(false).texOffs(85, 4).mirror().addBox(-13.7072F, -12.821F, 0.89F, 5.1657F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(86, 3).mirror().addBox(-16.7828F, -15.8966F, 0.89F, 5.1657F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).mirror(false).texOffs(86, 3).mirror()
				.addBox(-14.7404F, -13.8542F, 0.89F, 5.1657F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).mirror(false).texOffs(87, 2).mirror().addBox(-15.7735F, -14.8873F, 0.89F, 5.1657F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(88, 1).mirror().addBox(-17.8056F, -16.9195F, 0.89F, 5.1657F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).mirror(false).texOffs(89, 0).mirror()
				.addBox(-17.8057F, -17.9527F, 0.89F, 4.1326F, 1.0332F, 1.0332F, new CubeDeformation(0.0F)).mirror(false).texOffs(74, 17).mirror().addBox(-0.2763F, 0.6098F, 0.89F, 3.0994F, 1.0332F, 1.0332F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(74, 16).mirror().addBox(-0.2763F, -0.4233F, 0.89F, 3.0994F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).mirror(false).texOffs(74, 15).mirror()
				.addBox(-1.3095F, -1.4564F, 0.89F, 4.1326F, 1.0332F, 1.0332F, new CubeDeformation(0.0F)).mirror(false).texOffs(80, 14).addBox(-5.4421F, -2.4896F, 0.89F, 1.0332F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).texOffs(76, 14).mirror()
				.addBox(-2.3426F, -2.4896F, 0.89F, 3.0994F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).mirror(false).texOffs(77, 13).mirror().addBox(-6.4752F, -3.5227F, 0.89F, 6.1989F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(78, 12).mirror().addBox(-5.4421F, -4.5559F, 0.89F, 4.1326F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).mirror(false).texOffs(78, 11).mirror()
				.addBox(-6.4752F, -5.589F, 0.89F, 5.1657F, 1.0332F, 1.0332F, new CubeDeformation(0.0F)).mirror(false).texOffs(77, 10).mirror().addBox(-7.5083F, -6.6222F, 0.89F, 7.232F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(80, 9).mirror().addBox(-8.5415F, -7.6553F, 0.89F, 5.1657F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).mirror(false).texOffs(77, 9).addBox(-2.3426F, -7.6553F, 0.89F, 1.0332F, 1.0331F, 1.0332F, new CubeDeformation(0.0F))
				.texOffs(81, 8).mirror().addBox(-9.5746F, -8.6885F, 0.89F, 5.1657F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).mirror(false).texOffs(82, 7).mirror()
				.addBox(-10.6078F, -9.7216F, 0.89F, 5.1657F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).mirror(false).texOffs(83, 6).mirror().addBox(-11.6409F, -10.7548F, 0.89F, 5.1657F, 1.0332F, 1.0332F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(84, 5).mirror().addBox(-12.6741F, -11.7879F, 0.89F, 5.1657F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).mirror(false).texOffs(85, 4).mirror()
				.addBox(-13.7072F, -12.821F, 0.89F, 5.1657F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).mirror(false).texOffs(86, 3).mirror().addBox(-16.7828F, -15.8966F, 0.89F, 5.1657F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(86, 3).mirror().addBox(-14.7404F, -13.8542F, 0.89F, 5.1657F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).mirror(false).texOffs(87, 2).mirror()
				.addBox(-15.7735F, -14.8873F, 0.89F, 5.1657F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).mirror(false).texOffs(88, 1).mirror().addBox(-17.8056F, -16.9195F, 0.89F, 5.1657F, 1.0331F, 1.0332F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(89, 0).mirror().addBox(-17.8057F, -17.9527F, 0.89F, 4.1326F, 1.0332F, 1.0332F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 0.0F, 0.0F, 1.5708F, -0.7854F, 1.5708F));
		PartDefinition leftItemParticlePos = leftItemLayer.addOrReplaceChild("leftItemParticlePos", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -21.0F));
		PartDefinition rightLeg = whole.addOrReplaceChild("rightLeg",
				CubeListBuilder.create().texOffs(54, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(38, 0).mirror().addBox(-2.0F, 6.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.25F)).mirror(false),
				PartPose.offset(-1.9F, 12.0F, 0.0F));
		PartDefinition leftLeg = whole.addOrReplaceChild("leftLeg",
				CubeListBuilder.create().texOffs(54, 0).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(38, 0).addBox(-2.0F, 6.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.25F)),
				PartPose.offset(1.9F, 12.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.trueHead.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.trueHead.xRot = headPitch / (180F / (float) Math.PI);
	}
}