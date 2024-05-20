package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
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

const val FORECAST_OVERVIEW_ITEM_VIEW_TAG = "ForecastOverviewItemView"

@Composable
fun ForecastOverviewItemView(
    item: ForecastOverviewItem,
    clickableEnabled: Boolean,
    navigateToForecastAlerts: () -> Unit,
    navigateToForecastDaily: (forecastTime: Long?) -> Unit,
    navigateToForecastHourly: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .itemClickableModifier(
                item = item,
                clickableEnabled = clickableEnabled,
                navigateToForecastAlerts = navigateToForecastAlerts,
                navigateToForecastDaily = {
                    navigateToForecastDaily(null)
                },
                navigateToForecastHourly = navigateToForecastHourly
            )
            .testTag(FORECAST_OVERVIEW_ITEM_VIEW_TAG)
    ) {
        Title(
            item = item
        )
        Content(
            item = item,
            clickableEnabled = clickableEnabled,
            navigateToForecastDaily = navigateToForecastDaily
        )
    }
}

@Composable
private fun Content(
    item: ForecastOverviewItem,
    clickableEnabled: Boolean,
    navigateToForecastDaily: (forecastTime: Long?) -> Unit,
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
                    navigateToForecastDaily = navigateToForecastDaily
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
    navigateToForecastAlerts: () -> Unit,
    navigateToForecastDaily: () -> Unit,
    navigateToForecastHourly: () -> Unit
): Modifier {
    val onClick = when (item) {
        is ForecastOverviewAlertsModel -> navigateToForecastAlerts

        is ForecastOverviewCurrentWeatherModel,
        is ForecastOverviewPrecipitationModel,
        is ForecastOverviewSummaryModel,
        is ForecastOverviewSunAndMoonModel -> null

        is ForecastOverviewDailyModel -> navigateToForecastDaily

        is ForecastOverviewHourlyModel -> navigateToForecastHourly
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
