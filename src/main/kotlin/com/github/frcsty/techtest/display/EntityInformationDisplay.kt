package com.github.frcsty.techtest.display

import com.github.frcsty.techtest.display.node.GraphNode
import gg.essential.elementa.ElementaVersion
import gg.essential.elementa.WindowScreen
import gg.essential.elementa.components.TreeGraphComponent
import gg.essential.elementa.components.TreeGraphStyle
import gg.essential.elementa.components.UIImage
import gg.essential.elementa.components.inspector.Inspector
import gg.essential.elementa.constraints.CenterConstraint
import gg.essential.elementa.dsl.childOf
import gg.essential.elementa.dsl.constrain
import gg.essential.elementa.dsl.pixels
import gg.essential.elementa.dsl.plus
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.Entity
import java.awt.Color
import java.io.File
import java.net.URL
import javax.imageio.ImageIO

class EntityInformationDisplay(
    val currentEntity: Entity,
    private val stack: MatrixStack
) : WindowScreen(ElementaVersion.V2) {

    init {
        /*
        UIImage.ofURL(URL("https://imgur.com/GDH1ImS")).constrain {
            x = CenterConstraint()
            y = CenterConstraint()
        } childOf window


        val compound = VariableHolder.compoundMap[currentEntity.id]
        if (compound != null) {
            val root = TextNode("Type: ${StringUtils.capitalize(currentEntity.type.untranslatedName)}").withChildren {
                compound.keys().forEach { primary ->
                    this.add(TextNode(" Data: ${StringUtils.capitalize(primary)}").withChildren {
                        this.add(TextNode("  Child: ${compound.children(primary)}"))
                    })
                }
            }

            TreeListComponent(root).constrain {
                x = CenterConstraint() + 10.pixels()
                y = CenterConstraint() + 15.pixels()

                textScale = 0.5.pixels()
            } childOf window
        }
         */

        val root = GraphNode("Root Node").withChildren {
            add(GraphNode(" Child Node 1"))
            add(GraphNode(" Child Node 2"))
        }

        TreeGraphComponent(root, TreeGraphStyle(isHorizontal = true, lineColor = Color.BLUE)).constrain {
            x = 2.pixels()
            y = 4.pixels()
        } childOf window

        Inspector(window).constrain {
            x = CenterConstraint() + 10.pixels()
            y = 10.pixels()
        } childOf window
    }

    private fun applyColor(file: File, color: Color, target: Color): File {
        val image = ImageIO.read(file)

        for (x in 0 until image.width) {
            for (y in 0 until image.height) {
                val currentColor = Color(image.getRGB(x, y))
                if (currentColor == target)
                    image.setRGB(x, y, color.rgb)
            }
        }

        ImageIO.write(image, "png", file)
        return file
    }

}