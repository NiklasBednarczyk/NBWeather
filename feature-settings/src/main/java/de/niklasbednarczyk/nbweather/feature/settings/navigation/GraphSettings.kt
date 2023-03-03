package de.niklasbednarczyk.nbweather.feature.settings.navigation

import androidx.navigation.NavGraphBuilder
import de.niklasbednarczyk.nbweather.core.ui.navigation.graph.nbFragment
import de.niklasbednarczyk.nbweather.core.ui.navigation.graph.nbNavigation
import de.niklasbednarczyk.nbweather.feature.settings.screens.colorscheme.SettingsColorSchemeFragment
import de.niklasbednarczyk.nbweather.feature.settings.screens.language.SettingsLanguageFragment
import de.niklasbednarczyk.nbweather.feature.settings.screens.overview.SettingsOverviewFragment
import de.niklasbednarczyk.nbweather.feature.settings.screens.theme.SettingsThemeFragment
import de.niklasbednarczyk.nbweather.feature.settings.screens.timeformat.SettingsTimeFormatFragment
import de.niklasbednarczyk.nbweather.feature.settings.screens.units.SettingsUnitsFragment

fun NavGraphBuilder.graphSettings() {
    nbNavigation(
        topLevelDestination = DestinationsSettings.topLevel,
        startDestination = DestinationsSettings.Overview
    ) {
        nbFragment<SettingsColorSchemeFragment>(DestinationsSettings.ColorScheme)
        nbFragment<SettingsLanguageFragment>(DestinationsSettings.Language)
        nbFragment<SettingsOverviewFragment>(DestinationsSettings.Overview)
        nbFragment<SettingsThemeFragment>(DestinationsSettings.Theme)
        nbFragment<SettingsTimeFormatFragment>(DestinationsSettings.TimeFormat)
        nbFragment<SettingsUnitsFragment>(DestinationsSettings.Units)
    }
}