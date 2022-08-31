package com.github.frcsty.techtest.information

import com.github.frcsty.techtest.mixin.NbtCompoundAccessor
import net.minecraft.nbt.NbtCompound
import java.lang.ClassCastException

class NbtCompoundResolver(compound: NbtCompoundAccessor) {

    val nodeStructure = mutableMapOf<String, List<String>>()

    init {
        val iterator = compound.entries.keys.iterator()

        while (iterator.hasNext()) {
            val key = iterator.next()
            val element = compound.entries[key] ?: continue

            try {
                element as NbtCompoundAccessor
                val children = mutableListOf<String>()
                val iterator = element.entries.keys.iterator()

                while (iterator.hasNext()) {
                    val key = iterator.next()
                    val element = element.entries[key] ?: continue

                    children.add(element.asString())
                }

                nodeStructure[key] = children
            } catch (_: ClassCastException) {
                nodeStructure[key] = listOf(element.asString())
            }
        }
    }

}

class CompoundNode(
    private val text: String,
    private val indentation: Int
) {
}