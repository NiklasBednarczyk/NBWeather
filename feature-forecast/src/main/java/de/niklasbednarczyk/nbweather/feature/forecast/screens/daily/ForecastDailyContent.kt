package de.niklasbednarczyk.nbweather.feature.forecast.screens.daily

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.niklasbednarczyk.nbweather.core.ui.dimens.columnVerticalArrangementBig
import de.niklasbednarczyk.nbweather.core.ui.dimens.listContentPaddingValuesVertical
import de.niklasbednarczyk.nbweather.core.ui.dimens.screenHorizontalPadding
import de.niklasbednarczyk.nbweather.core.ui.grid.NBGridView
import de.niklasbednarczyk.nbweather.core.ui.pager.NBPagerView
import de.niklasbednarczyk.nbweather.core.ui.resource.NBResourceWithoutLoadingView
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.feature.forecast.extensions.toGridItems
import de.niklasbednarczyk.nbweather.feature.forecast.screens.daily.models.ForecastDailyDayInfoItem
import de.niklasbednarczyk.nbweather.feature.forecast.views.LimitTemperaturesView
import de.niklasbednarczyk.nbweather.feature.forecast.views.MoonPhaseGridView
import de.niklasbednarczyk.nbweather.feature.forecast.views.MoonTimesGridView
import de.niklasbednarczyk.nbweather.feature.forecast.views.SunGridView
import de.niklasbednarczyk.nbweather.feature.forecast.views.WeatherView

@Composable
fun ForecastDailyContent(
    uiState: ForecastDailyUiState
) {
    NBResourceWithoutLoadingView(uiState.viewDataResource) { viewData ->
        NBPagerView(viewData) { pagerItem ->
            LazyColumn(
                contentPadding = listContentPaddingValuesVertical,
                verticalArrangement = columnVerticalArrangementBig
            ) {
                items(pagerItem.infoItems) { infoItem ->
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
            }

        }
    }
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
        SunGridView(
            sunrise = sunAndMoon.sunrise,
            sunset = sunAndMoon.sunset
        )
        MoonTimesGridView(
            moonrise = sunAndMoon.moonrise,
            moonset = sunAndMoon.moonset
        )
        MoonPhaseGridView(
            moonPhase = sunAndMoon.moonPhase
        )
    }
}
