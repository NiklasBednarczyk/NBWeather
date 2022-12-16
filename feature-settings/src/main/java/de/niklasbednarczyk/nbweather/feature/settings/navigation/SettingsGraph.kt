package de.niklasbednarczyk.nbweather.feature.settings.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import de.niklasbednarczyk.nbweather.core.ui.navigation.NBNavigationDestination
import de.niklasbednarczyk.nbweather.core.ui.navigation.nbComposable
import de.niklasbednarczyk.nbweather.feature.settings.screens.colorscheme.SettingsColorSchemeScreen
import de.niklasbednarczyk.nbweather.feature.settings.screens.language.SettingsLanguageScreen
import de.niklasbednarczyk.nbweather.feature.settings.screens.overview.SettingsOverviewScreen
import de.niklasbednarczyk.nbweather.feature.settings.screens.theme.SettingsThemeScreen
import de.niklasbednarczyk.nbweather.feature.settings.screens.timeformat.SettingsTimeFormatScreen
import de.niklasbednarczyk.nbweather.feature.settings.screens.units.SettingsUnitsScreen

fun NavGraphBuilder.settingsGraph(
    navigationIconBack: @Composable () -> Unit,
    navigateToDestination: (NBNavigationDestination) -> Unit,
) {
    val navigateToOverview = { navigateToDestination(SettingsDestinations.Overview) }

    nbComposable(SettingsDestinations.ColorScheme) {
        SettingsColorSchemeScreen(
            navigationIcon = navigationIconBack,
            navigateToOverview = navigateToOverview
        )
    }
    nbComposable(SettingsDestinations.Language) {
        SettingsLanguageScreen(
            navigationIcon = navigationIconBack,
            navigateToOverview = navigateToOverview
        )
    }
    nbComposable(SettingsDestinations.Overview) {
        SettingsOverviewScreen(
            navigationIcon = navigationIconBack,
            navigateToDestination = navigateToDestination
        )
    }
    nbComposable(SettingsDestinations.Theme) {
        SettingsThemeScreen(
            navigationIcon = navigationIconBack,
            navigateToOverview = navigateToOverview
        )
    }
    nbComposable(SettingsDestinations.TimeFormat) {
        SettingsTimeFormatScreen(
            navigationIcon = navigationIconBack,
            navigateToOverview = navigateToOverview
        )
    }
    nbComposable(SettingsDestinations.Units) {
        SettingsUnitsScreen(
            navigationIcon = navigationIconBack,
            navigateToOverview = navigateToOverview
        )
    }
}