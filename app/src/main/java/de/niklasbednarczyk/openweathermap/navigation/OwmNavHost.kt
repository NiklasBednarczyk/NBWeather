package de.niklasbednarczyk.openweathermap.navigation

import androidx.compose.runtime.Composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIconButton
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIcons
import de.niklasbednarczyk.openweathermap.feature.location.navigation.LocationDestinations
import de.niklasbednarczyk.openweathermap.feature.location.navigation.locationGraph
import de.niklasbednarczyk.openweathermap.feature.search.navigation.SearchDestinations
import de.niklasbednarczyk.openweathermap.feature.search.navigation.searchGraph
import de.niklasbednarczyk.openweathermap.feature.settings.navigation.settingsGraph

@Composable
fun OwmNavHost(
    navigator: OwmNavigator,
    isInitialCurrentLocationSet: Boolean
) {
    val startDestination = if (isInitialCurrentLocationSet) {
        LocationDestinations.Overview.route
    } else {
        SearchDestinations.Overview.route
    }

    val navigationIconBack = @Composable {
        OwmIconButton(
            icon = OwmIcons.Back,
            onClick = { navigator.popBackStack() }
        )
    }
    val navigationIconDrawer = @Composable {
        OwmIconButton(
            icon = OwmIcons.Drawer,
            onClick = { navigator.openDrawer() }
        )
    }

    AnimatedNavHost(
        navController = navigator.navController,
        startDestination = startDestination
    ) {
        locationGraph(
            navigationIconDrawer = navigationIconDrawer,
            navigateToSearch = {
                navigator.navigateToDestination(SearchDestinations.Overview)
            }
        )
        searchGraph(
            navigationIconBack = navigationIconBack,
            navigateToLocation = navigator::navigateToLocation
        )
        settingsGraph(
            navigationIconBack = navigationIconBack,
            navigateToDestination = navigator::navigateToDestination
        )
    }

}