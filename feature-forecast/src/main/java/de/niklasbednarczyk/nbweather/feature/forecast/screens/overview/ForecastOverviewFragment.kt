package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview

import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBTopLevelDestinations
import de.niklasbednarczyk.nbweather.core.ui.screen.fragment.NBFragment
import de.niklasbednarczyk.nbweather.core.ui.screen.scaffold.topappbar.NBTopAppBarActionModel
import de.niklasbednarczyk.nbweather.core.ui.screen.scaffold.topappbar.NBTopAppBarItem
import de.niklasbednarczyk.nbweather.feature.forecast.navigation.DestinationsForecast

@AndroidEntryPoint
class ForecastOverviewFragment : NBFragment<ForecastOverviewUiState>() {

    override val viewModel: ForecastOverviewViewModel by viewModels()

    override fun createTopAppBarItem(uiState: ForecastOverviewUiState): NBTopAppBarItem {
        val location = uiState.locationResource.dataOrNull
        return NBTopAppBarItem.Material.CenterAligned(
            title = location?.localizedNameAndCountry,
            action = NBTopAppBarActionModel(
                icon = NBIcons.Search,
                onClick = ::navigateToSearch
            )
        )
    }

    @Composable
    override fun ScaffoldContent(uiState: ForecastOverviewUiState) {
        ForecastOverviewContent(
            uiState = uiState,
            itemsFlow = viewModel.itemsFlow,
            navigateToAlerts = ::navigateToAlerts,
            navigateToDaily = ::navigateToDaily,
            navigateToHourly = ::navigateToHourly
        )
    }

    private fun navigateToSearch() {
        navigate(NBTopLevelDestinations.Search)
    }

    private fun navigateToAlerts(
        latitude: Double?,
        longitude: Double?
    ) {
        val route = DestinationsForecast.Alerts.createRouteForNavigation(
            latitude = latitude,
            longitude = longitude
        )
        navigate(route)
    }

    private fun navigateToDaily(
        forecastTime: Long?,
        latitude: Double?,
        longitude: Double?
    ) {
        val route = DestinationsForecast.Daily.createRouteForNavigation(
            forecastTime = forecastTime,
            latitude = latitude,
            longitude = longitude
        )
        navigate(route)
    }

    private fun navigateToHourly(
        latitude: Double?,
        longitude: Double?
    ) {
        val route = DestinationsForecast.Hourly.createRouteForNavigation(
            latitude = latitude,
            longitude = longitude
        )
        navigate(route)
    }

}