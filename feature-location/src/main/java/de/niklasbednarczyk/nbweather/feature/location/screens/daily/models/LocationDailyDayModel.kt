package de.niklasbednarczyk.nbweather.feature.location.screens.daily.models

import de.niklasbednarczyk.nbweather.core.common.data.NBTimeFormatType
import de.niklasbednarczyk.nbweather.core.common.data.NBUnitsType
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.data.onecall.models.DailyForecastModelData
import de.niklasbednarczyk.nbweather.data.onecall.values.datetime.TimezoneOffsetValue
import de.niklasbednarczyk.nbweather.feature.location.cards.models.LocationCardItem

data class LocationDailyDayModel(
    val forecastTime: Long,
    val title: NBString?,
    val cardItems: List<LocationCardItem>
) {

    companion object {
        fun from(
            dailyForecast: DailyForecastModelData,
            timezoneOffset: TimezoneOffsetValue?,
            timeFormat: NBTimeFormatType,
            units: NBUnitsType
        ): LocationDailyDayModel? {

            val title = dailyForecast.forecastTime?.getDateWeekdayWithDateString(timezoneOffset)

            val cardItems = LocationCardItem.forDaily(
                timezoneOffset = timezoneOffset,
                units = units,
                timeFormat = timeFormat,
                dailyForecast = dailyForecast
            )

            return nbNullSafe(dailyForecast.forecastTime?.value) { forecastTime ->
                LocationDailyDayModel(
                    forecastTime = forecastTime,
                    title = title,
                    cardItems = cardItems
                )
            }
        }
    }

}