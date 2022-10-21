package de.niklasbednarczyk.openweathermap.core.ui.modifier

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Shape
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.placeholder

@Composable
fun Modifier.owmPlaceholder(shape: Shape): Modifier = composed {
    this.placeholder(
        visible = true,
        highlight = PlaceholderHighlight.fade(),
        color = MaterialTheme.colorScheme.secondaryContainer,
        shape = shape
    )
}