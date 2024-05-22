package de.niklasbednarczyk.nbweather.feature.forecast.screens.hourly.models.canvas

import androidx.compose.ui.graphics.Color
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R

data class ForecastHourlyCanvasGraphModel(
    private val name: NBString,
    private val symbol: NBString,
    val lineColor: Color,
    val values: List<ForecastHourlyCanvasGraphValueModel>
) {

    val label: NBString = NBString.ResString(
        R.string.format_brackets,
        name,
        symbol
    )

    val valuesZipped = values.zipWithNext()

}