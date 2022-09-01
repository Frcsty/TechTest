package com.github.frcsty.techtest.holder

import com.github.frcsty.techtest.display.EntityInformationDisplay
import net.minecraft.entity.Entity

class VariableHolder {

    companion object {
        var display: EntityInformationDisplay? = null
        var targetedEntity: Entity? = null

        const val debug = false
    }

}