package de.niklasbednarczyk.openweathermap.core.ui.values

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcon

@Composable
fun OwmValueIconView(
    modifier: Modifier = Modifier,
    valueIcon: OwmValueIconModel
) {
    OwmIcon(
        modifier = modifier.rotate(valueIcon.rotationDegrees),
        icon = valueIcon.icon,
    )
}
