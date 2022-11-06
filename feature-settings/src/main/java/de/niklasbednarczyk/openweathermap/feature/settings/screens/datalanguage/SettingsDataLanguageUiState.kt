package de.niklasbednarczyk.openweathermap.feature.settings.screens.datalanguage

import de.niklasbednarczyk.openweathermap.core.common.display.OwmDataLanguageType
import de.niklasbednarczyk.openweathermap.core.ui.radio.OwmRadioGroupModel

data class SettingsDataLanguageUiState(
    val radioGroup: OwmRadioGroupModel<OwmDataLanguageType>? = null
)