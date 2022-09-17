package de.niklasbednarczyk.openweathermap.feature.settings.navigation

import de.niklasbednarczyk.openweathermap.core.ui.navigation.OwmNavigationDestination

object SettingsDestinations {

    object Overview : OwmNavigationDestination {
        override val route: String
            get() = "settings_overview"
    }

}