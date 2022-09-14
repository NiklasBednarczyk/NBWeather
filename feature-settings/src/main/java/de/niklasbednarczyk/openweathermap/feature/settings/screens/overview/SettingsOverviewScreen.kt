package de.niklasbednarczyk.openweathermap.feature.settings.screens.overview

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SettingsOverviewScreen(
    viewModel: SettingsOverviewViewModel = hiltViewModel()
) {

    val uiState = viewModel.uiState.collectAsState()

    //TODO (#15) Do right design

    val settingsDisplay = uiState.value.settingsData

    if (settingsDisplay != null) {
        Row {
            TextButton(onClick = { viewModel.toggleUnits(settingsDisplay.units) }) {
                Text(text = settingsDisplay.units.name)
            }
            TextButton(onClick = { viewModel.toggleDataLanguage(settingsDisplay.dataLanguage) }) {
                Text(text = settingsDisplay.dataLanguage.name)
            }
        }
    }


}