package de.niklasbednarczyk.openweathermap.feature.location.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import de.niklasbednarczyk.openweathermap.core.ui.navigation.OwmNavigationDestination

object LocationDestinations {

    object Alerts : OwmNavigationDestination.Overview {
        const val KEY_LATITUDE = "keyLatitude"
        const val KEY_LONGITUDE = "keyLongitude"

        override val route: String
            get() = createRoute(
                latitudeString = getKeyForRoute(KEY_LATITUDE),
                longitudeString = getKeyForRoute(KEY_LONGITUDE)
            )

        override val arguments: List<NamedNavArgument> = listOf(
            navArgument(KEY_LATITUDE) { type = NavType.StringType },
            navArgument(KEY_LONGITUDE) { type = NavType.StringType }
        )

        private fun createRoute(latitudeString: String, longitudeString: String): String {
            return "location_alerts?${KEY_LATITUDE}=${latitudeString}&${KEY_LONGITUDE}=${longitudeString}"
        }

        fun createRoute(latitude: Double, longitude: Double) =
            createRoute(latitude.toString(), longitude.toString())

    }

    object Daily : OwmNavigationDestination.Overview {
        const val KEY_FORECAST_TIME = "keyForecastTime"
        const val KEY_LATITUDE = "keyLatitude"
        const val KEY_LONGITUDE = "keyLongitude"

        override val route: String
            get() = createRoute(
                forecastTimeString = getKeyForRoute(KEY_FORECAST_TIME),
                latitudeString = getKeyForRoute(KEY_LATITUDE),
                longitudeString = getKeyForRoute(KEY_LONGITUDE)
            )

        override val arguments: List<NamedNavArgument> = listOf(
            navArgument(KEY_FORECAST_TIME) { type = NavType.StringType },
            navArgument(KEY_LATITUDE) { type = NavType.StringType },
            navArgument(KEY_LONGITUDE) { type = NavType.StringType }
        )

        private fun createRoute(
            forecastTimeString: String,
            latitudeString: String,
            longitudeString: String
        ): String {
            return "locations_daily?${KEY_FORECAST_TIME}=${forecastTimeString}&${KEY_LATITUDE}=${latitudeString}&${KEY_LONGITUDE}=${longitudeString}"
        }

        fun createRoute(
            forecastTime: Long,
            latitude: Double,
            longitude: Double
        ): String =
            createRoute(forecastTime.toString(), latitude.toString(), longitude.toString())

    }

    object Hourly : OwmNavigationDestination.Overview {
        const val KEY_FORECAST_TIME = "keyForecastTime"
        const val KEY_LATITUDE = "keyLatitude"
        const val KEY_LONGITUDE = "keyLongitude"

        override val route: String
            get() = createRoute(
                forecastTimeString = getKeyForRoute(KEY_FORECAST_TIME),
                latitudeString = getKeyForRoute(KEY_LATITUDE),
                longitudeString = getKeyForRoute(KEY_LONGITUDE)
            )

        override val arguments: List<NamedNavArgument> = listOf(
            navArgument(KEY_FORECAST_TIME) { type = NavType.StringType },
            navArgument(KEY_LATITUDE) { type = NavType.StringType },
            navArgument(KEY_LONGITUDE) { type = NavType.StringType }
        )

        private fun createRoute(
            forecastTimeString: String,
            latitudeString: String,
            longitudeString: String
        ): String {
            return "locations_hourly?${KEY_FORECAST_TIME}=${forecastTimeString}&${KEY_LATITUDE}=${latitudeString}&${KEY_LONGITUDE}=${longitudeString}"
        }

        fun createRoute(
            forecastTime: Long,
            latitude: Double,
            longitude: Double
        ): String =
            createRoute(forecastTime.toString(), latitude.toString(), longitude.toString())


    }

    object Overview : OwmNavigationDestination.Drawer {
        const val KEY_LATITUDE = "keyLatitude"
        const val KEY_LONGITUDE = "keyLongitude"

        override val route: String
            get() = createRoute(
                latitudeString = getKeyForRoute(KEY_LATITUDE),
                longitudeString = getKeyForRoute(KEY_LONGITUDE)
            )

        override val arguments: List<NamedNavArgument> = listOf(
            navArgument(KEY_LATITUDE) { type = NavType.StringType },
            navArgument(KEY_LONGITUDE) { type = NavType.StringType }
        )

        private fun createRoute(latitudeString: String, longitudeString: String): String {
            return "location_overview?$KEY_LATITUDE=${latitudeString}&$KEY_LONGITUDE=${longitudeString}"
        }

        fun createRoute(latitude: Double, longitude: Double) =
            createRoute(latitude.toString(), longitude.toString())

    }

}