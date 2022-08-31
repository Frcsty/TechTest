package com.github.frcsty.techtest.display.construct

import com.github.frcsty.techtest.display.node.GraphNode
import com.github.frcsty.techtest.mixin.NbtCompoundAccessor
import gg.essential.elementa.components.Window
import net.minecraft.nbt.NbtCompound

class NbtTreeConstructor(
    compound: NbtCompound,
    private val window: Window
) {

    var node: GraphNode = GraphNode("", window)

    init {
        walk(node, compound as NbtCompoundAccessor)
    }

    private fun walk(root: GraphNode, accessor: NbtCompoundAccessor) {
        root.withChildren {
            accessor.entries.entries.forEach { primary ->
                if (primary.value == null) return@forEach

                val parent = GraphNode(primary.key, window)
                add(parent.withChildren {
                    try {
                        walk(parent, primary.value as NbtCompoundAccessor)
                    } catch (exception: ClassCastException) {
                        add(GraphNode(primary.value.asString(), window))
                    }
                })
            }
        }
    }

}