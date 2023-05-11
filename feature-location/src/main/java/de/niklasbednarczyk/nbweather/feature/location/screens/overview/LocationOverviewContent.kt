package de.niklasbednarczyk.nbweather.feature.location.screens.overview

import androidx.compose.runtime.Composable
import de.niklasbednarczyk.nbweather.core.ui.resource.NBResourceWithLoadingView
import de.niklasbednarczyk.nbweather.core.ui.swiperefresh.NBSwipeRefreshFlow
import de.niklasbednarczyk.nbweather.core.ui.swiperefresh.NBSwipeRefreshView
import de.niklasbednarczyk.nbweather.feature.location.cards.views.LocationCardsView
import de.niklasbednarczyk.nbweather.feature.location.screens.overview.models.LocationOverviewNavigationBarItem
import de.niklasbednarczyk.nbweather.feature.location.screens.overview.models.LocationOverviewViewData
import de.niklasbednarczyk.nbweather.feature.location.screens.overview.views.LocationOverviewDailyView
import de.niklasbednarczyk.nbweather.feature.location.screens.overview.views.LocationOverviewHourlyView

@Composable
fun LocationOverviewContent(
    uiState: LocationOverviewUiState,
    viewDataFlow: NBSwipeRefreshFlow<LocationOverviewViewData>?,
    navigateToAlerts: (latitude: Double?, longitude: Double?) -> Unit,
    navigateToDaily: (forecastTime: Long, latitude: Double?, longitude: Double?) -> Unit,
    navigateToHourly: (forecastTime: Long, latitude: Double?, longitude: Double?) -> Unit
) {
    val location = uiState.locationResource?.dataOrNull

    NBResourceWithLoadingView(uiState.viewDataResource) { viewDataOverview ->
        NBSwipeRefreshView(
            refreshFlow = viewDataFlow,
        ) {
            when (uiState.selectedNavigationBarItem) {
                LocationOverviewNavigationBarItem.TODAY -> {
                    LocationCardsView(
                        cardItems = viewDataOverview.todayCardItems,
                        navigateToAlerts = {
                            navigateToAlerts(
                                location?.latitude,
                                location?.longitude
                            )
                        }
                    )
                }
                LocationOverviewNavigationBarItem.HOURLY -> {
                    LocationOverviewHourlyView(
                        hourlyMap = viewDataOverview.hourlyMap,
                        navigateToHourly = { forecastTime ->
                            navigateToHourly(
                                forecastTime,
                                location?.latitude,
                                location?.longitude
                            )
                        }
                    )
                }
                LocationOverviewNavigationBarItem.DAILY -> {
                    LocationOverviewDailyView(
                        dailyModels = viewDataOverview.dailyModels,
                        navigateToDaily = { forecastTime ->
                            navigateToDaily(
                                forecastTime,
                                location?.latitude,
                                location?.longitude
                            )
                        }
                    )
                }
            }
        }
    }
}

