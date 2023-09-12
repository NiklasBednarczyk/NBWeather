package de.niklasbednarczyk.nbweather.core.ui.navigation.destination

object NBTopLevelDestinations {

    object About : NBTopLevelDestination()

    object Forecast : NBTopLevelDestination() {

        override val clearBackStack: Boolean
            get() = true

    }

    object Search : NBTopLevelDestination()

    object Settings : NBTopLevelDestination()

}