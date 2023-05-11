package de.niklasbednarczyk.nbweather.feature.location.screens.daily

import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import de.niklasbednarczyk.nbweather.core.ui.fragment.NBFragmentPager
import de.niklasbednarczyk.nbweather.core.ui.fragment.scaffold.topappbar.NBTopAppBarItem
import de.niklasbednarczyk.nbweather.core.ui.pager.NBPagerInfoModel
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
        LocationDailyContent(
            pagerInfo = viewData
        )
    }

}