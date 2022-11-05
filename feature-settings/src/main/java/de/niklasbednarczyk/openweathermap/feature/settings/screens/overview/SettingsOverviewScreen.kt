package de.niklasbednarczyk.openweathermap.feature.settings.screens.overview

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.settings.OwmSettings
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
                title = stringResource(R.string.screen_settings_overview_title)
            )
        },
        snackbarChannel = viewModel.snackbarChannel
    ) {
        //TODO (#15) Do right design

        val display = OwmSettings.display

        Column {
            TextButton(onClick = { viewModel.toggleTemperatureUnit(display.temperatureUnit) }) {
                Text(text = display.temperatureUnit.name)
            }
        }
    }

}