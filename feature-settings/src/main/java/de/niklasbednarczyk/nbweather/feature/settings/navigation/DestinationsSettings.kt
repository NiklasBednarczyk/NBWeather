package de.niklasbednarczyk.nbweather.feature.settings.navigation

import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBDestination
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBTopLevelDestination
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBTopLevelDestinations

object DestinationsSettings {

    val topLevel = NBTopLevelDestinations.Settings

    object ColorScheme : NBDestination.WithoutArguments() {

        override val topLevelDestination: NBTopLevelDestination
            get() = topLevel

        override val path: String
            get() = "color-scheme"

    }

    object Language : NBDestination.WithoutArguments() {

        override val topLevelDestination: NBTopLevelDestination
            get() = topLevel

        override val path: String
            get() = "language"

    }

    object Overview : NBDestination.WithoutArguments() {

        override val topLevelDestination: NBTopLevelDestination
            get() = topLevel

        override val path: String
            get() = "overview"

    }

    object Theme : NBDestination.WithoutArguments() {

        override val topLevelDestination: NBTopLevelDestination
            get() = topLevel

        override val path: String
            get() = "theme"

    }

    object TimeFormat : NBDestination.WithoutArguments() {

        override val topLevelDestination: NBTopLevelDestination
            get() = topLevel

        override val path: String
            get() = "time-format"

    }

    object Units : NBDestination.WithoutArguments() {

        override val topLevelDestination: NBTopLevelDestination
            get() = topLevel

        override val path: String
            get() = "units"

    }

}