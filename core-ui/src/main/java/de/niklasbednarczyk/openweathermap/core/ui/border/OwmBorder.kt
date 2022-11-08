package de.niklasbednarczyk.openweathermap.core.ui.border

import androidx.compose.foundation.border
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.owmBorder(
    shape: Shape = RectangleShape,
    width: Dp = 1.dp,
    color: Color = MaterialTheme.colorScheme.outline
): Modifier = composed {
    this.border(
        width = width,
        color = color,
        shape = shape
    )
}