package com.sweegy.invasioncodered.mixin;

import com.mojang.logging.LogUtils;
import com.sweegy.invasioncodered.InvasioncoderedsweegyportMod;
import com.sweegy.invasioncodered.interfaces.IRaidSpecialSpawns;
import com.sweegy.invasioncodered.raid.SpecialRaiderSpawner;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.raid.Raid;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Mixin(Raid.class)
public abstract class RaidMixin implements IRaidSpecialSpawns {

    @Unique
    private static final String INVASIONCODERED_SPAWNED_SPECIALS_TAG = "InvasionCodeRed_SpawnedSpecials";

    @Unique
    private Set<ResourceLocation> invasioncodered$spawnedSpecials;

    @Unique
    private Set<ResourceLocation> invasioncodered$getOrCreateSpawnedSpecials() {
        if (this.invasioncodered$spawnedSpecials == null) {
            this.invasioncodered$spawnedSpecials = new HashSet<>();
        }

        return this.invasioncodered$spawnedSpecials;
    }

    @Override
    public Set<ResourceLocation> invasioncodered$getSpawnedSpecials() {
        return Collections.unmodifiableSet(invasioncodered$getOrCreateSpawnedSpecials());
    }

    @Override
    public boolean invasioncodered$hasSpawnedSpecial(ResourceLocation id) {
        return id != null && invasioncodered$getOrCreateSpawnedSpecials().contains(id);
    }

    @Override
    public void invasioncodered$addSpawnedSpecial(ResourceLocation id) {
        if (id != null) {
            invasioncodered$getOrCreateSpawnedSpecials().add(id);
        }
    }

    @Inject(method = "spawnGroup(Lnet/minecraft/core/BlockPos;)V", at = @At("TAIL"), require = 1)
    private void invasioncodered$afterSpawnGroup(BlockPos spawnPos, CallbackInfo ci) {
        Raid raid = (Raid) (Object) this;
        SpecialRaiderSpawner.spawnSpecialRaiders(raid, spawnPos);
    }

    @Inject(method = "save(Lnet/minecraft/nbt/CompoundTag;)Lnet/minecraft/nbt/CompoundTag;", at = @At("TAIL"), require = 1)
    private void invasioncodered$saveSpecialSpawns(CompoundTag tag, CallbackInfoReturnable<CompoundTag> cir) {
        Set<ResourceLocation> spawned = invasioncodered$getOrCreateSpawnedSpecials();
        if (spawned.isEmpty()) {
            tag.remove(INVASIONCODERED_SPAWNED_SPECIALS_TAG);
            return;
        }
        ListTag list = new ListTag();
        for (ResourceLocation id : spawned) {
            list.add(StringTag.valueOf(id.toString()));
        }
        tag.put(INVASIONCODERED_SPAWNED_SPECIALS_TAG, list);
    }

    @Inject(method = "<init>(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/nbt/CompoundTag;)V", at = @At("TAIL"), require = 1)
    private void invasioncodered$loadSpecialSpawns(ServerLevel level, CompoundTag tag, CallbackInfo ci) {
        Set<ResourceLocation> spawned = invasioncodered$getOrCreateSpawnedSpecials();
        spawned.clear();
        if (!tag.contains(INVASIONCODERED_SPAWNED_SPECIALS_TAG, Tag.TAG_LIST)) {
            return;
        }
        ListTag list = tag.getList(INVASIONCODERED_SPAWNED_SPECIALS_TAG, Tag.TAG_STRING);
        for (int i = 0; i < list.size(); i++) {
            ResourceLocation id = ResourceLocation.tryParse(list.getString(i));
            if (id != null) {
                spawned.add(id);
            }
        }
    }
}