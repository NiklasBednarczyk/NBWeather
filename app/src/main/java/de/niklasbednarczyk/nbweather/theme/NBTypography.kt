package de.niklasbednarczyk.nbweather.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import de.niklasbednarczyk.nbweather.core.ui.font.changeFontFamily
import de.niklasbednarczyk.nbweather.core.ui.font.fontFamily
import de.niklasbednarczyk.nbweather.core.ui.settings.NBSettings

val typography: Typography
    @Composable
    get() {
        val font = NBSettings.font
        val typography = Typography()
        val fontFamily = font.fontFamily
        return typography.changeFontFamily(fontFamily)
    }