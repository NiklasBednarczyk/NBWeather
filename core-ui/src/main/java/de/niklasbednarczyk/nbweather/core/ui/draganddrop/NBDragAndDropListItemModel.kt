package de.niklasbednarczyk.nbweather.core.ui.draganddrop

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

data class NBDragAndDropListItemModel(
    val headlineContent: @Composable () -> Unit,
    val modifier: Modifier = Modifier,
    val trailingContent: @Composable (() -> Unit)? = null
)