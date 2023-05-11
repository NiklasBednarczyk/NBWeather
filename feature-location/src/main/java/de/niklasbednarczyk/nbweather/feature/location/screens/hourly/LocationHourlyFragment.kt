package de.niklasbednarczyk.nbweather.feature.location.screens.hourly

import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import de.niklasbednarczyk.nbweather.core.ui.fragment.NBFragmentPager
import de.niklasbednarczyk.nbweather.core.ui.fragment.scaffold.topappbar.NBTopAppBarItem
import de.niklasbednarczyk.nbweather.core.ui.pager.NBPagerInfoModel
import de.niklasbednarczyk.nbweather.feature.location.screens.hourly.models.LocationHourlyHourModel
import de.niklasbednarczyk.nbweather.feature.location.screens.hourly.models.LocationHourlyViewData

@AndroidEntryPoint
class LocationHourlyFragment :
    NBFragmentPager<LocationHourlyHourModel, LocationHourlyUiState, LocationHourlyViewData>() {

    override val viewModel: LocationHourlyViewModel by viewModels()

    override fun createTopAppBarItem(viewData: NBPagerInfoModel<LocationHourlyHourModel>?): NBTopAppBarItem {
        return NBTopAppBarItem.Material.Small(
            title = viewData?.item?.title
        )
    }

    @Composable
    override fun ScaffoldContent(viewData: NBPagerInfoModel<LocationHourlyHourModel>?) {
        LocationHourlyContent(
            pagerInfo = viewData
        )
    }


}