package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.views

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.*
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.views.today.LocationOverviewTodayAlertView
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.views.today.LocationOverviewTodayHeaderView

@Composable
fun LocationOverviewTodayView(
    todayItems: List<LocationOverviewTodayItem>,
    navigateToAlerts: () -> Unit
) {
    LazyColumn {
        items(todayItems) { todayItem ->
            when (todayItem) {
                is LocationOverviewTodayAlertModel -> {
                    LocationOverviewTodayAlertView(
                        alert = todayItem,
                        navigateToAlerts = navigateToAlerts
                    )
                }
                is LocationOverviewTodayCurrentWeatherModel -> {}
                is LocationOverviewTodayDayTemperaturesModel -> {}
                is LocationOverviewTodayHeaderModel -> {
                    LocationOverviewTodayHeaderView(
                        header = todayItem
                    )
                }
                is LocationOverviewTodaySunAndMoonModel -> {}
            }
        }
    }


}