package de.niklasbednarczyk.nbweather.feature.settings.screens.timeformat

import androidx.compose.runtime.Composable
import de.niklasbednarczyk.nbweather.core.common.data.NBTimeFormatType
import de.niklasbednarczyk.nbweather.core.ui.radio.NBRadioGroupView

@Composable
fun SettingsTimeFormatContent(
    uiState: SettingsTimeFormatUiState,
    onItemSelected: (item: NBTimeFormatType) -> Unit
) {
    NBRadioGroupView(
        radioGroup = uiState.radioGroup,
        onItemSelected = onItemSelected
    )
}