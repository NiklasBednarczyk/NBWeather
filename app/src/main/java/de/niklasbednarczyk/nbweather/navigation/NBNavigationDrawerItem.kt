package de.niklasbednarczyk.nbweather.navigation

import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconItem
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBTopLevelDestination
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBTopLevelDestinations

sealed interface NBNavigationDrawerItem {

    data object Divider : NBNavigationDrawerItem

    data class Headline(
        val label: NBString?
    ) : NBNavigationDrawerItem

    sealed interface Item : NBNavigationDrawerItem {
        val label: NBString?
        val icon: NBIconItem
        val selected: Boolean
        val topLevelDestination: NBTopLevelDestination

        data class Location(
            override val label: NBString?,
            override val icon: NBIconItem,
            override val selected: Boolean,
            val latitude: Double,
            val longitude: Double
        ) : Item {

            override val topLevelDestination: NBTopLevelDestination =
                NBTopLevelDestinations.Forecast

        }

        data class Other(
            override val label: NBString?,
            override val icon: NBIconItem,
            override val topLevelDestination: NBTopLevelDestination
        ) : Item {

            override val selected: Boolean
                get() = false

        }

    }

}