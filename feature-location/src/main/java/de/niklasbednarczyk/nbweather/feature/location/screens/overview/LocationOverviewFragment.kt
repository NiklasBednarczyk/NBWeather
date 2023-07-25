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
import de.niklasbednarczyk.nbweather.feature.location.navigation.DestinationsLocation

@AndroidEntryPoint
class LocationOverviewFragment : NBFragmentUiState<LocationOverviewUiState>() {

    override val viewModel: LocationOverviewViewModel by viewModels()

    @Composable
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
        LocationOverviewContent(
            uiState = viewData,
            viewDataFlow = viewModel.viewDataFlow,
            navigateToAlerts = ::navigateToAlerts,
            navigateToDaily = ::navigateToDaily,
            navigateToHourly = ::navigateToHourly
        )
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