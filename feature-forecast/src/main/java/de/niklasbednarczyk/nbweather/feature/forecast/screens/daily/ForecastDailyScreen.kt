package de.niklasbednarczyk.nbweather.feature.forecast.screens.daily

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.dimens.columnVerticalArrangementBig
import de.niklasbednarczyk.nbweather.core.ui.dimens.listContentPaddingValuesVertical
import de.niklasbednarczyk.nbweather.core.ui.dimens.screenHorizontalPadding
import de.niklasbednarczyk.nbweather.core.ui.grid.NBGridView
import de.niklasbednarczyk.nbweather.core.ui.pager.NBPagerView
import de.niklasbednarczyk.nbweather.core.ui.resource.NBResourceWithoutLoadingView
import de.niklasbednarczyk.nbweather.core.ui.screens.scaffold.NBScaffoldView
import de.niklasbednarczyk.nbweather.core.ui.screens.scaffold.topappbar.NBTopAppBarItem
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.feature.forecast.extensions.toGridItems
import de.niklasbednarczyk.nbweather.feature.forecast.models.sunandmoon.SunAndMoonItem
import de.niklasbednarczyk.nbweather.feature.forecast.screens.daily.models.ForecastDailyDayInfoItem
import de.niklasbednarczyk.nbweather.feature.forecast.views.LimitTemperaturesView
import de.niklasbednarczyk.nbweather.feature.forecast.views.MoonPhaseGridView
import de.niklasbednarczyk.nbweather.feature.forecast.views.MoonTimesGridView
import de.niklasbednarczyk.nbweather.feature.forecast.views.SunTimesGridView
import de.niklasbednarczyk.nbweather.feature.forecast.views.WeatherView

@Composable
fun ForecastDailyRoute(
    viewModel: ForecastDailyViewModel = hiltViewModel(),
    popBackStack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ForecastDailyScreen(
        uiState = uiState,
        popBackStack = popBackStack
    )
}

@Composable
internal fun ForecastDailyScreen(
    uiState: ForecastDailyUiState,
    popBackStack: () -> Unit
) {
    NBScaffoldView(
        topAppBarItem = NBTopAppBarItem.Small(
            title = NBString.ResString(R.string.screen_forecast_overview_daily_title),
            popBackStack = popBackStack
        )
    ) {
        NBResourceWithoutLoadingView(uiState.viewDataResource) { viewData ->
            NBPagerView(viewData) { pagerItem ->
                LazyColumn(
                    contentPadding = listContentPaddingValuesVertical,
                    verticalArrangement = columnVerticalArrangementBig
                ) {
                    items(pagerItem.infoItems) { infoItem ->
                        InfoItem(
                            infoItem = infoItem
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun Divider() {
    HorizontalDivider()
}

@Composable
private fun Forecasts(
    forecasts: ForecastDailyDayInfoItem.Forecasts
) {
    val gridItems = forecasts.forecastValues.toGridItems()

    NBGridView(
        modifier = Modifier.padding(
            horizontal = screenHorizontalPadding
        ),
        items = gridItems
    )
}

@Composable
private fun InfoItem(
    infoItem: ForecastDailyDayInfoItem
) {
    when (infoItem) {
        is ForecastDailyDayInfoItem.Divider -> {
            Divider()
        }

        is ForecastDailyDayInfoItem.Forecasts -> {
            Forecasts(
                forecasts = infoItem
            )
        }

        is ForecastDailyDayInfoItem.Summary -> {
            Summary(
                summary = infoItem
            )
        }

        is ForecastDailyDayInfoItem.SunAndMoon -> {
            SunAndMoon(
                sunAndMoon = infoItem
            )
        }
    }
}

@Composable
private fun Summary(
    summary: ForecastDailyDayInfoItem.Summary
) {
    Column(
        modifier = Modifier.padding(
            horizontal = screenHorizontalPadding
        ),
        verticalArrangement = columnVerticalArrangementBig
    ) {
        Text(
            text = summary.forecastTime.dateFull.asString(),
            style = MaterialTheme.typography.headlineLarge
        )
        WeatherView(
            weatherCondition = summary.weatherCondition,
            weatherIcon = summary.weatherIcon
        )
        LimitTemperaturesView(
            minTemperature = summary.minTemperature,
            maxTemperature = summary.maxTemperature
        )
    }

}

@Composable
private fun SunAndMoon(
    sunAndMoon: ForecastDailyDayInfoItem.SunAndMoon
) {
    Column(
        modifier = Modifier.padding(
            horizontal = screenHorizontalPadding
        ),
        verticalArrangement = columnVerticalArrangementBig
    ) {
        sunAndMoon.items.forEach { item ->
            when (item) {
                is SunAndMoonItem.MoonPhase -> {
                    MoonPhaseGridView(
                        moonPhase = item
                    )
                }

                is SunAndMoonItem.MoonTimes -> {
                    MoonTimesGridView(
                        moonTimes = item,
                    )
                }

                is SunAndMoonItem.SunTimes -> {
                    SunTimesGridView(
                        sunTimes = item,
                    )
                }
            }
        }
    }
}
