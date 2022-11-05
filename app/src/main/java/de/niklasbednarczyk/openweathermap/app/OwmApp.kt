package de.niklasbednarczyk.openweathermap.app

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import de.niklasbednarczyk.openweathermap.core.ui.resource.OwmResourceView
import de.niklasbednarczyk.openweathermap.data.settings.models.appearance.SettingsAppearanceModelData
import de.niklasbednarczyk.openweathermap.navigation.OwmNavHost
import de.niklasbednarczyk.openweathermap.navigation.OwmNavigationDrawer
import de.niklasbednarczyk.openweathermap.navigation.OwmNavigator
import de.niklasbednarczyk.openweathermap.theme.OwmTheme

@Composable
fun OwmApp(
    viewModel: OwmAppViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    val appearance = uiState.value.settingsAppearance

    if (appearance != null) {
        OwmTheme(appearance) {
            SetupSystemBar(appearance)

            val navigator = OwmNavigator.rememberOwmNavigator()

            SetupBackPressWithNavigationDrawer(navigator = navigator)

            Surface {
                OwmResourceView(
                    resource = uiState.value.visitedLocationsInfoResource
                ) { visitedLocationsInfo ->

                    OwmNavigationDrawer(
                        navigator = navigator,
                        visitedLocationsInfo = visitedLocationsInfo
                    ) {
                        OwmNavHost(
                            navigator = navigator,
                            isInitialCurrentLocationSet = visitedLocationsInfo.isInitialCurrentLocationSet
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SetupSystemBar(appearance: SettingsAppearanceModelData) {
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
