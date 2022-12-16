package de.niklasbednarczyk.nbweather.core.ui.values

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcon

@Composable
fun NBValueIconView(
    modifier: Modifier = Modifier,
    valueIcon: NBValueIconModel
) {
    NBIcon(
        modifier = modifier.rotate(valueIcon.rotationDegrees),
        icon = valueIcon.icon,
    )
}
