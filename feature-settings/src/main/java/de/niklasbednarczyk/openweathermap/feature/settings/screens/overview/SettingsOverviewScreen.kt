package de.niklasbednarczyk.openweathermap.feature.settings.screens.overview

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.OwmScaffold
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.OwmTopAppBar

@Composable
fun SettingsOverviewScreen(
    viewModel: SettingsOverviewViewModel = hiltViewModel(),
    navigationIcon: @Composable () -> Unit
) {

    val uiState = viewModel.uiState.collectAsState()

    OwmScaffold(
        topBar = { scrollBehavior ->
            OwmTopAppBar(
                scrollBehavior = scrollBehavior,
                navigationIcon = navigationIcon,
                title = "Settings" //TODO (#15) Do with string resource
            )
        }
    ) {
        //TODO (#15) Do right design

        val settingsUnits = uiState.value.settingsUnits

        if (settingsUnits != null) {
            Column {
                TextButton(onClick = { viewModel.toggleTemperatureUnit(settingsUnits.temperatureUnit) }) {
                    Text(text = settingsUnits.temperatureUnit.name)
                }
            }
        }
    }

}