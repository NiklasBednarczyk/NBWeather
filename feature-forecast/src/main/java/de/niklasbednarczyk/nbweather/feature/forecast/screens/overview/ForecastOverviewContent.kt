package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import de.niklasbednarczyk.nbweather.core.ui.dimens.columnVerticalArrangementBig
import de.niklasbednarczyk.nbweather.core.ui.dimens.listContentPaddingValuesVertical
import de.niklasbednarczyk.nbweather.core.ui.pulltorefresh.NBPullToRefreshView
import de.niklasbednarczyk.nbweather.core.ui.resource.NBResourceWithLoadingView
import de.niklasbednarczyk.nbweather.core.ui.settings.NBSettings
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.views.ForecastOverviewItemView

const val FORECAST_OVERVIEW_CONTENT_LAZY_COLUMN_TAG = "ForecastOverviewContentLazyColumn"

@Composable
fun ForecastOverviewContent(
    uiState: ForecastOverviewUiState,
    refreshData: suspend (latitude: Double, longitude: Double) -> Unit,
    navigateToAlerts: (latitude: Double, longitude: Double) -> Unit,
    navigateToDaily: (forecastTime: Long?, latitude: Double, longitude: Double) -> Unit,
    navigateToHourly: (latitude: Double, longitude: Double) -> Unit
) {
    NBResourceWithLoadingView(uiState.locationResource) { location ->
        val latitude = location.latitude
        val longitude = location.longitude

        val listState = rememberLazyListState()

        NBPullToRefreshView(
            refreshData = {
                refreshData(latitude, longitude)
            }
        ) { isRefreshing ->
            NBResourceWithLoadingView(uiState.itemsResource) { items ->
                val order = NBSettings.order
                val sortedItems = items.sortedBy { item ->
                    item.getSortOrder(order)
                }
                LazyColumn(
                    modifier = Modifier.testTag(FORECAST_OVERVIEW_CONTENT_LAZY_COLUMN_TAG),
                    state = listState,
                    contentPadding = listContentPaddingValuesVertical,
                    verticalArrangement = columnVerticalArrangementBig
                ) {
                    items(sortedItems) { item ->
                        ForecastOverviewItemView(
                            item = item,
                            clickableEnabled = !isRefreshing,
                            navigateToAlerts = {
                                navigateToAlerts(latitude, longitude)
                            },
                            navigateToDaily = { forecastTime ->
                                navigateToDaily(forecastTime, latitude, longitude)
                            },
                            navigateToHourly = {
                                navigateToHourly(latitude, longitude)
                            }
                        )
                    }
                }
            }
        }
    }
}

