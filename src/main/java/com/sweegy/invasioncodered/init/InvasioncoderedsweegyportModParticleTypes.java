package com.sweegy.invasioncodered.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleType;

import com.sweegy.invasioncodered.InvasioncoderedsweegyportMod;

public class InvasioncoderedsweegyportModParticleTypes {
	public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, InvasioncoderedsweegyportMod.MODID);
	public static final RegistryObject<SimpleParticleType> GASHSLIT_CRIT_PARTICLE = REGISTRY.register("gashslit_crit_particle", () -> new SimpleParticleType(true));
	public static final RegistryObject<SimpleParticleType> GASHSLIT_SMOKE_PARTICLE = REGISTRY.register("gashslit_smoke_particle", () -> new SimpleParticleType(true));
	public static final RegistryObject<SimpleParticleType> GASHSLIT_SLASH_BARRAGE_PARTICLE = REGISTRY.register("gashslit_slash_barrage_particle", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> GASHSLIT_POPFLAME_PARTICLE = REGISTRY.register("gashslit_popflame_particle", () -> new SimpleParticleType(false));
}