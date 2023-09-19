package de.niklasbednarczyk.nbweather.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import de.niklasbednarczyk.nbweather.core.ui.colors.LocalNBColors
import de.niklasbednarczyk.nbweather.core.ui.colors.NBColorsModel

@Composable
fun NBTheme(
    content: @Composable () -> Unit
) {
    val scheme = colorScheme
    val colors = NBColorsModel.from(scheme)

    CompositionLocalProvider(
        LocalNBColors provides colors
    ) {
        MaterialTheme(
            colorScheme = scheme,
            typography = typography,
            content = content
        )
    }
}
