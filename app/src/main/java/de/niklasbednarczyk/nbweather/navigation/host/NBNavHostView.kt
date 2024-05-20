package de.niklasbednarczyk.nbweather.navigation.host

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import de.niklasbednarczyk.nbweather.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.nbweather.feature.about.screens.overview.AboutOverviewRoute
import de.niklasbednarczyk.nbweather.feature.forecast.screens.alerts.ForecastAlertsRoute
import de.niklasbednarczyk.nbweather.feature.forecast.screens.daily.ForecastDailyRoute
import de.niklasbednarczyk.nbweather.feature.forecast.screens.hourly.ForecastHourlyRoute
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.ForecastOverviewRoute
import de.niklasbednarczyk.nbweather.feature.search.screens.overview.SearchOverviewRoute
import de.niklasbednarczyk.nbweather.feature.settings.screens.appearance.SettingsAppearanceRoute
import de.niklasbednarczyk.nbweather.feature.settings.screens.font.SettingsFontRoute
import de.niklasbednarczyk.nbweather.feature.settings.screens.order.SettingsOrderRoute
import de.niklasbednarczyk.nbweather.feature.settings.screens.overview.SettingsOverviewRoute
import de.niklasbednarczyk.nbweather.feature.settings.screens.units.SettingsUnitsRoute
import de.niklasbednarczyk.nbweather.navigation.destination.NBDestinations
import de.niklasbednarczyk.nbweather.navigation.drawer.NBDrawerController

@Composable
fun NBNavHostView(
    navController: NavHostController,
    drawerController: NBDrawerController,
    initialCurrentLocation: LocationModelData?
) {
    val searchOverviewIsStartDestination = initialCurrentLocation == null
    val startDestination = if (searchOverviewIsStartDestination) {
        NBDestinations.Search.Overview
    } else {
        NBDestinations.Forecast.Overview
    }

    NavHost(
        navController = navController,
        startDestination = startDestination.routeForGraph
    ) {
        // About
        nbComposable(
            destination = NBDestinations.About.Overview
        ) {
            AboutOverviewRoute(
                popBackStack = navController::popBackStack
            )
        }

        // Forecast
        nbComposable(
            destination = NBDestinations.Forecast.Alerts
        ) {
            ForecastAlertsRoute(
                popBackStack = navController::popBackStack
            )
        }
        nbComposable(
            destination = NBDestinations.Forecast.Daily
        ) {
            ForecastDailyRoute(
                popBackStack = navController::popBackStack
            )
        }
        nbComposable(
            destination = NBDestinations.Forecast.Hourly
        ) {
            ForecastHourlyRoute(
                popBackStack = navController::popBackStack
            )
        }
        nbComposable(
            destination = NBDestinations.Forecast.Overview,
            arguments = NBDestinations.Forecast.Overview.createNavArguments(
                initialCurrentLocation?.latitude,
                initialCurrentLocation?.longitude
            )
        ) {
            ForecastOverviewRoute(
                openDrawer = drawerController::openDrawer,
                navigateToForecastAlerts = navController::navigateToForecastAlerts,
                navigateToForecastDaily = navController::navigateToForecastDaily,
                navigateToForecastHourly = navController::navigateToForecastHourly,
                navigateToSearchOverview = navController::navigateToSearchOverview
            )
        }

        // Search
        nbComposable(
            destination = NBDestinations.Search.Overview,
            arguments = NBDestinations.Search.Overview.createNavArguments(
                isStartDestination = searchOverviewIsStartDestination
            )
        ) {
            SearchOverviewRoute(
                popBackStack = navController::popBackStack,
                navigateToForecastOverview = navController::navigateToForecastOverview
            )
        }

        // Settings
        nbComposable(
            destination = NBDestinations.Settings.Appearance
        ) {
            SettingsAppearanceRoute(
                popBackStack = navController::popBackStack
            )
        }
        nbComposable(
            destination = NBDestinations.Settings.Font
        ) {
            SettingsFontRoute(
                popBackStack = navController::popBackStack
            )
        }
        nbComposable(
            destination = NBDestinations.Settings.Order
        ) {
            SettingsOrderRoute(
                popBackStack = navController::popBackStack
            )
        }
        nbComposable(
            destination = NBDestinations.Settings.Overview
        ) {
            SettingsOverviewRoute(
                popBackStack = navController::popBackStack,
                navigateToSettingsAppearance = {
                    navController.navigate(NBDestinations.Settings.Appearance)
                },
                navigateToSettingsFont = {
                    navController.navigate(NBDestinations.Settings.Font)
                },
                navigateToSettingsOrder = {
                    navController.navigate(NBDestinations.Settings.Order)
                },
                navigateToSettingsUnits = {
                    navController.navigate(NBDestinations.Settings.Units)
                }
            )
        }
        nbComposable(
            destination = NBDestinations.Settings.Units
        ) {
            SettingsUnitsRoute(
                popBackStack = navController::popBackStack
            )
        }
    }
}