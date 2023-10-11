package de.niklasbednarczyk.nbweather.core.ui.settings

import androidx.compose.runtime.staticCompositionLocalOf
import de.niklasbednarczyk.nbweather.core.common.settings.appearance.NBAppearanceModel
import de.niklasbednarczyk.nbweather.core.common.settings.font.NBFontModel
import de.niklasbednarczyk.nbweather.core.common.settings.order.NBOrderModel
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsModel

val LocalNBAppearance =
    staticCompositionLocalOf<NBAppearanceModel> { error("No NBAppearanceModel provided.") }

val LocalNBFont =
    staticCompositionLocalOf<NBFontModel> { error("No NBFontModel provided.") }

val LocalNBOrder =
    staticCompositionLocalOf<NBOrderModel> { error("No NBOrderModel provided.") }

val LocalNBUnits =
    staticCompositionLocalOf<NBUnitsModel> { error("No NBUnitsModel provided.") }