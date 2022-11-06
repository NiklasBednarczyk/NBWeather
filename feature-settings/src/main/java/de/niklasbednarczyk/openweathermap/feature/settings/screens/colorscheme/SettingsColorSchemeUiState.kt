package de.niklasbednarczyk.openweathermap.feature.settings.screens.colorscheme

import de.niklasbednarczyk.openweathermap.core.ui.radio.OwmRadioGroupModel
import de.niklasbednarczyk.openweathermap.data.settings.models.appearance.ColorSchemeTypeData

data class SettingsColorSchemeUiState(
    val radioGroup: OwmRadioGroupModel<ColorSchemeTypeData>? = null
)