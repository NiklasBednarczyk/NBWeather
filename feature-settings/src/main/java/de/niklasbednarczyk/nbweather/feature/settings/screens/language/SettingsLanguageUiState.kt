package de.niklasbednarczyk.nbweather.feature.settings.screens.language

import de.niklasbednarczyk.nbweather.core.common.data.NBLanguageType
import de.niklasbednarczyk.nbweather.core.ui.radio.NBRadioGroupModel

data class SettingsLanguageUiState(
    val radioGroup: NBRadioGroupModel<NBLanguageType>? = null
)