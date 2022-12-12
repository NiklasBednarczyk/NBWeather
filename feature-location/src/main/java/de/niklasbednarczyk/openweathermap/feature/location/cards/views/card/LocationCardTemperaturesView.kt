package de.niklasbednarczyk.openweathermap.feature.location.cards.views.card

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import de.niklasbednarczyk.openweathermap.core.ui.grid.OwmGridRow
import de.niklasbednarczyk.openweathermap.core.ui.theme.columnVerticalArrangementDefault
import de.niklasbednarczyk.openweathermap.feature.location.cards.models.LocationCardTemperaturesModel

@Composable
fun LocationCardTemperaturesView(
    temperatures: LocationCardTemperaturesModel
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

