package com.sweegy.invasioncodered.client.particle;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.multiplayer.ClientLevel;

@OnlyIn(Dist.CLIENT)
public class GashslitSmokeParticleParticle extends TextureSheetParticle {
	public static GashslitSmokeParticleParticleProvider provider(SpriteSet spriteSet) {
		return new GashslitSmokeParticleParticleProvider(spriteSet);
	}

	public static class GashslitSmokeParticleParticleProvider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public GashslitSmokeParticleParticleProvider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			return new GashslitSmokeParticleParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
		}
	}

	private final SpriteSet spriteSet;

	protected GashslitSmokeParticleParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
		super(world, x, y, z);
		this.spriteSet = spriteSet;
		this.setSize(0.2f, 0.2f);
		this.quadSize = 0.55f;
		this.lifetime = 36;
		this.gravity = -0.01f;
		this.hasPhysics = false;
		this.xd = vx;
		this.yd = vy;
		this.zd = vz;
		this.setSpriteFromAge(spriteSet);
		this.rCol = 0.2666F; // 68/255
		this.gCol = 0.0862F; // 22/255
		this.bCol = 0.5294F; // 135/255
	}

	@Override
	public int getLightColor(float partialTick) {
		return 15728880;
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
	}

	@Override
	public void tick() {
		super.tick();
		if (!this.removed) {
			this.setSpriteFromAge(this.spriteSet);
			float colorProgress = this.age * 0.0277777F;
			this.rCol = 0.2666F + (0.5176F * colorProgress); // (200-68)/255
			this.gCol = 0.0862F - (0.0862F * colorProgress); // (0-22)/255
			this.bCol = 0.5294F - (0.4901F * colorProgress); // (10-135)/255
			if (this.age > 25) {
				float shrinkProgress = (this.age - 25) * 0.090909F;
				this.quadSize = 0.55f * (1.0f - shrinkProgress);
			}
		}
	}
}