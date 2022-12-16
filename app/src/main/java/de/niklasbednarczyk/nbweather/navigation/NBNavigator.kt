package de.niklasbednarczyk.nbweather.navigation

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import de.niklasbednarczyk.nbweather.core.ui.navigation.NBNavigationDestination
import de.niklasbednarczyk.nbweather.feature.location.navigation.LocationDestinations
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Stable
class NBNavigator(
    val navController: NavHostController,
    val drawerState: DrawerState,
    private val coroutineScope: CoroutineScope
) {

    companion object {

        @Composable
        fun rememberNBNavigator(
            navController: NavHostController = rememberAnimatedNavController(),
            drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
            coroutineScope: CoroutineScope = rememberCoroutineScope()
        ): NBNavigator {
            return remember(navController, drawerState, coroutineScope) {
                NBNavigator(navController, drawerState, coroutineScope)
            }
        }

    }

    private fun navigate(destination: NBNavigationDestination, customRoute: String? = null) {
        val route = customRoute ?: destination.route

        when (destination) {
            is NBNavigationDestination.Drawer -> {
                navController.navigate(route) {
                    val destinationId = navController.findDestination(destination.route)?.id
                    if (navController.backQueue.any { it.destination.id == destinationId }) {
                        if (destinationId != null) {
                            popUpTo(destinationId) {
                                inclusive = true
                            }
                        }
                    } else {
                        val currentDestinationId = navController.currentDestination?.id
                        if (currentDestinationId != null) {
                            popUpTo(currentDestinationId) {
                                inclusive = true
                            }
                        }
                    }
                }
            }
            is NBNavigationDestination.Detail -> {
                navController.navigate(route)

            }
            is NBNavigationDestination.Overview -> {
                navController.navigate(route) {
                    val previousId = navController.previousBackStackEntry?.destination?.id
                    if (previousId != null) {
                        popUpTo(previousId) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }

    fun navigateToDestination(destination: NBNavigationDestination) {
        navigate(destination)
    }

    fun navigateToLocation(latitude: Double, longitude: Double) {
        val route = LocationDestinations.Overview.createRoute(latitude, longitude)
        navigate(LocationDestinations.Overview, route)
    }

    fun navigateToAlerts(latitude: Double?, longitude: Double?) {
        if (latitude != null && longitude != null) {
            val route = LocationDestinations.Alerts.createRoute(latitude, longitude)
            navigate(LocationDestinations.Alerts, route)
        }
    }

    fun navigateToDaily(forecastTime: Long, latitude: Double?, longitude: Double?) {
        if (latitude != null && longitude != null) {
            val route = LocationDestinations.Daily.createRoute(forecastTime, latitude, longitude)
            navigate(LocationDestinations.Daily, route)
        }
    }

    fun navigateToHourly(forecastTime: Long, latitude: Double?, longitude: Double?) {
        if (latitude != null && longitude != null) {
            val route = LocationDestinations.Hourly.createRoute(forecastTime, latitude, longitude)
            navigate(LocationDestinations.Hourly, route)
        }
    }

    fun popBackStack() {
        navController.popBackStack()
    }

    fun closeDrawer() {
        coroutineScope.launch {
            drawerState.close()
        }
    }

    fun openDrawer() {
        coroutineScope.launch {
            drawerState.open()
        }
    }


}