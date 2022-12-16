package de.niklasbednarczyk.nbweather.feature.location.screens.hourly.models

import de.niklasbednarczyk.nbweather.core.common.data.NBTimeFormatType
import de.niklasbednarczyk.nbweather.core.common.data.NBUnitsType
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.data.onecall.models.HourlyForecastModelData
import de.niklasbednarczyk.nbweather.data.onecall.values.datetime.TimezoneOffsetValue
import de.niklasbednarczyk.nbweather.feature.location.cards.models.LocationCardItem

data class LocationHourlyHourModel(
    val forecastTime: Long,
    val title: NBString?,
    val cardItems: List<LocationCardItem>
) {

    companion object {
        fun from(
            hourlyForecast: HourlyForecastModelData,
            timezoneOffset: TimezoneOffsetValue?,
            timeFormat: NBTimeFormatType,
            units: NBUnitsType
        ): LocationHourlyHourModel? {

            val title = hourlyForecast.forecastTime?.getDateTimeString(timezoneOffset, timeFormat)

            val cardItems = LocationCardItem.forHourly(
                timezoneOffset = timezoneOffset,
                units = units,
                timeFormat = timeFormat,
                hourlyForecast = hourlyForecast
            )

            return nbNullSafe(hourlyForecast.forecastTime?.value) { forecastTime ->
                LocationHourlyHourModel(
                    forecastTime = forecastTime,
                    title = title,
                    cardItems = cardItems
                )
            }
        }
    }

}