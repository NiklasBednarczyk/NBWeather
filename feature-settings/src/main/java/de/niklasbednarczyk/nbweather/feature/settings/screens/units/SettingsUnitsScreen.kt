package de.niklasbednarczyk.nbweather.feature.settings.screens.units

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.dimens.listContentPaddingValuesVertical
import de.niklasbednarczyk.nbweather.core.ui.screens.scaffold.NBScaffoldView
import de.niklasbednarczyk.nbweather.core.ui.screens.scaffold.topappbar.NBTopAppBarItem
import de.niklasbednarczyk.nbweather.feature.settings.screens.units.models.SettingsUnitsItemModel
import de.niklasbednarczyk.nbweather.feature.settings.views.HeaderView
import de.niklasbednarczyk.nbweather.feature.settings.views.SegmentedControlView

@Composable
fun SettingsUnitsRoute(
    viewModel: SettingsUnitsViewModel = hiltViewModel(),
    popBackStack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SettingsUnitsScreen(
        uiState = uiState,
        popBackStack = popBackStack
    )
}

@Composable
internal fun SettingsUnitsScreen(
    uiState: SettingsUnitsUiState,
    popBackStack: () -> Unit
) {
    NBScaffoldView(
        topAppBarItem = NBTopAppBarItem.Small(
            title = NBString.ResString(R.string.screen_settings_units_title),
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
private fun Item(
    item: SettingsUnitsItemModel
) {
    Column {
        HeaderView(
            text = item.headerText
        )
        SegmentedControlView(
            segmentedControl = item.segmentedControl
        )
    }
}