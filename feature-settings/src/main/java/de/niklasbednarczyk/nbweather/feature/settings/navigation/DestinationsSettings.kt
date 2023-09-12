package de.niklasbednarczyk.nbweather.feature.settings.navigation

import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBDestination
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBTopLevelDestination
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBTopLevelDestinations

object DestinationsSettings {

    val topLevel = NBTopLevelDestinations.Settings

    object Appearance : NBDestination.WithoutArguments() {

        override val topLevelDestination: NBTopLevelDestination
            get() = topLevel

    }

    object Font : NBDestination.WithoutArguments() {

        override val topLevelDestination: NBTopLevelDestination
            get() = topLevel

    }

    object Overview : NBDestination.WithoutArguments() {

        override val topLevelDestination: NBTopLevelDestination
            get() = topLevel

    }

    object Units : NBDestination.WithoutArguments() {

        override val topLevelDestination: NBTopLevelDestination
            get() = topLevel

    }

}