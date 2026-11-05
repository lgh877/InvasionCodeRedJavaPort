package com.sweegy.invasioncodered.init;

import com.sweegy.invasioncodered.entity.brawler.BrawlerEntity;
import com.sweegy.invasioncodered.entity.cuccita.CuccitaEntity;
import com.sweegy.invasioncodered.entity.stonecutterman.StoneCutterManEntity;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;

import com.sweegy.invasioncodered.entity.projectile.GashslitSlashBigProjectileEntity;
import com.sweegy.invasioncodered.entity.gashslit.GashslitEntity;
import com.sweegy.invasioncodered.entity.gashslit.GashslitDragonEntity;
import com.sweegy.invasioncodered.entity.projectile.GashSlitSlashProjectileEntity;
import com.sweegy.invasioncodered.entity.projectile.DamageConfigurableSmallFireballEntity;
import com.sweegy.invasioncodered.InvasioncoderedsweegyportMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class InvasioncoderedsweegyportModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, InvasioncoderedsweegyportMod.MODID);
	public static final RegistryObject<EntityType<GashslitEntity>> GASHSLIT = register("gashslit",
			EntityType.Builder.<GashslitEntity>of(GashslitEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(GashslitEntity::new).fireImmune()
					.sized(0.6f, 1.9f));
    public static final RegistryObject<EntityType<StoneCutterManEntity>> STONE_CUTTER_MAN = register("stone_cutter_man",
			EntityType.Builder.<StoneCutterManEntity>of(StoneCutterManEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(StoneCutterManEntity::new)
					.sized(0.7f, 1.9f));
    public static final RegistryObject<EntityType<BrawlerEntity>> BRAWLER = register("brawler",
			EntityType.Builder.<BrawlerEntity>of(BrawlerEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(BrawlerEntity::new)
					.sized(0.7f, 1.9f));
    public static final RegistryObject<EntityType<CuccitaEntity>> CUCCITA = register("cuccita",
			EntityType.Builder.<CuccitaEntity>of(CuccitaEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CuccitaEntity::new)
					.sized(0.7f, 1.9f));
	public static final RegistryObject<EntityType<GashSlitSlashProjectileEntity>> GASH_SLIT_SLASH_PROJECTILE = register("gash_slit_slash_projectile",
			EntityType.Builder.<GashSlitSlashProjectileEntity>of(GashSlitSlashProjectileEntity::new, MobCategory.MISC).setCustomClientFactory(GashSlitSlashProjectileEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
					.setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<GashslitSlashBigProjectileEntity>> GASHSLIT_SLASH_BIG_PROJECTILE = register("gashslit_slash_big_projectile",
			EntityType.Builder.<GashslitSlashBigProjectileEntity>of(GashslitSlashBigProjectileEntity::new, MobCategory.MISC).setCustomClientFactory(GashslitSlashBigProjectileEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
					.setUpdateInterval(1).sized(0.5f, 0.5f));
	public static final RegistryObject<EntityType<GashslitDragonEntity>> GASHSLIT_DRAGON = register("gashslit_dragon", EntityType.Builder.<GashslitDragonEntity>of(GashslitDragonEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true)
			.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(GashslitDragonEntity::new).fireImmune().sized(0.6f, 0.6f));
	public static final RegistryObject<EntityType<DamageConfigurableSmallFireballEntity>> DAMAGE_CONFIGURABLE_SMALL_FIREBALL = register("damage_configurable_small_fireball",
			EntityType.Builder.<DamageConfigurableSmallFireballEntity>of(DamageConfigurableSmallFireballEntity::new, MobCategory.MISC).setCustomClientFactory(DamageConfigurableSmallFireballEntity::new).setShouldReceiveVelocityUpdates(true)
					.setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));

	// Start of user code block custom entities
	// End of user code block custom entities
	private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			GashslitEntity.init();
			GashslitDragonEntity.init();
		});
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(GASHSLIT.get(), GashslitEntity.createAttributes().build());
		event.put(GASHSLIT_DRAGON.get(), GashslitDragonEntity.createAttributes().build());
		event.put(STONE_CUTTER_MAN.get(), StoneCutterManEntity.createAttributes().build());
		event.put(BRAWLER.get(), BrawlerEntity.createAttributes().build());
	}
}