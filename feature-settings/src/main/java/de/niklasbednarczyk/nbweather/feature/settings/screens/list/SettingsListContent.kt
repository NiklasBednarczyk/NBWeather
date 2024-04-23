package de.niklasbednarczyk.nbweather.feature.settings.screens.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.niklasbednarczyk.nbweather.core.ui.dimens.dividerPaddingVertical
import de.niklasbednarczyk.nbweather.core.ui.dimens.listContentPaddingValuesVertical
import de.niklasbednarczyk.nbweather.core.ui.dimens.listContentPaddingVertical
import de.niklasbednarczyk.nbweather.core.ui.dimens.screenHorizontalPadding
import de.niklasbednarczyk.nbweather.core.ui.dimens.screenVerticalPadding
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconView
import de.niklasbednarczyk.nbweather.core.ui.list.nBStickyHeader
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBDestination
import de.niklasbednarczyk.nbweather.core.ui.segmented.NBSegmentedControlView
import de.niklasbednarczyk.nbweather.core.ui.slider.NBSliderView
import de.niklasbednarczyk.nbweather.core.ui.stickyheader.NBStickyHeaderView
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.core.ui.text.NBTextSingleLine
import de.niklasbednarczyk.nbweather.feature.settings.screens.list.models.SettingsListItemModel

private val horizontalPadding = screenHorizontalPadding
private val verticalPadding = screenVerticalPadding

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
            nBStickyHeader {
                NBStickyHeaderView(
                    model = stickyHeader
                )
            }
        }
        items(items) { item ->
            when (item) {
                is SettingsListItemModel.Divider -> {
                    Divider()
                }

                is SettingsListItemModel.Header -> {
                    Header(
                        item = item
                    )
                }

                is SettingsListItemModel.ItemButtons -> {
                    ItemButtons(
                        item = item
                    )
                }

                is SettingsListItemModel.ItemDestination -> {
                    ItemDestination(
                        item = item,
                        navigate = navigate
                    )
                }

                is SettingsListItemModel.ItemSlider -> {
                    ItemSlider(
                        item = item
                    )
                }

                is SettingsListItemModel.ItemSwitch -> {
                    ItemSwitch(
                        item = item
                    )
                }
            }
        }
    }
}

@Composable
private fun Header(
    item: SettingsListItemModel.Header
) {
    Text(
        modifier = Modifier.padding(
            horizontal = horizontalPadding,
            vertical = verticalPadding * 2
        ),
        text = item.text.asString(),
        style = MaterialTheme.typography.titleSmall
    )
}

@Composable
private fun ItemButtons(
    item: SettingsListItemModel.ItemButtons
) {
    NBSegmentedControlView(
        modifier = Modifier.padding(
            horizontal = horizontalPadding,
            vertical = verticalPadding
        ),
        segmentedControl = item.segmentedControl
    )
}

@Composable
private fun ItemDestination(
    item: SettingsListItemModel.ItemDestination,
    navigate: (destination: NBDestination.WithoutArguments) -> Unit
) {
    ListItem(
        modifier = Modifier.clickable {
            navigate(item.destination)
        },
        headlineContent = {
            Text(item.title.asString())
        },
        supportingContent = {
            NBTextSingleLine(text = item.description.asString())
        },
        leadingContent = {
            NBIconView(icon = item.icon)
        }
    )
}

@Composable
private fun Divider() {
    HorizontalDivider(
        modifier = Modifier.padding(
            vertical = dividerPaddingVertical
        )
    )
}

@Composable
private fun ItemSlider(
    item: SettingsListItemModel.ItemSlider
) {
    NBSliderView(
        modifier = Modifier.padding(
            horizontal = horizontalPadding,
            vertical = verticalPadding
        ),
        model = item.slider
    )
}

@Composable
private fun ItemSwitch(
    item: SettingsListItemModel.ItemSwitch
) {
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