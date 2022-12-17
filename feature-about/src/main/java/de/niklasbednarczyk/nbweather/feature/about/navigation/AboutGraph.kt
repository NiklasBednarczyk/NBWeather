package de.niklasbednarczyk.nbweather.feature.about.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import de.niklasbednarczyk.nbweather.core.ui.navigation.nbComposable
import de.niklasbednarczyk.nbweather.feature.about.screens.overview.AboutOverviewScreen

fun NavGraphBuilder.aboutGraph(
    navigationIconBack: @Composable () -> Unit,
) {
    nbComposable(AboutDestinations.Overview) {
        AboutOverviewScreen(
            navigationIcon = navigationIconBack
        )
    }
}