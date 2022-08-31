package com.github.frcsty.techtest.mixin;

import com.github.frcsty.techtest.display.EntityInformationDisplay;
import com.github.frcsty.techtest.holder.VariableHolder;
import gg.essential.universal.UMatrixStack;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public final class InGameHudMixin {

    private EntityInformationDisplay display;

    @Inject(method = "renderCrosshair", at = @At("HEAD"))
    private void shouldRenderCrosshair(MatrixStack matrixStack, CallbackInfo callbackInfo) {
        final Entity entity = VariableHolder.Companion.getTargetedEntity();
        if (entity == null) return;
        if (this.display == null || this.display.getCurrentEntity().getId() != entity.getId()) {
            this.display = new EntityInformationDisplay(entity);
        }

        this.display.getWindow().draw(new UMatrixStack(matrixStack));
    }

}
