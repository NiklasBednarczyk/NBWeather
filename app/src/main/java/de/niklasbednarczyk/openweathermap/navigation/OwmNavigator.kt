package de.niklasbednarczyk.openweathermap.navigation

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import de.niklasbednarczyk.openweathermap.core.ui.navigation.OwmNavigationDestination
import de.niklasbednarczyk.openweathermap.core.ui.navigation.OwmNavigationDrawerDestination
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

    fun navigate(destination: OwmNavigationDestination, customRoute: String? = null) {
        val route = customRoute ?: destination.route

        if (destination is OwmNavigationDrawerDestination) {
            navController.navigate(route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        } else {
            navController.navigate(route)
        }
    }

    fun navigateToLocation(latitude: Double?, longitude: Double?) {
        if (latitude != null && longitude != null) {
            val route = LocationDestinations.Overview.createRoute(latitude, longitude)
            navigate(LocationDestinations.Overview, route)
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