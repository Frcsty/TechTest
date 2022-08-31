package com.github.frcsty.techtest.display.node

import gg.essential.elementa.UIComponent
import gg.essential.elementa.components.TreeGraphNode
import gg.essential.elementa.components.UIText
import gg.essential.elementa.constraints.SiblingConstraint
import gg.essential.elementa.dsl.*
import java.awt.Color

class GraphNode(private val text: String, private val parent: UIComponent) : TreeGraphNode() {

    override fun makeComponent(): UIComponent = UIText(text).constrain {
        x = SiblingConstraint()
        width = text.width().pixels()
        height = 6.pixels()

        color = Color.WHITE.toConstraint()
    } childOf parent

}