package de.niklasbednarczyk.openweathermap.feature.settings.navigation

import de.niklasbednarczyk.openweathermap.core.ui.navigation.OwmNavigationDestination

object SettingsDestinations {

    object ColorScheme : OwmNavigationDestination.Detail {
        override val route: String
            get() = "settings_color_scheme"
    }

    object Language : OwmNavigationDestination.Detail {
        override val route: String
            get() = "settings_language"
    }

    object Overview : OwmNavigationDestination.Overview {
        override val route: String
            get() = "settings_overview"

        override val navigatesTo: List<OwmNavigationDestination>
            get() = listOf(
                ColorScheme,
                Language,
                Theme,
                Units
            )
    }

    object Theme : OwmNavigationDestination.Detail {
        override val route: String
            get() = "settings_theme"
    }

    object Units : OwmNavigationDestination.Detail {
        override val route: String
            get() = "settings_units"
    }

}