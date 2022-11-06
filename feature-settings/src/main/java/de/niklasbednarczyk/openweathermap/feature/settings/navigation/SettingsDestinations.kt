package de.niklasbednarczyk.openweathermap.feature.settings.navigation

import de.niklasbednarczyk.openweathermap.core.ui.navigation.OwmNavigationDestination

object SettingsDestinations {

    object ColorScheme : OwmNavigationDestination.Detail {
        override val route: String
            get() = "settings_color_scheme"
    }

    object DataLanguage : OwmNavigationDestination.Detail {
        override val route: String
            get() = "settings_data_language"
    }

    object Overview : OwmNavigationDestination.Overview {
        override val route: String
            get() = "settings_overview"

        override val navigatesTo: List<OwmNavigationDestination>
            get() = listOf(
                ColorScheme,
                DataLanguage,
                Theme,
                TimeFormat,
                Units
            )
    }

    object Theme : OwmNavigationDestination.Detail {
        override val route: String
            get() = "settings_theme"
    }

    object TimeFormat : OwmNavigationDestination.Detail {
        override val route: String
            get() = "settings_time_format"
    }

    object Units : OwmNavigationDestination.Detail {
        override val route: String
            get() = "settings_units"
    }

}