package de.niklasbednarczyk.nbweather.core.ui.screen.utils

import android.content.Context
import android.view.View
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy

fun nbSetContent(
    context: Context,
    viewCompositionStrategy: ViewCompositionStrategy = ViewCompositionStrategy.Default,
    content: @Composable () -> Unit
): View {
    return ComposeView(context).apply {
        consumeWindowInsets = false
        setViewCompositionStrategy(viewCompositionStrategy)
        setContent(content)
    }
}