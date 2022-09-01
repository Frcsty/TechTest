package com.github.frcsty.techtest.mixin;

import com.github.frcsty.techtest.display.EntityInformationDisplay;
import com.github.frcsty.techtest.holder.VariableHolder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public final class MinecraftClientMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    private void inject(CallbackInfo callbackInfo) {
        final Entity entity = MinecraftClient.getInstance().targetedEntity;
        VariableHolder.Companion.setTargetedEntity(entity);

        if (entity != null) {
            if (VariableHolder.Companion.getDisplay() == null || VariableHolder.Companion.getDisplay().getCurrentEntity().getId() != entity.getId()) {
                final NbtCompound compound = new NbtCompound();
                entity.writeNbt(compound);

                VariableHolder.Companion.setDisplay(new EntityInformationDisplay(entity, compound));
            }
        }
    }

}
