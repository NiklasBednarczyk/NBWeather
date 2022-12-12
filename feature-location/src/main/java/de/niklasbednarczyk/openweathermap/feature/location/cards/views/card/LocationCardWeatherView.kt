package de.niklasbednarczyk.openweathermap.feature.location.cards.views.card

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import de.niklasbednarczyk.openweathermap.core.ui.grid.OwmGridRow
import de.niklasbednarczyk.openweathermap.core.ui.theme.columnVerticalArrangementDefault
import de.niklasbednarczyk.openweathermap.core.ui.windowsize.getWidthSizeClass
import de.niklasbednarczyk.openweathermap.feature.location.cards.models.LocationCardWeatherModel

@Composable
fun LocationCardWeatherView(
    weather: LocationCardWeatherModel
) {
    val rowItemCount = when (getWidthSizeClass()) {
        WindowWidthSizeClass.Compact -> 3
        WindowWidthSizeClass.Medium -> 4
        WindowWidthSizeClass.Expanded -> 5
        else -> 3
    }

    Column(
        verticalArrangement = columnVerticalArrangementDefault,
    ) {
        weather.items.chunked(rowItemCount).forEach { rowItems ->
            OwmGridRow(
                items = rowItems,
                itemCount = rowItemCount
            )
        }
    }

}