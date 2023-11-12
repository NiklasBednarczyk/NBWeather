package de.niklasbednarczyk.nbweather.feature.forecast.screens.hourly.models.canvas

import androidx.compose.ui.graphics.Color
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.feature.forecast.models.ForecastUnitsLimitsItem
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.ForecastValue

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

    companion object {

        fun List<ForecastValue.Units>.getLimitValues(
            limits: ForecastUnitsLimitsItem
        ): Pair<Double, Double>? {
            val minValue = getMinValue(limits)
            val maxValue = getMaxValue(limits)
            return nbNullSafe(minValue, maxValue) { min, max ->
                Pair(min, max)
            }
        }

        private fun List<ForecastValue.Units>.getMinValue(
            limits: ForecastUnitsLimitsItem
        ): Double? {
            val limitMinValue = limits.min?.value
            val actualMinValue = minOfOrNull { value -> value.unitsValue.value.toDouble() }
            return listOfNotNull(limitMinValue, actualMinValue).minOrNull()
        }

        private fun List<ForecastValue.Units>.getMaxValue(
            limits: ForecastUnitsLimitsItem
        ): Double? {
            val limitMaxValue = limits.max?.value
            val actualMaxValue = maxOfOrNull { value -> value.unitsValue.value.toDouble() }
            return listOfNotNull(limitMaxValue, actualMaxValue).maxOrNull()
        }

    }

}