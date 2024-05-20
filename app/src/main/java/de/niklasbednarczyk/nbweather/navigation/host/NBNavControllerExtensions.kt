package de.niklasbednarczyk.nbweather.navigation.host

import androidx.navigation.NavController
import de.niklasbednarczyk.nbweather.navigation.destination.NBDestinationItem
import de.niklasbednarczyk.nbweather.navigation.destination.NBDestinations

fun NavController.navigate(
    destination: NBDestinationItem.WithoutArguments
) {
    navigate(destination.routeForNavigation)
}

fun NavController.navigateToForecastAlerts(
    latitude: Double,
    longitude: Double
) {
    val routeForNavigation = NBDestinations.Forecast.Alerts.createRouteForNavigation(
        latitude = latitude,
        longitude = longitude
    )
    navigate(routeForNavigation)
}

fun NavController.navigateToForecastDaily(
    forecastTime: Long?,
    latitude: Double,
    longitude: Double
) {
    val routeForNavigation = NBDestinations.Forecast.Daily.createRouteForNavigation(
        forecastTime = forecastTime,
        latitude = latitude,
        longitude = longitude
    )
    navigate(routeForNavigation)
}


fun NavController.navigateToForecastHourly(
    latitude: Double,
    longitude: Double
) {
    val routeForNavigation = NBDestinations.Forecast.Hourly.createRouteForNavigation(
        latitude = latitude,
        longitude = longitude
    )
    navigate(routeForNavigation)
}

fun NavController.navigateToForecastOverview(
    latitude: Double,
    longitude: Double
) {
    val routeForNavigation = NBDestinations.Forecast.Overview.createRouteForNavigation(
        latitude = latitude,
        longitude = longitude
    )
    navigate(routeForNavigation) {
        popUpTo(0)
    }
}

fun NavController.navigateToSearchOverview() {
    val routeForNavigation = NBDestinations.Search.Overview.createRouteForNavigation(
        isStartDestination = false
    )
    navigate(routeForNavigation)
}