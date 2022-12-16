package de.niklasbednarczyk.nbweather.feature.settings.screens.theme

import de.niklasbednarczyk.nbweather.core.ui.radio.NBRadioGroupModel
import de.niklasbednarczyk.nbweather.data.settings.models.appearance.ThemeTypeData

data class SettingsThemeUiState(
    val radioGroup: NBRadioGroupModel<ThemeTypeData>? = null
)