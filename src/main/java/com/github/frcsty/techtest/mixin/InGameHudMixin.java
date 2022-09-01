package com.github.frcsty.techtest.mixin;

import com.github.frcsty.techtest.display.EntityInformationDisplay;
import com.github.frcsty.techtest.holder.VariableHolder;
import gg.essential.universal.UMatrixStack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public final class InGameHudMixin {

    @Inject(method = "renderCrosshair", at = @At("TAIL"))
    private void shouldRenderCrosshair(MatrixStack matrixStack, CallbackInfo callbackInfo) {
        final EntityInformationDisplay display = VariableHolder.Companion.getDisplay();

        if (display != null && VariableHolder.Companion.getTargetedEntity() != null) {
            if (VariableHolder.debug) MinecraftClient.getInstance().setScreen(display);
            else display.getWindow().draw(new UMatrixStack(matrixStack));
        }
    }

}
