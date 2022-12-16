package de.niklasbednarczyk.nbweather.feature.search.navigation

import de.niklasbednarczyk.nbweather.core.ui.navigation.NBNavigationDestination

object SearchDestinations {

    object Overview : NBNavigationDestination.Overview {
        override val route: String
            get() = "search_overview"
    }

}