package de.niklasbednarczyk.nbweather.feature.forecast.screens.hourly.models.canvas

import androidx.compose.ui.text.TextLayoutResult
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.ForecastValue

data class ForecastHourlyCanvasGraphValueModel(
    val factor: Float,
    val displayValue: TextLayoutResult
) {

    companion object {

        fun ForecastValue.Units.calcFactor(
            minValue: Double,
            maxValue: Double
        ): Float? {
            val spanMinMax = maxValue - minValue
            return if (spanMinMax < 0) {
                null
            } else if (spanMinMax == 0.0) {
                0.5f
            } else {
                val spanValue = maxValue - unitsValue.value.toDouble()
                (spanValue / spanMinMax).toFloat()
            }
        }

    }

}