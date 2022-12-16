package de.niklasbednarczyk.nbweather.feature.settings.screens.units

import de.niklasbednarczyk.nbweather.core.common.data.NBUnitsType
import de.niklasbednarczyk.nbweather.core.ui.radio.NBRadioGroupModel

data class SettingsUnitsUiState(
    val radioGroup: NBRadioGroupModel<NBUnitsType>? = null
)