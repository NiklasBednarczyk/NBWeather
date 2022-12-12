package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.grid.OwmGridRow
import de.niklasbednarczyk.openweathermap.core.ui.stickyheader.OwmStickyHeaderListView
import de.niklasbednarczyk.openweathermap.core.ui.theme.listItemPaddingValuesVerticalOneLine
import de.niklasbednarczyk.openweathermap.core.ui.windowsize.getWidthSizeClass
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.hourly.LocationOverviewHourlyModel

@Composable
fun LocationOverviewHourlyView(
    hourlyMap: Map<OwmString?, List<LocationOverviewHourlyModel>>,
    navigateToHourly: (Long) -> Unit
) {
    val widthSizeClass = getWidthSizeClass()

    OwmStickyHeaderListView(hourlyMap) { hourly ->
        val items = when (widthSizeClass) {
            WindowWidthSizeClass.Compact -> hourly.itemsCompact
            WindowWidthSizeClass.Medium -> hourly.itemsMedium
            WindowWidthSizeClass.Expanded -> hourly.itemsExpanded
            else -> hourly.itemsCompact
        }
        OwmGridRow(
            modifier = Modifier
                .clickable {
                    navigateToHourly(hourly.forecastTime)
                }
                .padding(listItemPaddingValuesVerticalOneLine),
            items = items
        )
    }
}