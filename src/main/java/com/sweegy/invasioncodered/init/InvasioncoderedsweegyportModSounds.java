package com.sweegy.invasioncodered.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

import com.sweegy.invasioncodered.InvasioncoderedsweegyportMod;

public class InvasioncoderedsweegyportModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, InvasioncoderedsweegyportMod.MODID);
	public static final RegistryObject<SoundEvent> GASH_HURT = REGISTRY.register("gash_hurt", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.tryParse("invasioncodered:gash_hurt")));
	public static final RegistryObject<SoundEvent> GASH_IDLE = REGISTRY.register("gash_idle", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.tryParse("invasioncodered:gash_idle")));
	public static final RegistryObject<SoundEvent> GASH_DASH = REGISTRY.register("gash_dash", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.tryParse("invasioncodered:gash_dash")));
	public static final RegistryObject<SoundEvent> GASH_DEATH = REGISTRY.register("gash_death", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.tryParse("invasioncodered:gash_death")));
	public static final RegistryObject<SoundEvent> GASH_ATTACK = REGISTRY.register("gash_attack", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.tryParse("invasioncodered:gash_attack")));
	public static final RegistryObject<SoundEvent> GASH_SLOWATTACK = REGISTRY.register("gash_slowattack", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.tryParse("invasioncodered:gash_slowattack")));
	public static final RegistryObject<SoundEvent> GASH_PREPARES_DASH = REGISTRY.register("gash_prepares_dash", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.tryParse("invasioncodered:gash_prepares_dash")));
	public static final RegistryObject<SoundEvent> GASH_SLASH_BARRAGE = REGISTRY.register("gash_slash_barrage", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.tryParse("invasioncodered:gash_slash_barrage")));
	public static final RegistryObject<SoundEvent> GASH_BARRAGE_PREPARE = REGISTRY.register("gash_barrage_prepare", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.tryParse("invasioncodered:gash_barrage_prepare")));
    public static final RegistryObject<SoundEvent> GASH_STEP = REGISTRY.register("gash_step", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.tryParse("invasioncodered:gash_step")));
    public static final RegistryObject<SoundEvent> GASH_DRAGON_HURT = REGISTRY.register("gash_dragon_hurt", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.tryParse("invasioncodered:gash_dragon_hurt")));
    public static final RegistryObject<SoundEvent> GASH_DRAGON_DEATH = REGISTRY.register("gash_dragon_death", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.tryParse("invasioncodered:gash_dragon_death")));
    public static final RegistryObject<SoundEvent> CURSED_SLASH_SHOOT = REGISTRY.register("cursed_slash_shoot", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.tryParse("invasioncodered:cursed_slash_shoot")));
}