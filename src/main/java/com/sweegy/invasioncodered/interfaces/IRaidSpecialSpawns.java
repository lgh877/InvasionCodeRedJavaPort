package com.sweegy.invasioncodered.interfaces;

import net.minecraft.resources.ResourceLocation;
import java.util.Set;

public interface IRaidSpecialSpawns {
    Set<ResourceLocation> invasioncodered$getSpawnedSpecials();
    boolean invasioncodered$hasSpawnedSpecial(ResourceLocation id);
    void invasioncodered$addSpawnedSpecial(ResourceLocation id);
}