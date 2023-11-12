package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.views

import androidx.compose.runtime.Composable
import de.niklasbednarczyk.nbweather.core.ui.grid.NBGridView
import de.niklasbednarczyk.nbweather.feature.forecast.extensions.toGridItems
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.ForecastOverviewCurrentWeatherModel

@Composable
fun ForecastOverviewCurrentWeatherView(
    currentWeather: ForecastOverviewCurrentWeatherModel
) {
    val gridItems = currentWeather.items.toGridItems()

    NBGridView(
        items = gridItems
    )
}
