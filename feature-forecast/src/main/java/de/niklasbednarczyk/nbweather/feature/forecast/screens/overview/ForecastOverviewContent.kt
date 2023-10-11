package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import de.niklasbednarczyk.nbweather.core.ui.dimens.columnVerticalArrangementBig
import de.niklasbednarczyk.nbweather.core.ui.dimens.listContentPaddingValuesVertical
import de.niklasbednarczyk.nbweather.core.ui.resource.NBResourceWithLoadingView
import de.niklasbednarczyk.nbweather.core.ui.settings.NBSettings
import de.niklasbednarczyk.nbweather.core.ui.swiperefresh.NBSwipeRefreshFlow
import de.niklasbednarczyk.nbweather.core.ui.swiperefresh.NBSwipeRefreshView
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewItem
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.views.ForecastOverviewItemView

@Composable
fun ForecastOverviewContent(
    uiState: ForecastOverviewUiState,
    itemsFlow: NBSwipeRefreshFlow<List<ForecastOverviewItem>>?,
    navigateToAlerts: (latitude: Double?, longitude: Double?) -> Unit,
    navigateToDaily: (forecastTime: Long?, latitude: Double?, longitude: Double?) -> Unit,
    navigateToHourly: (latitude: Double?, longitude: Double?) -> Unit
) {
    val location = uiState.locationResource?.dataOrNull
    val latitude = location?.latitude
    val longitude = location?.longitude

    NBResourceWithLoadingView(uiState.itemsResource) { items ->
        NBSwipeRefreshView(refreshFlow = itemsFlow) {
            val order = NBSettings.order
            val sortedItems = items.sortedBy { item ->
                item.getSortOrder(order)
            }
            LazyColumn(
                contentPadding = listContentPaddingValuesVertical,
                verticalArrangement = columnVerticalArrangementBig
            ) {
                items(sortedItems) { item ->
                    ForecastOverviewItemView(
                        item = item,
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

