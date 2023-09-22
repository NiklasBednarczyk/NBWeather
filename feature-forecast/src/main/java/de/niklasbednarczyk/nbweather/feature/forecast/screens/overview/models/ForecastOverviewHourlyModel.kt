package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafeList
import de.niklasbednarczyk.nbweather.data.onecall.models.OneCallModelData
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.hourly.ForecastOverviewHourlyItemModel

data class ForecastOverviewHourlyModel(
    val itemPairs: List<Pair<ForecastOverviewHourlyItemModel, ForecastOverviewHourlyItemModel>>
) : ForecastOverviewItem {

    companion object {

        fun from(
            oneCall: OneCallModelData
        ): ForecastOverviewHourlyModel? {
            return nbNullSafeList(oneCall.hourlyForecasts) { hourlyForecasts ->
                val timezoneOffset = oneCall.timezoneOffset
                val itemPairs = hourlyForecasts
                    .map { hourlyForecast ->
                        ForecastOverviewHourlyItemModel.from(
                            hourlyForecast = hourlyForecast,
                            timezoneOffset = timezoneOffset
                        ) ?: return null
                    }.zipWithNext()
                ForecastOverviewHourlyModel(
                    itemPairs = itemPairs
                )
            }
        }

    }

}