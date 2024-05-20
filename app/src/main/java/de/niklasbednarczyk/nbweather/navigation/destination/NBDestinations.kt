package de.niklasbednarczyk.nbweather.navigation.destination

import androidx.navigation.NamedNavArgument
import de.niklasbednarczyk.nbweather.core.ui.navigation.NBArgumentKeyItem
import de.niklasbednarczyk.nbweather.core.ui.navigation.NBArgumentKeys

object NBDestinations {

    private const val FEATURE_NAME_ABOUT = "about"
    private const val FEATURE_NAME_FORECAST = "forecast"
    private const val FEATURE_NAME_SEARCH = "search"
    private const val FEATURE_NAME_SETTINGS = "settings"

    object About {

        object Overview : NBDestinationItem.WithoutArguments {

            override val featureName: String = FEATURE_NAME_ABOUT

            override val destinationName: String = "overview"

        }

    }

    object Forecast {

        object Alerts : NBDestinationItem.WithArguments {

            override val featureName: String = FEATURE_NAME_FORECAST

            override val destinationName: String = "alerts"

            override val argumentKeys: List<NBArgumentKeyItem<*>> = listOf(
                NBArgumentKeys.Latitude,
                NBArgumentKeys.Longitude
            )

            fun createRouteForNavigation(
                latitude: Double,
                longitude: Double
            ): String {
                return createRouteForNavigation(
                    mapOf(
                        NBArgumentKeys.Latitude to latitude,
                        NBArgumentKeys.Longitude to longitude
                    )
                )
            }

        }

        object Daily : NBDestinationItem.WithArguments {

            override val featureName: String = FEATURE_NAME_FORECAST

            override val destinationName: String = "daily"

            override val argumentKeys: List<NBArgumentKeyItem<*>> = listOf(
                NBArgumentKeys.ForecastTime,
                NBArgumentKeys.Latitude,
                NBArgumentKeys.Longitude
            )

            fun createRouteForNavigation(
                forecastTime: Long?,
                latitude: Double,
                longitude: Double
            ): String {
                return createRouteForNavigation(
                    mapOf(
                        NBArgumentKeys.ForecastTime to forecastTime,
                        NBArgumentKeys.Latitude to latitude,
                        NBArgumentKeys.Longitude to longitude
                    )
                )
            }

        }

        object Hourly : NBDestinationItem.WithArguments {

            override val featureName: String = FEATURE_NAME_FORECAST

            override val destinationName: String = "hourly"

            override val argumentKeys: List<NBArgumentKeyItem<*>> = listOf(
                NBArgumentKeys.Latitude,
                NBArgumentKeys.Longitude
            )

            fun createRouteForNavigation(
                latitude: Double,
                longitude: Double
            ): String {
                return createRouteForNavigation(
                    mapOf(
                        NBArgumentKeys.Latitude to latitude,
                        NBArgumentKeys.Longitude to longitude
                    )
                )
            }

        }

        object Overview : NBDestinationItem.WithArguments {

            override val featureName: String = FEATURE_NAME_FORECAST

            override val destinationName: String = "overview"

            override val argumentKeys: List<NBArgumentKeyItem<*>> = listOf(
                NBArgumentKeys.Latitude,
                NBArgumentKeys.Longitude
            )

            fun createNavArguments(
                latitude: Double?,
                longitude: Double?
            ): List<NamedNavArgument> {
                return argumentKeys.mapNotNull { argumentKey ->
                    when (argumentKey) {
                        NBArgumentKeys.ForecastTime, NBArgumentKeys.IsStartDestination -> null

                        NBArgumentKeys.Latitude -> createNavArgument(
                            argumentKey = argumentKey,
                            defaultValue = latitude
                        )

                        NBArgumentKeys.Longitude -> createNavArgument(
                            argumentKey = argumentKey,
                            defaultValue = longitude
                        )
                    }
                }
            }

            fun createRouteForNavigation(
                latitude: Double,
                longitude: Double
            ): String {
                return createRouteForNavigation(
                    mapOf(
                        NBArgumentKeys.Latitude to latitude,
                        NBArgumentKeys.Longitude to longitude
                    )
                )
            }

        }

    }

    object Search {

        object Overview : NBDestinationItem.WithArguments {

            override val featureName: String = FEATURE_NAME_SEARCH

            override val destinationName: String = "overview"

            override val argumentKeys: List<NBArgumentKeyItem<*>> = listOf(
                NBArgumentKeys.IsStartDestination
            )

            fun createNavArguments(
                isStartDestination: Boolean
            ): List<NamedNavArgument> {
                return argumentKeys.mapNotNull { argumentKey ->
                    when (argumentKey) {
                        NBArgumentKeys.ForecastTime, NBArgumentKeys.Latitude, NBArgumentKeys.Longitude -> null

                        NBArgumentKeys.IsStartDestination -> createNavArgument(
                            argumentKey = argumentKey,
                            defaultValue = isStartDestination
                        )
                    }
                }
            }

            fun createRouteForNavigation(
                isStartDestination: Boolean
            ): String {
                return createRouteForNavigation(
                    mapOf(
                        NBArgumentKeys.IsStartDestination to isStartDestination
                    )
                )
            }

        }

    }

    object Settings {

        object Appearance : NBDestinationItem.WithoutArguments {

            override val featureName: String = FEATURE_NAME_SETTINGS

            override val destinationName: String = "appearance"

        }

        object Font : NBDestinationItem.WithoutArguments {

            override val featureName: String = FEATURE_NAME_SETTINGS

            override val destinationName: String = "font"

        }

        object Order : NBDestinationItem.WithoutArguments {

            override val featureName: String = FEATURE_NAME_SETTINGS

            override val destinationName: String = "order"

        }

        object Overview : NBDestinationItem.WithoutArguments {

            override val featureName: String = FEATURE_NAME_SETTINGS

            override val destinationName: String = "overview"

        }

        object Units : NBDestinationItem.WithoutArguments {

            override val featureName: String = FEATURE_NAME_SETTINGS

            override val destinationName: String = "units"

        }

    }

}