package com.sweegy.invasioncodered.config;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public class InvasionCodeRedConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.ConfigValue<Double> GASHSLIT_HEALTH_MULTIPLIER;
    public static final ForgeConfigSpec.ConfigValue<Double> GASHSLIT_DAMAGE_MULTIPLIER;
    public static final ForgeConfigSpec.ConfigValue<Float> GASHSLIT_ANIM_FRAME;
    public static final ForgeConfigSpec.ConfigValue<Double> CURSED_DRAGON_HEALTH_MULTIPLIER;
    public static final ForgeConfigSpec.ConfigValue<Double> CURSED_DRAGON_DAMAGE_MULTIPLIER;
    public static final ForgeConfigSpec.ConfigValue<Double> CURSED_DRAGON_KNOCKBACK_MULTIPLIER;
    public static final ForgeConfigSpec.ConfigValue<Integer> CURSED_DRAGON_SHOOT_FREQUENCY;
    public static final ForgeConfigSpec.ConfigValue<Double> CURSED_DRAGON_SHOOT_CHANCE;
    public static final ForgeConfigSpec.ConfigValue<Integer> CURSED_DRAGON_LIFE_SPAN;
    public static final ForgeConfigSpec.ConfigValue<Double> STONE_CUTTER_MAN_MELEE_COMBO_CHANCE;
    public static final ForgeConfigSpec.ConfigValue<Integer> STONE_CUTTER_MAN_KNOCK_CHANCE;
    public static final ForgeConfigSpec.ConfigValue<Integer> STONE_CUTTER_MAN_KNOCK_DOWN_CHANCE;
    public static final ForgeConfigSpec.ConfigValue<Double> STONE_CUTTER_MAN_HEALTH_MULTIPLIER;
    public static final ForgeConfigSpec.ConfigValue<Double> STONE_CUTTER_MAN_DAMAGE_MULTIPLIER;
    public static final ForgeConfigSpec.ConfigValue<Float> STONE_CUTTER_MAN_ANIM_FRAME;

    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> CUSTOM_RAIDER_LIST;

    static {
        BUILDER.push("Gashslit Details");
        GASHSLIT_HEALTH_MULTIPLIER = BUILDER.comment("Multiplies health of Gashslit").define("Health Multiplier", (double) 1);
        GASHSLIT_DAMAGE_MULTIPLIER = BUILDER.comment("Multiplies damage of Gashslit").define("Damage Multiplier", (double) 1);
        GASHSLIT_ANIM_FRAME = BUILDER.comment("Sets the max framerate of Gashslit's animation\nGood for FPS optimization or funny look if you set the frame low").define("Framerate controller", 60f);
        BUILDER.pop();

        BUILDER.push("Cursed Dragon Details");
        CURSED_DRAGON_HEALTH_MULTIPLIER = BUILDER.comment("Multiplies health of Cursed Dragon").define("Health Multiplier", (double) 1);
        CURSED_DRAGON_DAMAGE_MULTIPLIER = BUILDER.comment("Multiplies damage of Cursed Dragon").define("Damage Multiplier", (double) 1);
        CURSED_DRAGON_KNOCKBACK_MULTIPLIER = BUILDER.comment("Multiplies knockback power of Cursed Dragon\nNegative values are allowed too, but that will make the fireball pull the victim").define("Knockback Multiplier", (double) 1);
        CURSED_DRAGON_SHOOT_FREQUENCY = BUILDER.comment("Sets fireball shooting frequency of Cursed Dragon").defineInRange("Shooting Frequency", 5, 0, 31);
        CURSED_DRAGON_SHOOT_CHANCE = BUILDER.comment("Sets fireball shooting chance per attempt of Cursed Dragon").define("Shooting Chance", 0.4);
        CURSED_DRAGON_LIFE_SPAN = BUILDER.comment("Sets life span of Cursed Dragon").defineInRange("Life Span", 200, 1, Integer.MAX_VALUE);
        BUILDER.pop();

        BUILDER.push("StoneCutterMan Details");
        STONE_CUTTER_MAN_HEALTH_MULTIPLIER = BUILDER.comment("Multiplies health of StoneCutterMan").define("Health Multiplier", (double) 1);
        STONE_CUTTER_MAN_DAMAGE_MULTIPLIER = BUILDER.comment("Multiplies damage of StoneCutterMan").define("Damage Multiplier", (double) 1);
        STONE_CUTTER_MAN_ANIM_FRAME = BUILDER.comment("Sets the max framerate of StoneCutterMan's animation\nGood for FPS optimization or funny look if you set the frame low").define("Framerate controller", 60f);
        STONE_CUTTER_MAN_MELEE_COMBO_CHANCE = BUILDER.comment("Sets StoneCutterMan's chance to use melee attack in row").define("Melee Combo Chance", 0.3);
        STONE_CUTTER_MAN_KNOCK_CHANCE = BUILDER.comment("Sets StoneCutterMan's chance to be knocked when attacked. Cacluated with knock down chance is added.").define("Knock Chance", 5);
        STONE_CUTTER_MAN_KNOCK_DOWN_CHANCE = BUILDER.comment("Sets StoneCutterMan's chance to be knocked down when attacked. Cacluated with knock chance is added.").define("Knock Down Chance", 1);
        BUILDER.pop();

        BUILDER.push("Custom Raiders");
        CUSTOM_RAIDER_LIST = BUILDER.comment(
                "Used for registering ICR raiders, but you can register your own if you want.",
                "Form : 'mob registry name|wave1,wave2,wave3,wave4,wave5,wave6,wave7,wave8'",
                "Example : 'invasioncodered:gashslit|0,0,1,2,2,3,3,4'"
        ).defineList(
                "Custom Raider List",
                List.of(
                        "invasioncodered:gashslit|0,0,0,0,0,0,0,1"
                ),
                obj -> obj instanceof String && ((String) obj).contains("|")
        );
        BUILDER.pop();

        SPEC = BUILDER.build();
    }
}