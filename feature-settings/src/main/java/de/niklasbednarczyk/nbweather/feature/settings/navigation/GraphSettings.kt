package de.niklasbednarczyk.nbweather.feature.settings.navigation

import androidx.navigation.NavGraphBuilder
import de.niklasbednarczyk.nbweather.core.ui.navigation.graph.nbFragment
import de.niklasbednarczyk.nbweather.core.ui.navigation.graph.nbNavigation
import de.niklasbednarczyk.nbweather.feature.settings.screens.appearance.SettingsAppearanceFragment
import de.niklasbednarczyk.nbweather.feature.settings.screens.font.SettingsFontFragment
import de.niklasbednarczyk.nbweather.feature.settings.screens.overview.SettingsOverviewFragment
import de.niklasbednarczyk.nbweather.feature.settings.screens.units.SettingsUnitsFragment

fun NavGraphBuilder.graphSettings() {
    nbNavigation(
        topLevelDestination = DestinationsSettings.topLevel,
        startDestination = DestinationsSettings.Overview
    ) {
        nbFragment<SettingsAppearanceFragment>(DestinationsSettings.Appearance)
        nbFragment<SettingsFontFragment>(DestinationsSettings.Font)
        nbFragment<SettingsOverviewFragment>(DestinationsSettings.Overview)
        nbFragment<SettingsUnitsFragment>(DestinationsSettings.Units)
    }
}