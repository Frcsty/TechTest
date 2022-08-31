package com.github.frcsty.techtest.holder

import net.minecraft.entity.Entity
import net.minecraft.nbt.NbtCompound

class VariableHolder {

    companion object {
        val compoundMap = mutableMapOf<Int, NbtCompound>()
        var targetedEntity: Entity? = null
    }

}