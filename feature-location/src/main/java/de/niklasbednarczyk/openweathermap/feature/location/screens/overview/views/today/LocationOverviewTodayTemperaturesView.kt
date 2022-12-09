package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.views.today

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import de.niklasbednarczyk.openweathermap.core.ui.grid.OwmGridRow
import de.niklasbednarczyk.openweathermap.core.ui.theme.columnVerticalArrangementDefault
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.LocationOverviewTodayTemperaturesModel

@Composable
fun LocationOverviewTodayTemperaturesView(
    temperatures: LocationOverviewTodayTemperaturesModel
) {
    Column(
        verticalArrangement = columnVerticalArrangementDefault
    ) {
        OwmGridRow(
            items = temperatures.thresholdItems
        )
        OwmGridRow(
            items = temperatures.dayItems
        )
    }
}

