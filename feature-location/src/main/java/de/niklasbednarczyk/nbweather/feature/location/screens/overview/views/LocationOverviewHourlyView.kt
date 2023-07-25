package de.niklasbednarczyk.nbweather.feature.location.screens.overview.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.niklasbednarczyk.nbweather.core.common.datetime.displayNameFull
import de.niklasbednarczyk.nbweather.core.ui.dimens.listItemPaddingValuesVerticalOneLine
import de.niklasbednarczyk.nbweather.core.ui.grid.NBGridRowOneLine
import de.niklasbednarczyk.nbweather.core.ui.stickyheader.NBStickyHeaderListView
import de.niklasbednarczyk.nbweather.core.ui.windowsize.getWidthSizeClass
import de.niklasbednarczyk.nbweather.feature.location.screens.overview.models.hourly.LocationOverviewHourlyModel
import java.time.DayOfWeek

@Composable
fun LocationOverviewHourlyView(
    hourlyMap: Map<DayOfWeek?, List<LocationOverviewHourlyModel>>,
    navigateToHourly: (Long) -> Unit
) {
    val widthSizeClass = getWidthSizeClass()

    NBStickyHeaderListView(
        stickyHeaderMap = hourlyMap,
        keyToString = { key -> key.displayNameFull },
        item = { hourly ->
            val items = when (widthSizeClass) {
                WindowWidthSizeClass.Compact -> hourly.itemsCompact
                WindowWidthSizeClass.Medium -> hourly.itemsMedium
                WindowWidthSizeClass.Expanded -> hourly.itemsExpanded
                else -> hourly.itemsCompact
            }
            NBGridRowOneLine(
                modifier = Modifier
                    .clickable {
                        hourly.forecastTime?.let { forecastTime ->
                            navigateToHourly(forecastTime.value)
                        }
                    }
                    .padding(listItemPaddingValuesVerticalOneLine),
                items = items
            )
        }
    )

}