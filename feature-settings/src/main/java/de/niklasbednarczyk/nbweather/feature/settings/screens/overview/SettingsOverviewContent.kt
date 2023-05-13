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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcon
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBDestination
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.core.ui.theme.dimens.dividerPaddingVertical
import de.niklasbednarczyk.nbweather.core.ui.theme.dimens.listContentPaddingValuesVertical
import de.niklasbednarczyk.nbweather.core.ui.theme.dimens.listContentPaddingVertical
import de.niklasbednarczyk.nbweather.core.ui.theme.dimens.screenHorizontalPadding
import de.niklasbednarczyk.nbweather.feature.settings.screens.overview.models.SettingsOverviewItemModel

@Composable
fun SettingsOverviewContent(
    uiState: SettingsOverviewUiState,
    navigate: (destination: NBDestination.WithoutArguments) -> Unit
) {
    val items = uiState.items
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
                            navigate(item.destination)
                        },
                        headlineContent = {
                            Text(item.title.asString())
                        },
                        supportingContent = {
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