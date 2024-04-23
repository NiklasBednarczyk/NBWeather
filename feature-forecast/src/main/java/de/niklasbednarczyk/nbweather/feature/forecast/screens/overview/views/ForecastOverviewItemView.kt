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
    clickableEnabled: Boolean,
    navigateToAlerts: () -> Unit,
    navigateToDaily: (forecastTime: Long?) -> Unit,
    navigateToHourly: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .itemClickableModifier(
                item = item,
                clickableEnabled = clickableEnabled,
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
            clickableEnabled = clickableEnabled,
            navigateToDaily = navigateToDaily
        )
    }
}

@Composable
private fun Content(
    item: ForecastOverviewItem,
    clickableEnabled: Boolean,
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
                    clickableEnabled = clickableEnabled,
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
    nbNullSafe(item.title) { title ->
        Text(
            modifier = Modifier
                .padding(
                    horizontal = screenHorizontalPadding,
                    vertical = screenVerticalPadding
                ),
            text = title.asString(),
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
    clickableEnabled: Boolean,
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
        Modifier.clickable(
            enabled = clickableEnabled,
            onClick = onClick
        )
    } else {
        Modifier
    }

    return this.then(clickableModifier)
}
