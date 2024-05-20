package de.niklasbednarczyk.nbweather.feature.settings.screens.appearance

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.dimens.dividerPaddingVertical
import de.niklasbednarczyk.nbweather.core.ui.dimens.listContentPaddingValuesVertical
import de.niklasbednarczyk.nbweather.core.ui.screens.scaffold.NBScaffoldView
import de.niklasbednarczyk.nbweather.core.ui.screens.scaffold.topappbar.NBTopAppBarItem
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.feature.settings.screens.appearance.models.SettingsAppearanceItem
import de.niklasbednarczyk.nbweather.feature.settings.views.HeaderView
import de.niklasbednarczyk.nbweather.feature.settings.views.SegmentedControlView

@Composable
fun SettingsAppearanceRoute(
    viewModel: SettingsAppearanceViewModel = hiltViewModel(),
    popBackStack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SettingsAppearanceScreen(
        uiState = uiState,
        popBackStack = popBackStack
    )
}

@Composable
internal fun SettingsAppearanceScreen(
    uiState: SettingsAppearanceUiState,
    popBackStack: () -> Unit
) {
    NBScaffoldView(
        topAppBarItem = NBTopAppBarItem.Small(
            title = NBString.ResString(R.string.screen_settings_appearance_title),
            popBackStack = popBackStack
        )
    ) {
        LazyColumn(
            contentPadding = listContentPaddingValuesVertical
        ) {
            items(uiState.items) { item ->
                Item(
                    item = item
                )
            }
        }
    }
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
private fun Header(
    item: SettingsAppearanceItem.Header
) {
    HeaderView(
        text = item.text
    )
}

@Composable
private fun Item(
    item: SettingsAppearanceItem
) {
    when (item) {
        SettingsAppearanceItem.Divider -> {
            Divider()
        }

        is SettingsAppearanceItem.Header -> {
            Header(
                item = item
            )
        }

        is SettingsAppearanceItem.SegmentedControl -> {
            SegmentedControl(
                item = item
            )
        }

        is SettingsAppearanceItem.SwitchItem -> {
            SwitchItem(
                item = item
            )
        }
    }
}

@Composable
private fun SegmentedControl(
    item: SettingsAppearanceItem.SegmentedControl
) {
    SegmentedControlView(
        segmentedControl = item.segmentedControl
    )
}

@Composable
private fun SwitchItem(
    item: SettingsAppearanceItem.SwitchItem
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