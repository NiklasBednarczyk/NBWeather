package de.niklasbednarczyk.nbweather.feature.location.screens.daily

import androidx.compose.runtime.Composable
import com.google.accompanist.pager.HorizontalPager
import de.niklasbednarczyk.nbweather.core.ui.pager.NBPagerInfoModel
import de.niklasbednarczyk.nbweather.feature.location.cards.views.LocationCardsView
import de.niklasbednarczyk.nbweather.feature.location.screens.daily.models.LocationDailyDayModel

@Composable
fun LocationDailyContent(
    pagerInfo: NBPagerInfoModel<LocationDailyDayModel>?
) {
    if (pagerInfo != null) {
        HorizontalPager(
            count = pagerInfo.count,
            state = pagerInfo.pagerState
        ) {
            LocationCardsView(
                cardItems = pagerInfo.item.cardItems,
                navigateToAlerts = null
            )
        }
    }
}