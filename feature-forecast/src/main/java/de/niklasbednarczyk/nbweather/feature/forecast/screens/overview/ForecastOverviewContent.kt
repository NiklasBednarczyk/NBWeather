package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.niklasbednarczyk.nbweather.core.ui.dimens.columnVerticalArrangementBig
import de.niklasbednarczyk.nbweather.core.ui.dimens.listContentPaddingValuesVertical
import de.niklasbednarczyk.nbweather.core.ui.dimens.screenHorizontalPadding
import de.niklasbednarczyk.nbweather.core.ui.resource.NBResourceWithLoadingView
import de.niklasbednarczyk.nbweather.core.ui.strings.asString
import de.niklasbednarczyk.nbweather.core.ui.swiperefresh.NBSwipeRefreshFlow
import de.niklasbednarczyk.nbweather.core.ui.swiperefresh.NBSwipeRefreshView
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewAlertsModel
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewItem
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewSummaryModel
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.views.ForecastOverviewAlertsView
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.views.ForecastOverviewSummaryView

@Composable
fun ForecastOverviewContent(
    uiState: ForecastOverviewUiState,
    itemsFlow: NBSwipeRefreshFlow<List<ForecastOverviewItem>>?,
    navigateToAlerts: (latitude: Double?, longitude: Double?) -> Unit
) {
    val location = uiState.locationResource?.dataOrNull
    val latitude = location?.latitude
    val longitude = location?.longitude

    NBResourceWithLoadingView(uiState.itemsResource) { items ->
        NBSwipeRefreshView(refreshFlow = itemsFlow) {
            LazyColumn(
                contentPadding = listContentPaddingValuesVertical,
                verticalArrangement = columnVerticalArrangementBig
            ) {
                items(items) { item ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .paddingModifier(item)
                            .clickableModifier(
                                item = item,
                                navigateToAlerts = { navigateToAlerts(latitude, longitude) }
                            ),
                        verticalArrangement = columnVerticalArrangementBig
                    ) {
                        Title(
                            item = item
                        )
                        Content(
                            item = item
                        )
                    }
                }
            }
        }
    }
}

private fun Modifier.clickableModifier(
    item: ForecastOverviewItem,
    navigateToAlerts: () -> Unit
): Modifier {
    val onClick = when (item) {
        is ForecastOverviewAlertsModel -> navigateToAlerts
        is ForecastOverviewSummaryModel -> null
    }

    val clickableModifier = if (onClick != null) {
        Modifier.clickable(onClick = onClick)
    } else {
        Modifier
    }

    return this.then(clickableModifier)
}

private fun Modifier.paddingModifier(
    item: ForecastOverviewItem
): Modifier {
    val paddingModifier = when (item) {
        is ForecastOverviewAlertsModel -> Modifier
        is ForecastOverviewSummaryModel -> Modifier.padding(
            horizontal = screenHorizontalPadding
        )
    }
    return this.then(paddingModifier)
}

@Composable
private fun Title(item: ForecastOverviewItem) {
    item.title?.let { title ->
        Text(
            text = title.asString(),
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

@Composable
private fun Content(item: ForecastOverviewItem) {
    when (item) {
        is ForecastOverviewAlertsModel -> {
            ForecastOverviewAlertsView(
                alerts = item
            )
        }

        is ForecastOverviewSummaryModel -> {
            ForecastOverviewSummaryView(
                summary = item
            )
        }
    }
}

