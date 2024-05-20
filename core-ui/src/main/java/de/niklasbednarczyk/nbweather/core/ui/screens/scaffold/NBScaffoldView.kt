package de.niklasbednarczyk.nbweather.core.ui.screens.scaffold

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.ui.screens.scaffold.snackbar.NBSnackbarController
import de.niklasbednarczyk.nbweather.core.ui.screens.scaffold.topappbar.NBTopAppBarItem
import de.niklasbednarczyk.nbweather.core.ui.screens.scaffold.topappbar.NBTopAppBarView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NBScaffoldView(
    topAppBarItem: NBTopAppBarItem?,
    snackbarController: NBSnackbarController? = null,
    content: @Composable () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    val windowInsetsModifier = Modifier.windowInsetsPadding(
        WindowInsets.navigationBars.only(
            WindowInsetsSides.Horizontal
        )
    )

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            Box(
                modifier = windowInsetsModifier
            ) {
                nbNullSafe(topAppBarItem) { item ->
                    NBTopAppBarView(
                        scrollBehavior = scrollBehavior,
                        item = item
                    )
                }
            }
        },
        snackbarHost = {
            nbNullSafe(snackbarController) { controller ->
                SnackbarHost(controller.state)
            }
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier.padding(innerPadding)
            ) {
                content()
            }
        }
    )
}

