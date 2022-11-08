package de.niklasbednarczyk.openweathermap.navigation

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.core.ui.navigation.OwmNavigationDestination
import de.niklasbednarczyk.openweathermap.feature.location.navigation.LocationDestinations
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Stable
class OwmNavigator(
    val navController: NavHostController,
    val drawerState: DrawerState,
    private val coroutineScope: CoroutineScope
) {

    companion object {

        @Composable
        fun rememberOwmNavigator(
            navController: NavHostController = rememberAnimatedNavController(),
            drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
            coroutineScope: CoroutineScope = rememberCoroutineScope()
        ): OwmNavigator {
            return remember(navController, drawerState, coroutineScope) {
                OwmNavigator(navController, drawerState, coroutineScope)
            }
        }

    }

    private fun navigate(destination: OwmNavigationDestination, customRoute: String? = null) {
        val route = customRoute ?: destination.route

        when (destination) {
            is OwmNavigationDestination.Drawer -> {
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
            is OwmNavigationDestination.Detail -> {
                navController.navigate(route)

            }
            is OwmNavigationDestination.Overview -> {
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

    fun navigateToDestination(destination: OwmNavigationDestination) {
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