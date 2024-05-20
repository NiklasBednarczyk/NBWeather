package de.niklasbednarczyk.nbweather

import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.settings.appearance.NBAppearanceModel
import de.niklasbednarczyk.nbweather.core.common.settings.font.NBFontModel
import de.niklasbednarczyk.nbweather.core.common.settings.order.NBOrderModel
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsModel
import de.niklasbednarczyk.nbweather.core.ui.resource.NBResourceWithoutLoadingView
import de.niklasbednarczyk.nbweather.core.ui.settings.LocalNBAppearance
import de.niklasbednarczyk.nbweather.core.ui.settings.LocalNBFont
import de.niklasbednarczyk.nbweather.core.ui.settings.LocalNBOrder
import de.niklasbednarczyk.nbweather.core.ui.settings.LocalNBUnits
import de.niklasbednarczyk.nbweather.core.ui.settings.NBSettings
import de.niklasbednarczyk.nbweather.navigation.drawer.NBDrawerController.Companion.rememberNBDrawerController
import de.niklasbednarczyk.nbweather.navigation.drawer.NBNavigationDrawerView
import de.niklasbednarczyk.nbweather.navigation.host.NBNavHostView
import de.niklasbednarczyk.nbweather.theme.NBTheme


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {

        /**
         * The default light scrim, as defined by androidx and the platform:
         * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=35-38;drc=27e7d52e8604a080133e8b842db10c89b4482598
         */
        private val lightScrim = android.graphics.Color.argb(0xe6, 0xFF, 0xFF, 0xFF)

        /**
         * The default dark scrim, as defined by androidx and the platform:
         * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=40-44;drc=27e7d52e8604a080133e8b842db10c89b4482598
         */
        private val darkScrim = android.graphics.Color.argb(0x80, 0x1b, 0x1b, 0x1b)

    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            val navController = rememberNavController()
            val drawerController = rememberNBDrawerController()

            SetupSettings(
                appearance = uiState.appearance,
                font = uiState.font,
                order = uiState.order,
                units = uiState.units
            ) {
                SetupEdgeToEdge()

                NBTheme {
                    Surface {
                        NBResourceWithoutLoadingView(uiState.viewDataResource) { viewData ->
                            NBNavigationDrawerView(
                                navController = navController,
                                drawerController = drawerController,
                                drawerItems = viewData.drawerItems
                            ) {
                                NBNavHostView(
                                    navController = navController,
                                    drawerController = drawerController,
                                    initialCurrentLocation = viewData.initialCurrentLocation
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun SetupSettings(
        appearance: NBAppearanceModel?,
        font: NBFontModel?,
        order: NBOrderModel?,
        units: NBUnitsModel?,
        content: @Composable () -> Unit
    ) {
        nbNullSafe(
            appearance,
            font,
            order,
            units
        ) { a, f, o, u ->
            CompositionLocalProvider(
                LocalNBAppearance provides a,
                LocalNBFont provides f,
                LocalNBOrder provides o,
                LocalNBUnits provides u,
                content = content
            )
        }
    }

    @Composable
    private fun SetupEdgeToEdge() {
        val isDarkTheme = NBSettings.isDarkTheme
        DisposableEffect(isDarkTheme) {
            enableEdgeToEdge(
                statusBarStyle = SystemBarStyle.auto(
                    lightScrim = android.graphics.Color.TRANSPARENT,
                    darkScrim = android.graphics.Color.TRANSPARENT,
                    detectDarkMode = { isDarkTheme }
                ),
                navigationBarStyle = SystemBarStyle.auto(
                    lightScrim = lightScrim,
                    darkScrim = darkScrim,
                    detectDarkMode = { isDarkTheme }
                )
            )
            onDispose {}
        }
    }

}

