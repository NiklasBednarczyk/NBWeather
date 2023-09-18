package de.niklasbednarczyk.nbweather.feature.about.navigation

import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBDestination
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBTopLevelDestination
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBTopLevelDestinations

object DestinationsAbout {

    val topLevel = NBTopLevelDestinations.About

    object Overview : NBDestination.WithoutArguments() {

        override val topLevelDestination: NBTopLevelDestination = topLevel

    }

}