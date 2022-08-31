package com.github.frcsty.techtest.display

import com.github.frcsty.techtest.display.construct.NbtTreeConstructor
import com.github.frcsty.techtest.display.texture.TextureMap
import com.github.frcsty.techtest.holder.VariableHolder
import gg.essential.elementa.ElementaVersion
import gg.essential.elementa.WindowScreen
import gg.essential.elementa.components.TreeGraphComponent
import gg.essential.elementa.components.TreeGraphStyle
import gg.essential.elementa.dsl.childOf
import gg.essential.elementa.dsl.constrain
import gg.essential.elementa.dsl.pixels
import net.minecraft.client.MinecraftClient
import net.minecraft.entity.Entity
import net.minecraft.util.Identifier

class EntityInformationDisplay(
    val currentEntity: Entity
) : WindowScreen(ElementaVersion.V2) {

    private val resource = MinecraftClient.getInstance().resourceManager.getResource(
        Identifier(
            "minecraft",
            "textures/item/spawn_egg.png"
        )
    )
    private val overlayResource = MinecraftClient.getInstance().resourceManager.getResource(
        Identifier(
            "minecraft",
            "textures/item/spawn_egg_overlay.png"
        )
    )

    init {
        val compound = VariableHolder.compoundMap[currentEntity.id]
        if (compound != null) {
            val root = NbtTreeConstructor(compound, window).node

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

        val textureMap: TextureMap? = TextureMap.forType(currentEntity.type.untranslatedName)
        if (textureMap != null) {
            println("First: ${textureMap.first}, Second: ${textureMap.second}")
            println(resource)
            println(overlayResource)
            // Recolor the initial images, retrieve as a texture and somehow display it instead of the crosshair
        }
    }

}