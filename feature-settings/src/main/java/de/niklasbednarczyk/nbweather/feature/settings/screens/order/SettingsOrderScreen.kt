package de.niklasbednarczyk.nbweather.feature.settings.screens.order

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.draganddrop.NBDragAndDropListItemModel
import de.niklasbednarczyk.nbweather.core.ui.draganddrop.NBDragAndDropView
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.screens.scaffold.NBScaffoldView
import de.niklasbednarczyk.nbweather.core.ui.screens.scaffold.topappbar.NBTopAppBarActionModel
import de.niklasbednarczyk.nbweather.core.ui.screens.scaffold.topappbar.NBTopAppBarItem
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.feature.settings.screens.order.models.SettingsOrderItemType

@Composable
fun SettingsOrderRoute(
    viewModel: SettingsOrderViewModel = hiltViewModel(),
    popBackStack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SettingsOrderScreen(
        uiState = uiState,
        popBackStack = popBackStack,
        updateOrder = viewModel::updateOrder,
        resetToDefault = viewModel::resetToDefault
    )
}

@Composable
internal fun SettingsOrderScreen(
    uiState: SettingsOrderUiState,
    popBackStack: () -> Unit,
    updateOrder: (items: List<SettingsOrderItemType>) -> Unit,
    resetToDefault: () -> Unit
) {
    NBScaffoldView(
        topAppBarItem = NBTopAppBarItem.Small(
            title = NBString.ResString(R.string.screen_settings_order_title),
            popBackStack = popBackStack,
            actions = listOf(
                NBTopAppBarActionModel(
                    icon = NBIcons.Reset,
                    onClick = resetToDefault
                )
            )
        )
    ) {
        NBDragAndDropView(
            items = uiState.items,
            updateKeys = updateOrder,
            getKey = { item ->
                item
            },
            getListItem = { item ->
                NBDragAndDropListItemModel(
                    headlineContent = {
                        Text(
                            text = item.title.asString()
                        )
                    }
                )
            }
        )
    }
}