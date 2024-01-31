package de.niklasbednarczyk.nbweather.core.ui.icons

import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NBIconButtonView(
    modifier: Modifier = Modifier,
    icon: NBIconItem,
    enabled: Boolean = true,
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
