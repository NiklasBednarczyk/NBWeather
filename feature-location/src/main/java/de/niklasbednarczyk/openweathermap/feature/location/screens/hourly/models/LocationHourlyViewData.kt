package de.niklasbednarczyk.openweathermap.feature.location.screens.hourly.models

import de.niklasbednarczyk.openweathermap.core.common.data.OwmTimeFormatType
import de.niklasbednarczyk.openweathermap.core.ui.pager.OwmPagerViewData
import de.niklasbednarczyk.openweathermap.data.onecall.models.OneCallModelData

data class LocationHourlyViewData(
    override val items: List<LocationHourlyHourModel>,
    override val initialKey: Long?
) : OwmPagerViewData<LocationHourlyHourModel, Long?> {

    override fun isInitialItem(item: LocationHourlyHourModel): Boolean {
        return item.forecastTime == initialKey
    }

    companion object {

        fun from(
            oneCall: OneCallModelData,
            timeFormat: OwmTimeFormatType,
            forecastTime: Long?
        ): LocationHourlyViewData {
            val units = oneCall.metadata.units
            val timezoneOffset = oneCall.metadata.timezoneOffset
            val hourlyForecasts = oneCall.hourlyForecasts

            val items = hourlyForecasts.mapNotNull { hourlyForecast ->
                LocationHourlyHourModel.from(
                    hourlyForecast,
                    timezoneOffset,
                    timeFormat,
                    units
                )
            }

            return LocationHourlyViewData(
                initialKey = forecastTime,
                items = items
            )
        }

    }

}