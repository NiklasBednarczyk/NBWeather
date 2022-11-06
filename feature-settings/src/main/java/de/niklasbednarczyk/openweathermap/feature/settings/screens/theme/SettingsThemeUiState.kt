package de.niklasbednarczyk.openweathermap.feature.settings.screens.theme

import de.niklasbednarczyk.openweathermap.core.ui.radio.OwmRadioGroupModel
import de.niklasbednarczyk.openweathermap.data.settings.models.appearance.ThemeTypeData

data class SettingsThemeUiState(
    val radioGroup: OwmRadioGroupModel<ThemeTypeData>? = null
)