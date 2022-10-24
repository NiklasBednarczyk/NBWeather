package de.niklasbednarczyk.openweathermap.app

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.resource.Resource
import de.niklasbednarczyk.openweathermap.core.ui.compositions.settings.OwmLocalSettings
import de.niklasbednarczyk.openweathermap.core.ui.compositions.settings.OwmSettingsModel
import de.niklasbednarczyk.openweathermap.core.ui.resource.OwmResourceView
import de.niklasbednarczyk.openweathermap.data.settings.models.units.SettingsUnitsModelData
import de.niklasbednarczyk.openweathermap.navigation.OwmNavHost
import de.niklasbednarczyk.openweathermap.navigation.OwmNavigationDrawer
import de.niklasbednarczyk.openweathermap.navigation.OwmNavigator
import de.niklasbednarczyk.openweathermap.theme.OwmTheme

@Composable
fun OwmApp(
    viewModel: OwmAppViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    SetupSettings(
        settingsUnits = uiState.value.settingsUnits
    ) {
        OwmTheme {
            SetupSystemBar()

            val navigator = OwmNavigator.rememberOwmNavigator()

            SetupBackPressWithNavigationDrawer(navigator = navigator)

            val locationsResource = Resource.combine(
                uiState.value.visitedLocationsResource,
                uiState.value.currentLocationResource,
                uiState.value.isInitialCurrentLocationSetResource
            )

            Surface {
                OwmResourceView(
                    resource = locationsResource
                ) { locations ->

                    val visitedLocations = locations.first
                    val currentLocation = locations.second
                    val isInitialCurrentLocationSet = locations.third

                    OwmNavigationDrawer(
                        navigator = navigator,
                        visitedLocations = visitedLocations,
                        currentLocation = currentLocation
                    ) {
                        OwmNavHost(
                            navigator = navigator,
                            isInitialCurrentLocationSet = isInitialCurrentLocationSet
                        )
                    }
                }
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

@Composable
private fun SetupSettings(
    settingsUnits: SettingsUnitsModelData?,
    content: @Composable () -> Unit
) {
    if (settingsUnits != null) {
        val settings = OwmSettingsModel(
            units = settingsUnits
        )
        CompositionLocalProvider(
            OwmLocalSettings provides settings
        ) {
            content()
        }
    }
}