package de.niklasbednarczyk.openweathermap.feature.settings.screens.overview

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcon
import de.niklasbednarczyk.openweathermap.core.ui.navigation.OwmNavigationDestination
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.OwmScaffold
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.OwmTopAppBar
import de.niklasbednarczyk.openweathermap.core.ui.strings.asString
import de.niklasbednarczyk.openweathermap.core.ui.theme.defaultScreenHorizontalPadding
import de.niklasbednarczyk.openweathermap.core.ui.theme.dividerPaddingVertical
import de.niklasbednarczyk.openweathermap.core.ui.theme.listContentPaddingValues
import de.niklasbednarczyk.openweathermap.core.ui.theme.listContentPaddingVertical
import de.niklasbednarczyk.openweathermap.feature.settings.screens.overview.models.SettingsOverviewItemModel

@Composable
fun SettingsOverviewScreen(
    viewModel: SettingsOverviewViewModel = hiltViewModel(),
    navigationIcon: @Composable () -> Unit,
    navigateToDestination: (OwmNavigationDestination) -> Unit
) {

    val uiState = viewModel.uiState.collectAsState()

    OwmScaffold(
        topBar = { scrollBehavior ->
            OwmTopAppBar(
                scrollBehavior = scrollBehavior,
                navigationIcon = navigationIcon,
                title = OwmString.Resource(R.string.screen_settings_overview_title)
            )
        },
        snackbarChannel = viewModel.snackbarChannel
    ) {
        val items = uiState.value.items
        val firstItemIsHeader = items.firstOrNull() is SettingsOverviewItemModel.Header
        val contentPadding = if (firstItemIsHeader) {
            PaddingValues(
                bottom = listContentPaddingVertical
            )
        } else {
            listContentPaddingValues
        }

        LazyColumn(
            contentPadding = contentPadding
        ) {
            items(items) { item ->
                when (item) {
                    is SettingsOverviewItemModel.Divider -> {
                        Divider(
                            modifier = Modifier.padding(
                                vertical = dividerPaddingVertical
                            )
                        )
                    }
                    is SettingsOverviewItemModel.Header -> {
                        Text(
                            modifier = Modifier.padding(
                                horizontal = defaultScreenHorizontalPadding,
                                vertical = 16.dp
                            ),
                            text = item.text.asString(),
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                    is SettingsOverviewItemModel.Item -> {
                        ListItem(
                            modifier = Modifier.clickable {
                                navigateToDestination(item.destination)
                            },
                            headlineText = {
                                Text(item.title.asString())
                            },
                            supportingText = {
                                Text(item.value.asString())
                            },
                            leadingContent = {
                                OwmIcon(icon = item.icon)
                            }
                        )
                    }
                }
            }
        }
    }

}