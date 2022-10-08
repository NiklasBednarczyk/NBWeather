package de.niklasbednarczyk.openweathermap.core.ui.icons

import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha

@Composable
fun OwmIconButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: OwmIconModel,
    onClick: () -> Unit,
) {
    IconButton(
        modifier = modifier,
        content = {
            OwmIcon(
                icon = icon
            )
        },
        enabled = enabled,
        onClick = onClick
    )
}

@Composable
fun OwmIconButtonEmpty() {
    IconButton(
        modifier = Modifier.alpha(0f),
        content = {},
        enabled = false,
        onClick = {}
    )
}
