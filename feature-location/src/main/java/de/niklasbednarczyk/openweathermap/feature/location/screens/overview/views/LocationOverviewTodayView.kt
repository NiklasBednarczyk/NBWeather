package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.views

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import de.niklasbednarczyk.openweathermap.core.ui.strings.asString
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.LocationOverviewTodayItem

@Composable
fun LocationOverviewTodayView(
    todayItems: List<LocationOverviewTodayItem>
) {
    LazyColumn {
        items(todayItems) { todayItem ->
            when (todayItem) {
                is LocationOverviewTodayItem.Alert -> {

                }
                is LocationOverviewTodayItem.CurrentWeather -> {

                }
                is LocationOverviewTodayItem.DayTemperatures -> {

                }
                is LocationOverviewTodayItem.Header -> {
                    Text(todayItem.test.asString())
                }
                is LocationOverviewTodayItem.SunAndMoon -> {

                }
            }
        }
    }


}