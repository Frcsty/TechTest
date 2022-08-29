package com.github.frcsty.techtest.information

import net.minecraft.entity.Entity

class VariableHolder {

    companion object {
        var targetedEntity: Entity? = null
        var compoundMap = mutableMapOf<Int, NbtCompoundResolver>()
    }

}