package com.sweegy.invasioncodered.init;

import com.sweegy.invasioncodered.client.renderer.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.renderer.entity.ThrownItemRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class InvasioncoderedsweegyportModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(InvasioncoderedsweegyportModEntities.GASHSLIT.get(), GashslitRenderer::new);
		event.registerEntityRenderer(InvasioncoderedsweegyportModEntities.GASH_SLIT_SLASH_PROJECTILE.get(), GashSlitSlashProjectileRenderer::new);
		event.registerEntityRenderer(InvasioncoderedsweegyportModEntities.GASHSLIT_SLASH_BIG_PROJECTILE.get(), GashslitSlashBigProjectileRenderer::new);
		event.registerEntityRenderer(InvasioncoderedsweegyportModEntities.GASHSLIT_DRAGON.get(), GashslitDragonRenderer::new);
		event.registerEntityRenderer(InvasioncoderedsweegyportModEntities.DAMAGE_CONFIGURABLE_SMALL_FIREBALL.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(InvasioncoderedsweegyportModEntities.STONE_CUTTER_MAN.get(), StoneCutterManRenderer::new);
	}
}