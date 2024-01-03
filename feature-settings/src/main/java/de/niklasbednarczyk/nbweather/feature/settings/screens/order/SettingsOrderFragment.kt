package de.niklasbednarczyk.nbweather.feature.settings.screens.order

import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.screen.fragment.NBFragment
import de.niklasbednarczyk.nbweather.core.ui.screen.scaffold.topappbar.NBTopAppBarActionModel
import de.niklasbednarczyk.nbweather.core.ui.screen.scaffold.topappbar.NBTopAppBarItem

@AndroidEntryPoint
class SettingsOrderFragment : NBFragment<SettingsOrderUiState>() {

    override val viewModel: SettingsOrderViewModel by viewModels()

    override fun createTopAppBarItem(uiState: SettingsOrderUiState): NBTopAppBarItem {
        return NBTopAppBarItem.Small(
            title = NBString.ResString(R.string.screen_settings_order_title),
            actions = listOf(
                NBTopAppBarActionModel(
                    icon = NBIcons.Reset,
                    onClick = viewModel::resetToDefault
                )
            )
        )
    }

    @Composable
    override fun ScaffoldContent(uiState: SettingsOrderUiState) {
        SettingsOrderContent(
            uiState = uiState,
            updateOrder = viewModel::updateOrder
        )
    }

}