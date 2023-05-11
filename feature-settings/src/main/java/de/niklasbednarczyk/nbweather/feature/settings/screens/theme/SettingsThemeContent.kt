package de.niklasbednarczyk.nbweather.feature.settings.screens.theme

import androidx.compose.runtime.Composable
import de.niklasbednarczyk.nbweather.core.ui.radio.NBRadioGroupView
import de.niklasbednarczyk.nbweather.data.settings.models.appearance.ThemeTypeData

@Composable
fun SettingsThemeContent(
    uiState: SettingsThemeUiState,
    onItemSelected: (item: ThemeTypeData) -> Unit
) {
    NBRadioGroupView(
        radioGroup = uiState.radioGroup,
        onItemSelected = onItemSelected
    )
}