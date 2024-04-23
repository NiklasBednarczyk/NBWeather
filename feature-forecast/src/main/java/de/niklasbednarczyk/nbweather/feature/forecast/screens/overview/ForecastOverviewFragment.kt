package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview

import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import de.niklasbednarczyk.nbweather.core.data.localremote.models.resource.NBResource
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBTopLevelDestinations
import de.niklasbednarczyk.nbweather.core.ui.resource.text
import de.niklasbednarczyk.nbweather.core.ui.screen.fragment.NBFragment
import de.niklasbednarczyk.nbweather.core.ui.screen.scaffold.snackbar.NBSnackbarModel
import de.niklasbednarczyk.nbweather.core.ui.screen.scaffold.topappbar.NBTopAppBarActionModel
import de.niklasbednarczyk.nbweather.core.ui.screen.scaffold.topappbar.NBTopAppBarItem
import de.niklasbednarczyk.nbweather.feature.forecast.navigation.DestinationsForecast

@AndroidEntryPoint
class ForecastOverviewFragment : NBFragment<ForecastOverviewUiState>() {

    override val viewModel: ForecastOverviewViewModel by viewModels()

    override fun createTopAppBarItem(uiState: ForecastOverviewUiState): NBTopAppBarItem {
        val location = uiState.locationResource.dataOrNull
        return NBTopAppBarItem.CenterAligned(
            title = location?.title,
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
            refreshData = ::refreshData,
            navigateToAlerts = ::navigateToAlerts,
            navigateToDaily = ::navigateToDaily,
            navigateToHourly = ::navigateToHourly
        )
    }

    private suspend fun refreshData(
        latitude: Double,
        longitude: Double
    ) {
        val resource = viewModel.refreshData(
            latitude = latitude,
            longitude = longitude
        )

        if (resource is NBResource.Error) {
            val snackbar = NBSnackbarModel(
                message = resource.errorType.text
            )
            sendSnackbar(snackbar)
        }
    }

    private fun navigateToSearch() {
        navigate(NBTopLevelDestinations.Search)
    }

    private fun navigateToAlerts(
        latitude: Double,
        longitude: Double
    ) {
        val route = DestinationsForecast.Alerts.createRouteForNavigation(
            latitude = latitude,
            longitude = longitude
        )
        navigate(route)
    }

    private fun navigateToDaily(
        forecastTime: Long?,
        latitude: Double,
        longitude: Double
    ) {
        val route = DestinationsForecast.Daily.createRouteForNavigation(
            forecastTime = forecastTime,
            latitude = latitude,
            longitude = longitude
        )
        navigate(route)
    }

    private fun navigateToHourly(
        latitude: Double,
        longitude: Double
    ) {
        val route = DestinationsForecast.Hourly.createRouteForNavigation(
            latitude = latitude,
            longitude = longitude
        )
        navigate(route)
    }

}