package de.niklasbednarczyk.nbweather.feature.settings.screens.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.niklasbednarczyk.nbweather.core.ui.dimens.dividerPaddingVertical
import de.niklasbednarczyk.nbweather.core.ui.dimens.listContentPaddingValuesVertical
import de.niklasbednarczyk.nbweather.core.ui.dimens.listContentPaddingVertical
import de.niklasbednarczyk.nbweather.core.ui.dimens.screenHorizontalPadding
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcon
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBDestination
import de.niklasbednarczyk.nbweather.core.ui.segmented.NBSegmentedControlView
import de.niklasbednarczyk.nbweather.core.ui.slider.NBSlider
import de.niklasbednarczyk.nbweather.core.ui.stickyheader.NBStickyHeaderView
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.core.ui.text.NBTextSingleLine
import de.niklasbednarczyk.nbweather.feature.settings.screens.list.models.SettingsListItemModel

private val horizontalPadding = screenHorizontalPadding
private val verticalPadding = 8.dp

@Composable
fun SettingsListContent(
    uiState: SettingsListUiState,
    navigate: (destination: NBDestination.WithoutArguments) -> Unit
) {
    val items = uiState.items
    val firstItemIsHeader = items.firstOrNull() is SettingsListItemModel.Header
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
        uiState.stickyHeader?.let { stickyHeader ->
            stickyHeader {
                NBStickyHeaderView(
                    model = stickyHeader
                )
            }
        }
        items(items) { item ->
            when (item) {
                is SettingsListItemModel.Divider -> {
                    Divider(
                        modifier = Modifier.padding(
                            vertical = dividerPaddingVertical
                        )
                    )
                }

                is SettingsListItemModel.Header -> {
                    Text(
                        modifier = Modifier.padding(
                            horizontal = horizontalPadding,
                            vertical = verticalPadding * 2
                        ),
                        text = item.text.asString(),
                        style = MaterialTheme.typography.titleSmall
                    )
                }

                is SettingsListItemModel.ItemButtons -> {
                    NBSegmentedControlView(
                        modifier = Modifier.padding(
                            horizontal = horizontalPadding,
                            vertical = verticalPadding
                        ),
                        segmentedControl = item.segmentedControl
                    )
                }

                is SettingsListItemModel.ItemDestination -> {
                    ListItem(
                        modifier = Modifier.clickable {
                            navigate(item.destination)
                        },
                        headlineContent = {
                            Text(item.title.asString())
                        },
                        supportingContent = {
                            NBTextSingleLine(text = item.value.asString())
                        },
                        leadingContent = {
                            NBIcon(icon = item.icon)
                        }
                    )
                }

                is SettingsListItemModel.ItemSlider -> {
                    NBSlider(
                        modifier = Modifier.padding(
                            horizontal = horizontalPadding,
                            vertical = verticalPadding
                        ),
                        model = item.slider
                    )
                }

                is SettingsListItemModel.ItemSwitch -> {
                    ListItem(
                        modifier = Modifier.clickable {
                            item.onCheckedChange(!item.checked)
                        },
                        headlineContent = {
                            Text(item.title.asString())
                        },
                        supportingContent = {
                            Text(item.value.asString())
                        },
                        trailingContent = {
                            Switch(
                                checked = item.checked,
                                onCheckedChange = item.onCheckedChange
                            )
                        }
                    )
                }
            }
        }
    }


}