package de.niklasbednarczyk.nbweather.core.ui.common

import androidx.compose.runtime.Composable
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsValue
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.settings.NBSettings

val NBUnitsValue?.displayValueWithSymbol: NBString?
    @Composable
    get() = this?.getDisplayValueWithSymbol(NBSettings.units)
