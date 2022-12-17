package de.niklasbednarczyk.nbweather.feature.settings.screens.overview

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
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcon
import de.niklasbednarczyk.nbweather.core.ui.navigation.NBNavigationDestination
import de.niklasbednarczyk.nbweather.core.ui.scaffold.NBScaffold
import de.niklasbednarczyk.nbweather.core.ui.scaffold.topappbar.NBTopAppBar
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.core.ui.theme.dimens.screenHorizontalPadding
import de.niklasbednarczyk.nbweather.core.ui.theme.dimens.dividerPaddingVertical
import de.niklasbednarczyk.nbweather.core.ui.theme.dimens.listContentPaddingValuesVertical
import de.niklasbednarczyk.nbweather.core.ui.theme.dimens.listContentPaddingVertical
import de.niklasbednarczyk.nbweather.feature.settings.screens.overview.models.SettingsOverviewItemModel

@Composable
fun SettingsOverviewScreen(
    viewModel: SettingsOverviewViewModel = hiltViewModel(),
    navigationIcon: @Composable () -> Unit,
    navigateToDestination: (NBNavigationDestination) -> Unit
) {

    val uiState = viewModel.uiState.collectAsState()

    NBScaffold(
        topBar = { scrollBehavior ->
            NBTopAppBar(
                scrollBehavior = scrollBehavior,
                navigationIcon = navigationIcon,
                title = NBString.Resource(R.string.screen_settings_overview_title)
            )
        }
    ) {
        val items = uiState.value.items
        val firstItemIsHeader = items.firstOrNull() is SettingsOverviewItemModel.Header
        val contentPadding = if (firstItemIsHeader) {
            PaddingValues(
                bottom = listContentPaddingVertical
            )
        } else {
            listContentPaddingValuesVertical
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
                                horizontal = screenHorizontalPadding,
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
                                NBIcon(icon = item.icon)
                            }
                        )
                    }
                }
            }
        }
    }

}