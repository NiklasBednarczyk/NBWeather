package de.niklasbednarczyk.nbweather.core.ui.icons

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Constraints

fun Modifier.nbIconFit() = this.layout { measurable, constraints ->
    if (constraints.maxHeight == Constraints.Infinity) {
        layout(0, 0) {}
    } else {
        val placeable = measurable.measure(constraints)
        layout(placeable.width, placeable.height) {
            placeable.place(0, 0)
        }
    }
}

fun Modifier.nbIconFillHeight() = this
    .fillMaxHeight()
    .aspectRatio(1f)

fun Modifier.nbIconFillWidth() = this
    .fillMaxHeight()
    .aspectRatio(1f)