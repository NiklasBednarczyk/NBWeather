package de.niklasbednarczyk.nbweather.core.ui.border

import androidx.compose.foundation.border
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.nbBorder(
    shape: Shape = RectangleShape,
    width: Dp = 1.dp,
): Modifier = composed {
    val color = MaterialTheme.colorScheme.outline
    this.border(
        width = width,
        color = color,
        shape = shape
    )
}