package de.niklasbednarczyk.nbweather.feature.location.cards.views.card

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import de.niklasbednarczyk.nbweather.core.ui.grid.NBGridRow
import de.niklasbednarczyk.nbweather.core.ui.theme.dimens.columnVerticalArrangementDefault
import de.niklasbednarczyk.nbweather.feature.location.cards.models.LocationCardTemperaturesModel

@Composable
fun LocationCardTemperaturesView(
    temperatures: LocationCardTemperaturesModel
) {
    Column(
        verticalArrangement = columnVerticalArrangementDefault
    ) {
        NBGridRow(
            items = temperatures.thresholdItems
        )
        NBGridRow(
            items = temperatures.dayItems
        )
    }
}

