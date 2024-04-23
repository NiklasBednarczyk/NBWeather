package de.niklasbednarczyk.nbweather.core.ui.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable

@OptIn(ExperimentalFoundationApi::class)
fun LazyListScope.nBStickyHeader(
    content: @Composable LazyItemScope.() -> Unit
) {
    stickyHeader(
        content = content
    )
}