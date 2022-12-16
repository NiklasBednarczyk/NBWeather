package de.niklasbednarczyk.nbweather.feature.settings.navigation

import de.niklasbednarczyk.nbweather.core.ui.navigation.NBNavigationDestination

object SettingsDestinations {

    object ColorScheme : NBNavigationDestination.Detail {
        override val route: String
            get() = "settings_color_scheme"
    }

    object Language : NBNavigationDestination.Detail {
        override val route: String
            get() = "settings_language"
    }

    object Overview : NBNavigationDestination.Overview {
        override val route: String
            get() = "settings_overview"

        override val navigatesTo: List<NBNavigationDestination>
            get() = listOf(
                ColorScheme,
                Language,
                Theme,
                TimeFormat,
                Units
            )
    }

    object Theme : NBNavigationDestination.Detail {
        override val route: String
            get() = "settings_theme"
    }

    object TimeFormat : NBNavigationDestination.Detail {
        override val route: String
            get() = "settings_time_format"
    }

    object Units : NBNavigationDestination.Detail {
        override val route: String
            get() = "settings_units"
    }

}