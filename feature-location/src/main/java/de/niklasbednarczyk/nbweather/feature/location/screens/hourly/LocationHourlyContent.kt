package de.niklasbednarczyk.nbweather.feature.location.screens.hourly

import androidx.compose.runtime.Composable
import androidx.compose.foundation.pager.HorizontalPager
import de.niklasbednarczyk.nbweather.core.ui.pager.NBPagerInfoModel
import de.niklasbednarczyk.nbweather.feature.location.cards.views.LocationCardsView
import de.niklasbednarczyk.nbweather.feature.location.screens.hourly.models.LocationHourlyHourModel

@Composable
fun LocationHourlyContent(
    pagerInfo: NBPagerInfoModel<LocationHourlyHourModel>?
) {
    if (pagerInfo != null) {
        HorizontalPager(
            pageCount = pagerInfo.count,
            state = pagerInfo.pagerState
        ) {
            LocationCardsView(
                cardItems = pagerInfo.item.cardItems,
                navigateToAlerts = null
            )
        }
    }
}