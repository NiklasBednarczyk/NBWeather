package de.niklasbednarczyk.nbweather.feature.search.navigation

import androidx.navigation.NavGraphBuilder
import de.niklasbednarczyk.nbweather.core.ui.navigation.graph.nbFragment
import de.niklasbednarczyk.nbweather.core.ui.navigation.graph.nbNavigation
import de.niklasbednarczyk.nbweather.feature.search.screens.overview.SearchOverviewFragment

fun NavGraphBuilder.graphSearch() {
    nbNavigation(
        topLevelDestination = DestinationsSearch.topLevel,
        startDestination = DestinationsSearch.Overview
    ) {
        nbFragment<SearchOverviewFragment>(DestinationsSearch.Overview)
    }
}