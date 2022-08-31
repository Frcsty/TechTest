package com.github.frcsty.techtest.display

import com.github.frcsty.techtest.display.node.GraphNode
import com.github.frcsty.techtest.information.VariableHolder
import gg.essential.elementa.ElementaVersion
import gg.essential.elementa.WindowScreen
import gg.essential.elementa.components.TreeGraphComponent
import gg.essential.elementa.components.TreeGraphStyle
import gg.essential.elementa.components.UIImage
import gg.essential.elementa.constraints.CenterConstraint
import gg.essential.elementa.dsl.childOf
import gg.essential.elementa.dsl.constrain
import gg.essential.elementa.dsl.pixels
import net.minecraft.client.MinecraftClient
import net.minecraft.entity.Entity
import net.minecraft.util.Identifier
import java.util.concurrent.CompletableFuture
import javax.imageio.ImageIO

class EntityInformationDisplay(
    val currentEntity: Entity
) : WindowScreen(ElementaVersion.V2) {

    init {
        val resource = MinecraftClient.getInstance().resourceManager.getResource(
            Identifier(
                "techtest",
                "textures/item/spawn_egg_2.png"
            )
        )

        val compound = VariableHolder.compoundMap[currentEntity.id]
        if (compound != null) {
            val root = GraphNode("", window).withChildren {
                compound.nodeStructure.keys.forEach { primary ->
                    add(GraphNode(primary, window).withChildren {
                        compound.nodeStructure[primary]?.forEach {
                            add(GraphNode(it, window))
                        }
                    })
                }
            }

            TreeGraphComponent(
                root, TreeGraphStyle(
                    widthBetweenNodes = 4f,
                    heightBetweenRows = 4f,
                    isHorizontal = true
                )
            ).constrain {
                x = 2.pixels()
                y = 2.pixels()
            } childOf window
        }

        UIImage(imageFuture = CompletableFuture.supplyAsync {
            ImageIO.read(resource.inputStream)
        }).constrain {
            x = CenterConstraint()
            y = CenterConstraint()
        } childOf window
    }

}