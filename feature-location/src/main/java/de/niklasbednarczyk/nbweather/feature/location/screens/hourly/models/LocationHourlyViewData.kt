package de.niklasbednarczyk.nbweather.feature.location.screens.hourly.models

import de.niklasbednarczyk.nbweather.core.common.data.NBTimeFormatType
import de.niklasbednarczyk.nbweather.core.ui.pager.NBPagerViewData
import de.niklasbednarczyk.nbweather.data.onecall.models.OneCallModelData

data class LocationHourlyViewData(
    override val items: List<LocationHourlyHourModel>,
    override val initialKey: Long?
) : NBPagerViewData<LocationHourlyHourModel, Long?> {

    override fun isInitialItem(item: LocationHourlyHourModel): Boolean {
        return item.forecastTime == initialKey
    }

    companion object {

        fun from(
            oneCall: OneCallModelData,
            timeFormat: NBTimeFormatType,
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