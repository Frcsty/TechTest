package com.github.frcsty.techtest.mixin;

import com.github.frcsty.techtest.information.NbtCompoundResolver;
import com.github.frcsty.techtest.information.VariableHolder;
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
            final NbtCompound compound = new NbtCompound();

            entity.writeNbt(compound);
            VariableHolder.Companion.getCompoundMap().put(entity.getId(), new NbtCompoundResolver(((NbtCompoundAccessor) compound).getEntries()));
        }
    }

}
