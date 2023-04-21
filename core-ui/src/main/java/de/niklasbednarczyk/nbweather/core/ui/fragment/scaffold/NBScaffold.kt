package de.niklasbednarczyk.nbweather.core.ui.fragment.scaffold

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll

@Composable
internal fun NBScaffold(
    topBar: @Composable (scrollBehavior: TopAppBarScrollBehavior) -> Unit,
    bottomBar: @Composable () -> Unit,
    snackbarHostState: SnackbarHostState,
    scaffoldContent: @Composable () -> Unit
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
                topBar(scrollBehavior)
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = bottomBar,
        content = { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                scaffoldContent()
            }
        }
    )
}

