package de.niklasbednarczyk.nbweather.feature.about.navigation

import de.niklasbednarczyk.nbweather.core.ui.navigation.NBNavigationDestination

object AboutDestinations {

    object Overview : NBNavigationDestination.Overview {
        override val route: String
            get() = "about_overview"
    }

}