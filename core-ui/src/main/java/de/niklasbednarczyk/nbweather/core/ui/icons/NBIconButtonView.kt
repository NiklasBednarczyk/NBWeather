package de.niklasbednarczyk.nbweather.core.ui.icons

import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha

@Composable
fun NBIconButtonView(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: NBIconModel,
    onClick: () -> Unit,
) {
    IconButton(
        modifier = modifier,
        content = {
            NBIconView(
                icon = icon
            )
        },
        enabled = enabled,
        onClick = onClick
    )
}

val emptyIcon = @Composable {
    IconButton(
        modifier = Modifier.alpha(0f),
        content = {},
        enabled = false,
        onClick = {}
    )
}
