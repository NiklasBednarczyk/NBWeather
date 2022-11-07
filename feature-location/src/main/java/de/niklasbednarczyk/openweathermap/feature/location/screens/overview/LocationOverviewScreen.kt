package de.niklasbednarczyk.openweathermap.feature.location.screens.overview

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIconButton
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons
import de.niklasbednarczyk.openweathermap.core.ui.resource.OwmResourceView
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.OwmCenterAlignedTopAppBar
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.OwmScaffold
import de.niklasbednarczyk.openweathermap.core.ui.scaffold.navigationbar.OwmNavigationBar
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.LocationOverviewNavigationBarItem
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.views.LocationOverviewTodayView

@Composable
fun LocationOverviewScreen(
    viewModel: LocationOverviewViewModel = hiltViewModel(),
    navigationIcon: @Composable () -> Unit,
    navigateToSearch: () -> Unit
) {

    val uiState = viewModel.uiState.collectAsState()


    //TODO (#9) Add swipeToRefresh


    OwmResourceView(
        resource = uiState.value.locationResource
    ) { location ->
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
            //TODO (#9) Do right design with placeholders


            OwmResourceView(uiState.value.viewDataResource) { viewData ->
                when(uiState.value.selectedNavigationBarItem) {
                    LocationOverviewNavigationBarItem.TODAY -> {
                        LocationOverviewTodayView(viewData.todayItems)
                    }
                    LocationOverviewNavigationBarItem.HOURLY -> {
                        Text("HOURLY")
                    }
                    LocationOverviewNavigationBarItem.DAILY -> {
                        Text("DAILY")
                    }
                }
            }






        }
    }


}