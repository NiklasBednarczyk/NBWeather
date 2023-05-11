package de.niklasbednarczyk.nbweather.feature.settings.screens.colorscheme

import androidx.compose.runtime.Composable
import de.niklasbednarczyk.nbweather.core.ui.radio.NBRadioGroupView
import de.niklasbednarczyk.nbweather.data.settings.models.appearance.ColorSchemeTypeData

@Composable
fun SettingsColorSchemeContent(
    uiState: SettingsColorSchemeUiState,
    onItemSelected: (item: ColorSchemeTypeData) -> Unit
) {
    NBRadioGroupView(
        radioGroup = uiState.radioGroup,
        onItemSelected = onItemSelected
    )
}