package com.github.frcsty.techtest.display.node

import gg.essential.elementa.UIComponent
import gg.essential.elementa.components.TreeArrowComponent
import gg.essential.elementa.components.TreeNode
import gg.essential.elementa.components.UIBlock
import gg.essential.elementa.components.UIText
import gg.essential.elementa.constraints.SiblingConstraint
import gg.essential.elementa.dsl.*
import java.awt.Color

class TextNode(private val text: String) : TreeNode() {

    override fun getPrimaryComponent(): UIComponent {
        return UIText(text).constrain {
            x = 10.pixels()
            y = SiblingConstraint() - 5.pixels()
        }
    }

    override fun getArrowComponent(): TreeArrowComponent {
        return ArrowComponent()
    }

    class ArrowComponent : TreeArrowComponent() {

        init {
            UIBlock(Color.RED) constrain {
                width = 4.pixels()
                height = 1.pixels()
            } childOf this
        }

        override fun open() {
            children.forEach { it.unhide() }
        }

        override fun close() {
            children.forEach { it.unhide() }
        }

    }

}