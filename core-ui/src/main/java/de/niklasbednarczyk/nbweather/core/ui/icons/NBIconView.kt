package de.niklasbednarczyk.nbweather.core.ui.icons

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import de.niklasbednarczyk.nbweather.core.ui.strings.asString

@Composable
fun NBIconView(
    modifier: Modifier = Modifier,
    icon: NBIconItem,
) {
    Icon(
        modifier = modifier,
        painter = painterResource(icon.resId),
        contentDescription = icon.contentDescription.asString()
    )
}