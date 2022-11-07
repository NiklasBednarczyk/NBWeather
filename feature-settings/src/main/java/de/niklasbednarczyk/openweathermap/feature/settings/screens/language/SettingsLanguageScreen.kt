package de.niklasbednarczyk.openweathermap.feature.settings.screens.language

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.radio.OwmRadioGroupView
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.OwmScaffold
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.OwmTopAppBar

@Composable
fun SettingsLanguageScreen(
    viewModel: SettingsLanguageViewModel = hiltViewModel(),
    navigationIcon: @Composable () -> Unit,
    navigateToOverview: () -> Unit
) {

    val uiState = viewModel.uiState.collectAsState()

    OwmScaffold(
        topBar = { scrollBehavior ->
            OwmTopAppBar(
                scrollBehavior = scrollBehavior,
                navigationIcon = navigationIcon,
                title = OwmString.Resource(R.string.screen_settings_language_title)
            )
        }
    ) {

        OwmRadioGroupView(
            radioGroup = uiState.value.radioGroup,
            onItemSelected = { language ->
                viewModel.updateLanguage(language)
                navigateToOverview()
            },
            sortItemsAlphabetically = true
        )

    }

}