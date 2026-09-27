package com.sweegy.invasioncodered.raid;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.mojang.logging.LogUtils;
import com.sweegy.invasioncodered.InvasioncoderedsweegyportMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(modid = InvasioncoderedsweegyportMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SpecialRaiderManager extends SimpleJsonResourceReloadListener {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final Gson GSON = new GsonBuilder().create();
    public static final SpecialRaiderManager INSTANCE = new SpecialRaiderManager();
    private volatile List<SpecialRaiderData> raiderData = List.of();

    private SpecialRaiderManager() {
        super(GSON, "special_raiders");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> objects, ResourceManager resourceManager, ProfilerFiller profiler) {
        List<SpecialRaiderData> loaded = new ArrayList<>();
        objects.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.naturalOrder())).forEach(entry -> {
            ResourceLocation id = entry.getKey();
            try {
                SpecialRaiderData data = GSON.fromJson(entry.getValue(), SpecialRaiderData.class);

                if (data == null) {
                    throw new JsonParseException("JSON produced null data");
                }

                data.id = id;
                validate(data);
                loaded.add(data);
            } catch (Exception e) {
                LOGGER.error("Failed to load special raider definition {}", id, e);
            }
        });

        this.raiderData = List.copyOf(loaded);
        InvasioncoderedsweegyportMod.LOGGER.info("Generated new raidrs");
        LOGGER.info("Loaded {} special raider definitions", this.raiderData.size());
    }

    public List<SpecialRaiderData> getAllRaiders() {
        return this.raiderData;
    }

    private static void validate(SpecialRaiderData data) {
        if (data.id == null) {
            throw new JsonParseException("Special raider definition has no id");
        }

        validateMultiplier(data.id, "difficulty_multiplier", data.difficultyMultiplier);
        validateMultiplier(data.id, "wave_multiplier", data.waveMultiplier);
        validateMultiplier(data.id, "level_multiplier", data.levelMultiplier);

        if (data.pools == null || data.pools.isEmpty()) {
            throw new JsonParseException(data.id + ": pools must not be empty");
        }

        for (int poolIndex = 0; poolIndex < data.pools.size(); poolIndex++) {
            SpecialRaiderData.RaiderPool pool = data.pools.get(poolIndex);
            if (pool == null) {
                throw new JsonParseException(data.id + ": pools[" + poolIndex + "] is null");
            }
            if (pool.rolls < 0) {
                throw new JsonParseException(data.id + ": pools[" + poolIndex + "].rolls must be >= 0");
            }
            if (pool.rolls == 0) {
                continue;
            }
            if (pool.entries == null || pool.entries.isEmpty()) {
                throw new JsonParseException(data.id + ": pools[" + poolIndex + "].entries must not be empty");
            }

            long totalWeight = 0L;

            for (int entryIndex = 0; entryIndex < pool.entries.size(); entryIndex++) {
                SpecialRaiderData.RaiderEntry entry = pool.entries.get(entryIndex);
                validateEntry(data.id, poolIndex, entryIndex, entry);
                totalWeight += entry.weight;

                if (totalWeight > Integer.MAX_VALUE) {
                    throw new JsonParseException(data.id + ": pools[" + poolIndex + "] total weight exceeds Integer.MAX_VALUE");
                }
            }
        }
    }

    private static void validateMultiplier(ResourceLocation id, String name, List<Float> values) {
        if (values == null || values.isEmpty()) {
            throw new JsonParseException(id + ": " + name + " must not be empty");
        }

        for (int i = 0; i < values.size(); i++) {
            Float value = values.get(i);
            if (value == null || !Float.isFinite(value) || value < 0.0F) {
                throw new JsonParseException(id + ": invalid " + name + "[" + i + "] = " + value);
            }
        }
    }

    private static void validateEntry(ResourceLocation dataId, int poolIndex, int entryIndex, SpecialRaiderData.RaiderEntry entry) {
        String prefix = dataId + ": pools[" + poolIndex + "].entries[" + entryIndex + "]";
        if (entry == null) {
            throw new JsonParseException(prefix + " is null");
        }
        ResourceLocation entityId = entry.type == null ? null : ResourceLocation.tryParse(entry.type);
        if (entityId == null) {
            throw new JsonParseException(prefix + ": invalid entity type '" + entry.type + "'");
        }
        EntityType<?> entityType = ForgeRegistries.ENTITY_TYPES.getValue(entityId);
        if (entityType == null) {
            throw new JsonParseException(prefix + ": unknown entity type " + entityId);
        }
        entry.parsedType = entityId;
        if (entry.weight <= 0) {
            throw new JsonParseException(prefix + ": weight must be > 0");
        }
        if (entry.count == null) {
            throw new JsonParseException(prefix + ": count is missing");
        }
        if (entry.count.min < 0) {
            throw new JsonParseException(prefix + ": count.min must be >= 0");
        }
        if (entry.count.max < entry.count.min) {
            throw new JsonParseException(prefix + ": count.max must be >= count.min");
        }
        long range = (long) entry.count.max - entry.count.min + 1L;
        if (range > Integer.MAX_VALUE) {
            throw new JsonParseException(prefix + ": count range is too large");
        }
        if (entry.executedCommands != null) {
            for (int i = 0; i < entry.executedCommands.size(); i++) {
                if (entry.executedCommands.get(i) == null) {
                    throw new JsonParseException(prefix + ": executed_commands[" + i + "] is null");
                }
            }
        }
    }

    @SubscribeEvent
    public static void onAddReloadListeners(AddReloadListenerEvent event) {
        event.addListener(INSTANCE);
    }
}