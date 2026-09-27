package com.sweegy.invasioncodered.raid;

import com.mojang.logging.LogUtils;
import com.sweegy.invasioncodered.interfaces.IRaidSpecialSpawns;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.entity.raid.Raider;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

import java.util.List;

public final class SpecialRaiderSpawner {

    private static final Logger LOGGER = LogUtils.getLogger();

    private SpecialRaiderSpawner() {
    }

    public static void spawnSpecialRaiders(Raid raid, BlockPos spawnPos) {
        if (!(raid.getLevel() instanceof ServerLevel level)) {
            return;
        }
        if (level.getDifficulty() == Difficulty.PEACEFUL) {
            return;
        }

        int currentWave = raid.getGroupsSpawned();

        if (currentWave <= 0) {
            return;
        }

        RandomSource random = level.random;
        IRaidSpecialSpawns specialSpawns = (IRaidSpecialSpawns) raid;

        for (SpecialRaiderData data : SpecialRaiderManager.INSTANCE.getAllRaiders()) {
            if (data == null || data.id == null) {
                continue;
            }
            if (data.shouldAppearOnlyOnce && specialSpawns.invasioncodered$hasSpawnedSpecial(data.id)) {
                continue;
            }

            int difficultyIndex = level.getDifficulty().getId() - 1;
            int waveIndex = currentWave - 1;
            int omenIndex = raid.getBadOmenLevel() - 1;
            float difficultyMultiplier = getMultiplier(data.difficultyMultiplier, difficultyIndex);
            float waveMultiplier = getMultiplier(data.waveMultiplier, waveIndex);
            float levelMultiplier = getMultiplier(data.levelMultiplier, omenIndex);
            double rawChance = (double) difficultyMultiplier * (double) waveMultiplier * (double) levelMultiplier;

            if (!Double.isFinite(rawChance) || rawChance <= 0.0D) {
                continue;
            }

            float chance = (float) Math.min(rawChance, 1.0D);

            if (random.nextFloat() >= chance) {
                continue;
            }

            boolean spawned = spawnFromPools(data, raid, currentWave, spawnPos, level, random);

            if (spawned && data.shouldAppearOnlyOnce) {
                specialSpawns.invasioncodered$addSpawnedSpecial(data.id);
            }
        }
    }

    private static boolean spawnFromPools(SpecialRaiderData data, Raid raid, int wave, BlockPos spawnPos, ServerLevel level, RandomSource random) {
        if (data.pools == null || data.pools.isEmpty()) {
            return false;
        }

        boolean spawnedAny = false;

        for (SpecialRaiderData.RaiderPool pool : data.pools) {
            if (pool == null || pool.rolls <= 0 || pool.entries == null || pool.entries.isEmpty()) {
                continue;
            }
            for (int roll = 0; roll < pool.rolls; roll++) {
                SpecialRaiderData.RaiderEntry entry = getRandomEntryByWeight(pool.entries, random);
                if (entry == null) {
                    continue;
                }

                int count = getRandomCount(entry.count, random);

                if (count <= 0) {
                    continue;
                }

                for (int i = 0; i < count; i++) {
                    if (spawnEntry(data, entry, raid, wave, spawnPos, level)) {
                        spawnedAny = true;
                    }
                }
            }
        }

        return spawnedAny;
    }

    private static boolean spawnEntry(SpecialRaiderData data, SpecialRaiderData.RaiderEntry entry, Raid raid, int wave, BlockPos spawnPos, ServerLevel level) {
        ResourceLocation entityId = entry.parsedType;

        if (entityId == null && entry.type != null) {
            entityId = ResourceLocation.tryParse(entry.type);
        }

        if (entityId == null) {
            return false;
        }

        EntityType<?> entityType = ForgeRegistries.ENTITY_TYPES.getValue(entityId);

        if (entityType == null) {
            LOGGER.warn("Special raider definition {} references missing entity type {}", data.id, entityId);
            return false;
        }

        Entity entity = entityType.create(level);

//        if (!(entity instanceof Raider raider)) {
//            LOGGER.warn("Special raider definition {} references entity {} which does not extend Raider", data.id, entityId);
//
//            if (entity != null) {
//                entity.discard();
//            }
//
//            return false;
//        }
        if(entity instanceof Raider raider) {
            raid.joinRaid(wave, raider, spawnPos, false);
        }

        executeCommands(entry.executedCommands, entity, level);
        return true;
    }

    private static void executeCommands(List<String> commands, Entity raider, ServerLevel level) {
        if (commands == null || commands.isEmpty()) {
            return;
        }

        CommandSourceStack source = level.getServer().createCommandSourceStack().withLevel(level).withPosition(raider.position()).withEntity(raider).withSuppressedOutput();

        for (String command : commands) {
            if (command == null) {
                continue;
            }

            String normalized = command.trim();

            if (normalized.isEmpty()) {
                continue;
            }
            if (normalized.startsWith("/")) {
                normalized = normalized.substring(1);
            }
            if (normalized.isEmpty()) {
                continue;
            }

            try {
                level.getServer().getCommands().performPrefixedCommand(source, normalized);

            } catch (RuntimeException e) {
                LOGGER.error("Failed to execute command '{}' for special raider {}", normalized, raider.getUUID(), e);
            }
        }
    }

    private static int getRandomCount(SpecialRaiderData.CountRange range, RandomSource random) {
        if (range == null || range.min < 0 || range.max < range.min) {
            return 0;
        }

        long bound = (long) range.max - (long) range.min + 1L;

        if (bound <= 0L || bound > Integer.MAX_VALUE) {
            return 0;
        }

        return range.min + random.nextInt((int) bound);
    }

    private static float getMultiplier(List<Float> list, int index) {
        if (list == null || list.isEmpty() || index < 0) {
            return 0.0F;
        }

        int safeIndex = Math.min(index, list.size() - 1);

        Float value = list.get(safeIndex);

        if (value == null || !Float.isFinite(value) || value < 0.0F) {
            return 0.0F;
        }

        return value;
    }

    private static SpecialRaiderData.RaiderEntry getRandomEntryByWeight(List<SpecialRaiderData.RaiderEntry> entries, RandomSource random) {
        if (entries == null || entries.isEmpty()) {
            return null;
        }

        long totalWeight = 0L;

        for (SpecialRaiderData.RaiderEntry entry : entries) {
            if (entry == null || entry.weight <= 0) {
                continue;
            }

            totalWeight += entry.weight;

            if (totalWeight > Integer.MAX_VALUE) {
                return null;
            }
        }
        if (totalWeight <= 0L) {
            return null;
        }

        int randomWeight = random.nextInt((int) totalWeight);

        for (SpecialRaiderData.RaiderEntry entry : entries) {
            if (entry == null || entry.weight <= 0) {
                continue;
            }

            randomWeight -= entry.weight;

            if (randomWeight < 0) {
                return entry;
            }
        }
        return null;
    }
}