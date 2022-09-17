package de.niklasbednarczyk.openweathermap.core.ui.icons

import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable

@Composable
fun OwmIconButton(
    icon: OwmIconModel,
    onClick: () -> Unit,
) {
    IconButton(
        content = {
            OwmIcon(
                icon = icon
            )
        },
        onClick = onClick
    )
}
