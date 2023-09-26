package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafeList
import de.niklasbednarczyk.nbweather.data.onecall.models.OneCallModelData
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.daily.ForecastOverviewDailyItemModel

data class ForecastOverviewDailyModel(
    val items: List<ForecastOverviewDailyItemModel>,
    val maxTemperatureTotalValue: Double,
    val minTemperatureTotalValue: Double
) : ForecastOverviewItem {

    companion object {

        fun from(
            oneCall: OneCallModelData
        ): ForecastOverviewDailyModel? {
            return nbNullSafeList(oneCall.dailyForecasts) { dailyForecasts ->
                val timezoneOffset = oneCall.timezoneOffset

                val items = dailyForecasts.map { dailyForecast ->
                    ForecastOverviewDailyItemModel.from(
                        dailyForecast = dailyForecast,
                        timezoneOffset = timezoneOffset
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