package de.niklasbednarczyk.nbweather.feature.location.screens.overview

import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import de.niklasbednarczyk.nbweather.core.ui.fragment.NBFragmentUiState
import de.niklasbednarczyk.nbweather.core.ui.fragment.scaffold.navigationbar.NBNavigationBar
import de.niklasbednarczyk.nbweather.core.ui.fragment.scaffold.topappbar.NBTopAppBarActionModel
import de.niklasbednarczyk.nbweather.core.ui.fragment.scaffold.topappbar.NBTopAppBarItem
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBTopLevelDestinations
import de.niklasbednarczyk.nbweather.core.ui.resource.NBResourceWithLoadingView
import de.niklasbednarczyk.nbweather.core.ui.swiperefresh.NBSwipeRefreshView
import de.niklasbednarczyk.nbweather.feature.location.cards.views.LocationCardsView
import de.niklasbednarczyk.nbweather.feature.location.navigation.DestinationsLocation
import de.niklasbednarczyk.nbweather.feature.location.screens.overview.models.LocationOverviewNavigationBarItem
import de.niklasbednarczyk.nbweather.feature.location.screens.overview.views.LocationOverviewDailyView
import de.niklasbednarczyk.nbweather.feature.location.screens.overview.views.LocationOverviewHourlyView

@AndroidEntryPoint
class LocationOverviewFragment : NBFragmentUiState<LocationOverviewUiState>() {

    override val viewModel: LocationOverviewViewModel by viewModels()

    override fun createTopAppBarItem(viewData: LocationOverviewUiState): NBTopAppBarItem {
        val location = viewData.locationResource?.dataOrNull
        return NBTopAppBarItem.Material.CenterAligned(
            title = location?.localizedNameAndCountry,
            action = NBTopAppBarActionModel(
                icon = NBIcons.Search,
                onClick = ::navigateToSearch
            )
        )
    }

    private fun navigateToSearch() {
        navigate(NBTopLevelDestinations.Search)
    }

    @Composable
    override fun BottomBar(viewData: LocationOverviewUiState) {
        NBNavigationBar(
            selectedNavigationBarItem = viewData.selectedNavigationBarItem,
            selectNavigationBarItem = viewModel::updateSelectedNavigationBarItem
        )
    }

    @Composable
    override fun ScaffoldContent(viewData: LocationOverviewUiState) {
        val location = viewData.locationResource?.dataOrNull

        NBResourceWithLoadingView(viewData.viewDataResource) { viewDataOverview ->
            NBSwipeRefreshView(
                refreshFlow = viewModel.viewDataFlow,
            ) {
                when (viewData.selectedNavigationBarItem) {
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

    private fun navigateToAlerts(latitude: Double?, longitude: Double?) {
        if (latitude != null && longitude != null) {
            val route = DestinationsLocation.Alerts.createRouteForNavigation(latitude, longitude)
            navigate(route)
        }
    }

    private fun navigateToDaily(forecastTime: Long, latitude: Double?, longitude: Double?) {
        if (latitude != null && longitude != null) {
            val route = DestinationsLocation.Daily.createRouteForNavigation(
                forecastTime,
                latitude,
                longitude
            )
            navigate(route)
        }
    }

    private fun navigateToHourly(forecastTime: Long, latitude: Double?, longitude: Double?) {
        if (latitude != null && longitude != null) {
            val route = DestinationsLocation.Hourly.createRouteForNavigation(
                forecastTime,
                latitude,
                longitude
            )
            navigate(route)
        }
    }

}