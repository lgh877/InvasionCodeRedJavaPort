package com.sweegy.invasioncodered.client.renderer.layer;

import com.sweegy.invasioncodered.client.model.GashslitAnimatedModel;
import com.sweegy.invasioncodered.util.animation.ModelPartUtil;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;
import net.minecraft.util.Mth;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.MultiBufferSource;

import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModParticleTypes;
import com.sweegy.invasioncodered.entity.gashslit.GashslitEntity;

import com.mojang.blaze3d.vertex.PoseStack;

public class GashslitSwordParticleLayer extends RenderLayer<GashslitEntity, GashslitAnimatedModel> {
	private final GashslitAnimatedModel model;
	private static final float UPDATES_PER_TICK = 1.52F;

	public GashslitSwordParticleLayer(RenderLayerParent<GashslitEntity, GashslitAnimatedModel> p_117346_) {
		super(p_117346_);
		model = getParentModel();
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource bufferSource, int light, GashslitEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		if (entity.getSwingType() != 0) {
			int currentUpdateStep = (int) (ageInTicks * UPDATES_PER_TICK);
			if (currentUpdateStep != entity.prevPartialTicks) {
				float yaw = Mth.rotLerp(partialTicks, entity.yBodyRotO, entity.yBodyRot);
				Vec3 basePos = entity.getPosition(partialTicks);
				if (entity.getSwingType(0)) {
					Vec3 rightParticlePos = ModelPartUtil.getModelPosition(entity, yaw, model::translateRightHand, basePos);
					rightParticlePos = entity.position().subtract(rightParticlePos).length() > entity.getBbWidth() * 5 ? entity.rightParticlePrev : rightParticlePos;
					spawnTrailParticles(entity.level(), entity.rightParticlePrev, rightParticlePos, entity.getScale(), entity.didSwing, entity.getRandom());
					entity.rightParticlePrev = rightParticlePos;
				}
				if (entity.getSwingType(1)) {
					Vec3 leftParticlePos = ModelPartUtil.getModelPosition(entity, yaw, model::translateLeftHand, basePos);
					leftParticlePos = entity.position().subtract(leftParticlePos).length() > entity.getBbWidth() * 5 ? entity.leftParticlePrev : leftParticlePos;
					spawnTrailParticles(entity.level(), entity.leftParticlePrev, leftParticlePos, entity.getScale(), entity.didSwing, entity.getRandom());
					entity.leftParticlePrev = leftParticlePos;
				}
				entity.didSwing = true;
				entity.prevPartialTicks = currentUpdateStep;
			}
		}
	}

	private void spawnTrailParticles(Level level, Vec3 prevPos, Vec3 currentPos, double scaleVal, boolean didSwing, RandomSource randomSource) {
		double jitter = 0.1 * scaleVal;
		if (prevPos == null || !didSwing) {
			int count = (int) (scaleVal * scaleVal) + 1;
			for (int i = 0; i < count; i++) {
				spawnSingleParticle(level, currentPos, jitter);
			}
			return;
		}
		Vec3 lengthVec = prevPos.subtract(currentPos);
		double distance = lengthVec.length();
		int particleCount = (int) (scaleVal * scaleVal * distance) + 1;
		for (int i = 0; i < particleCount; i++) {
			double lerpFactor = randomSource.nextDouble();
			double lerpX = Mth.lerp(lerpFactor, prevPos.x(), currentPos.x());
			double lerpY = Mth.lerp(lerpFactor, prevPos.y(), currentPos.y());
			double lerpZ = Mth.lerp(lerpFactor, prevPos.z(), currentPos.z());
			spawnSingleParticle(level, new Vec3(lerpX, lerpY, lerpZ), jitter);
		}
	}

	private void spawnSingleParticle(Level level, Vec3 pos, double jitter) {
		level.addParticle(InvasioncoderedsweegyportModParticleTypes.GASHSLIT_CRIT_PARTICLE.get(), pos.x() + (Math.random() - 0.5) * jitter, pos.y() + (Math.random() - 0.5) * jitter, pos.z() + (Math.random() - 0.5) * jitter, 0.0D, 0.0D, 0.0D);
	}
}