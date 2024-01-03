package de.niklasbednarczyk.nbweather.core.ui.draganddrop

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListItemInfo
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ListItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.zIndex
import de.niklasbednarczyk.nbweather.core.ui.dimens.columnVerticalArrangementBig
import de.niklasbednarczyk.nbweather.core.ui.dimens.listContentPaddingValuesVertical
import de.niklasbednarczyk.nbweather.core.ui.dimens.listItemElevationDragged
import de.niklasbednarczyk.nbweather.core.ui.dimens.listItemElevationEnabled
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconView
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

@Composable
fun <T> NBDragAndDropView(
    items: List<T>,
    updateItems: (items: List<T>) -> Unit,
    getKey: (item: T) -> Any,
    headlineContent: @Composable (item: T) -> Unit
) {
    var itemsCached by remember { mutableStateOf(items) }
    var dragAndDropItems by remember { mutableStateOf(items) }
    if (itemsCached != items) {
        itemsCached = items
        dragAndDropItems = items
    }

    val listState = rememberLazyListState()
    val dragDropState = rememberDragDropState(
        lazyListState = listState,
        onMove = { fromIndex, toIndex ->
            dragAndDropItems = dragAndDropItems.toMutableList().apply {
                add(toIndex, removeAt(fromIndex))
            }
        },
        onDragStopped = {
            updateItems(dragAndDropItems)
        }
    )

    LazyColumn(
        modifier = Modifier
            .dragContainer(dragDropState)
            .fillMaxHeight(),
        state = listState,
        contentPadding = listContentPaddingValuesVertical,
        verticalArrangement = columnVerticalArrangementBig
    ) {
        itemsIndexed(dragAndDropItems, key = { _, item -> getKey(item) }) { index, item ->
            DraggableItem(dragDropState, index) { isDragging ->
                val tonalElevation by animateDpAsState(
                    targetValue = if (isDragging) {
                        listItemElevationDragged
                    } else {
                        listItemElevationEnabled
                    },
                    label = "dragAndDropTonalElevation"
                )
                ListItem(
                    leadingContent = {
                        NBIconView(
                            icon = NBIcons.DragAndDrop
                        )
                    },
                    headlineContent = {
                        headlineContent(item)
                    },
                    tonalElevation = tonalElevation
                )
            }
        }
    }
}

@Composable
private fun rememberDragDropState(
    lazyListState: LazyListState,
    onMove: (Int, Int) -> Unit,
    onDragStopped: () -> Unit,
): DragDropState {
    val scope = rememberCoroutineScope()
    val state = remember(lazyListState) {
        DragDropState(
            state = lazyListState,
            scope = scope,
            onMove = onMove,
            onDragStopped = onDragStopped
        )
    }
    LaunchedEffect(state) {
        while (true) {
            val diff = state.scrollChannel.receive()
            lazyListState.scrollBy(diff)
        }
    }
    return state
}

private class DragDropState(
    private val state: LazyListState,
    private val scope: CoroutineScope,
    private val onMove: (Int, Int) -> Unit,
    private val onDragStopped: () -> Unit
) {
    var draggingItemIndex by mutableStateOf<Int?>(null)
        private set

    val scrollChannel = Channel<Float>()

    private var draggingItemDraggedDelta by mutableFloatStateOf(0f)
    private var draggingItemInitialOffset by mutableIntStateOf(0)
    val draggingItemOffset: Float
        get() = draggingItemLayoutInfo?.let { item ->
            draggingItemInitialOffset + draggingItemDraggedDelta - item.offset
        } ?: 0f

    private val draggingItemLayoutInfo: LazyListItemInfo?
        get() = state.layoutInfo.visibleItemsInfo
            .firstOrNull { it.index == draggingItemIndex }

    var previousIndexOfDraggedItem by mutableStateOf<Int?>(null)
        private set
    var previousItemOffset = Animatable(0f)
        private set

    fun onDragStart(offset: Offset) {
        state.layoutInfo.visibleItemsInfo
            .firstOrNull { item ->
                offset.y.toInt() in item.offset..(item.offset + item.size)
            }?.also {
                draggingItemIndex = it.index
                draggingItemInitialOffset = it.offset
            }
    }

    fun onDragInterrupted() {
        if (draggingItemIndex != null) {
            previousIndexOfDraggedItem = draggingItemIndex
            val startOffset = draggingItemOffset
            scope.launch {
                previousItemOffset.snapTo(startOffset)
                previousItemOffset.animateTo(
                    0f,
                    spring(
                        stiffness = Spring.StiffnessMediumLow,
                        visibilityThreshold = 1f
                    )
                )
                previousIndexOfDraggedItem = null
            }
        }
        draggingItemDraggedDelta = 0f
        draggingItemIndex = null
        draggingItemInitialOffset = 0
        onDragStopped()
    }

    fun onDrag(offset: Offset) {
        draggingItemDraggedDelta += offset.y

        val draggingItem = draggingItemLayoutInfo ?: return
        val startOffset = draggingItem.offset + draggingItemOffset
        val endOffset = startOffset + draggingItem.size
        val middleOffset = startOffset + (endOffset - startOffset) / 2f

        val targetItem = state.layoutInfo.visibleItemsInfo.find { item ->
            middleOffset.toInt() in item.offset..item.offsetEnd &&
                    draggingItem.index != item.index
        }
        if (targetItem != null) {
            val scrollToIndex = if (targetItem.index == state.firstVisibleItemIndex) {
                draggingItem.index
            } else if (draggingItem.index == state.firstVisibleItemIndex) {
                targetItem.index
            } else {
                null
            }
            if (scrollToIndex != null) {
                scope.launch {
                    // this is needed to neutralize automatic keeping the first item first.
                    state.scrollToItem(scrollToIndex, state.firstVisibleItemScrollOffset)
                    onMove.invoke(draggingItem.index, targetItem.index)
                }
            } else {
                onMove.invoke(draggingItem.index, targetItem.index)
            }
            draggingItemIndex = targetItem.index
        } else {
            val overscroll = when {
                draggingItemDraggedDelta > 0 ->
                    (endOffset - state.layoutInfo.viewportEndOffset).coerceAtLeast(0f)

                draggingItemDraggedDelta < 0 ->
                    (startOffset - state.layoutInfo.viewportStartOffset).coerceAtMost(0f)

                else -> 0f
            }
            if (overscroll != 0f) {
                scrollChannel.trySend(overscroll)
            }
        }
    }

    private val LazyListItemInfo.offsetEnd: Int
        get() = this.offset + this.size
}

private fun Modifier.dragContainer(dragDropState: DragDropState): Modifier {
    return pointerInput(dragDropState) {
        detectDragGesturesAfterLongPress(
            onDrag = { change, offset ->
                change.consume()
                dragDropState.onDrag(offset = offset)
            },
            onDragStart = { offset -> dragDropState.onDragStart(offset) },
            onDragEnd = { dragDropState.onDragInterrupted() },
            onDragCancel = { dragDropState.onDragInterrupted() }
        )
    }
}

@Composable
private fun LazyItemScope.DraggableItem(
    dragDropState: DragDropState,
    index: Int,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.(isDragging: Boolean) -> Unit
) {
    val isDragging = index == dragDropState.draggingItemIndex
    val draggingModifier = if (isDragging) {
        Modifier
            .zIndex(1f)
            .graphicsLayer {
                translationY = dragDropState.draggingItemOffset
            }
    } else if (index == dragDropState.previousIndexOfDraggedItem) {
        Modifier
            .zIndex(1f)
            .graphicsLayer {
                translationY = dragDropState.previousItemOffset.value
            }
    } else {
        Modifier.animateItemPlacement()
    }
    Column(modifier = modifier.then(draggingModifier)) {
        content(isDragging)
    }
}