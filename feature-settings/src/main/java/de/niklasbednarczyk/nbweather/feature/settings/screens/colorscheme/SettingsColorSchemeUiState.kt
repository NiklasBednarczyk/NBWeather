package de.niklasbednarczyk.nbweather.feature.settings.screens.colorscheme

import de.niklasbednarczyk.nbweather.core.ui.radio.NBRadioGroupModel
import de.niklasbednarczyk.nbweather.data.settings.models.appearance.ColorSchemeTypeData

data class SettingsColorSchemeUiState(
    val radioGroup: NBRadioGroupModel<ColorSchemeTypeData>? = null
)