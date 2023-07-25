package de.niklasbednarczyk.nbweather.feature.location.cards.views.card

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import de.niklasbednarczyk.nbweather.core.ui.grid.NBGridRowThreeLines
import de.niklasbednarczyk.nbweather.core.ui.dimens.columnVerticalArrangementDefault
import de.niklasbednarczyk.nbweather.core.ui.windowsize.getWidthSizeClass
import de.niklasbednarczyk.nbweather.feature.location.cards.models.LocationCardWeatherModel

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
            NBGridRowThreeLines(
                items = rowItems,
                itemCount = rowItemCount
            )
        }
    }

}