package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.views.today

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.core.ui.card.OwmCard
import de.niklasbednarczyk.openweathermap.core.ui.grid.OwmGridRow
import de.niklasbednarczyk.openweathermap.core.ui.theme.columnVerticalArrangementDefault
import de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.LocationOverviewTodayTemperaturesModel

@Composable
fun LocationOverviewTodayTemperaturesView(
    temperatures: LocationOverviewTodayTemperaturesModel
) {
    OwmCard(OwmString.Resource(R.string.screen_location_overview_today_card_temperatures_title)) {
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

}

