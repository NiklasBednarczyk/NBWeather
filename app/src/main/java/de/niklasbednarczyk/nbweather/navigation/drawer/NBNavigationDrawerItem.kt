package de.niklasbednarczyk.nbweather.navigation.drawer

import de.niklasbednarczyk.nbweather.core.common.coordinates.NBCoordinatesModel
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconItem
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.data.geocoding.models.LocationModelData
import de.niklasbednarczyk.nbweather.navigation.destination.NBDestinationItem


sealed interface NBNavigationDrawerItem {

    data object Divider : NBNavigationDrawerItem

    data class Headline(
        val label: NBString?
    ) : NBNavigationDrawerItem

    sealed interface Destination : NBNavigationDrawerItem {

        val label: NBString?
        val icon: NBIconItem
        val selected: Boolean

        data class WithoutArguments(
            val destination: NBDestinationItem.WithoutArguments,
            override val label: NBString?,
            override val icon: NBIconItem
        ) : Destination {

            override val selected: Boolean = false

        }

        data class ForecastOverview(
            val coordinates: NBCoordinatesModel,
            override val label: NBString?,
            override val selected: Boolean
        ) : Destination {

            companion object {

                fun from(
                    visitedLocation: LocationModelData,
                    currentLocation: LocationModelData?
                ): ForecastOverview {
                     val selected = visitedLocation.coordinates == currentLocation?.coordinates

                    return ForecastOverview(
                        coordinates = visitedLocation.coordinates,
                        label = visitedLocation.localizedNameAndCountry,
                        selected = selected
                    )
                }

            }

            override val icon: NBIconItem = NBIcons.Location

        }

    }

}