package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models

import de.niklasbednarczyk.nbweather.core.common.datetime.NBTimezoneOffsetValue
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafeList
import de.niklasbednarczyk.nbweather.data.onecall.models.DailyForecastModelData
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.daily.ForecastOverviewDailyItemModel

data class ForecastOverviewDailyModel(
    val items: List<ForecastOverviewDailyItemModel>,
    val maxTemperatureTotalValue: Double,
    val minTemperatureTotalValue: Double
) : ForecastOverviewItem {

    fun calcFactor(
        temperatureCurrentValue: Double
    ): Float {
        val spanTotal = maxTemperatureTotalValue - minTemperatureTotalValue
        val spanCurrent = maxTemperatureTotalValue - temperatureCurrentValue
        val factor = spanCurrent / spanTotal
        return factor.toFloat()
    }

    companion object {

        fun from(
            timezoneOffset: NBTimezoneOffsetValue?,
            dailyForecasts: List<DailyForecastModelData>
        ): ForecastOverviewDailyModel? {
            return nbNullSafeList(dailyForecasts) { forecasts ->
                val items = forecasts.map { dailyForecast ->
                    ForecastOverviewDailyItemModel.from(
                        timezoneOffset = timezoneOffset,
                        dailyForecast = dailyForecast,
                    ) ?: return null
                }

                val maxTemperatureTotalValue = items.maxOfOrNull { item ->
                    item.maxTemperature.value.toDouble()
                }

                val minTemperatureTotalValue = items.minOfOrNull { item ->
                    item.minTemperature.value.toDouble()
                }

                nbNullSafe(
                    maxTemperatureTotalValue,
                    minTemperatureTotalValue
                ) { max, min ->
                    if (max <= min) return null

                    ForecastOverviewDailyModel(
                        items = items,
                        maxTemperatureTotalValue = max,
                        minTemperatureTotalValue = min
                    )
                }
            }
        }

    }

}