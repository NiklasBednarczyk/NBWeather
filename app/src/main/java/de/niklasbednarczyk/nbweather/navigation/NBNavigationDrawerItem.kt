package de.niklasbednarczyk.nbweather.navigation

import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconModel
import de.niklasbednarczyk.nbweather.core.ui.navigation.NBNavigationDestination

sealed interface NBNavigationDrawerItem {

    object Divider : NBNavigationDrawerItem

    data class Headline(
        val label: NBString?
    ) : NBNavigationDrawerItem

    sealed interface Item : NBNavigationDrawerItem {
        val label: NBString?
        val icon: NBIconModel
        val selected: Boolean

        data class Location(
            override val label: NBString?,
            override val icon: NBIconModel,
            override val selected: Boolean,
            val latitude: Double,
            val longitude: Double
        ) : Item

        data class Other(
            override val label: NBString?,
            override val icon: NBIconModel,
            val destination: NBNavigationDestination
        ) : Item {
            override val selected: Boolean
                get() = false
        }

    }

}