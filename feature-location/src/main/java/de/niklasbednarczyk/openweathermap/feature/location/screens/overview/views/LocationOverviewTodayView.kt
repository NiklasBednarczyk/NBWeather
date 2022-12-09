package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.views

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import de.niklasbednarczyk.openweathermap.core.ui.card.OwmCard
import de.niklasbednarczyk.openweathermap.core.ui.list.OwmListItem
import de.niklasbednarczyk.openweathermap.core.ui.theme.columnVerticalArrangementDefault
import de.niklasbednarczyk.openweathermap.core.ui.theme.listContentPaddingValues
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.*
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.views.today.*

@Composable
fun LocationOverviewTodayView(
    todayItems: List<OwmListItem<LocationOverviewTodayItem>>,
    navigateToAlerts: () -> Unit,
) {
    LazyColumn(
        contentPadding = listContentPaddingValues,
        verticalArrangement = columnVerticalArrangementDefault
    ) {
        items(todayItems) { listItem ->
            OwmCard(item = listItem) { todayItem ->
                when (todayItem) {
                    is LocationOverviewTodayCurrentWeatherModel -> {
                        LocationOverviewTodayCurrentWeatherView(
                            currentWeather = todayItem
                        )
                    }
                    is LocationOverviewTodayOverviewModel -> {
                        LocationOverviewTodayOverviewView(
                            overview = todayItem,
                            navigateToAlerts = navigateToAlerts,
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
}