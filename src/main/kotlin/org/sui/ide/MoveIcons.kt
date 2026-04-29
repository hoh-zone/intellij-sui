package org.sui.ide

import com.intellij.icons.AllIcons
import com.intellij.openapi.util.IconLoader
import java.awt.Component
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.Icon

object MoveIcons {
    val MODULE = load("/icons/module.svg")

    val STRUCT = load("/icons/struct.svg")
    val STRUCT_FIELD = AllIcons.Nodes.Field

    val CONST = AllIcons.Nodes.Constant
    val FUNCTION = AllIcons.Nodes.Function

    val VARIABLE = AllIcons.Nodes.Variable
    val PARAMETER = AllIcons.Nodes.Parameter

    val SUI_LOGO = load("/icons/sui.svg").sized(16, 16)
    private fun load(path: String): Icon = IconLoader.getIcon(path, MoveIcons::class.java)
}

fun Icon.sized(width: Int, height: Int): Icon {
    val delegate = this
    return object : Icon {
        override fun getIconWidth(): Int = width
        override fun getIconHeight(): Int = height

        override fun paintIcon(c: Component, g: Graphics, x: Int, y: Int) {
            val g2d = g.create() as Graphics2D
            try {
                g2d.translate(x.toDouble(), y.toDouble())
                g2d.scale(
                    width.toDouble() / delegate.iconWidth.toDouble(),
                    height.toDouble() / delegate.iconHeight.toDouble()
                )
                delegate.paintIcon(c, g2d, 0, 0)
            } finally {
                g2d.dispose()
            }
        }
    }
}
