package de.niklasbednarczyk.nbweather.feature.forecast.navigation

import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBDestination
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBTopLevelDestination
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBTopLevelDestinations

object DestinationsForecast {

    val topLevel = NBTopLevelDestinations.Forecast

    object Overview : NBDestination.WithoutArguments() {

        override val topLevelDestination: NBTopLevelDestination
            get() = topLevel

    }

}