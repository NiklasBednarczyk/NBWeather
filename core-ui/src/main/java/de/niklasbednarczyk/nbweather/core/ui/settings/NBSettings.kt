package de.niklasbednarczyk.nbweather.core.ui.settings

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import de.niklasbednarczyk.nbweather.core.common.settings.appearance.NBAppearanceModel
import de.niklasbednarczyk.nbweather.core.common.settings.appearance.NBThemeType
import de.niklasbednarczyk.nbweather.core.common.settings.font.NBFontModel
import de.niklasbednarczyk.nbweather.core.common.settings.order.NBOrderModel
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsModel

object NBSettings {

    val appearance: NBAppearanceModel
        @Composable
        get() = LocalNBAppearance.current

    val font: NBFontModel
        @Composable
        get() = LocalNBFont.current

    val order: NBOrderModel
        @Composable
        get() = LocalNBOrder.current

    val units: NBUnitsModel
        @Composable
        get() = LocalNBUnits.current

    val isLightTheme: Boolean
        @Composable
        get() = if (appearance.useDeviceTheme) {
            !isSystemInDarkTheme()
        } else {
            when (appearance.theme) {
                NBThemeType.LIGHT -> true
                NBThemeType.DARK -> false
            }
        }

}