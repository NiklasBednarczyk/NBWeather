package de.niklasbednarczyk.nbweather.feature.search.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import de.niklasbednarczyk.nbweather.core.ui.navigation.nbComposable
import de.niklasbednarczyk.nbweather.feature.search.screens.overview.SearchOverviewScreen

fun NavGraphBuilder.searchGraph(
    navigationIconBack: @Composable () -> Unit,
    navigateToLocation: (Double, Double) -> Unit
) {
    nbComposable(SearchDestinations.Overview) {
        SearchOverviewScreen(
            navigationIcon = navigationIconBack,
            navigateToLocation = navigateToLocation
        )
    }
}