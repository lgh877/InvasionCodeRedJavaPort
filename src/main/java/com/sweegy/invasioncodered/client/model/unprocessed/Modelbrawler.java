package com.sweegy.invasioncodered.client.model.unprocessed;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
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

public class Modelbrawler<T extends Entity> extends EntityModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.tryParse("invasioncodered:brawler"), "main");
    public final ModelPart whole;
    public final ModelPart bone;
    public final ModelPart LeftLeg;
    public final ModelPart RightLeg;
    public final ModelPart bone3;
    public final ModelPart Body;
    public final ModelPart trueHead;
    public final ModelPart head;
    public final ModelPart nose;
    public final ModelPart arms;
    public final ModelPart LeftArm;
    public final ModelPart RightArm;

    public Modelbrawler(ModelPart root) {
        this.whole = root.getChild("whole");
        this.bone = this.whole.getChild("bone");
        this.LeftLeg = this.bone.getChild("LeftLeg");
        this.RightLeg = this.bone.getChild("RightLeg");
        this.bone3 = this.whole.getChild("bone3");
        this.Body = this.bone3.getChild("Body");
        this.trueHead = this.bone3.getChild("trueHead");
        this.head = this.trueHead.getChild("head");
        this.nose = this.head.getChild("nose");
        this.arms = this.bone3.getChild("arms");
        this.LeftArm = this.arms.getChild("LeftArm");
        this.RightArm = this.arms.getChild("RightArm");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition whole = partdefinition.addOrReplaceChild("whole", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bone = whole.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, -12.0F, 0.0F));

        PartDefinition LeftLeg = bone.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(72, 0).addBox(-2.0F, 1.0F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 0.0F, 0.0F));

        PartDefinition RightLeg = bone.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(72, 0).addBox(-2.0F, 1.0F, -2.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 0.0F, 0.0F));

        PartDefinition bone3 = whole.addOrReplaceChild("bone3", CubeListBuilder.create(), PartPose.offset(0.0F, -12.0F, 0.0F));

        PartDefinition Body = bone3.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 29).addBox(-4.0F, -7.0F, -3.0F, 8.0F, 13.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(54, 25).addBox(-4.0F, -7.0F, -3.0F, 8.0F, 13.0F, 6.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, -5.0F, 0.0F));

        PartDefinition trueHead = bone3.addOrReplaceChild("trueHead", CubeListBuilder.create(), PartPose.offset(0.0F, -12.0F, 0.0F));

        PartDefinition head = trueHead.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r1 = head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(24, 2).mirror().addBox(-2.0F, -2.0F, -1.01F, 4.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, -4.0F, -3.0F, 0.0F, 0.0F, 0.0873F));

        PartDefinition cube_r2 = head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(24, 2).addBox(-2.0F, -2.0F, -1.01F, 4.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -4.0F, -3.0F, 0.0F, 0.0F, -0.0873F));

        PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));

        PartDefinition arms = bone3.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(0.0F, -10.0F, 0.0F));

        PartDefinition LeftArm = arms.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(32, 0).mirror().addBox(-1.0F, -2.0F, -2.0F, 5.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, 0.0F, 0.0F));

        PartDefinition RightArm = arms.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -2.0F, -2.0F, 5.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 0.0F, 0.0F));

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