package de.niklasbednarczyk.openweathermap.feature.location.screens.hourly.models

import de.niklasbednarczyk.openweathermap.core.common.data.OwmTimeFormatType
import de.niklasbednarczyk.openweathermap.core.common.data.OwmUnitsType
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.data.onecall.models.HourlyForecastModelData
import de.niklasbednarczyk.openweathermap.data.onecall.values.datetime.TimezoneOffsetValue
import de.niklasbednarczyk.openweathermap.feature.location.cards.models.LocationCardItem

data class LocationHourlyHourModel(
    val forecastTime: Long,
    val title: OwmString?,
    val cardItems: List<LocationCardItem>
) {

    companion object {
        fun from(
            hourlyForecast: HourlyForecastModelData,
            timezoneOffset: TimezoneOffsetValue?,
            timeFormat: OwmTimeFormatType,
            units: OwmUnitsType
        ): LocationHourlyHourModel? {

            val title = hourlyForecast.forecastTime?.getDateTimeString(timezoneOffset, timeFormat)

            val cardItems = LocationCardItem.forHourly(
                timezoneOffset = timezoneOffset,
                units = units,
                timeFormat = timeFormat,
                hourlyForecast = hourlyForecast
            )

            return owmNullSafe(hourlyForecast.forecastTime?.value) { forecastTime ->
                LocationHourlyHourModel(
                    forecastTime = forecastTime,
                    title = title,
                    cardItems = cardItems
                )
            }
        }
    }

}