package de.niklasbednarczyk.nbweather.feature.forecast.navigation

import androidx.navigation.NavGraphBuilder
import de.niklasbednarczyk.nbweather.core.ui.navigation.graph.nbFragment
import de.niklasbednarczyk.nbweather.core.ui.navigation.graph.nbNavigation
import de.niklasbednarczyk.nbweather.feature.forecast.screens.alerts.ForecastAlertsFragment
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.ForecastOverviewFragment

fun NavGraphBuilder.graphForecast() {
    nbNavigation(
        topLevelDestination = DestinationsForecast.topLevel,
        startDestination = DestinationsForecast.Overview
    ) {
        nbFragment<ForecastAlertsFragment>(DestinationsForecast.Alerts)
        nbFragment<ForecastOverviewFragment>(DestinationsForecast.Overview)
    }
}