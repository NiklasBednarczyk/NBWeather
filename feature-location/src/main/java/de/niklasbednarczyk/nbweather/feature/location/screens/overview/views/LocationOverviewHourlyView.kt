package de.niklasbednarczyk.nbweather.feature.location.screens.overview.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.niklasbednarczyk.nbweather.core.common.datetime.displayNameFull
import de.niklasbednarczyk.nbweather.core.ui.dimens.listContentPaddingValuesVertical
import de.niklasbednarczyk.nbweather.core.ui.dimens.listItemPaddingValuesVerticalOneLine
import de.niklasbednarczyk.nbweather.core.ui.grid.NBGridRowOneLine
import de.niklasbednarczyk.nbweather.core.ui.stickyheader.NBStickyHeaderModel
import de.niklasbednarczyk.nbweather.core.ui.stickyheader.NBStickyHeaderView
import de.niklasbednarczyk.nbweather.core.ui.windowsize.getWidthSizeClass
import de.niklasbednarczyk.nbweather.feature.location.screens.overview.models.hourly.LocationOverviewHourlyModel
import java.time.DayOfWeek

@Composable
fun LocationOverviewHourlyView(
    hourlyMap: Map<DayOfWeek?, List<LocationOverviewHourlyModel>>,
    navigateToHourly: (Long) -> Unit
) {
    val widthSizeClass = getWidthSizeClass()

    LazyColumn(
        contentPadding = listContentPaddingValuesVertical
    ) {
        hourlyMap.forEach { (key, hourlyItems) ->
            if (key != null) {
                stickyHeader {
                    NBStickyHeaderView(
                        model = NBStickyHeaderModel(
                            text = key.displayNameFull
                        )
                    )
                }
                items(hourlyItems) { hourlyItem ->
                    val items = when (widthSizeClass) {
                        WindowWidthSizeClass.Compact -> hourlyItem.itemsCompact
                        WindowWidthSizeClass.Medium -> hourlyItem.itemsMedium
                        WindowWidthSizeClass.Expanded -> hourlyItem.itemsExpanded
                        else -> hourlyItem.itemsCompact
                    }
                    NBGridRowOneLine(
                        modifier = Modifier
                            .clickable {
                                hourlyItem.forecastTime?.let { forecastTime ->
                                    navigateToHourly(forecastTime.value)
                                }
                            }
                            .padding(listItemPaddingValuesVerticalOneLine),
                        items = items
                    )
                }
            }
        }
    }

}