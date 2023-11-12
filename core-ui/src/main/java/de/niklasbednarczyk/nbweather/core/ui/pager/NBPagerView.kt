package de.niklasbednarczyk.nbweather.core.ui.pager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import de.niklasbednarczyk.nbweather.core.ui.dimens.alphaContentDisabled
import de.niklasbednarczyk.nbweather.core.ui.dimens.elevationLevel2
import kotlin.math.absoluteValue
import kotlin.math.sign

@Composable
fun <K, T> NBPagerView(
    viewData: NBPagerViewData<K, T>,
    content: @Composable (T) -> Unit
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
    activeColor: Color = LocalContentColor.current.copy(alpha = MaterialTheme.colorScheme.onSurface.alpha),
    inactiveColor: Color = activeColor.copy(alphaContentDisabled),
    paddingVertical: Dp = 16.dp,
    indicatorWidth: Dp = 8.dp,
    indicatorHeight: Dp = indicatorWidth,
    spacing: Dp = indicatorWidth,
    indicatorShape: Shape = CircleShape,
) {
    with(LocalDensity.current) {
        val indicatorWidthPx = remember { indicatorWidth.roundToPx() }
        val spacingPx = remember { spacing.roundToPx() }

        val pageCount = pagerState.pageCount
        val currentPage = pagerState.currentPage
        val offset = pagerState.currentPageOffsetFraction

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(rowColor)
                .padding(
                    vertical = paddingVertical
                ),
            contentAlignment = Alignment.Center
        ) {
            Box(
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(spacing),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    val indicatorModifier = Modifier
                        .size(width = indicatorWidth, height = indicatorHeight)
                        .background(color = inactiveColor, shape = indicatorShape)

                    repeat(pageCount) {
                        Box(indicatorModifier)
                    }
                }

                Box(
                    Modifier
                        .offset {
                            val next = currentPage + offset.sign.toInt()
                            val scrollPosition =
                                ((next - currentPage) * offset.absoluteValue + currentPage).coerceIn(
                                    0f,
                                    (pageCount - 1)
                                        .coerceAtLeast(0)
                                        .toFloat()
                                )

                            IntOffset(
                                x = ((spacingPx + indicatorWidthPx) * scrollPosition).toInt(),
                                y = 0
                            )
                        }
                        .size(
                            width = indicatorWidth,
                            height = indicatorHeight
                        )
                        .background(
                            color = activeColor,
                            shape = indicatorShape,
                        )
                )
            }
        }
    }

}