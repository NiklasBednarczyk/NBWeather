package de.niklasbednarczyk.openweathermap.feature.location.navigation

import de.niklasbednarczyk.openweathermap.core.ui.navigation.OwmNavigationDrawerDestination

object LocationDestinations {

    object Overview : OwmNavigationDrawerDestination {
        override val route: String
            get() = "location_overview"
    }

}