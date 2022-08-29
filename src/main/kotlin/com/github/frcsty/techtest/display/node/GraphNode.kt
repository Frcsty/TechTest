package com.github.frcsty.techtest.display.node

import gg.essential.elementa.UIComponent
import gg.essential.elementa.components.TreeGraphNode
import gg.essential.elementa.components.UIText
import gg.essential.elementa.dsl.constrain
import gg.essential.elementa.dsl.pixels
import gg.essential.elementa.dsl.toConstraint
import java.awt.Color

class GraphNode(private val text: String) : TreeGraphNode() {

    override fun makeComponent(): UIComponent = UIText(text).constrain {
        width = (text.length * 4).pixels()
        height = 6.pixels()

        color = Color.BLUE.toConstraint()
    }

}