package de.niklasbednarczyk.openweathermap.navigation

import androidx.compose.runtime.Composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIconButton
import de.niklasbednarczyk.openweathermap.feature.location.navigation.LocationDestinations
import de.niklasbednarczyk.openweathermap.feature.location.navigation.locationGraph
import de.niklasbednarczyk.openweathermap.feature.search.navigation.SearchDestinations
import de.niklasbednarczyk.openweathermap.feature.search.navigation.searchGraph
import de.niklasbednarczyk.openweathermap.feature.settings.navigation.settingsGraph
import de.niklasbednarczyk.openweathermap.icons.AppIcons

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
            icon = AppIcons.Back,
            onClick = { navigator.popBackStack() }
        )
    }
    val navigationIconDrawer = @Composable {
        OwmIconButton(
            icon = AppIcons.Drawer,
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
                navigator.navigate(SearchDestinations.Overview)
            }
        )
        searchGraph(
            navigationIconBack = navigationIconBack,
            navigateToLocation = navigator::navigateToLocation
        )
        settingsGraph(
            navigationIconBack = navigationIconBack
        )
    }

}