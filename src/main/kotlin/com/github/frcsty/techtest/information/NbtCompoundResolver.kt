package com.github.frcsty.techtest.information

import net.minecraft.nbt.NbtElement

class NbtCompoundResolver(entries: Map<String, NbtElement>) {

    private val nodeStructure = mutableMapOf<String, String>()

    init {
        val iterator = entries.keys.iterator()

        while (iterator.hasNext()) {
            val key = iterator.next()
            val element = entries[key] ?: continue

            if (element.asString().length > 2) continue
            nodeStructure[key] = element.asString()
        }
    }

    fun keys(): Set<String> {
        return nodeStructure.keys
    }

    fun children(key: String): String {
        return nodeStructure[key].orEmpty()
    }

}