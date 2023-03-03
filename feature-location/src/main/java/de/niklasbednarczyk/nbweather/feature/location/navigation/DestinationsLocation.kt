package de.niklasbednarczyk.nbweather.feature.location.navigation

import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBDestination
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBTopLevelDestination
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBTopLevelDestinations

object DestinationsLocation {

    val topLevel = NBTopLevelDestinations.Location

    object Alerts : NBDestination.WithArguments() {

        const val KEY_LATITUDE = "keyLatitude"
        const val KEY_LONGITUDE = "keyLongitude"

        override val topLevelDestination: NBTopLevelDestination
            get() = topLevel

        override val path: String
            get() = "alerts"

        override val arguments: List<String>
            get() = listOf(
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

        const val KEY_FORECAST_TIME = "keyForecastTime"
        const val KEY_LATITUDE = "keyLatitude"
        const val KEY_LONGITUDE = "keyLongitude"

        override val topLevelDestination: NBTopLevelDestination
            get() = topLevel

        override val path: String
            get() = "daily"

        override val arguments: List<String>
            get() = listOf(
                KEY_FORECAST_TIME,
                KEY_LATITUDE,
                KEY_LONGITUDE
            )

        fun createRouteForNavigation(
            forecastTime: Long,
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

        const val KEY_FORECAST_TIME = "keyForecastTime"
        const val KEY_LATITUDE = "keyLatitude"
        const val KEY_LONGITUDE = "keyLongitude"


        override val topLevelDestination: NBTopLevelDestination
            get() = topLevel

        override val path: String
            get() = "hourly"

        override val arguments: List<String>
            get() = listOf(
                KEY_FORECAST_TIME,
                KEY_LATITUDE,
                KEY_LONGITUDE
            )

        fun createRouteForNavigation(
            forecastTime: Long,
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

    object Overview : NBDestination.WithoutArguments() {

        override val topLevelDestination: NBTopLevelDestination
            get() = topLevel

        override val path: String
            get() = "overview"

    }

}