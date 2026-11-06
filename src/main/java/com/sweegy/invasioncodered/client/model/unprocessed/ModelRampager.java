package com.sweegy.invasioncodered.client.model.unprocessed;

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

public class ModelRampager<T extends Entity> extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.tryParse("invasioncodered:rampager"), "main");
    public final ModelPart whole;
    public final ModelPart right_leg;
    public final ModelPart left_leg;
    public final ModelPart torso;
    public final ModelPart trueHead;
    public final ModelPart head;
    public final ModelPart bone;
    public final ModelPart left_tendril;
    public final ModelPart right_tendril;
    public final ModelPart nose;
    public final ModelPart left_arm;
    public final ModelPart right_arm;
    public final ModelPart bone2;
    public final ModelPart bone5;
    public final ModelPart bone4;
    public final ModelPart bone3;

    public ModelRampager(ModelPart root) {
        this.whole = root.getChild("whole");
        this.right_leg = this.whole.getChild("right_leg");
        this.left_leg = this.whole.getChild("left_leg");
        this.torso = this.whole.getChild("torso");
        this.trueHead = this.torso.getChild("trueHead");
        this.head = this.trueHead.getChild("head");
        this.bone = this.head.getChild("bone");
        this.left_tendril = this.bone.getChild("left_tendril");
        this.right_tendril = this.bone.getChild("right_tendril");
        this.nose = this.head.getChild("nose");
        this.left_arm = this.torso.getChild("left_arm");
        this.right_arm = this.torso.getChild("right_arm");
        this.bone2 = this.torso.getChild("bone2");
        this.bone5 = this.torso.getChild("bone5");
        this.bone4 = this.torso.getChild("bone4");
        this.bone3 = this.torso.getChild("bone3");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition whole = partdefinition.addOrReplaceChild("whole", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition right_leg = whole.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(90, 55).addBox(-4.1F, 0.0F, -3.0F, 7.0F, 22.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -22.0F, -1.0F));

        PartDefinition left_leg = whole.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 100).addBox(-1.9F, 0.0F, -2.0F, 7.0F, 22.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -22.0F, -1.0F));

        PartDefinition torso = whole.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, -26.0F, -4.0F, 18.0F, 25.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -21.0F, -1.0F));

        PartDefinition trueHead = torso.addOrReplaceChild("trueHead", CubeListBuilder.create(), PartPose.offset(0.0F, -26.0F, 0.0F));

        PartDefinition head = trueHead.addOrReplaceChild("head", CubeListBuilder.create().texOffs(119, 0).addBox(-8.0F, -19.0F, -7.0F, 16.0F, 10.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(130, 30).addBox(-1.0F, -21.0F, -9.0F, 2.0F, 14.0F, 20.0F, new CubeDeformation(0.0F))
                .texOffs(48, 26).addBox(-6.0F, -16.0F, -6.0F, 12.0F, 16.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition head_r1 = head.addOrReplaceChild("head_r1", CubeListBuilder.create().texOffs(30, 76).mirror().addBox(-2.0F, 0.0F, -8.0F, 2.0F, 9.0F, 16.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(8.0F, -9.0F, 1.0F, 0.0F, 0.0F, -0.3054F));

        PartDefinition head_r2 = head.addOrReplaceChild("head_r2", CubeListBuilder.create().texOffs(30, 76).addBox(0.0F, 0.0F, -8.0F, 2.0F, 9.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, 10.0F, 1.5708F, 1.2654F, 1.5708F));

        PartDefinition head_r3 = head.addOrReplaceChild("head_r3", CubeListBuilder.create().texOffs(30, 76).addBox(0.0F, 0.0F, -8.0F, 2.0F, 9.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, -9.0F, 1.0F, 0.0F, 0.0F, 0.3054F));

        PartDefinition bone = head.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -10.0F, -6.0F, 0.0436F, 0.0F, 0.0F));

        PartDefinition left_tendril = bone.addOrReplaceChild("left_tendril", CubeListBuilder.create().texOffs(26, 101).addBox(0.0F, -13.0F, -1.2F, 16.0F, 16.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0436F, 0.0F));

        PartDefinition right_tendril = bone.addOrReplaceChild("right_tendril", CubeListBuilder.create().texOffs(26, 101).mirror().addBox(-16.0F, -13.0F, -1.2F, 16.0F, 16.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.0436F, 0.0F));

        PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(0, 36).addBox(-2.0F, 0.0F, -4.0F, 4.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, -5.0F));

        PartDefinition left_arm = torso.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0, 62).addBox(-4.0F, -8.0F, -4.0F, 7.0F, 30.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(58, 0).addBox(3.0F, -8.0F, -4.0F, 2.0F, 14.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(82, 110).addBox(-2.0F, 18.0F, -8.0F, 3.0F, 3.0F, 15.0F, new CubeDeformation(0.0F))
                .texOffs(171, 12).addBox(-3.0F, 17.0F, -30.0F, 6.0F, 6.0F, 22.0F, new CubeDeformation(0.0F))
                .texOffs(182, 71).mirror().addBox(-7.0F, 20.0F, -30.0F, 4.0F, 0.0F, 22.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(182, 71).addBox(3.0F, 20.0F, -30.0F, 4.0F, 0.0F, 22.0F, new CubeDeformation(0.0F))
                .texOffs(132, 51).addBox(0.0F, 13.0F, -30.0F, 0.0F, 4.0F, 22.0F, new CubeDeformation(0.0F))
                .texOffs(156, 59).mirror().addBox(0.0F, 23.0F, -30.0F, 0.0F, 4.0F, 22.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(13.0F, -18.0F, 1.0F));

        PartDefinition right_arm = torso.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(56, 54).addBox(-3.0F, -9.0F, -4.0F, 7.0F, 30.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(58, 93).addBox(-5.0F, -9.0F, -4.0F, 2.0F, 14.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-13.0F, -17.0F, 1.0F));

        PartDefinition bone2 = torso.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(125, 29).addBox(-5.0F, -2.0F, 0.9F, 11.0F, 14.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -4.0F));

        PartDefinition bone5 = torso.addOrReplaceChild("bone5", CubeListBuilder.create(), PartPose.offsetAndRotation(11.0F, 0.0F, -4.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition bone5_r1 = bone5.addOrReplaceChild("bone5_r1", CubeListBuilder.create().texOffs(125, 29).addBox(-5.0F, -2.0F, -0.1F, 11.0F, 14.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 0.0F, 2.0F, -0.0436F, 0.0F, 0.0F));

        PartDefinition bone4 = torso.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(125, 29).addBox(-6.1F, -2.0F, 0.9F, 11.0F, 14.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-9.0F, 0.0F, 1.0F, 0.0F, 1.5708F, 0.0436F));

        PartDefinition bone3 = torso.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(125, 29).addBox(-5.0F, -2.0F, 1.9F, 11.0F, 14.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 5.0F));

        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        whole.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}