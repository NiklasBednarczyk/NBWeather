package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models

import de.niklasbednarczyk.nbweather.core.common.datetime.NBTimezoneOffsetValue
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafeList
import de.niklasbednarczyk.nbweather.data.onecall.models.HourlyForecastModelData
import de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.hourly.ForecastOverviewHourlyItemModel

data class ForecastOverviewHourlyModel(
    private val items: List<ForecastOverviewHourlyItemModel>
) : ForecastOverviewItem {

    val itemPairs = items.zipWithNext()

    companion object {

        fun from(
            timezoneOffset: NBTimezoneOffsetValue?,
            hourlyForecasts: List<HourlyForecastModelData>
        ): ForecastOverviewHourlyModel? {
            return nbNullSafeList(hourlyForecasts) { forecasts ->
                val items = forecasts.map { hourlyForecast ->
                    ForecastOverviewHourlyItemModel.from(
                        timezoneOffset = timezoneOffset,
                        hourlyForecast = hourlyForecast
                    ) ?: return null
                }
                ForecastOverviewHourlyModel(
                    items = items
                )
            }
        }

    }

}