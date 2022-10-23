package de.niklasbednarczyk.openweathermap.feature.settings.screens.overview

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import de.niklasbednarczyk.openweathermap.core.ui.compositions.settings.OwmSettings
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.OwmScaffold
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.OwmTopAppBar

@Composable
fun SettingsOverviewScreen(
    viewModel: SettingsOverviewViewModel = hiltViewModel(),
    navigationIcon: @Composable () -> Unit
) {

    OwmScaffold(
        topBar = { scrollBehavior ->
            OwmTopAppBar(
                scrollBehavior = scrollBehavior,
                navigationIcon = navigationIcon,
                title = "Settings" //TODO (#15) Do with string resource
            )
        },
        snackbarChannel = viewModel.snackbarChannel
    ) {
        //TODO (#15) Do right design

        val units = OwmSettings.units

        Column {
            TextButton(onClick = { viewModel.toggleTemperatureUnit(units.temperatureUnit) }) {
                Text(text = units.temperatureUnit.name)
            }
        }
    }

}