package de.niklasbednarczyk.nbweather.core.ui.screen.fragment

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.font.FontFamily
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.common.string.NBString.Companion.asString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.font.changeFontFamily
import de.niklasbednarczyk.nbweather.core.ui.font.fontFamily
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBNavControllerContainer
import de.niklasbednarczyk.nbweather.core.ui.navigation.drawer.NBNavigationDrawerViewModel
import de.niklasbednarczyk.nbweather.core.ui.screen.scaffold.NBScaffold
import de.niklasbednarczyk.nbweather.core.ui.screen.scaffold.snackbar.NBSnackbarModel
import de.niklasbednarczyk.nbweather.core.ui.screen.scaffold.topappbar.NBTopAppBar
import de.niklasbednarczyk.nbweather.core.ui.screen.scaffold.topappbar.NBTopAppBarItem
import de.niklasbednarczyk.nbweather.core.ui.screen.utils.nbSetContent
import de.niklasbednarczyk.nbweather.core.ui.screen.viewmodel.NBViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class NBFragment<UiState> : Fragment(), NBNavControllerContainer {

    protected abstract val viewModel: NBViewModel<UiState>
    private val navigationDrawerViewModel: NBNavigationDrawerViewModel by activityViewModels()

    private val snackbarHostState = SnackbarHostState()
    private val snackbarChannel: Channel<NBSnackbarModel> = Channel()
    private val snackbarEvent = snackbarChannel.receiveAsFlow()

    override val navController: NavController
        get() = findNavController()


    protected open val keepInitialFontFamily: Boolean = false
    private var initialFontFamily: FontFamily? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setupSnackbar()

        return nbSetContent(
            context = requireContext(),
            viewCompositionStrategy = ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
        ) {
            if (keepInitialFontFamily) {
                val typography = MaterialTheme.typography
                if (initialFontFamily == null) {
                    initialFontFamily = typography.fontFamily
                }
                MaterialTheme(
                    typography = typography.changeFontFamily(initialFontFamily)
                ) {
                    SetupScaffold()
                }
            } else {
                SetupScaffold()
            }
        }
    }

    @Composable
    private fun SetupScaffold() {
        val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
        NBScaffold(
            topBar = { scrollBehavior ->
                val topAppBarItem = createTopAppBarItem(uiState)
                if (topAppBarItem != null) {
                    NBTopAppBar(
                        scrollBehavior = scrollBehavior,
                        item = topAppBarItem,
                        openDrawer = navigationDrawerViewModel::openDrawer,
                        popBackStack = this::popBackStack
                    )
                }
            },
            snackbarHostState = snackbarHostState
        ) {
            ScaffoldContent(uiState)
        }
    }

    protected abstract fun createTopAppBarItem(uiState: UiState): NBTopAppBarItem?

    @Composable
    protected abstract fun ScaffoldContent(uiState: UiState)

    protected fun isPermissionGranted(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun setupSnackbar() {
        launchSuspend {
            snackbarChannel.receiveAsFlow().collect { snackbar ->
                val result = snackbarHostState.showSnackbar(
                    message = snackbar.message.asString(requireContext()),
                    actionLabel = snackbar.action?.label.asString(requireContext()),
                    duration = snackbar.duration
                )
                when (result) {
                    SnackbarResult.ActionPerformed -> {
                        snackbar.action?.onActionPerformed?.invoke()
                    }

                    SnackbarResult.Dismissed -> {
                        snackbar.onDismissed()
                    }
                }
            }
        }
    }

    protected fun sendSnackbar(snackbar: NBSnackbarModel) {
        snackbarChannel.trySend(snackbar)
    }

    protected fun startIntent(intent: Intent?) {
        if (intent == null) return

        if (intent.resolveActivity(requireContext().packageManager) != null) {
            startActivity(intent)
        } else {
            val snackbar = NBSnackbarModel(
                message = NBString.ResString(R.string.fragment_snackbar_intent_failed_message)
            )
            sendSnackbar(snackbar)
        }
    }

    protected fun launchSuspend(
        invokeSuspend: suspend () -> Unit
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            invokeSuspend()
        }
    }

}