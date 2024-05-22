package de.niklasbednarczyk.nbweather.feature.forecast.screens.hourly.models.canvas

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.ForecastValue
import de.niklasbednarczyk.nbweather.feature.forecast.models.limits.ForecastUnitsLimitsItem

data class ForecastHourlyCanvasGraphLimitValuesModel(
    val minValue: Double,
    val maxValue: Double
) {

    companion object {

        fun from(
            forecasts: List<ForecastValue.Units>,
            limits: ForecastUnitsLimitsItem
        ): ForecastHourlyCanvasGraphLimitValuesModel? {
            val minValue = forecasts.getMinValue(limits)
            val maxValue = forecasts.getMaxValue(limits)
            return nbNullSafe(minValue, maxValue) { min, max ->
                ForecastHourlyCanvasGraphLimitValuesModel(
                    minValue = min,
                    maxValue = max
                )
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