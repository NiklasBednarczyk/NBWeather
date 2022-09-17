package de.niklasbednarczyk.openweathermap.core.ui.icons

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun OwmIcon(
    modifier: Modifier = Modifier,
    icon: OwmIconModel,
) {
    Icon(
        modifier = modifier,
        imageVector = icon.imageVector,
        contentDescription = stringResource(icon.contentDescriptionResId)
    )
}