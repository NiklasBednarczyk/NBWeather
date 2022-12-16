package de.niklasbednarczyk.nbweather.core.ui.navigation

import androidx.navigation.NamedNavArgument

sealed interface NBNavigationDestination {

    val route: String
    val arguments: List<NamedNavArgument>
        get() = emptyList()

    fun getKeyForRoute(key: String): String = "{${key}}"

    interface Detail : NBNavigationDestination

    interface Drawer : NBNavigationDestination

    interface Overview : NBNavigationDestination {

        val navigatesTo: List<NBNavigationDestination>
            get() = emptyList()

    }

}