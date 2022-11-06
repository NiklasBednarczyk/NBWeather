package de.niklasbednarczyk.openweathermap.feature.search.navigation

import de.niklasbednarczyk.openweathermap.core.ui.navigation.OwmNavigationDestination

object SearchDestinations {

    object Overview : OwmNavigationDestination.Overview {
        override val route: String
            get() = "search_overview"
    }

}