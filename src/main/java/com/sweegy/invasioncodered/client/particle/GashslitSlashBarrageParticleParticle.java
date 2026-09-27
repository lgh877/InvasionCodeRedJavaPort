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
public class GashslitSlashBarrageParticleParticle extends TextureSheetParticle {
	public static GashslitSlashBarrageParticleParticleProvider provider(SpriteSet spriteSet) {
		return new GashslitSlashBarrageParticleParticleProvider(spriteSet);
	}

	public static class GashslitSlashBarrageParticleParticleProvider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet spriteSet;

		public GashslitSlashBarrageParticleParticleProvider(SpriteSet spriteSet) {
			this.spriteSet = spriteSet;
		}

		public Particle createParticle(SimpleParticleType typeIn, ClientLevel worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			return new GashslitSlashBarrageParticleParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
		}
	}

	protected GashslitSlashBarrageParticleParticle(ClientLevel world, double x, double y, double z, double vx, double vy, double vz, SpriteSet spriteSet) {
		super(world, x, y, z);
		this.setSize(1.5f, 1.5f);
		this.quadSize = 1.2f;
		this.lifetime = 25;
		this.gravity = 0.03f;
		this.hasPhysics = false;
		this.xd = vx;
		this.yd = vy;
		this.zd = vz;
		this.roll = this.random.nextFloat() * Mth.TWO_PI;
		this.oRoll = this.roll;
		this.pickSprite(spriteSet);
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
		this.bCol *= 0.8F;
		this.gCol *= 0.8F;
	}
}