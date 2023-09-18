package de.niklasbednarczyk.nbweather.feature.forecast.navigation

import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBDestination
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBTopLevelDestination
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBTopLevelDestinations

object DestinationsForecast {

    val topLevel = NBTopLevelDestinations.Forecast

    object Alerts : NBDestination.WithArguments() {

        const val KEY_LATITUDE = "latitude"
        const val KEY_LONGITUDE = "longitude"

        override val topLevelDestination: NBTopLevelDestination = topLevel

        override val arguments: List<String> = listOf(
            KEY_LATITUDE,
            KEY_LONGITUDE
        )

        fun createRouteForNavigation(
            latitude: Double?,
            longitude: Double?
        ): String {
            return createRouteForNavigation(
                mapOf(
                    KEY_LATITUDE to latitude,
                    KEY_LONGITUDE to longitude
                )
            )
        }

    }

    object Overview : NBDestination.WithoutArguments() {

        override val topLevelDestination: NBTopLevelDestination = topLevel

    }

}