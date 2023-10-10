package de.niklasbednarczyk.nbweather.feature.forecast.screens.hourly.models.canvas

import androidx.compose.ui.graphics.Color
import de.niklasbednarczyk.nbweather.core.common.string.NBString

data class ForecastHourlyCanvasGraphModel(
    val name: NBString,
    val symbol: NBString,
    val lineColor: Color,
    val values: List<ForecastHourlyCanvasGraphValueModel>
) {

    val valuesZipped = values.zipWithNext()

}