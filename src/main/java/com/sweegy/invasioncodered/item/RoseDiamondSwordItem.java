package com.sweegy.invasioncodered.item;

import com.sweegy.invasioncodered.entity.projectile.GashSlitSlashProjectileEntity;
import com.sweegy.invasioncodered.entity.projectile.GashslitSlashBigProjectileEntity;
import com.sweegy.invasioncodered.init.CustomGlintRegistry;
import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModEntities;
import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModSounds;
import com.sweegy.invasioncodered.interfaces.ICustomGlint;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.protocol.game.ClientboundAnimatePacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

import java.util.Collection;

public class RoseDiamondSwordItem extends SwordItem implements ICustomGlint {

    private static final String NEXT_HAND_TAG = "RoseDiamondNextHand";

    private static final int SINGLE_COOLDOWN = 20;

    private static final int DUAL_COOLDOWN = 6;

    public RoseDiamondSwordItem() {
        super(new Tier() {
            @Override
            public int getUses() {
                return 7000;
            }

            @Override
            public float getSpeed() {
                return 4.0F;
            }

            @Override
            public float getAttackDamageBonus() {
                return 5.0F;
            }

            @Override
            public int getLevel() {
                return 0;
            }

            @Override
            public int getEnchantmentValue() {
                return 10;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return Ingredient.of();
            }
        }, 3, -2.4F, new Item.Properties());
    }

    @Override
    public Component getName(ItemStack stack) {
        return ((MutableComponent) super.getName(stack)).withStyle(ChatFormatting.RED);
    }

    @Override
    public ResourceLocation getCustomGlintTexture(ItemStack stack) {
        return CustomGlintRegistry.RAGE_GLINT;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand clickedHand) {
        ItemStack clickedStack = player.getItemInHand(clickedHand);

        if (canBlock(player)) {
            return InteractionResultHolder.fail(clickedStack);
        }

        boolean dualWielding = isDualWielding(player);

        if (dualWielding) {
            InteractionHand expectedHand = getNextAttackHand(player);
            if (clickedHand != expectedHand) {
                return InteractionResultHolder.pass(clickedStack);
            }
        }

        if (!level.isClientSide()) {
            level.playSound(null, player.blockPosition(), InvasioncoderedsweegyportModSounds.CURSED_SLASH_SHOOT.get(), SoundSource.PLAYERS, 1, 1);
            spawnProjectile(player, clickedStack);
        }

        if (dualWielding) {
            InteractionHand nextHand = (clickedHand == InteractionHand.MAIN_HAND) ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;

            setNextAttackHand(player, nextHand);
            player.getCooldowns().addCooldown(this, DUAL_COOLDOWN);

        } else {
            setNextAttackHand(player, InteractionHand.MAIN_HAND);
            player.getCooldowns().addCooldown(this, SINGLE_COOLDOWN);
        }

        return InteractionResultHolder.sidedSuccess(clickedStack, level.isClientSide());
    }

    public static boolean isDualWielding(Player player) {
        return player.getMainHandItem().getItem() instanceof RoseDiamondSwordItem && player.getOffhandItem().getItem() instanceof RoseDiamondSwordItem;
    }

    public static boolean canBlock(Player player) {
        return player.isShiftKeyDown() && isDualWielding(player);
    }

    public static boolean isBlocking(Player player) {
        return canBlock(player) && player.isUsingItem() && player.getUseItem().getItem() instanceof RoseDiamondSwordItem;
    }

    private static InteractionHand getNextAttackHand(Player player) {
        boolean offhand = player.getPersistentData().getBoolean(NEXT_HAND_TAG);

        return offhand ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
    }

    private static void setNextAttackHand(Player player, InteractionHand hand) {
        player.getPersistentData().putBoolean(NEXT_HAND_TAG, hand == InteractionHand.OFF_HAND);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BLOCK;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    private static void spawnProjectile(Player player, ItemStack weaponStack) {
        Level level = player.level();

        if (level.isClientSide()) {
            return;
        }

        boolean secondPhase = player.getHealth() < player.getMaxHealth() * 0.5F;

        float weaponDamage = calculateWeaponDamage(player, weaponStack);

        Projectile projectile;

        if (!secondPhase) {

            GashSlitSlashProjectileEntity slash = new GashSlitSlashProjectileEntity(InvasioncoderedsweegyportModEntities.GASH_SLIT_SLASH_PROJECTILE.get(), level);

            slash.setOwner(player);

            slash.setBaseDamage(weaponDamage * 0.8f);

            projectile = slash;

        } else {

            GashslitSlashBigProjectileEntity bigSlash = new GashslitSlashBigProjectileEntity(InvasioncoderedsweegyportModEntities.GASHSLIT_SLASH_BIG_PROJECTILE.get(), level);

            bigSlash.setOwner(player);

            bigSlash.setBaseDamage(weaponDamage * 1.6f);

            projectile = bigSlash;
        }

        projectile.setPos(player.getX(), player.getEyeY() - 0.1D, player.getZ());

        projectile.shoot(player.getLookAngle().x, player.getLookAngle().y, player.getLookAngle().z, 2.0F, 0.0F);

        level.addFreshEntity(projectile);
    }

    private static float calculateWeaponDamage(Player player, ItemStack weaponStack) {
        double damage = player.getAttributeValue(Attributes.ATTACK_DAMAGE);
        ItemStack mainHandStack = player.getMainHandItem();

        if (weaponStack != mainHandStack) {
            damage -= getItemAttackDamage(mainHandStack);
            damage += getItemAttackDamage(weaponStack);
        }

        damage += EnchantmentHelper.getDamageBonus(weaponStack, MobType.UNDEFINED);

        return (float) damage;
    }

    private static double getItemAttackDamage(ItemStack stack) {
        if (stack.isEmpty()) return 0.0;

        double bonus = 0.0;
        Collection<AttributeModifier> modifiers = stack.getAttributeModifiers(EquipmentSlot.MAINHAND).get(Attributes.ATTACK_DAMAGE);

        for (AttributeModifier modifier : modifiers) {
            if (modifier.getOperation() == AttributeModifier.Operation.ADDITION) {
                bonus += modifier.getAmount();
            }
        }
        return bonus;
    }
}