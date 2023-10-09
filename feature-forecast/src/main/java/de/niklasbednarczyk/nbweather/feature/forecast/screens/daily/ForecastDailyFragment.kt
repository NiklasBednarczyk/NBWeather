package de.niklasbednarczyk.nbweather.feature.forecast.screens.daily

import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.screen.fragment.NBFragment
import de.niklasbednarczyk.nbweather.core.ui.screen.scaffold.topappbar.NBTopAppBarItem

@AndroidEntryPoint
class ForecastDailyFragment : NBFragment<ForecastDailyUiState>() {

    override val viewModel: ForecastDailyViewModel by viewModels()

    override fun createTopAppBarItem(uiState: ForecastDailyUiState): NBTopAppBarItem {
        return NBTopAppBarItem.Material.Small(
            title = NBString.ResString(R.string.screen_forecast_overview_daily_title)
        )
    }

    @Composable
    override fun ScaffoldContent(uiState: ForecastDailyUiState) {
        ForecastDailyContent(
            uiState = uiState
        )
    }

}