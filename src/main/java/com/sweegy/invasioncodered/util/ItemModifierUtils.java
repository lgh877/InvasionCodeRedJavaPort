package com.sweegy.invasioncodered.util;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

import java.util.EnumMap;
import java.util.Map;
import java.util.UUID;

public final class ItemModifierUtils {
    public static final UUID BASE_ATTACK_DAMAGE_UUID = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
    public static final UUID BASE_ATTACK_SPEED_UUID = UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3");

    public static final UUID MAINHAND_SLOT_UUID = BASE_ATTACK_DAMAGE_UUID;
    public static final UUID OFFHAND_SLOT_UUID = UUID.fromString("5B6201B7-99E7-4560-848D-ED11D155462D");

    public static final UUID ARMOR_HEAD_UUID = UUID.fromString("2AD3F240-F726-461D-B841-E1F75E9F4E76");
    public static final UUID ARMOR_CHEST_UUID = UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E");
    public static final UUID ARMOR_LEGS_UUID = UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D");
    public static final UUID ARMOR_FEET_UUID = UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B");

    public static final UUID[] ARMOR_MODIFIER_UUID_PER_SLOT = new UUID[]{
            ARMOR_FEET_UUID,
            ARMOR_LEGS_UUID,
            ARMOR_CHEST_UUID,
            ARMOR_HEAD_UUID
    };

    public static final UUID ELYTRA_CHEST_UUID = UUID.fromString("65E26E55-257E-4AA0-80E1-0F22A3705C55");
    public static final UUID HORSE_ARMOR_UUID = UUID.fromString("556E1665-8B10-40C8-8F9D-CF9B1667F295");

    public static final UUID SPEED_MODIFIER_UUID = UUID.fromString("91AEAA56-376B-4498-935B-2F7F68070635");
    public static final UUID SLOWNESS_MODIFIER_UUID = UUID.fromString("7107DE5E-7CE8-4030-940E-514C1F160890");
    public static final UUID STRENGTH_MODIFIER_UUID = UUID.fromString("648D7064-6A60-4F59-8ABE-C2C23A6DD7A9");
    public static final UUID WEAKNESS_MODIFIER_UUID = UUID.fromString("22653B89-116E-49DC-9B6B-9971489B5BE5");
    public static final UUID HASTE_MODIFIER_UUID = UUID.fromString("AF8B6E3F-3328-4C0A-AA36-5BA2BB9DBEF3");
    public static final UUID MINING_FATIGUE_MODIFIER_UUID = UUID.fromString("55FCED67-E92A-486E-9800-B47F20AC4363");
    public static final UUID SPRINTING_SPEED_MODIFIER_UUID = UUID.fromString("662AE53D-DCB5-4C61-BFDC-BE5E0FF5CE01");
    public static final UUID SLOW_FALLING_SPEED_MODIFIER_UUID = UUID.fromString("A5B6CF2A-2F7C-31EF-9022-7C3E7D5E6ABA");

    public static final Map<EquipmentSlot, UUID> SLOT_UUID_MAP = new EnumMap<>(EquipmentSlot.class);

    static {
        SLOT_UUID_MAP.put(EquipmentSlot.FEET, ARMOR_FEET_UUID);
        SLOT_UUID_MAP.put(EquipmentSlot.LEGS, ARMOR_LEGS_UUID);
        SLOT_UUID_MAP.put(EquipmentSlot.CHEST, ARMOR_CHEST_UUID);
        SLOT_UUID_MAP.put(EquipmentSlot.HEAD, ARMOR_HEAD_UUID);
        SLOT_UUID_MAP.put(EquipmentSlot.MAINHAND, MAINHAND_SLOT_UUID);
        SLOT_UUID_MAP.put(EquipmentSlot.OFFHAND, OFFHAND_SLOT_UUID);
    }

    public static UUID getUuidForSlot(EquipmentSlot slot) {
        return SLOT_UUID_MAP.getOrDefault(slot, MAINHAND_SLOT_UUID);
    }

    public static UUID getSubModifierUuid(EquipmentSlot slot, int subIndex) {
        UUID base = getUuidForSlot(slot);
        return new UUID(base.getMostSignificantBits(), base.getLeastSignificantBits() + subIndex);
    }

    public static Multimap<Attribute, AttributeModifier> appendModifier(
            Multimap<Attribute, AttributeModifier> baseModifiers,
            Attribute attribute,
            UUID uuid,
            String name,
            double amount,
            AttributeModifier.Operation operation
    ) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.putAll(baseModifiers);
        builder.put(attribute, new AttributeModifier(uuid, name, amount, operation));
        return builder.build();
    }
}
