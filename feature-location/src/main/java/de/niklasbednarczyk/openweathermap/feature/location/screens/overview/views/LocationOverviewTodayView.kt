package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import de.niklasbednarczyk.openweathermap.core.ui.theme.columnVerticalArrangement
import de.niklasbednarczyk.openweathermap.core.ui.theme.listContentPaddingValues
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.*
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.views.today.*

@Composable
fun LocationOverviewTodayView(
    todayItems: List<LocationOverviewTodayItem>,
    navigateToAlerts: () -> Unit,
    shouldAnimateTodayHeader: Boolean,
    setShouldAnimateTodayHeader: (Boolean) -> Unit
) {
    LazyColumn(
        contentPadding = listContentPaddingValues,
        verticalArrangement = Arrangement.spacedBy(columnVerticalArrangement)
    ) {
        items(todayItems) { todayItem ->
            when (todayItem) {
                is LocationOverviewTodayAlertModel -> {
                    LocationOverviewTodayAlertView(
                        alert = todayItem,
                        navigateToAlerts = navigateToAlerts
                    )
                }
                is LocationOverviewTodayCurrentWeatherModel -> {
                    LocationOverviewTodayCurrentWeatherView(
                        currentWeather = todayItem
                    )
                }
                is LocationOverviewTodayHeaderModel -> {
                    LocationOverviewTodayHeaderView(
                        header = todayItem,
                        shouldAnimateTodayHeader = shouldAnimateTodayHeader,
                        setShouldAnimateTodayHeader = setShouldAnimateTodayHeader
                    )
                }
                is LocationOverviewTodaySunAndMoonModel -> {
                    LocationOverviewTodaySunAndMoonView(
                        sunAndMoon = todayItem
                    )
                }
                is LocationOverviewTodayTemperaturesModel -> {
                    LocationOverviewTodayTemperaturesView(
                        temperatures = todayItem
                    )
                }
            }
        }
    }


}