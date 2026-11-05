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

public class Modelcuccita<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.tryParse("invasioncodered:cuccita"), "main");
	public final ModelPart bone5;
	public final ModelPart head;
	public final ModelPart bone6;
	public final ModelPart bone;
	public final ModelPart bone2;
	public final ModelPart bone4;
	public final ModelPart bone3;

	public Modelcuccita(ModelPart root) {
		this.bone5 = root.getChild("bone5");
		this.head = this.bone5.getChild("head");
		this.bone6 = this.head.getChild("bone6");
		this.bone = this.bone5.getChild("bone");
		this.bone2 = this.bone5.getChild("bone2");
		this.bone4 = this.bone5.getChild("bone4");
		this.bone3 = this.bone5.getChild("bone3");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone5 = partdefinition.addOrReplaceChild("bone5", CubeListBuilder.create(), PartPose.offset(0.0F, 19.0F, 0.0F));

		PartDefinition head = bone5.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 5).addBox(0.0F, -13.0F, -14.0F, 0.0F, 14.0F, 20.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-6.0F, -5.0F, -7.0F, 12.0F, 9.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(0, 11).addBox(-1.0F, 1.0F, -9.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.0F, -5.0F, 9.0F, 6.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition bone6 = head.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(32, 31).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 11.0F));

		PartDefinition bone = bone5.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(40, 0).addBox(1.0F, -4.0F, -5.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.0F, 0.0F));

		PartDefinition bone2 = bone5.addOrReplaceChild("bone2", CubeListBuilder.create().texOffs(0, 39).addBox(1.0F, -4.0F, 4.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.0F, 0.0F));

		PartDefinition bone4 = bone5.addOrReplaceChild("bone4", CubeListBuilder.create().texOffs(40, 7).addBox(-5.0F, -4.0F, -5.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.0F, 0.0F));

		PartDefinition bone3 = bone5.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(14, 39).addBox(-5.0F, -4.0F, 4.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bone5.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}