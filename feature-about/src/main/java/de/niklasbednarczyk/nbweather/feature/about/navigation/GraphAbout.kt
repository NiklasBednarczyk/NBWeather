package de.niklasbednarczyk.nbweather.feature.about.navigation

import androidx.navigation.NavGraphBuilder
import de.niklasbednarczyk.nbweather.core.ui.navigation.graph.nbFragment
import de.niklasbednarczyk.nbweather.core.ui.navigation.graph.nbNavigation
import de.niklasbednarczyk.nbweather.feature.about.screens.overview.AboutOverviewFragment

fun NavGraphBuilder.graphAbout() {
    nbNavigation(
        topLevelDestination = DestinationsAbout.topLevel,
        startDestination = DestinationsAbout.Overview
    ) {
        nbFragment<AboutOverviewFragment>(DestinationsAbout.Overview)
    }
}