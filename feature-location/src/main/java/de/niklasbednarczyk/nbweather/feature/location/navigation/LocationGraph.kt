package de.niklasbednarczyk.nbweather.feature.location.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import de.niklasbednarczyk.nbweather.core.ui.navigation.nbComposable
import de.niklasbednarczyk.nbweather.feature.location.screens.alerts.LocationAlertsScreen
import de.niklasbednarczyk.nbweather.feature.location.screens.daily.LocationDailyScreen
import de.niklasbednarczyk.nbweather.feature.location.screens.hourly.LocationHourlyScreen
import de.niklasbednarczyk.nbweather.feature.location.screens.overview.LocationOverviewScreen

fun NavGraphBuilder.locationGraph(
    navigationIconDrawer: @Composable () -> Unit,
    navigationIconBack: @Composable () -> Unit,
    navigateToAlerts: (Double?, Double?) -> Unit,
    navigateToDaily: (Long, Double?, Double?) -> Unit,
    navigateToHourly: (Long, Double?, Double?) -> Unit,
    navigateToSearch: () -> Unit,
) {
    nbComposable(LocationDestinations.Alerts) {
        LocationAlertsScreen(
            navigationIcon = navigationIconBack
        )
    }
    nbComposable(LocationDestinations.Daily) {
        LocationDailyScreen(
            navigationIcon = navigationIconBack
        )
    }
    nbComposable(LocationDestinations.Hourly) {
        LocationHourlyScreen(
            navigationIcon = navigationIconBack
        )
    }
    nbComposable(LocationDestinations.Overview) {
        LocationOverviewScreen(
            navigationIcon = navigationIconDrawer,
            navigateToAlerts = navigateToAlerts,
            navigateToDaily = navigateToDaily,
            navigateToHourly = navigateToHourly,
            navigateToSearch = navigateToSearch
        )
    }
}