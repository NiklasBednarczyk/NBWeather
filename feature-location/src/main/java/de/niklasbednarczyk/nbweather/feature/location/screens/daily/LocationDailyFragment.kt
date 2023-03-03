package de.niklasbednarczyk.nbweather.feature.location.screens.daily

import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import com.google.accompanist.pager.HorizontalPager
import dagger.hilt.android.AndroidEntryPoint
import de.niklasbednarczyk.nbweather.core.ui.fragment.NBFragmentPager
import de.niklasbednarczyk.nbweather.core.ui.fragment.scaffold.topappbar.NBTopAppBarItem
import de.niklasbednarczyk.nbweather.core.ui.pager.NBPagerInfoModel
import de.niklasbednarczyk.nbweather.feature.location.cards.views.LocationCardsView
import de.niklasbednarczyk.nbweather.feature.location.screens.daily.models.LocationDailyDayModel
import de.niklasbednarczyk.nbweather.feature.location.screens.daily.models.LocationDailyViewData

@AndroidEntryPoint
class LocationDailyFragment :
    NBFragmentPager<LocationDailyDayModel, LocationDailyUiState, LocationDailyViewData>() {

    override val viewModel: LocationDailyViewModel by viewModels()

    override fun createTopAppBarItem(viewData: NBPagerInfoModel<LocationDailyDayModel>?): NBTopAppBarItem {
        return NBTopAppBarItem.Material.Small(
            title = viewData?.item?.title
        )
    }

    @Composable
    override fun ScaffoldContent(viewData: NBPagerInfoModel<LocationDailyDayModel>?) {
        if (viewData != null) {
            HorizontalPager(
                count = viewData.count,
                state = viewData.pagerState
            ) {
                LocationCardsView(
                    cardItems = viewData.item.cardItems,
                    navigateToAlerts = null
                )
            }
        }

    }


}