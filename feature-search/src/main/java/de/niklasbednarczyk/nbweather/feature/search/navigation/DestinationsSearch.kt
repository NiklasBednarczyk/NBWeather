package de.niklasbednarczyk.nbweather.feature.search.navigation

import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBDestination
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBTopLevelDestination
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBTopLevelDestinations

object DestinationsSearch {

    val topLevel = NBTopLevelDestinations.Search

    object Overview : NBDestination.WithoutArguments() {

        override val topLevelDestination: NBTopLevelDestination = topLevel

    }

}