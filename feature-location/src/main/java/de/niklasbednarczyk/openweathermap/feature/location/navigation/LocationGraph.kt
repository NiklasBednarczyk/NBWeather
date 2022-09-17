package de.niklasbednarczyk.openweathermap.feature.location.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import de.niklasbednarczyk.openweathermap.core.ui.navigation.owmComposable
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.LocationOverviewScreen

fun NavGraphBuilder.locationGraph(
    navigationIconDrawer: @Composable () -> Unit,
    navigateToSearch: () -> Unit
) {
    owmComposable(LocationDestinations.Overview) {
        LocationOverviewScreen(
            navigationIcon = navigationIconDrawer,
            navigateToSearch = navigateToSearch
        )
    }
}