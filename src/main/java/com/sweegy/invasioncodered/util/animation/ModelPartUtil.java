/**
 * The code of this mod element is always locked.
 *
 * You can register new events in this class too.
 *
 * If you want to make a plain independent class, create it using
 * Project Browser -> New... and make sure to make the class
 * outside com.sweegy.invasioncoderedjavaport as this package is managed by MCreator.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
 *
 * This class will be added in the mod root package.
*/
package com.sweegy.invasioncodered.util.animation;

import org.joml.Vector4f;
import org.joml.Vector3f;
import org.joml.Quaternionf;
import org.joml.Matrix4f;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.client.model.geom.ModelPart;

import java.util.function.Consumer;
import java.util.List;

import com.mojang.blaze3d.vertex.PoseStack;

public class ModelPartUtil {
	public static Vec3 getModelPosition(Entity entity, float yaw, Consumer<PoseStack> poseStackTransformer, Vec3 entityPos) {
		PoseStack stack = new PoseStack();
		stack.translate(entityPos.x(), entityPos.y(), entityPos.z());
		stack.mulPose(new Quaternionf().rotationY((-yaw + 180.0F) * ((float) Math.PI / 180.0F)));
		stack.scale(-1.0F, -1.0F, 1.0F);
		stack.translate(0.0F, -1.501F, 0.0F);
		poseStackTransformer.accept(stack);
		Matrix4f matrix = stack.last().pose();
		Vector4f vec = new Vector4f(0.0F, 0.0F, 0.0F, 1.0F).mul(matrix);
		Vec3 pos = new Vec3(vec.x(), vec.y(), vec.z());
		Vec3 offset = pos.subtract(entityPos);
		double scale = (entity instanceof LivingEntity living) ? living.getScale() : 1.0;
		return entity.position().add(offset.scale(scale));
	}

	public static void applyInverseRotationToLeaf(List<ModelPart> hierarchy) {
		if (hierarchy == null || hierarchy.size() < 2)
			return;
		Quaternionf cumulativeRotation = new Quaternionf();
		for (int i = 0; i < hierarchy.size() - 1; i++) {
			ModelPart part = hierarchy.get(i);
			Quaternionf partQuat = new Quaternionf().rotateZYX(part.zRot, part.yRot, part.xRot);
			cumulativeRotation.mul(partQuat);
		}
		cumulativeRotation.conjugate();
		Vector3f inverseEuler = new Vector3f();
		cumulativeRotation.getEulerAnglesZYX(inverseEuler);
		ModelPart leaf = hierarchy.get(hierarchy.size() - 1);
		leaf.xRot = inverseEuler.x;
		leaf.yRot = inverseEuler.y;
		leaf.zRot = inverseEuler.z;
	}

	public static void syncOffsetByMath(List<ModelPart> hierarchy, ModelPart target) {
		if (hierarchy == null || hierarchy.isEmpty())
			return;
		Matrix4f transformMatrix = new Matrix4f();
		for (ModelPart part : hierarchy) {
			transformMatrix.translate(part.x, part.y, part.z);
			if (part.zRot != 0.0F)
				transformMatrix.rotateZ(part.zRot);
			if (part.yRot != 0.0F)
				transformMatrix.rotateY(part.yRot);
			if (part.xRot != 0.0F)
				transformMatrix.rotateX(part.xRot);
		}
		Vector3f globalPos = transformMatrix.transformPosition(new Vector3f(0, 0, 0));
		target.x = globalPos.x;
		target.y = globalPos.y;
		target.z = globalPos.z;
	}
}