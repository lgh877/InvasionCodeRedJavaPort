package com.sweegy.invasioncodered.raid;

import com.google.gson.annotations.SerializedName;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class SpecialRaiderData {
    public transient ResourceLocation id;

    @SerializedName("difficulty_multiplier")
    public List<Float> difficultyMultiplier;

    @SerializedName("wave_multiplier")
    public List<Float> waveMultiplier;

    @SerializedName("level_multiplier")
    public List<Float> levelMultiplier;

    @SerializedName("should_appear_only_once")
    public boolean shouldAppearOnlyOnce;

    public List<RaiderPool> pools;

    public static class RaiderPool {
        public int rolls;
        public List<RaiderEntry> entries;
    }

    public static class RaiderEntry {
        public String type;
        public transient ResourceLocation parsedType;
        public int weight;

        @SerializedName("executed_commands")
        public List<String> executedCommands;

        public CountRange count;
    }

    public static class CountRange {
        public int min;
        public int max;
    }
}