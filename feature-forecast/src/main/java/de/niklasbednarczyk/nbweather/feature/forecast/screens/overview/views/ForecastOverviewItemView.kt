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
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewHourlyModel
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewItem
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewPrecipitationModel
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewSummaryModel

@Composable
fun ForecastOverviewItemView(
    item: ForecastOverviewItem,
    navigateToAlerts: () -> Unit,
    navigateToHourly: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .itemClickableModifier(
                item = item,
                navigateToAlerts = navigateToAlerts,
                navigateToHourly = navigateToHourly
            )
    ) {
        Title(
            item = item
        )
        Content(
            item = item
        )
    }
}

@Composable
private fun Content(
    item: ForecastOverviewItem
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

        is ForecastOverviewHourlyModel -> NBString.ResString(R.string.screen_forecast_hourly_title)
        is ForecastOverviewPrecipitationModel -> NBString.ResString(R.string.screen_forecast_overview_precipitation_title)
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

        is ForecastOverviewHourlyModel -> Modifier.padding(
            vertical = screenVerticalPadding
        )

        is ForecastOverviewPrecipitationModel,
        is ForecastOverviewSummaryModel -> Modifier.padding(
            horizontal = screenHorizontalPadding,
            vertical = screenVerticalPadding
        )
    }
    return this.then(paddingModifier)
}

private fun Modifier.itemClickableModifier(
    item: ForecastOverviewItem,
    navigateToAlerts: () -> Unit,
    navigateToHourly: () -> Unit
): Modifier {
    val onClick = when (item) {
        is ForecastOverviewAlertsModel -> navigateToAlerts

        is ForecastOverviewHourlyModel -> navigateToHourly

        is ForecastOverviewPrecipitationModel,
        is ForecastOverviewSummaryModel -> null
    }

    val clickableModifier = if (onClick != null) {
        Modifier.clickable(onClick = onClick)
    } else {
        Modifier
    }

    return this.then(clickableModifier)
}
