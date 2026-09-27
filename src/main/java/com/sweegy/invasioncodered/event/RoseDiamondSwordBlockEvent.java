package com.sweegy.invasioncodered.event;

import com.sweegy.invasioncodered.item.RoseDiamondSwordItem;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@Mod.EventBusSubscriber
public class RoseDiamondSwordBlockEvent {
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        Player player = event.player;

        boolean canBlock = RoseDiamondSwordItem.canBlock(player);
        boolean isUsingCustomSword = player.isUsingItem() && player.getUseItem().getItem() instanceof RoseDiamondSwordItem;

        if (canBlock && !isUsingCustomSword) {
            player.startUsingItem(InteractionHand.MAIN_HAND);
        } else if (!canBlock && isUsingCustomSword) {
            player.releaseUsingItem();
        }
    }

    @SubscribeEvent
    public static void onAttackEntity(AttackEntityEvent event) {
        if (RoseDiamondSwordItem.isBlocking(event.getEntity())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        if (RoseDiamondSwordItem.isBlocking(event.getEntity())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (RoseDiamondSwordItem.isBlocking(event.getEntity())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        if (RoseDiamondSwordItem.isBlocking(event.getEntity())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        if (RoseDiamondSwordItem.isBlocking(event.getEntity())) {
            event.setCanceled(true);
        }
    }
}