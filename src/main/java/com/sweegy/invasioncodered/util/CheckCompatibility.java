/**
 * The code of this mod element is always locked.
 *
 * You can register new events in this class too.
 *
 * If you want to make a plain independent class, create it using
 * Project Browser -> New... and make sure to make the class
 * outside com.sweegy.invasioncoderedjavaport as this package is managed by MCreator.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
 *
 * This class will be added in the mod root package.
*/
package com.sweegy.invasioncodered.util;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@Mod.EventBusSubscriber(modid = "invasioncoderedsweegyport", bus = Mod.EventBusSubscriber.Bus.MOD)
public class CheckCompatibility {
	public static boolean IS_EPIC_FIGHT_LOADED = false;

	//@Mixin(FishingHook.class)
	//public abstract class FishingHookMixin {
	//	@Inject(//
	//			method = {"pullEntity"}, //
	//			at = {@At("HEAD")}, //
	//			cancellable = true//
	//	)
	//	private void reversePull(Entity hookedEntity, CallbackInfo ci) {
	//		if (hookedEntity instanceof GashslitEntity) {
	//			Projectile hook = (Projectile) (Object) this;
	//			Entity owner = hook.getOwner();
	//			if (owner != null) {
	//				Vec3 reverseVec = (new Vec3(//
	//						hook.getX() - owner.getX(), //
	//						hook.getY() - owner.getY(), //
	//						hook.getZ() - owner.getZ()//
	//				)).scale(0.1D);
	//				owner.setDeltaMovement(owner.getDeltaMovement().add(reverseVec));
	//				owner.hurtMarked = true;
	//			}
	//			ci.cancel();
	//		}
	//	}
	//}
	@SubscribeEvent
	public static void onCommonSetup(FMLCommonSetupEvent event) {
		IS_EPIC_FIGHT_LOADED = ModList.get().isLoaded("epicfight");
	}
}