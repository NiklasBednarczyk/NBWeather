package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.views.today

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.card.OwmCard
import de.niklasbednarczyk.openweathermap.core.ui.grid.OwmGridRow
import de.niklasbednarczyk.openweathermap.core.ui.theme.columnVerticalArrangementDefault
import de.niklasbednarczyk.openweathermap.core.ui.windowsize.getWidthSizeClass
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.LocationOverviewTodayCurrentWeatherModel

@Composable
fun LocationOverviewTodayCurrentWeatherView(
    currentWeather: LocationOverviewTodayCurrentWeatherModel
) {
    val rowItemCount = when (getWidthSizeClass()) {
        WindowWidthSizeClass.Compact -> 3
        WindowWidthSizeClass.Medium -> 4
        WindowWidthSizeClass.Expanded -> 5
        else -> 3
    }

    OwmCard(OwmString.Resource(R.string.screen_location_overview_today_card_current_weather_title)) {
        Column(
            verticalArrangement = columnVerticalArrangementDefault,
        ) {
            currentWeather.items.chunked(rowItemCount).forEach { rowItems ->
                OwmGridRow(
                    items = rowItems,
                    itemCount = rowItemCount
                )
            }
        }


    }


}