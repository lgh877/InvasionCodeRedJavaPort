package com.sweegy.invasioncodered.client.particle;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.util.Mth;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.multiplayer.ClientLevel;

@OnlyIn(Dist.CLIENT)
public class GashslitCritParticleParticle extends TextureSheetParticle {
	public static GashslitCritParticleParticleProvider provider(SpriteSet spriteSet) {
		return new GashslitCritParticleParticleProvider(spriteSet);
	}

	public static class GashslitCritParticleParticleProvider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public GashslitCritParticleParticleProvider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			return new GashslitCritParticleParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
		}
	}

	protected GashslitCritParticleParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
		super(world, x, y, z);
		this.setSize(0.2f, 0.2f);
		this.lifetime = Math.max((int) (6.0F / (this.random.nextFloat() * 0.8F + 0.6F)), 1);
		this.gravity = -0.5f;
		this.hasPhysics = false;
		this.pickSprite(spriteSet);
		float f = this.random.nextFloat() * 0.3F + 0.6F;
		this.rCol = f;
		this.gCol = f * 0.1f;
		this.bCol = f * 0.03f;
		this.quadSize *= 0.75f;
		this.friction = 0.7F;
		this.xd = (this.xd * 0.1D) + (vx * 0.4D);
		this.yd = (this.yd * 0.1D) + (vy * 0.4D);
		this.zd = (this.zd * 0.1D) + (vz * 0.4D);
	}

	@Override
	public int getLightColor(float partialTick) {
		return 15728880;
	}

	public float getQuadSize(float p_105938_) {
		return this.quadSize * Mth.clamp(((float) this.age + p_105938_) / (float) this.lifetime * 32.0F, 0.0F, 1.0F);
	}

	@Override
	public ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}

	@Override
	public void tick() {
		super.tick();
		this.rCol *= 0.9F;
		this.gCol *= 0.96F;
	}
}