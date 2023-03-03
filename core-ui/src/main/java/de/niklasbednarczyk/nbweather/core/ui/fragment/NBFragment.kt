package de.niklasbednarczyk.nbweather.core.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import de.niklasbednarczyk.nbweather.core.ui.fragment.scaffold.NBScaffold
import de.niklasbednarczyk.nbweather.core.ui.fragment.scaffold.topappbar.NBTopAppBar
import de.niklasbednarczyk.nbweather.core.ui.fragment.scaffold.topappbar.NBTopAppBarItem
import de.niklasbednarczyk.nbweather.core.ui.fragment.utils.nbSetContent
import de.niklasbednarczyk.nbweather.core.ui.fragment.viewmodel.NBViewModel
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBNavControllerContainer
import de.niklasbednarczyk.nbweather.core.ui.navigation.drawer.NBNavigationDrawerViewModel
import de.niklasbednarczyk.nbweather.core.ui.snackbar.NBSnackbarViewModel

abstract class NBFragment<UiState, ViewData> : Fragment(), NBNavControllerContainer {

    protected abstract val viewModel: NBViewModel<UiState>
    private val navigationDrawerViewModel: NBNavigationDrawerViewModel by activityViewModels()
    val snackbarViewModel: NBSnackbarViewModel by activityViewModels()

    override val navController: NavController
        get() = findNavController()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return nbSetContent(
            context = requireContext(),
            viewCompositionStrategy = ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
        ) {
            val viewData = createViewData()
            NBScaffold(
                topBar = { scrollBehavior ->
                    val topAppBarItem = createTopAppBarItem(viewData)
                    NBTopAppBar(
                        scrollBehavior = scrollBehavior,
                        item = topAppBarItem,
                        openDrawer = navigationDrawerViewModel::openDrawer,
                        popBackStack = this::popBackStack
                    )
                },
                bottomBar = {
                    BottomBar(viewData)
                }
            ) {
                ScaffoldContent(viewData)
            }
        }
    }

    @Composable
    protected abstract fun createViewData(): ViewData

    protected abstract fun createTopAppBarItem(viewData: ViewData): NBTopAppBarItem

    @Composable
    protected open fun BottomBar(viewData: ViewData) {
    }

    @Composable
    protected abstract fun ScaffoldContent(viewData: ViewData)

}