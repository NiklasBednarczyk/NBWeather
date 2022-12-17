package de.niklasbednarczyk.nbweather.navigation

import androidx.compose.runtime.Composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconButton
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import de.niklasbednarczyk.nbweather.feature.about.navigation.aboutGraph
import de.niklasbednarczyk.nbweather.feature.location.navigation.LocationDestinations
import de.niklasbednarczyk.nbweather.feature.location.navigation.locationGraph
import de.niklasbednarczyk.nbweather.feature.search.navigation.SearchDestinations
import de.niklasbednarczyk.nbweather.feature.search.navigation.searchGraph
import de.niklasbednarczyk.nbweather.feature.settings.navigation.settingsGraph

@Composable
fun NBNavHost(
    navigator: NBNavigator,
    isInitialCurrentLocationSet: Boolean
) {
    val startDestination = if (isInitialCurrentLocationSet) {
        LocationDestinations.Overview.route
    } else {
        SearchDestinations.Overview.route
    }

    val navigationIconBack = @Composable {
        NBIconButton(
            icon = NBIcons.Back,
            onClick = { navigator.popBackStack() }
        )
    }
    val navigationIconDrawer = @Composable {
        NBIconButton(
            icon = NBIcons.Drawer,
            onClick = { navigator.openDrawer() }
        )
    }

    AnimatedNavHost(
        navController = navigator.navController,
        startDestination = startDestination
    ) {
        aboutGraph(
            navigationIconBack = navigationIconBack
        )
        locationGraph(
            navigationIconDrawer = navigationIconDrawer,
            navigationIconBack = navigationIconBack,
            navigateToAlerts = navigator::navigateToAlerts,
            navigateToDaily = navigator::navigateToDaily,
            navigateToHourly = navigator::navigateToHourly,
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