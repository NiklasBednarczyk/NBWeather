package de.niklasbednarczyk.openweathermap.feature.location.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import de.niklasbednarczyk.openweathermap.core.ui.navigation.owmComposable
import de.niklasbednarczyk.openweathermap.feature.location.screens.alerts.LocationAlertsScreen
import de.niklasbednarczyk.openweathermap.feature.location.screens.hourly.LocationHourlyScreen
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.LocationOverviewScreen

fun NavGraphBuilder.locationGraph(
    navigationIconDrawer: @Composable () -> Unit,
    navigationIconBack: @Composable () -> Unit,
    navigateToSearch: () -> Unit,
    navigateToAlerts: (Double?, Double?) -> Unit,
    navigateToHourly: (Long, Double?, Double?) -> Unit
) {
    owmComposable(LocationDestinations.Alerts) {
        LocationAlertsScreen(
            navigationIcon = navigationIconBack
        )
    }
    owmComposable(LocationDestinations.Hourly) {
        LocationHourlyScreen(
            navigationIcon = navigationIconBack
        )
    }
    owmComposable(LocationDestinations.Overview) {
        LocationOverviewScreen(
            navigationIcon = navigationIconDrawer,
            navigateToSearch = navigateToSearch,
            navigateToAlerts = navigateToAlerts,
            navigateToHourly = navigateToHourly
        )
    }
}