package de.niklasbednarczyk.nbweather.navigation.host

import androidx.navigation.NavController
import de.niklasbednarczyk.nbweather.core.common.coordinates.NBCoordinatesModel
import de.niklasbednarczyk.nbweather.navigation.destination.NBDestinationItem
import de.niklasbednarczyk.nbweather.navigation.destination.NBDestinations

fun NavController.navigate(
    destination: NBDestinationItem.WithoutArguments
) {
    navigate(destination.routeForNavigation)
}

fun NavController.navigateToForecastAlerts(
    coordinates: NBCoordinatesModel
) {
    val routeForNavigation = NBDestinations.Forecast.Alerts.createRouteForNavigation(
        coordinates = coordinates
    )
    navigate(routeForNavigation)
}

fun NavController.navigateToForecastDaily(
    forecastTime: Long?,
    coordinates: NBCoordinatesModel
) {
    val routeForNavigation = NBDestinations.Forecast.Daily.createRouteForNavigation(
        forecastTime = forecastTime,
        coordinates = coordinates
    )
    navigate(routeForNavigation)
}


fun NavController.navigateToForecastHourly(
    coordinates: NBCoordinatesModel
) {
    val routeForNavigation = NBDestinations.Forecast.Hourly.createRouteForNavigation(
        coordinates = coordinates
    )
    navigate(routeForNavigation)
}

fun NavController.navigateToForecastOverview(
    coordinates: NBCoordinatesModel
) {
    val routeForNavigation = NBDestinations.Forecast.Overview.createRouteForNavigation(
        coordinates = coordinates
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