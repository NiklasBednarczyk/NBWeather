package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.niklasbednarczyk.openweathermap.core.ui.card.OwmCard
import de.niklasbednarczyk.openweathermap.core.ui.grid.OwmGridRow
import de.niklasbednarczyk.openweathermap.core.ui.list.OwmListItem
import de.niklasbednarczyk.openweathermap.core.ui.theme.columnVerticalArrangementDefault
import de.niklasbednarczyk.openweathermap.core.ui.theme.listContentPaddingValues
import de.niklasbednarczyk.openweathermap.core.ui.theme.listItemPaddingValuesVerticalOneLine
import de.niklasbednarczyk.openweathermap.core.ui.windowsize.getWidthSizeClass
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.hourly.LocationOverviewHourlyModel

@Composable
fun LocationOverviewHourlyView(
    hourlyItems: List<OwmListItem<LocationOverviewHourlyModel>>
) {
    LazyColumn(
        contentPadding = listContentPaddingValues,
        verticalArrangement = columnVerticalArrangementDefault
    ) {
        items(hourlyItems) { listItem ->
            OwmCard(item = listItem) { hourlyModel ->
                hourlyModel.hours.forEach { hour ->
                    val items = when (getWidthSizeClass()) {
                        WindowWidthSizeClass.Compact -> hour.itemsCompact
                        WindowWidthSizeClass.Medium -> hour.itemsMedium
                        WindowWidthSizeClass.Expanded -> hour.itemsExpanded
                        else -> hour.itemsCompact
                    }

                    OwmGridRow(
                        modifier = Modifier
                            .clickable {
                                //TODO (#9) Navigate to hours screen
                            }
                            .padding(listItemPaddingValuesVerticalOneLine),
                        items = items
                    )
                }
            }
        }
    }


}