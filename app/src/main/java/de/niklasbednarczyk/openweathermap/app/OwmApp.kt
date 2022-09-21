package de.niklasbednarczyk.openweathermap.app

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.Resource
import de.niklasbednarczyk.openweathermap.core.ui.resource.ResourceView
import de.niklasbednarczyk.openweathermap.navigation.OwmNavHost
import de.niklasbednarczyk.openweathermap.navigation.OwmNavigationDrawer
import de.niklasbednarczyk.openweathermap.navigation.OwmNavigator
import de.niklasbednarczyk.openweathermap.theme.OwmTheme

@Composable
fun OwmApp(
    viewModel: OwmAppViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    OwmTheme {
        SetupSystemBar()

        val navigator = OwmNavigator.rememberOwmNavigator()

        SetupBackPressWithNavigationDrawer(navigator = navigator)

        val locationsResource = Resource.combine(
            uiState.value.savedLocationsResource,
            uiState.value.currentLocationResource
        )

        ResourceView(
            resource = locationsResource,
            loadingContent = {}
        ) { locations ->

            val savedLocations = locations.first
            val currentLocation = locations.second

            OwmNavigationDrawer(
                navigator = navigator,
                savedLocations = savedLocations,
                currentLocation = currentLocation
            ) {
                OwmNavHost(
                    navigator = navigator,
                    currentLocation = currentLocation
                )
            }
        }


    }
}

@Composable
private fun SetupSystemBar() {
    val systemUiController = rememberSystemUiController()
    val darkIcons = !isSystemInDarkTheme() //TODO (#15) Replace with settings is in dark theme
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = darkIcons
        )
    }
}

@Composable
private fun SetupBackPressWithNavigationDrawer(
    navigator: OwmNavigator
) {
    BackHandler(enabled = navigator.drawerState.isOpen) {
        navigator.closeDrawer()
    }
}