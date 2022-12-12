package de.niklasbednarczyk.openweathermap.feature.location.screens.daily.models

import de.niklasbednarczyk.openweathermap.core.common.data.OwmTimeFormatType
import de.niklasbednarczyk.openweathermap.core.ui.pager.OwmPagerViewData
import de.niklasbednarczyk.openweathermap.data.onecall.models.OneCallModelData

data class LocationDailyViewData(
    override val items: List<LocationDailyDayModel>,
    override val initialKey: Long?
) : OwmPagerViewData<LocationDailyDayModel, Long?> {

    override fun isInitialItem(item: LocationDailyDayModel): Boolean {
        return item.forecastTime == initialKey
    }

    companion object {

        fun from(
            oneCall: OneCallModelData,
            timeFormat: OwmTimeFormatType,
            forecastTime: Long?
        ): LocationDailyViewData {
            val units = oneCall.metadata.units
            val timezoneOffset = oneCall.metadata.timezoneOffset
            val dailyForecasts = oneCall.dailyForecasts

            val items = dailyForecasts.mapNotNull { dailyForecast ->
                LocationDailyDayModel.from(
                    dailyForecast,
                    timezoneOffset,
                    timeFormat,
                    units
                )
            }

            return LocationDailyViewData(
                initialKey = forecastTime,
                items = items
            )
        }

    }

}