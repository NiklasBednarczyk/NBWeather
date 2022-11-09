package de.niklasbednarczyk.openweathermap.feature.settings.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import de.niklasbednarczyk.openweathermap.core.ui.navigation.OwmNavigationDestination
import de.niklasbednarczyk.openweathermap.core.ui.navigation.owmComposable
import de.niklasbednarczyk.openweathermap.feature.settings.screens.colorscheme.SettingsColorSchemeScreen
import de.niklasbednarczyk.openweathermap.feature.settings.screens.language.SettingsLanguageScreen
import de.niklasbednarczyk.openweathermap.feature.settings.screens.overview.SettingsOverviewScreen
import de.niklasbednarczyk.openweathermap.feature.settings.screens.theme.SettingsThemeScreen
import de.niklasbednarczyk.openweathermap.feature.settings.screens.timeformat.SettingsTimeFormatScreen
import de.niklasbednarczyk.openweathermap.feature.settings.screens.units.SettingsUnitsScreen

fun NavGraphBuilder.settingsGraph(
    navigationIconBack: @Composable () -> Unit,
    navigateToDestination: (OwmNavigationDestination) -> Unit,
) {
    val navigateToOverview = { navigateToDestination(SettingsDestinations.Overview) }

    owmComposable(SettingsDestinations.ColorScheme) {
        SettingsColorSchemeScreen(
            navigationIcon = navigationIconBack,
            navigateToOverview = navigateToOverview
        )
    }
    owmComposable(SettingsDestinations.Language) {
        SettingsLanguageScreen(
            navigationIcon = navigationIconBack,
            navigateToOverview = navigateToOverview
        )
    }
    owmComposable(SettingsDestinations.Overview) {
        SettingsOverviewScreen(
            navigationIcon = navigationIconBack,
            navigateToDestination = navigateToDestination
        )
    }
    owmComposable(SettingsDestinations.Theme) {
        SettingsThemeScreen(
            navigationIcon = navigationIconBack,
            navigateToOverview = navigateToOverview
        )
    }
    owmComposable(SettingsDestinations.TimeFormat) {
        SettingsTimeFormatScreen(
            navigationIcon = navigationIconBack,
            navigateToOverview = navigateToOverview
        )
    }
    owmComposable(SettingsDestinations.Units) {
        SettingsUnitsScreen(
            navigationIcon = navigationIconBack,
            navigateToOverview = navigateToOverview
        )
    }
}