package de.niklasbednarczyk.openweathermap.core.ui.navigation

import androidx.navigation.NamedNavArgument

interface OwmNavigationDestination {

    val route: String
    val arguments: List<NamedNavArgument>
        get() = emptyList()

    fun getKeyForRoute(key: String): String = "{${key}}"

}