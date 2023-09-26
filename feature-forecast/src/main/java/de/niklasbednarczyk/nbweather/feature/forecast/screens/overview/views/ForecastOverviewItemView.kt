package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.dimens.columnVerticalArrangementBig
import de.niklasbednarczyk.nbweather.core.ui.dimens.screenHorizontalPadding
import de.niklasbednarczyk.nbweather.core.ui.dimens.screenVerticalPadding
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewAlertsModel
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewCurrentWeatherModel
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewDailyModel
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewHourlyModel
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewItem
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewPrecipitationModel
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewSummaryModel
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewSunAndMoonModel

@Composable
fun ForecastOverviewItemView(
    item: ForecastOverviewItem,
    navigateToAlerts: () -> Unit,
    navigateToDaily: (forecastTime: Long?) -> Unit,
    navigateToHourly: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .itemClickableModifier(
                item = item,
                navigateToAlerts = navigateToAlerts,
                navigateToDaily = {
                    navigateToDaily(null)
                },
                navigateToHourly = navigateToHourly
            )
    ) {
        Title(
            item = item
        )
        Content(
            item = item,
            navigateToDaily = navigateToDaily
        )
    }
}

@Composable
private fun Content(
    item: ForecastOverviewItem,
    navigateToDaily: (forecastTime: Long?) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .contentPaddingModifier(item),
        verticalArrangement = columnVerticalArrangementBig
    ) {
        when (item) {
            is ForecastOverviewAlertsModel -> {
                ForecastOverviewAlertsView(
                    alerts = item
                )
            }

            is ForecastOverviewCurrentWeatherModel -> {
                ForecastOverviewCurrentWeatherView(
                    currentWeather = item
                )
            }

            is ForecastOverviewDailyModel -> {
                ForecastOverviewDailyView(
                    daily = item,
                    navigateToDaily = navigateToDaily
                )
            }

            is ForecastOverviewHourlyModel -> {
                ForecastOverviewHourlyView(
                    hourly = item
                )
            }

            is ForecastOverviewPrecipitationModel -> {
                ForecastOverviewPrecipitationView(
                    precipitation = item
                )
            }

            is ForecastOverviewSummaryModel -> {
                ForecastOverviewSummaryView(
                    summary = item
                )
            }

            is ForecastOverviewSunAndMoonModel -> {
                ForecastOverviewSunAndMoonView(
                    sunAndMoon = item
                )
            }
        }
    }
}

@Composable
private fun Title(
    item: ForecastOverviewItem
) {
    val title = when (item) {
        is ForecastOverviewAlertsModel,
        is ForecastOverviewSummaryModel -> null

        is ForecastOverviewCurrentWeatherModel -> NBString.ResString(R.string.screen_forecast_overview_current_weather_title)

        is ForecastOverviewDailyModel -> NBString.ResString(R.string.screen_forecast_daily_title)

        is ForecastOverviewHourlyModel -> NBString.ResString(R.string.screen_forecast_hourly_title)

        is ForecastOverviewPrecipitationModel -> NBString.ResString(R.string.screen_forecast_overview_precipitation_title)

        is ForecastOverviewSunAndMoonModel -> NBString.ResString(R.string.screen_forecast_overview_sun_and_moon_title)
    }
    nbNullSafe(title) { t ->
        Text(
            modifier = Modifier
                .padding(
                    horizontal = screenHorizontalPadding,
                    vertical = screenVerticalPadding
                ),
            text = t.asString(),
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

private fun Modifier.contentPaddingModifier(
    item: ForecastOverviewItem
): Modifier {
    val paddingModifier = when (item) {
        is ForecastOverviewAlertsModel -> Modifier

        is ForecastOverviewCurrentWeatherModel,
        is ForecastOverviewPrecipitationModel,
        is ForecastOverviewSummaryModel,
        is ForecastOverviewSunAndMoonModel -> Modifier.padding(
            horizontal = screenHorizontalPadding,
            vertical = screenVerticalPadding
        )

        is ForecastOverviewDailyModel,
        is ForecastOverviewHourlyModel -> Modifier.padding(
            vertical = screenVerticalPadding
        )
    }
    return this.then(paddingModifier)
}

private fun Modifier.itemClickableModifier(
    item: ForecastOverviewItem,
    navigateToAlerts: () -> Unit,
    navigateToDaily: () -> Unit,
    navigateToHourly: () -> Unit
): Modifier {
    val onClick = when (item) {
        is ForecastOverviewAlertsModel -> navigateToAlerts

        is ForecastOverviewCurrentWeatherModel,
        is ForecastOverviewPrecipitationModel,
        is ForecastOverviewSummaryModel,
        is ForecastOverviewSunAndMoonModel -> null

        is ForecastOverviewDailyModel -> navigateToDaily

        is ForecastOverviewHourlyModel -> navigateToHourly
    }

    val clickableModifier = if (onClick != null) {
        Modifier.clickable(onClick = onClick)
    } else {
        Modifier
    }

    return this.then(clickableModifier)
}
