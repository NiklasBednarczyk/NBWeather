package de.niklasbednarczyk.nbweather.app

import androidx.activity.compose.BackHandler
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import de.niklasbednarczyk.nbweather.core.ui.resource.NBResourceWithoutLoadingView
import de.niklasbednarczyk.nbweather.data.settings.models.appearance.SettingsAppearanceModelData
import de.niklasbednarczyk.nbweather.navigation.NBNavHost
import de.niklasbednarczyk.nbweather.navigation.NBNavigationDrawer
import de.niklasbednarczyk.nbweather.navigation.NBNavigator
import de.niklasbednarczyk.nbweather.theme.NBTheme
import de.niklasbednarczyk.nbweather.theme.isLightTheme

@Composable
fun NBApp(
    viewModel: NBAppViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    val appearance = uiState.value.settingsAppearance

    if (appearance != null) {
        NBTheme(appearance) {
            SetupSystemBar(appearance)

            val navigator = NBNavigator.rememberNBNavigator()

            SetupBackPressWithNavigationDrawer(navigator = navigator)

            Surface {
                NBResourceWithoutLoadingView(uiState.value.isInitialCurrentLocationSetResource) { isInitialCurrentLocationSet ->
                    NBNavigationDrawer(
                        navigator = navigator,
                        drawerItems = uiState.value.drawerItems
                    ) {
                        NBNavHost(
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
private fun SetupSystemBar(appearance: SettingsAppearanceModelData) {
    val systemUiController = rememberSystemUiController()
    val darkIcons = appearance.theme.isLightTheme
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = darkIcons
        )
    }
}

@Composable
private fun SetupBackPressWithNavigationDrawer(
    navigator: NBNavigator
) {
    BackHandler(enabled = navigator.drawerState.isOpen) {
        navigator.closeDrawer()
    }
}
