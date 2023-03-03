package de.niklasbednarczyk.nbweather.feature.location.navigation

import androidx.navigation.NavGraphBuilder
import de.niklasbednarczyk.nbweather.core.ui.navigation.graph.nbFragment
import de.niklasbednarczyk.nbweather.core.ui.navigation.graph.nbNavigation
import de.niklasbednarczyk.nbweather.feature.location.screens.alerts.LocationAlertsFragment
import de.niklasbednarczyk.nbweather.feature.location.screens.daily.LocationDailyFragment
import de.niklasbednarczyk.nbweather.feature.location.screens.hourly.LocationHourlyFragment
import de.niklasbednarczyk.nbweather.feature.location.screens.overview.LocationOverviewFragment

fun NavGraphBuilder.graphLocation() {
    nbNavigation(
        topLevelDestination = DestinationsLocation.topLevel,
        startDestination = DestinationsLocation.Overview
    ) {
        nbFragment<LocationAlertsFragment>(DestinationsLocation.Alerts)
        nbFragment<LocationDailyFragment>(DestinationsLocation.Daily)
        nbFragment<LocationHourlyFragment>(DestinationsLocation.Hourly)
        nbFragment<LocationOverviewFragment>(DestinationsLocation.Overview)
    }
}