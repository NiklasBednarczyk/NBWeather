package de.niklasbednarczyk.nbweather.feature.settings.screens.units

import androidx.compose.runtime.Composable
import de.niklasbednarczyk.nbweather.core.common.data.NBUnitsType
import de.niklasbednarczyk.nbweather.core.ui.radio.NBRadioGroupView

@Composable
fun SettingsUnitsContent(
    uiState: SettingsUnitsUiState,
    onItemSelected: (item: NBUnitsType) -> Unit
) {
    NBRadioGroupView(
        radioGroup = uiState.radioGroup,
        onItemSelected = onItemSelected
    )
}