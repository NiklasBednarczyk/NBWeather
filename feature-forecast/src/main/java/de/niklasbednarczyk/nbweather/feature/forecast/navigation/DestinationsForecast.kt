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
            latitude: Double,
            longitude: Double
        ): String {
            return createRouteForNavigation(
                mapOf(
                    KEY_LATITUDE to latitude,
                    KEY_LONGITUDE to longitude
                )
            )
        }

    }

    object Daily : NBDestination.WithArguments() {

        const val KEY_FORECAST_TIME = "forecast_time"
        const val KEY_LATITUDE = "latitude"
        const val KEY_LONGITUDE = "longitude"

        override val topLevelDestination: NBTopLevelDestination = topLevel

        override val arguments: List<String> = listOf(
            KEY_FORECAST_TIME,
            KEY_LATITUDE,
            KEY_LONGITUDE
        )

        fun createRouteForNavigation(
            forecastTime: Long?,
            latitude: Double,
            longitude: Double
        ): String {
            return createRouteForNavigation(
                mapOf(
                    KEY_FORECAST_TIME to forecastTime,
                    KEY_LATITUDE to latitude,
                    KEY_LONGITUDE to longitude
                )
            )
        }

    }

    object Hourly : NBDestination.WithArguments() {

        const val KEY_LATITUDE = "latitude"
        const val KEY_LONGITUDE = "longitude"

        override val topLevelDestination: NBTopLevelDestination = topLevel

        override val arguments: List<String> = listOf(
            KEY_LATITUDE,
            KEY_LONGITUDE
        )

        fun createRouteForNavigation(
            latitude: Double,
            longitude: Double
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