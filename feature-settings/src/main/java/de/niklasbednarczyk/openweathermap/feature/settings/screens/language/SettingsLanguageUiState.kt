package de.niklasbednarczyk.openweathermap.feature.settings.screens.language

import de.niklasbednarczyk.openweathermap.core.common.data.OwmLanguageType
import de.niklasbednarczyk.openweathermap.core.ui.radio.OwmRadioGroupModel

data class SettingsLanguageUiState(
    val radioGroup: OwmRadioGroupModel<OwmLanguageType>? = null
)