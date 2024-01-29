package de.niklasbednarczyk.nbweather.core.ui.canvas

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.vectorResource
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconItem

@Composable
fun NBIconItem.toVectorPainter(): VectorPainter {
    val image = ImageVector.vectorResource(resId)
    return rememberVectorPainter(image)
}

fun DrawScope.drawIcon(
    icon: VectorPainter,
    left: Float,
    top: Float,
    color: Color
) {
    translate(
        left = left,
        top = top
    ) {
        with(icon) {
            draw(
                size = intrinsicSize,
                colorFilter = ColorFilter.tint(color)
            )
        }
    }
}

fun DrawScope.drawIcon(
    icon: VectorPainter,
    center: Offset,
    color: Color
) {
    drawIcon(
        icon = icon,
        left = center.x - icon.intrinsicSize.width / 2,
        top = center.y - icon.intrinsicSize.height / 2,
        color = color
    )
}