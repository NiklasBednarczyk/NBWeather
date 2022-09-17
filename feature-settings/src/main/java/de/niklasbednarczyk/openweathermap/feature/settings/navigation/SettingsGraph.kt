package de.niklasbednarczyk.openweathermap.feature.settings.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import de.niklasbednarczyk.openweathermap.core.ui.navigation.owmComposable
import de.niklasbednarczyk.openweathermap.feature.settings.screens.overview.SettingsOverviewScreen

fun NavGraphBuilder.settingsGraph(
    navigationIconBack: @Composable () -> Unit
) {
    owmComposable(SettingsDestinations.Overview) {
        SettingsOverviewScreen(
            navigationIcon = navigationIconBack
        )
    }
}