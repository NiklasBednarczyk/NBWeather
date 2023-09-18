package de.niklasbednarczyk.nbweather.core.ui.pager

import androidx.compose.foundation.pager.PagerState
import kotlin.math.absoluteValue

internal fun <Item, Key> NBPagerViewData<Item, Key>.getInitialPage(): Int {
    val index = items.indexOfFirst { item ->
        getItemKey(item) == initialKey
    }
    return if (index >= 0) index else 0
}

internal fun PagerState.calculateCurrentOffsetForPage(page: Int): Float {
    return (currentPage - page) + currentPageOffsetFraction.absoluteValue
}
