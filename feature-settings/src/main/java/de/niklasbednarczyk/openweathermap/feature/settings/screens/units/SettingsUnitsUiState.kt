package de.niklasbednarczyk.openweathermap.feature.settings.screens.units

import de.niklasbednarczyk.openweathermap.core.common.data.OwmUnitsType
import de.niklasbednarczyk.openweathermap.core.ui.radio.OwmRadioGroupModel

data class SettingsUnitsUiState(
    val radioGroup: OwmRadioGroupModel<OwmUnitsType>? = null
)