package de.niklasbednarczyk.nbweather.feature.settings.screens.timeformat

import de.niklasbednarczyk.nbweather.core.common.data.NBTimeFormatType
import de.niklasbednarczyk.nbweather.core.ui.radio.NBRadioGroupModel

data class SettingsTimeFormatUiState(
    val radioGroup: NBRadioGroupModel<NBTimeFormatType>? = null
)