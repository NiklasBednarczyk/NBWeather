package de.niklasbednarczyk.nbweather.core.ui.pager

import androidx.compose.foundation.pager.PagerState

data class NBPagerInfoModel<Item>(
    val count: Int,
    val pagerState: PagerState,
    val item: Item
)