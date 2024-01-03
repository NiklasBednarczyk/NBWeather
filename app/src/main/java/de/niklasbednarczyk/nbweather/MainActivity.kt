package de.niklasbednarczyk.nbweather

import android.os.Bundle
import androidx.activity.compose.BackHandler
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.settings.appearance.NBAppearanceModel
import de.niklasbednarczyk.nbweather.core.common.settings.font.NBFontModel
import de.niklasbednarczyk.nbweather.core.common.settings.order.NBOrderModel
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsModel
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBNavControllerContainer
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBTopLevelDestinations
import de.niklasbednarczyk.nbweather.core.ui.navigation.drawer.NBNavigationDrawerEventType
import de.niklasbednarczyk.nbweather.core.ui.navigation.drawer.NBNavigationDrawerViewModel
import de.niklasbednarczyk.nbweather.core.ui.resource.NBResourceWithoutLoadingView
import de.niklasbednarczyk.nbweather.core.ui.screen.utils.nbSetContent
import de.niklasbednarczyk.nbweather.core.ui.settings.LocalNBAppearance
import de.niklasbednarczyk.nbweather.core.ui.settings.LocalNBFont
import de.niklasbednarczyk.nbweather.core.ui.settings.LocalNBOrder
import de.niklasbednarczyk.nbweather.core.ui.settings.LocalNBUnits
import de.niklasbednarczyk.nbweather.core.ui.settings.NBSettings
import de.niklasbednarczyk.nbweather.databinding.ContentAppBinding
import de.niklasbednarczyk.nbweather.feature.about.navigation.graphAbout
import de.niklasbednarczyk.nbweather.feature.forecast.navigation.graphForecast
import de.niklasbednarczyk.nbweather.feature.search.navigation.graphSearch
import de.niklasbednarczyk.nbweather.feature.settings.navigation.graphSettings
import de.niklasbednarczyk.nbweather.navigation.NBNavigationDrawer
import de.niklasbednarczyk.nbweather.navigation.NBNavigationDrawerItem
import de.niklasbednarczyk.nbweather.theme.NBTheme
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NBNavControllerContainer {

    private val viewModel: MainViewModel by viewModels()

    private val navigationDrawerViewModel: NBNavigationDrawerViewModel by viewModels()

    override val navController: NavController
        get() {
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            return navHostFragment.navController
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)


        setContentView(nbSetContent(this) {
            val uiState = viewModel.uiState.collectAsStateWithLifecycle()
            SetupSettings(
                appearance = uiState.value.appearance,
                font = uiState.value.font,
                order = uiState.value.order,
                units = uiState.value.units
            ) {
                NBTheme {
                    SetupSystemBar()
                    Surface {
                        NBResourceWithoutLoadingView(uiState.value.isInitialCurrentLocationSetResource) { isInitialCurrentLocationSet ->
                            SetupNavigationDrawer(
                                drawerItems = uiState.value.drawerItems,
                            ) {
                                SetupViewBinding(
                                    isInitialCurrentLocationSet = isInitialCurrentLocationSet,
                                )
                            }
                        }
                    }
                }
            }
        })
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
    private fun SetupSystemBar() {
        val systemUiController = rememberSystemUiController()
        val darkIcons = NBSettings.isLightTheme
        SideEffect {
            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = darkIcons
            )
        }
    }

    @Composable
    private fun SetupNavigationDrawer(
        drawerItems: List<NBNavigationDrawerItem>, content: @Composable () -> Unit
    ) {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

        BackHandler(drawerState.isOpen) {
            navigationDrawerViewModel.closeDrawer()
        }

        val scope = rememberCoroutineScope()

        navigationDrawerViewModel.setupChannel { event ->
            when (event) {
                NBNavigationDrawerEventType.OPEN -> {
                    scope.launch {
                        drawerState.open()
                    }
                }

                NBNavigationDrawerEventType.CLOSE -> {
                    scope.launch {
                        drawerState.close()
                    }
                }
            }
        }

        NBNavigationDrawer(
            drawerState = drawerState,
            drawerItems = drawerItems,
            closeDrawer = navigationDrawerViewModel::closeDrawer,
            navigateToTopLevelDestination = ::navigate,
            setCurrentLocation = viewModel::setCurrentLocation,
            content = content
        )
    }

    @Composable
    private fun SetupViewBinding(
        isInitialCurrentLocationSet: Boolean,
    ) {
        AndroidViewBinding(ContentAppBinding::inflate) {
            setupGraph(
                isInitialCurrentLocationSet = isInitialCurrentLocationSet,
                navHostFragment = navHostFragment
            )
        }

    }

    private fun setupGraph(
        isInitialCurrentLocationSet: Boolean,
        navHostFragment: FragmentContainerView
    ) {
        val startDestination = if (isInitialCurrentLocationSet) {
            NBTopLevelDestinations.Forecast
        } else {
            NBTopLevelDestinations.Search
        }

        val navController = navHostFragment.getFragment<NavHostFragment>().navController
        navController.graph = navController.createGraph(
            startDestination = startDestination.routeForGraph
        ) {
            graphAbout()
            graphForecast()
            graphSearch()
            graphSettings()
        }
    }


}

