package com.sweegy.invasioncodered.client.model;// Made with Blockbench 5.1.3

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

public class Modelstonecutterman<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.tryParse("invasioncodered:modelstonecutterman"), "main");
    public final ModelPart whole;
    public final ModelPart bone3;
    public final ModelPart Body;
    public final ModelPart trueHead;
    public final ModelPart head;
    public final ModelPart nose;
    public final ModelPart Chainsaw_Hat;
    public final ModelPart RightArm;
    public final ModelPart rightitem;
    public final ModelPart LeftArm;
    public final ModelPart bone;
    public final ModelPart LeftLeg;
    public final ModelPart bone2;
    public final ModelPart RightLeg;

    public Modelstonecutterman(ModelPart root) {
        this.whole = root.getChild("whole");
        this.bone3 = this.whole.getChild("bone3");
        this.Body = this.bone3.getChild("Body");
        this.trueHead = this.bone3.getChild("trueHead");
        this.head = this.trueHead.getChild("head");
        this.nose = this.head.getChild("nose");
        this.Chainsaw_Hat = this.head.getChild("Chainsaw_Hat");
        this.RightArm = this.bone3.getChild("RightArm");
        this.rightitem = this.RightArm.getChild("rightitem");
        this.LeftArm = this.bone3.getChild("LeftArm");
        this.bone = this.whole.getChild("bone");
        this.LeftLeg = this.bone.getChild("LeftLeg");
        this.bone2 = this.LeftLeg.getChild("bone2");
        this.RightLeg = this.bone.getChild("RightLeg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition whole = partdefinition.addOrReplaceChild("whole", CubeListBuilder.create(), PartPose.offset(0.0F, 12.0F, 0.0F));

        PartDefinition bone3 = whole.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(0, 29).addBox(-4.0F, -12.0F, -3.0F, 8.0F, 13.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(54, 25).addBox(-4.0F, -12.0F, -3.0F, 8.0F, 13.0F, 6.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition Body = bone3.addOrReplaceChild("Body", CubeListBuilder.create(), PartPose.offset(0.0F, -5.0F, 0.0F));

        PartDefinition trueHead = bone3.addOrReplaceChild("trueHead", CubeListBuilder.create(), PartPose.offset(0.0F, -12.0F, 0.0F));

        PartDefinition head = trueHead.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(24, 2).mirror().addBox(-1.0F, -2.5F, -1.01F, 4.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.0F, -4.0F, -3.0F, 0.0F, 0.0F, 0.0873F));

        PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(24, 2).addBox(-3.0F, -2.5F, -1.01F, 4.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -4.0F, -3.0F, 0.0F, 0.0F, -0.0873F));

        PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -2.0F, -6.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));

        PartDefinition Chainsaw_Hat = head.addOrReplaceChild("Chainsaw_Hat", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -37.0F, -17.0F, 0.0F, 8.0F, 21.0F, new CubeDeformation(0.0F))
                .texOffs(0, 48).addBox(-4.0F, -34.0F, -8.15F, 8.0F, 6.0F, 4.0F, new CubeDeformation(0.1F))
                .texOffs(32, 45).addBox(-4.0F, -34.0F, -4.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.1F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition RightArm = bone3.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -2.5F, -2.0F, 5.0F, 14.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(28, 35).addBox(-7.0F, 2.5F, 0.0F, 5.0F, 16.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(51, 0).addBox(-6.0F, 4.5F, -3.0F, 4.0F, 8.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(51, 15).mirror().addBox(-4.0F, -2.5F, -2.0F, 5.0F, 5.0F, 4.0F, new CubeDeformation(0.3F)).mirror(false), PartPose.offset(-5.0F, -10.0F, 0.0F));

        PartDefinition rightitem = RightArm.addOrReplaceChild("rightitem", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 0.0F));

        PartDefinition LeftArm = bone3.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(32, 0).addBox(-1.0F, -2.5F, -2.0F, 5.0F, 14.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(51, 15).addBox(-1.0F, -2.5F, -2.0F, 5.0F, 5.0F, 4.0F, new CubeDeformation(0.3F))
                .texOffs(51, 0).addBox(2.0F, 4.5F, -3.0F, 4.0F, 8.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(28, 35).addBox(2.0F, 2.5F, 0.0F, 5.0F, 16.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -10.0F, 0.0F));

        PartDefinition bone = whole.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition LeftLeg = bone.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(72, 0).addBox(-2.0F, 1.0F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 0.0F, 0.0F));

        PartDefinition bone2 = LeftLeg.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offset(-7.0F, -9.0F, 0.0F));

        PartDefinition RightLeg = bone.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(72, 0).addBox(-2.0F, 1.0F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.trueHead.yRot = netHeadYaw / (180F / (float) Math.PI);
        this.trueHead.xRot = headPitch / (180F / (float) Math.PI);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        whole.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}