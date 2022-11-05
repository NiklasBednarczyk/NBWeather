package de.niklasbednarczyk.openweathermap.feature.settings.screens.overview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import de.niklasbednarczyk.openweathermap.core.ui.R
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
                title = stringResource(R.string.screen_settings_overview_title)
            )
        },
        snackbarChannel = viewModel.snackbarChannel
    ) {
        //TODO (#15) Do right design

    }

}