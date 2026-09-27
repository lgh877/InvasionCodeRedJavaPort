package com.sweegy.invasioncodered.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.api.distmarker.Dist;

import com.sweegy.invasioncodered.client.particle.GashslitSmokeParticleParticle;
import com.sweegy.invasioncodered.client.particle.GashslitSlashBarrageParticleParticle;
import com.sweegy.invasioncodered.client.particle.GashslitPopflameParticleParticle;
import com.sweegy.invasioncodered.client.particle.GashslitCritParticleParticle;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class InvasioncoderedsweegyportModParticles {
	@SubscribeEvent
	public static void registerParticles(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(InvasioncoderedsweegyportModParticleTypes.GASHSLIT_CRIT_PARTICLE.get(), GashslitCritParticleParticle::provider);
		event.registerSpriteSet(InvasioncoderedsweegyportModParticleTypes.GASHSLIT_SMOKE_PARTICLE.get(), GashslitSmokeParticleParticle::provider);
		event.registerSpriteSet(InvasioncoderedsweegyportModParticleTypes.GASHSLIT_SLASH_BARRAGE_PARTICLE.get(), GashslitSlashBarrageParticleParticle::provider);
		event.registerSpriteSet(InvasioncoderedsweegyportModParticleTypes.GASHSLIT_POPFLAME_PARTICLE.get(), GashslitPopflameParticleParticle::provider);
	}
}