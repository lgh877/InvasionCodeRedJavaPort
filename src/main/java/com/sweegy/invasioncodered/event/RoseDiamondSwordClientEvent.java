package com.sweegy.invasioncodered.event;

import com.sweegy.invasioncodered.item.RoseDiamondSwordItem;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class RoseDiamondSwordClientEvent {

    private static boolean wasBlocking = false;

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;

        if (player == null) return;

        boolean canBlock = RoseDiamondSwordItem.canBlock(player);

        if (canBlock) {
            wasBlocking = true;
            mc.options.keyUse.setDown(true);
        } else if (wasBlocking) {
            wasBlocking = false;
            mc.options.keyUse.setDown(false);
        }
    }

    @SubscribeEvent
    public static void onInteractionKey(InputEvent.InteractionKeyMappingTriggered event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        if (RoseDiamondSwordItem.isBlocking(mc.player)) {
            if (event.isAttack()) {
                event.setSwingHand(false);
                event.setCanceled(true);
            }
        }
    }
}