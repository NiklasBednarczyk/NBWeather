package de.niklasbednarczyk.nbweather.core.ui.pager

import androidx.compose.runtime.Composable
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState

@Composable
fun <Item, Key> NBPagerView(
    viewData: NBPagerViewData<Item, Key>,
    content: @Composable (count: Int, pagerState: PagerState, item: Item) -> Unit
) {
    val pagerState = rememberPagerState(viewData.initialPage)

    val currentPage = pagerState.currentPage
    val currentItem = viewData.items.getOrNull(currentPage)

    if (currentItem != null) {
        content(
            count = viewData.items.size,
            pagerState = pagerState,
            item = currentItem
        )
    }


}