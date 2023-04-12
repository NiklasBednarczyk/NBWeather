package de.niklasbednarczyk.nbweather

import android.os.Bundle
import android.view.View
import androidx.activity.compose.BackHandler
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import de.niklasbednarczyk.nbweather.core.common.string.NBString.Companion.asString
import de.niklasbednarczyk.nbweather.core.ui.fragment.utils.nbSetContent
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBNavControllerContainer
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBTopLevelDestinations
import de.niklasbednarczyk.nbweather.core.ui.navigation.drawer.NBNavigationDrawerEventType
import de.niklasbednarczyk.nbweather.core.ui.navigation.drawer.NBNavigationDrawerViewModel
import de.niklasbednarczyk.nbweather.core.ui.resource.NBResourceWithoutLoadingView
import de.niklasbednarczyk.nbweather.core.ui.snackbar.NBSnackbarViewModel
import de.niklasbednarczyk.nbweather.core.ui.theme.NBTheme
import de.niklasbednarczyk.nbweather.databinding.ContentAppBinding
import de.niklasbednarczyk.nbweather.feature.about.navigation.graphAbout
import de.niklasbednarczyk.nbweather.feature.location.navigation.graphLocation
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

    private val snackbarViewModel: NBSnackbarViewModel by viewModels()

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
            val uiState = viewModel.uiState.collectAsState()
            val appearance = uiState.value.settingsAppearance

            if (appearance != null) {
                NBTheme(appearance) {
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
    private fun SetupSystemBar() {
        val systemUiController = rememberSystemUiController()
        val darkIcons = NBTheme.isLightTheme
        SideEffect {
            systemUiController.setSystemBarsColor(
                color = Color.Transparent, darkIcons = darkIcons
            )
        }
    }

    @Composable
    private fun SetupNavigationDrawer(
        drawerItems: List<NBNavigationDrawerItem>, content: @Composable () -> Unit
    ) {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

        BackHandler(enabled = drawerState.isOpen) {
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
            setupSnackbar(this.root)
        }

    }

    private fun setupGraph(
        isInitialCurrentLocationSet: Boolean,
        navHostFragment: FragmentContainerView
    ) {
        val startDestination = if (isInitialCurrentLocationSet) {
            NBTopLevelDestinations.Location
        } else {
            NBTopLevelDestinations.Search
        }

        val navController = navHostFragment.getFragment<NavHostFragment>().navController
        navController.graph = navController.createGraph(
            startDestination = startDestination.routeForNavigation
        ) {
            graphAbout()
            graphLocation()
            graphSearch()
            graphSettings()
        }
    }


    private fun setupSnackbar(view: View) {
        snackbarViewModel.setupChannel { snackbarModel ->
            val message = snackbarModel.message.asString(this)

            val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)

            snackbar.show()
        }
    }

}

