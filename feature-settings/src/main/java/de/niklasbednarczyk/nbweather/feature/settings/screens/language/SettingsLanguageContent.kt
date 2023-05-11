package de.niklasbednarczyk.nbweather.feature.settings.screens.language

import androidx.compose.runtime.Composable
import de.niklasbednarczyk.nbweather.core.common.data.NBLanguageType
import de.niklasbednarczyk.nbweather.core.ui.radio.NBRadioGroupView

@Composable
fun SettingsLanguageContent(
    uiState: SettingsLanguageUiState,
    onItemSelected: (item: NBLanguageType) -> Unit
) {
    NBRadioGroupView(
        radioGroup = uiState.radioGroup,
        onItemSelected = onItemSelected,
        sortItemsAlphabetically = true
    )
}