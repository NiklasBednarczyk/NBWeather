package de.niklasbednarczyk.openweathermap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import de.niklasbednarczyk.openweathermap.navigation.*
import de.niklasbednarczyk.openweathermap.theme.OwmTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupInsets()

        setContent {
            OwmTheme {
                SetupBackPressHandler {
                    SetupSystemBar()

                    val navigator = OwmNavigator.rememberOwmNavigator()

                    SetupBackPressWithNavigationDrawer(navigator = navigator)

                    OwmNavigationDrawer(
                        navigator = navigator
                    ) {
                        OwmNavHost(navigator)
                    }
                }
            }
        }
    }


    private fun setupInsets() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
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
    private fun SetupBackPressHandler(
        content: @Composable () -> Unit
    ) {
        CompositionLocalProvider(
            LocalBackPressedDispatcher provides this.onBackPressedDispatcher
        ) {
            content()
        }
    }

    @Composable
    private fun SetupBackPressWithNavigationDrawer(
        navigator: OwmNavigator
    ) {
        if (navigator.drawerState.isOpen) {
            OwmBackPressHandler {
                navigator.closeDrawer()
            }
        }
    }
}

