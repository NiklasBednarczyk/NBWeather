package de.niklasbednarczyk.openweathermap.feature.location.screens.daily.models

import de.niklasbednarczyk.openweathermap.core.common.data.OwmTimeFormatType
import de.niklasbednarczyk.openweathermap.core.common.data.OwmUnitsType
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.data.onecall.models.DailyForecastModelData
import de.niklasbednarczyk.openweathermap.data.onecall.values.datetime.TimezoneOffsetValue
import de.niklasbednarczyk.openweathermap.feature.location.cards.models.LocationCardItem

data class LocationDailyDayModel(
    val forecastTime: Long,
    val title: OwmString?,
    val cardItems: List<LocationCardItem>
) {

    companion object {
        fun from(
            dailyForecast: DailyForecastModelData,
            timezoneOffset: TimezoneOffsetValue?,
            timeFormat: OwmTimeFormatType,
            units: OwmUnitsType
        ): LocationDailyDayModel? {

            val title = dailyForecast.forecastTime?.getDateWeekdayWithDateString(timezoneOffset)

            val cardItems = LocationCardItem.forDaily(
                timezoneOffset = timezoneOffset,
                units = units,
                timeFormat = timeFormat,
                dailyForecast = dailyForecast
            )

            return owmNullSafe(dailyForecast.forecastTime?.value) { forecastTime ->
                LocationDailyDayModel(
                    forecastTime = forecastTime,
                    title = title,
                    cardItems = cardItems
                )
            }
        }
    }

}