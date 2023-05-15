package de.niklasbednarczyk.nbweather.core.ui.fragment

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.niklasbednarczyk.nbweather.core.ui.pager.NBPagerInfoModel
import de.niklasbednarczyk.nbweather.core.ui.pager.NBPagerUiState
import de.niklasbednarczyk.nbweather.core.ui.pager.NBPagerViewData

abstract class NBFragmentPager<Item : Any, UiState : NBPagerUiState<ViewData>, ViewData : NBPagerViewData<Item, *>> :
    NBFragment<UiState, NBPagerInfoModel<Item>?>() {

    @Composable
    override fun createViewData(): NBPagerInfoModel<Item>? {
        val uiState = viewModel.uiState.collectAsStateWithLifecycle()
        val viewData = uiState.value.viewDataResource?.dataOrNull ?: return null

        val pagerState = rememberPagerState(viewData.initialPage)
        val currentPage = pagerState.currentPage
        val currentItem = viewData.items.getOrNull(currentPage)

        return if (currentItem != null) {
            NBPagerInfoModel(
                count = viewData.items.size,
                pagerState = pagerState,
                item = currentItem
            )
        } else {
            null
        }

    }
}