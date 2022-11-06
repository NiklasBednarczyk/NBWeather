package de.niklasbednarczyk.openweathermap.feature.settings.screens.timeformat

import de.niklasbednarczyk.openweathermap.core.common.display.OwmTimeFormatType
import de.niklasbednarczyk.openweathermap.core.ui.radio.OwmRadioGroupModel

data class SettingsTimeFormatUiState(
    val radioGroup: OwmRadioGroupModel<OwmTimeFormatType>? = null
)