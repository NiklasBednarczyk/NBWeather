package de.niklasbednarczyk.openweathermap.navigation

import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIconModel
import de.niklasbednarczyk.openweathermap.core.ui.navigation.OwmNavigationDestination

sealed interface OwmNavigationDrawerItem {

    // TODO (#20) Add header with app icon

    object Divider : OwmNavigationDrawerItem

    sealed interface Item : OwmNavigationDrawerItem {
        val label: OwmString?
        val icon: OwmIconModel
        val selected: Boolean

        data class Location(
            override val label: OwmString?,
            override val icon: OwmIconModel,
            override val selected: Boolean,
            val latitude: Double,
            val longitude: Double
        ) : Item

        data class Other(
            override val label: OwmString?,
            override val icon: OwmIconModel,
            val destination: OwmNavigationDestination
        ): Item {
            override val selected: Boolean
                get() = false
        }

    }

}