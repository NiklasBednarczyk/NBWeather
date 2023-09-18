package de.niklasbednarczyk.nbweather.navigation

import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconModel
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBTopLevelDestination
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBTopLevelDestinations
import de.niklasbednarczyk.nbweather.data.geocoding.models.LocationModelData

sealed interface NBNavigationDrawerItem {

    data object Divider : NBNavigationDrawerItem

    data class Headline(
        val label: NBString?
    ) : NBNavigationDrawerItem

    sealed interface Item : NBNavigationDrawerItem {
        val label: NBString?
        val icon: NBIconModel
        val selected: Boolean
        val topLevelDestination: NBTopLevelDestination

        data class Location(
            override val label: NBString?,
            override val icon: NBIconModel,
            override val selected: Boolean,
            val location: LocationModelData,
        ) : Item {

            override val topLevelDestination: NBTopLevelDestination =
                NBTopLevelDestinations.Forecast

        }

        data class Other(
            override val label: NBString?,
            override val icon: NBIconModel,
            override val topLevelDestination: NBTopLevelDestination
        ) : Item {

            override val selected: Boolean
                get() = false

        }

    }

}