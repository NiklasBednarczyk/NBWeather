package de.niklasbednarczyk.openweathermap.feature.location.screens.overview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIconButton
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons
import de.niklasbednarczyk.openweathermap.core.ui.resource.OwmResourceWithLoadingView
import de.niklasbednarczyk.openweathermap.core.ui.resource.OwmResourceWithoutLoadingView
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.OwmScaffold
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.navigationbar.OwmNavigationBar
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.topappbar.OwmCenterAlignedTopAppBar
import de.niklasbednarczyk.openweathermap.core.ui.swiperefresh.OwmSwipeRefreshView
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.LocationOverviewNavigationBarItem
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.views.LocationOverviewDailyView
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.views.LocationOverviewHourlyView
import de.niklasbednarczyk.openweathermap.feature.location.cards.views.LocationCardsView

@Composable
fun LocationOverviewScreen(
    viewModel: LocationOverviewViewModel = hiltViewModel(),
    navigationIcon: @Composable () -> Unit,
    navigateToSearch: () -> Unit,
    navigateToAlerts: (Double?, Double?) -> Unit,
    navigateToHourly: (Long, Double?, Double?) -> Unit
) {

    val uiState = viewModel.uiState.collectAsState()

    OwmResourceWithoutLoadingView(uiState.value.locationResource) { location ->
        OwmScaffold(
            topBar = { scrollBehavior ->
                OwmCenterAlignedTopAppBar(
                    scrollBehavior = scrollBehavior,
                    navigationIcon = navigationIcon,
                    title = location?.localizedNameAndCountry,
                    actions = {
                        OwmIconButton(
                            icon = OwmIcons.Search,
                            onClick = navigateToSearch
                        )
                    }
                )
            },
            bottomBar = {
                OwmNavigationBar(
                    selectedNavigationBarItem = uiState.value.selectedNavigationBarItem,
                    selectNavigationBarItem = viewModel::updateSelectedNavigationBarItem
                )
            }
        ) {
            OwmResourceWithLoadingView(uiState.value.viewDataResource) { viewData ->
                OwmSwipeRefreshView(
                    refreshFlow = viewModel.viewDataFlow,
                ) {
                    when (uiState.value.selectedNavigationBarItem) {
                        LocationOverviewNavigationBarItem.TODAY -> {
                            LocationCardsView(
                                cardItems = viewData.todayCardItems,
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
                                hourlyMap = viewData.hourlyMap,
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
                                dailyModels = viewData.dailyModels
                            )
                        }
                    }
                }
            }
        }
    }


}