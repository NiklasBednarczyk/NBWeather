package de.niklasbednarczyk.nbweather.feature.settings.screens.timeformat

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.radio.NBRadioGroupView
import de.niklasbednarczyk.nbweather.core.ui.scaffold.NBScaffold
import de.niklasbednarczyk.nbweather.core.ui.scaffold.topappbar.NBTopAppBar

@Composable
fun SettingsTimeFormatScreen(
    viewModel: SettingsTimeFormatViewModel = hiltViewModel(),
    navigationIcon: @Composable () -> Unit,
    navigateToOverview: () -> Unit
) {

    val uiState = viewModel.uiState.collectAsState()

    NBScaffold(
        topBar = { scrollBehavior ->
            NBTopAppBar(
                scrollBehavior = scrollBehavior,
                navigationIcon = navigationIcon,
                title = NBString.Resource(R.string.screen_settings_time_format_title)
            )
        }
    ) {

        NBRadioGroupView(
            radioGroup = uiState.value.radioGroup,
            onItemSelected = { timeFormat ->
                viewModel.updateTimeFormat(timeFormat)
                navigateToOverview()
            }
        )

    }

}