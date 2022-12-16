package de.niklasbednarczyk.nbweather.feature.location.screens.daily.models

import de.niklasbednarczyk.nbweather.core.common.data.NBTimeFormatType
import de.niklasbednarczyk.nbweather.core.ui.pager.NBPagerViewData
import de.niklasbednarczyk.nbweather.data.onecall.models.OneCallModelData

data class LocationDailyViewData(
    override val items: List<LocationDailyDayModel>,
    override val initialKey: Long?
) : NBPagerViewData<LocationDailyDayModel, Long?> {

    override fun isInitialItem(item: LocationDailyDayModel): Boolean {
        return item.forecastTime == initialKey
    }

    companion object {

        fun from(
            oneCall: OneCallModelData,
            timeFormat: NBTimeFormatType,
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