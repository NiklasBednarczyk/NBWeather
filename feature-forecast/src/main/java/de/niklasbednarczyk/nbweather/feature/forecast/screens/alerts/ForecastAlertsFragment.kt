package de.niklasbednarczyk.nbweather.feature.forecast.screens.alerts

import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.screen.fragment.NBFragment
import de.niklasbednarczyk.nbweather.core.ui.screen.scaffold.topappbar.NBTopAppBarItem

@AndroidEntryPoint
class ForecastAlertsFragment : NBFragment<ForecastAlertsUiState>() {

    override val viewModel: ForecastAlertsViewModel by viewModels()

    override fun createTopAppBarItem(uiState: ForecastAlertsUiState): NBTopAppBarItem {
        return NBTopAppBarItem.Material.Small(
            title = NBString.ResString(R.string.screen_forecast_alerts_title)
        )
    }

    @Composable
    override fun ScaffoldContent(uiState: ForecastAlertsUiState) {
        ForecastAlertsContent(
            uiState = uiState,
            startIntent = ::startIntent
        )
    }

}