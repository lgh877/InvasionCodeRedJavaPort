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
public class GashslitPopflameParticleParticle extends TextureSheetParticle {
	public static GashslitPopflameParticleParticleProvider provider(SpriteSet spriteSet) {
		return new GashslitPopflameParticleParticleProvider(spriteSet);
	}

	public static class GashslitPopflameParticleParticleProvider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public GashslitPopflameParticleParticleProvider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			return new GashslitPopflameParticleParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
		}
	}

	private final SpriteSet spriteSet;

	protected GashslitPopflameParticleParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
		super(world, x, y, z);
		this.spriteSet = spriteSet;
		this.setSize(0.5f, 0.5f);
		this.quadSize = 0.55f;
		this.lifetime = 32;
		this.gravity = 0f;
		this.hasPhysics = true;
		this.xd = vx;
		this.yd = vy;
		this.zd = vz;
		this.setSpriteFromAge(spriteSet);
	}

	@Override
	public int getLightColor(float partialTick) {
		return 15728880;
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	@Override
	public void tick() {
		super.tick();
		if (!this.removed) {
			this.setSpriteFromAge(this.spriteSet);
			if (this.age > 25) {
				float shrinkProgress = (this.age - 25) * 0.142857F;
				this.quadSize = 0.55f * (1.0f - shrinkProgress);
			}
		}
	}
}