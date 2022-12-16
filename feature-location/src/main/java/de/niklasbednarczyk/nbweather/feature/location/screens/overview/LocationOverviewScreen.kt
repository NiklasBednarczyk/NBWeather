package de.niklasbednarczyk.nbweather.feature.location.screens.overview

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconButton
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.resource.NBResourceWithLoadingView
import de.niklasbednarczyk.nbweather.core.ui.resource.NBResourceWithoutLoadingView
import de.niklasbednarczyk.nbweather.core.ui.scaffold.NBScaffold
import de.niklasbednarczyk.nbweather.core.ui.scaffold.navigationbar.NBNavigationBar
import de.niklasbednarczyk.nbweather.core.ui.scaffold.topappbar.NBCenterAlignedTopAppBar
import de.niklasbednarczyk.nbweather.core.ui.swiperefresh.NBSwipeRefreshView
import de.niklasbednarczyk.nbweather.feature.location.cards.views.LocationCardsView
import de.niklasbednarczyk.nbweather.feature.location.screens.overview.models.LocationOverviewNavigationBarItem
import de.niklasbednarczyk.nbweather.feature.location.screens.overview.views.LocationOverviewDailyView
import de.niklasbednarczyk.nbweather.feature.location.screens.overview.views.LocationOverviewHourlyView

@Composable
fun LocationOverviewScreen(
    viewModel: LocationOverviewViewModel = hiltViewModel(),
    navigationIcon: @Composable () -> Unit,
    navigateToAlerts: (Double?, Double?) -> Unit,
    navigateToDaily: (Long, Double?, Double?) -> Unit,
    navigateToHourly: (Long, Double?, Double?) -> Unit,
    navigateToSearch: () -> Unit
) {

    val uiState = viewModel.uiState.collectAsState()

    NBResourceWithoutLoadingView(uiState.value.locationResource) { location ->
        NBScaffold(
            topBar = { scrollBehavior ->
                NBCenterAlignedTopAppBar(
                    scrollBehavior = scrollBehavior,
                    navigationIcon = navigationIcon,
                    title = location?.localizedNameAndCountry,
                    actions = {
                        NBIconButton(
                            icon = NBIcons.Search,
                            onClick = navigateToSearch
                        )
                    }
                )
            },
            bottomBar = {
                NBNavigationBar(
                    selectedNavigationBarItem = uiState.value.selectedNavigationBarItem,
                    selectNavigationBarItem = viewModel::updateSelectedNavigationBarItem
                )
            }
        ) {
            NBResourceWithLoadingView(uiState.value.viewDataResource) { viewData ->
                NBSwipeRefreshView(
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
                                dailyModels = viewData.dailyModels,
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
    }


}