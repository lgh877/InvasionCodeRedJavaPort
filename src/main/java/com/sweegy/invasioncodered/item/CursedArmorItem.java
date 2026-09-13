package com.sweegy.invasioncodered.item;

import com.google.common.collect.Multimap;
import com.sweegy.invasioncodered.init.CustomGlintRegistry;
import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModAttributes;
import com.sweegy.invasioncodered.interfaces.ICustomGlint;
import com.sweegy.invasioncodered.util.ItemModifierUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.Minecraft;

import java.util.function.Consumer;
import java.util.Map;
import java.util.Collections;

import com.sweegy.invasioncodered.init.InvasioncoderedsweegyportModItems;
import com.sweegy.invasioncodered.client.model.Modelgashslit_head_armor;
import com.sweegy.invasioncodered.client.model.Modelgashslit_chestplate;
import com.sweegy.invasioncodered.client.model.Modelgashslit_boots;
import com.sweegy.invasioncodered.client.model.GashslitLeggingsAnimatedModel;

public abstract class CursedArmorItem extends ArmorItem implements ICustomGlint {
	public CursedArmorItem(ArmorItem.Type type, Item.Properties properties) {
		super(new ArmorMaterial() {
			@Override
			public int getDurabilityForType(ArmorItem.Type type) {
				return new int[]{13, 15, 16, 11}[type.getSlot().getIndex()] * 37;
			}

			@Override
			public int getDefenseForType(ArmorItem.Type type) {
				return new int[]{3, 6, 8, 3}[type.getSlot().getIndex()];
			}

			@Override
			public int getEnchantmentValue() {
				return 9;
			}

			@Override
			public SoundEvent getEquipSound() {
				return ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.tryParse("invasioncodered:gash_hurt"));
			}

			@Override
			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(InvasioncoderedsweegyportModItems.GASH_INGOT.get()));
			}

			@Override
			public String getName() {
				return "cursed_armor";
			}

			@Override
			public float getToughness() {
				return 3f;
			}

			@Override
			public float getKnockbackResistance() {
				return 0.1f;
			}
		}, type, properties);
	}

    @Override
    public ResourceLocation getCustomGlintTexture(ItemStack stack) {
        return CustomGlintRegistry.RAGE_GLINT;
    }

    @Override
    public Component getName(ItemStack stack) {
        return ((MutableComponent) super.getName(stack)).withStyle(ChatFormatting.RED);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        Multimap<Attribute, AttributeModifier> defaultModifiers = super.getDefaultAttributeModifiers(slot);

        if (slot == this.type.getSlot()) {
            return ItemModifierUtils.appendModifier(
                    defaultModifiers,
                    InvasioncoderedsweegyportModAttributes.GASHSLIT_EQUITMENT_STATE_CHECKER.get(),
                    ItemModifierUtils.getUuidForSlot(slot),
                    "Cursed armor state checker",
                    1.0D,
                    AttributeModifier.Operation.ADDITION
            );
        }

        return defaultModifiers;
    }

	public static class Helmet extends CursedArmorItem {
		public Helmet() {
			super(ArmorItem.Type.HELMET, new Item.Properties().fireResistant());
		}

		@Override
		public void initializeClient(Consumer<IClientItemExtensions> consumer) {
			consumer.accept(new IClientItemExtensions() {
				private HumanoidModel armorModel = null;

				@Override
				@OnlyIn(Dist.CLIENT)
				public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
					if (armorModel == null) {
						armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(),
								Map.of("head", new Modelgashslit_head_armor(Minecraft.getInstance().getEntityModels().bakeLayer(Modelgashslit_head_armor.LAYER_LOCATION)).head, "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
										"body", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_arm",
										new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg",
										new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
					}
					armorModel.crouching = living.isShiftKeyDown();
					armorModel.riding = defaultModel.riding;
					armorModel.young = living.isBaby();
					return armorModel;
				}
			});
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "invasioncodered:textures/entities/gashslit_helmet_texture.png";
		}
	}

	public static class Chestplate extends CursedArmorItem {
		public Chestplate() {
			super(ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant());
		}

		@Override
		public void initializeClient(Consumer<IClientItemExtensions> consumer) {
			consumer.accept(new IClientItemExtensions() {
				private HumanoidModel armorModel = null;

				@Override
				@OnlyIn(Dist.CLIENT)
				public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
					if (armorModel == null) {
						Modelgashslit_chestplate model = new Modelgashslit_chestplate(Minecraft.getInstance().getEntityModels().bakeLayer(Modelgashslit_chestplate.LAYER_LOCATION));
						armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(),
								Map.of("body", model.Body, "left_arm", model.LeftArm, "right_arm", model.RightArm, "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat",
										new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_leg", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg",
										new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
					}
					armorModel.crouching = living.isShiftKeyDown();
					armorModel.riding = defaultModel.riding;
					armorModel.young = living.isBaby();
					return armorModel;
				}
			});
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "invasioncodered:textures/entities/gashslit_armor.png";
		}
	}

	public static class Leggings extends CursedArmorItem {
		public Leggings() {
			super(ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant());
		}

		@Override
		public void initializeClient(Consumer<IClientItemExtensions> consumer) {
			consumer.accept(new IClientItemExtensions() {
				private GashslitLeggingsAnimatedModel<LivingEntity> armorModel = null;

				@Override
				@OnlyIn(Dist.CLIENT)
				public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
					if (armorModel == null) {
						armorModel = new GashslitLeggingsAnimatedModel<>(//
								Minecraft.getInstance().getEntityModels().bakeLayer(GashslitLeggingsAnimatedModel.LAYER_LOCATION)//
						);
					}
					defaultModel.copyPropertiesTo((HumanoidModel) armorModel);
					float partialTicks = Minecraft.getInstance().getFrameTime();
					armorModel.setupCustomAnim(living, partialTicks);
					return armorModel;
				}
			});
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "invasioncodered:textures/entities/gashslit_armor.png";
		}
	}

	public static class Boots extends CursedArmorItem {
		public Boots() {
			super(ArmorItem.Type.BOOTS, new Item.Properties().fireResistant());
		}

		@Override
		public void initializeClient(Consumer<IClientItemExtensions> consumer) {
			consumer.accept(new IClientItemExtensions() {
				private HumanoidModel armorModel = null;

				@Override
				@OnlyIn(Dist.CLIENT)
				public HumanoidModel getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
					if (armorModel == null) {
						Modelgashslit_boots model = new Modelgashslit_boots(Minecraft.getInstance().getEntityModels().bakeLayer(Modelgashslit_boots.LAYER_LOCATION));
						armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(),
								Map.of("left_leg", model.LeftLeg, "right_leg", model.RightLeg, "head", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body",
										new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_arm",
										new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
					}
					armorModel.crouching = living.isShiftKeyDown();
					armorModel.riding = defaultModel.riding;
					armorModel.young = living.isBaby();
					return armorModel;
				}
			});
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "invasioncodered:textures/entities/gashslit_armor.png";
		}
	}
}