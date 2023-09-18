package de.niklasbednarczyk.nbweather.core.ui.pager

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import de.niklasbednarczyk.nbweather.core.ui.dimens.alphaContentDisabled
import de.niklasbednarczyk.nbweather.core.ui.dimens.elevationLevel2

@Composable
fun <Item, Key> NBPager(
    viewData: NBPagerViewData<Item, Key>,
    content: @Composable (Item) -> Unit
) {
    val pagerState = rememberPagerState(
        initialPage = viewData.getInitialPage(),
        pageCount = { viewData.items.size }
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            PagerContent(
                page = page,
                pagerState = pagerState
            ) {
                val item = viewData.items.getOrNull(page)
                if (item != null) {
                    content(item)
                }
            }
        }
        if (pagerState.pageCount > 1) {
            PagerIndicators(
                pagerState = pagerState
            )
        }
    }
}

@Composable
private fun PagerContent(
    page: Int,
    pagerState: PagerState,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer {
                val pageOffset = pagerState.calculateCurrentOffsetForPage(page)

                alpha = lerp(
                    start = 0.5f,
                    stop = 1f,
                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                )
            }
    ) {
        content()
    }
}

@Composable
private fun PagerIndicators(
    pagerState: PagerState,
    rowColor: Color = MaterialTheme.colorScheme.surfaceColorAtElevation(elevationLevel2),
    indicatorActiveColor: Color = LocalContentColor.current.copy(alpha = MaterialTheme.colorScheme.onSurface.alpha),
    indicatorInactiveColor: Color = indicatorActiveColor.copy(alphaContentDisabled),
    paddingVertical: Dp = 16.dp,
    indicatorFullSize: Dp = 8.dp,
    indicatorMiniSize: Dp = indicatorFullSize / 2,
    indicatorSpacing: Dp = indicatorFullSize,
    maxIndicators: Int = 5
) {
    val indicatorScrollState = rememberLazyListState()

    val currentPage = pagerState.currentPage
    val firstPage = 0
    val lastPage = pagerState.pageCount - 1

    val layoutInfo = remember { derivedStateOf { indicatorScrollState.layoutInfo } }
    val visibleItemsSize = layoutInfo.value.visibleItemsInfo.size
    val firstVisibleItemIndex = layoutInfo.value.visibleItemsInfo.firstOrNull()?.index
    val lastVisibleItemIndex = layoutInfo.value.visibleItemsInfo.lastOrNull()?.index

    LaunchedEffect(currentPage) {
        if (firstVisibleItemIndex == null || lastVisibleItemIndex == null) return@LaunchedEffect

        if (currentPage <= firstVisibleItemIndex && currentPage != firstPage) {
            indicatorScrollState.animateScrollToItem(currentPage - 1)
        } else if (currentPage >= lastVisibleItemIndex && currentPage != lastPage) {
            indicatorScrollState.animateScrollToItem(currentPage - visibleItemsSize + 2)
        }
    }

    val widthIndicatorsFullWidth = (indicatorSpacing * 2 + indicatorFullSize) * (maxIndicators - 2)
    val widthIndicatorsMiniWidth = (indicatorSpacing * 2 + indicatorMiniSize) * 2

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(rowColor),
    ) {
        LazyRow(
            modifier = Modifier
                .width(widthIndicatorsFullWidth + widthIndicatorsMiniWidth)
                .padding(vertical = paddingVertical)
                .align(Alignment.Center),
            state = indicatorScrollState,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            userScrollEnabled = false
        ) {
            items(pagerState.pageCount) { page ->
                val color =
                    if (pagerState.currentPage == page) indicatorActiveColor else indicatorInactiveColor
                val size by animateDpAsState(
                    targetValue = when {
                        page == firstPage -> indicatorFullSize
                        page == lastPage -> indicatorFullSize
                        firstVisibleItemIndex != null && lastVisibleItemIndex != null && page in firstVisibleItemIndex + 1..<lastVisibleItemIndex -> indicatorFullSize
                        else -> indicatorMiniSize
                    },
                    label = "pagerIndicatorSize"
                )

                Box(
                    modifier = Modifier
                        .padding(horizontal = indicatorSpacing)
                        .clip(CircleShape)
                        .background(color)
                        .size(size)

                )
            }
        }
    }
}
