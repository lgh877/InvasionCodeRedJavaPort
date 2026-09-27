package com.sweegy.invasioncodered.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.sweegy.invasioncodered.client.renderer.CustomRenderTypes;
import com.sweegy.invasioncodered.interfaces.ICustomGlint;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.client.resources.model.BakedModel;
import com.mojang.blaze3d.vertex.VertexMultiConsumer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    @Inject(
            method = "render(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemDisplayContext;ZLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;IILnet/minecraft/client/resources/model/BakedModel;)V",
            at = @At("HEAD")
    )
    private void captureStack(ItemStack stack, ItemDisplayContext ctx, boolean leftHanded,
                              PoseStack pose, MultiBufferSource buffers, int light, int overlay,
                              BakedModel model, CallbackInfo ci) {
        ICustomGlint.CURRENT_ITEM.set(stack);
    }

    @Inject(
            method = "render(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemDisplayContext;ZLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;IILnet/minecraft/client/resources/model/BakedModel;)V",
            at = @At("RETURN")
    )
    private void clearStack(ItemStack stack, ItemDisplayContext ctx, boolean leftHanded,
                            PoseStack pose, MultiBufferSource buffers, int light, int overlay,
                            BakedModel model, CallbackInfo ci) {
        ICustomGlint.CURRENT_ITEM.remove();
    }

    @Inject(
            method = "getFoilBuffer(Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/client/renderer/RenderType;ZZ)Lcom/mojang/blaze3d/vertex/VertexConsumer;",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void swapFoil(
            MultiBufferSource buffers,
            RenderType renderType,
            boolean isItem,
            boolean hasFoil,
            CallbackInfoReturnable<VertexConsumer> cir) {

        if (!hasFoil) {
            return;
        }

        ItemStack stack = ICustomGlint.CURRENT_ITEM.get();

        if (stack == null || stack.isEmpty()) {
            return;
        }

        if (!(stack.getItem() instanceof ICustomGlint customGlintItem)) {
            return;
        }

        if (!customGlintItem.hasCustomGlint(stack)) {
            return;
        }

        ResourceLocation texture =
                customGlintItem.getCustomGlintTexture(stack);

        if (texture == null) {
            return;
        }

        RenderType customGlintRenderType;

        if (isItem) {
            customGlintRenderType =
                    CustomRenderTypes.getCustomGlint(texture);
        } else {
            customGlintRenderType =
                    CustomRenderTypes.getCustomEntityGlint(texture);
        }

        cir.setReturnValue(
                VertexMultiConsumer.create(
                        buffers.getBuffer(customGlintRenderType),
                        buffers.getBuffer(renderType)
                )
        );
    }

    @Inject(
            method = "getFoilBufferDirect(Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/client/renderer/RenderType;ZZ)Lcom/mojang/blaze3d/vertex/VertexConsumer;",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void swapFoilDirect(
            MultiBufferSource buffers,
            RenderType renderType,
            boolean isItem,
            boolean hasFoil,
            CallbackInfoReturnable<VertexConsumer> cir) {

        if (!hasFoil) {
            return;
        }

        ItemStack stack = ICustomGlint.CURRENT_ITEM.get();

        if (stack == null || stack.isEmpty()) {
            return;
        }

        if (!(stack.getItem() instanceof ICustomGlint customGlintItem)) {
            return;
        }

        if (!customGlintItem.hasCustomGlint(stack)) {
            return;
        }

        ResourceLocation texture =
                customGlintItem.getCustomGlintTexture(stack);

        if (texture == null) {
            return;
        }

        RenderType customGlintRenderType =
                isItem
                        ? CustomRenderTypes.getCustomGlintDirect(texture)
                        : CustomRenderTypes.getCustomEntityGlintDirect(texture);

        cir.setReturnValue(
                VertexMultiConsumer.create(
                        buffers.getBuffer(customGlintRenderType),
                        buffers.getBuffer(renderType)
                )
        );
    }

    @Inject(
            method = "getArmorFoilBuffer(Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/client/renderer/RenderType;ZZ)Lcom/mojang/blaze3d/vertex/VertexConsumer;",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void swapArmorFoil(
            MultiBufferSource pBufferSource,
            RenderType pRenderType,
            boolean pNoEntity,
            boolean pWithGlint,
            CallbackInfoReturnable<VertexConsumer> cir) {

        if (!pWithGlint) {
            return;
        }

        // HumanoidArmorLayerMixin에서 캡처한 아이템을 가져옵니다.
        ItemStack stack = ICustomGlint.CURRENT_ITEM.get();

        if (stack == null || stack.isEmpty()) {
            return;
        }

        if (!(stack.getItem() instanceof ICustomGlint customGlintItem)) {
            return;
        }

        if (!customGlintItem.hasCustomGlint(stack)) {
            return;
        }

        ResourceLocation texture = customGlintItem.getCustomGlintTexture(stack);

        if (texture == null) {
            return;
        }

        RenderType customGlintRenderType = CustomRenderTypes.getCustomArmorEntityGlint(texture);

        cir.setReturnValue(
                VertexMultiConsumer.create(
                        pBufferSource.getBuffer(customGlintRenderType),
                        pBufferSource.getBuffer(pRenderType)
                )
        );
    }
}