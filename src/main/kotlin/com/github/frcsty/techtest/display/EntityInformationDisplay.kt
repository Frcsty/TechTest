package com.github.frcsty.techtest.display

import com.github.frcsty.techtest.display.construct.NbtTreeConstructor
import com.github.frcsty.techtest.display.texture.TextureMap
import com.github.frcsty.techtest.holder.VariableHolder
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
import net.minecraft.client.MinecraftClient
import net.minecraft.entity.Entity
import net.minecraft.nbt.NbtCompound
import net.minecraft.util.Identifier
import java.awt.Color
import java.awt.image.BufferedImage
import java.util.concurrent.CompletableFuture
import javax.imageio.ImageIO


class EntityInformationDisplay(
    val currentEntity: Entity,
    compound: NbtCompound
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

        val textureMap: TextureMap? = TextureMap.forType(currentEntity.type.untranslatedName)
        if (textureMap != null) {
            UIImage(imageFuture = CompletableFuture.supplyAsync {
                recolor(
                    ImageIO.read(resource.inputStream),
                    ImageIO.read(overlayResource.inputStream),
                    textureMap.first,
                    textureMap.second
                )
            }).constrain {
                x = CenterConstraint()
                y = CenterConstraint()

                width = 16.pixels()
                height = 16.pixels()
            } childOf window
        }

        if (VariableHolder.debug) {
            Inspector(
                rootComponent = window,
                outlineColor = Color.RED
            ).constrain {
                x = CenterConstraint() + 100.pixels()
                y = 2.pixels()
            } childOf window
        }
    }

    private fun recolor(
        base: BufferedImage,
        overlay: BufferedImage,
        baseReplacement: Int,
        overplayReplacement: Int
    ): BufferedImage {
        val image = BufferedImage(base.width, base.height, BufferedImage.TYPE_INT_ARGB)

        val baseRed: Int = baseReplacement ushr 16 and 0xFF
        val baseGreen: Int = baseReplacement ushr 8 and 0xFF
        val baseBlue: Int = baseReplacement ushr 0 and 0xFF

        val overlayRed: Int = overplayReplacement ushr 16 and 0xFF
        val overlayGreen: Int = overplayReplacement ushr 8 and 0xFF
        val overlayBlue: Int = overplayReplacement ushr 0 and 0xFF

        for (x in 0 until image.width) {
            for (y in 0 until image.height) {
                // Overlay
                val overlayColor: Color? = compute(
                    Color(overlay.getRGB(x, y)),
                    overlayRed,
                    overlayGreen,
                    overlayBlue
                )

                if (overlayColor != null) image.setRGB(x, y, overlayColor.rgb)

                // Base
                val baseColor: Color? = compute(
                    Color(base.getRGB(x, y)),
                    baseRed,
                    baseGreen,
                    baseBlue
                )

                if (baseColor != null) image.setRGB(x, y, baseColor.rgb)
            }
        }

        return image
    }

    private fun compute(color: Color, red: Int, green: Int, blue: Int): Color? {
        if (!shouldDraw(color)) {
            return null
        }

        val amplifier: Float = (color.red).toFloat() / 255f
        return Color(
            (red * amplifier).toInt(),
            (green * amplifier).toInt(),
            (blue * amplifier).toInt()
        )
    }

    private fun shouldDraw(color: Color): Boolean {
        if (color.red == 73 && color.green == 161 && color.blue == 148) {
            return false
        }
        if (color.red == 0 && color.green == 0 && color.blue == 0) {
            return false
        }

        return true
    }

}