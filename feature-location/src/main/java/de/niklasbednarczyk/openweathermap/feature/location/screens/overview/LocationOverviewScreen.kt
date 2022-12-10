package de.niklasbednarczyk.openweathermap.feature.location.screens.overview

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.TextUnit
import androidx.hilt.navigation.compose.hiltViewModel
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIconButton
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons
import de.niklasbednarczyk.openweathermap.core.ui.resource.OwmResourceView
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.OwmScaffold
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.navigationbar.OwmNavigationBar
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.topappbar.OwmCenterAlignedTopAppBar
import de.niklasbednarczyk.openweathermap.core.ui.swiperefresh.OwmSwipeRefreshView
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.LocationOverviewNavigationBarItem
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.views.LocationOverviewHourlyView
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.views.LocationOverviewTodayView

@Composable
fun LocationOverviewScreen(
    viewModel: LocationOverviewViewModel = hiltViewModel(),
    navigationIcon: @Composable () -> Unit,
    navigateToSearch: () -> Unit,
    navigateToAlerts: (Double?, Double?) -> Unit
) {

    val uiState = viewModel.uiState.collectAsState()

    OwmScaffold(
        topBar = { scrollBehavior ->
            OwmCenterAlignedTopAppBar(
                scrollBehavior = scrollBehavior,
                navigationIcon = navigationIcon,
                title = uiState.value.location?.localizedNameAndCountry,
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
        OwmResourceView(uiState.value) {
            OwmSwipeRefreshView(
                refreshFlow = viewModel.viewDataFlow,
            ) {
                when (uiState.value.selectedNavigationBarItem) {
                    LocationOverviewNavigationBarItem.TODAY -> {
                        LocationOverviewTodayView(
                            todayItems = uiState.value.viewData.todayItems,
                            navigateToAlerts = {
                                navigateToAlerts(
                                    uiState.value.location?.latitude,
                                    uiState.value.location?.longitude
                                )
                            }
                        )
                    }
                    LocationOverviewNavigationBarItem.HOURLY -> {
                        LocationOverviewHourlyView(
                            hourlyItems = uiState.value.viewData.hourlyItems
                        )
                    }
                    LocationOverviewNavigationBarItem.DAILY -> {
                        //TODO (#9) Add daily
                    }
                }
            }
        }
    }

}