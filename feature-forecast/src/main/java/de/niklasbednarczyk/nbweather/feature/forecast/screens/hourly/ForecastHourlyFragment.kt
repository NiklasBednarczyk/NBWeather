package de.niklasbednarczyk.nbweather.feature.forecast.screens.hourly

import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.screen.fragment.NBFragment
import de.niklasbednarczyk.nbweather.core.ui.screen.scaffold.topappbar.NBTopAppBarItem

@AndroidEntryPoint
class ForecastHourlyFragment : NBFragment<ForecastHourlyUiState>() {

    override val viewModel: ForecastHourlyViewModel by viewModels()

    override fun createTopAppBarItem(uiState: ForecastHourlyUiState): NBTopAppBarItem {
        return NBTopAppBarItem.Material.Small(
            title = NBString.ResString(R.string.screen_forecast_hourly_title)
        )
    }

    @Composable
    override fun ScaffoldContent(uiState: ForecastHourlyUiState) {
        ForecastHourlyContent(
            uiState = uiState
        )
    }

}