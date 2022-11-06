package de.niklasbednarczyk.openweathermap.core.ui.navigation

import androidx.navigation.NamedNavArgument

sealed interface OwmNavigationDestination {

    val route: String
    val arguments: List<NamedNavArgument>
        get() = emptyList()

    fun getKeyForRoute(key: String): String = "{${key}}"

    interface Detail : OwmNavigationDestination

    interface Drawer : OwmNavigationDestination

    interface Overview : OwmNavigationDestination {

        val navigatesTo: List<OwmNavigationDestination>
            get() = emptyList()

    }

}