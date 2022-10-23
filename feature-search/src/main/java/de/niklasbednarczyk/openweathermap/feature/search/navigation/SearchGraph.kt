package de.niklasbednarczyk.openweathermap.feature.search.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import de.niklasbednarczyk.openweathermap.core.ui.navigation.owmComposable
import de.niklasbednarczyk.openweathermap.feature.search.screens.overview.SearchOverviewScreen

fun NavGraphBuilder.searchGraph(
    navigationIconBack: @Composable () -> Unit,
    navigateToLocation: (Double, Double) -> Unit
) {
    owmComposable(SearchDestinations.Overview) {
        SearchOverviewScreen(
            navigationIcon = navigationIconBack,
            navigateToLocation = navigateToLocation
        )
    }
}