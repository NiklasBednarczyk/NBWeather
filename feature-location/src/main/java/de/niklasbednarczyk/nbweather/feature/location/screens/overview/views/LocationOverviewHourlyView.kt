package de.niklasbednarczyk.nbweather.feature.location.screens.overview.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.grid.NBGridRow
import de.niklasbednarczyk.nbweather.core.ui.stickyheader.NBStickyHeaderListView
import de.niklasbednarczyk.nbweather.core.ui.theme.dimens.listItemPaddingValuesVerticalOneLine
import de.niklasbednarczyk.nbweather.core.ui.windowsize.getWidthSizeClass
import de.niklasbednarczyk.nbweather.feature.location.screens.overview.models.hourly.LocationOverviewHourlyModel

@Composable
fun LocationOverviewHourlyView(
    hourlyMap: Map<NBString?, List<LocationOverviewHourlyModel>>,
    navigateToHourly: (Long) -> Unit
) {
    val widthSizeClass = getWidthSizeClass()

    NBStickyHeaderListView(hourlyMap) { hourly ->
        val items = when (widthSizeClass) {
            WindowWidthSizeClass.Compact -> hourly.itemsCompact
            WindowWidthSizeClass.Medium -> hourly.itemsMedium
            WindowWidthSizeClass.Expanded -> hourly.itemsExpanded
            else -> hourly.itemsCompact
        }
        NBGridRow(
            modifier = Modifier
                .clickable {
                    navigateToHourly(hourly.forecastTime)
                }
                .padding(listItemPaddingValuesVerticalOneLine),
            items = items
        )
    }
}