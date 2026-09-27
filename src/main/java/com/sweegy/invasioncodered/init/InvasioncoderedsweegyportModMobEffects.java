package com.sweegy.invasioncodered.init;

import com.sweegy.invasioncodered.InvasioncoderedsweegyportMod;
import com.sweegy.invasioncodered.potion.CursedRageMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class InvasioncoderedsweegyportModMobEffects {
    public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, InvasioncoderedsweegyportMod.MODID);
    public static final RegistryObject<MobEffect> CURSED_RAGE = REGISTRY.register("cursed_rage", CursedRageMobEffect::new);
}
