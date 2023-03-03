package de.niklasbednarczyk.nbweather.core.ui.navigation.destination

object NBTopLevelDestinations {

    object About : NBTopLevelDestination() {

        override val authority: String
            get() = "about"

    }

    object Location : NBTopLevelDestination() {

        override val authority: String
            get() = "location"

        override val clearBackStack: Boolean
            get() = true

    }

    object Search : NBTopLevelDestination() {

        override val authority: String
            get() = "search"

    }

    object Settings : NBTopLevelDestination() {

        override val authority: String
            get() = "settings"

    }

}