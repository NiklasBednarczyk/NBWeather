package de.niklasbednarczyk.nbweather.feature.location.screens.hourly.models

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeModel
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.models.HourlyForecastModelData
import de.niklasbednarczyk.nbweather.feature.location.cards.models.LocationCardItem

data class LocationHourlyHourModel(
    val forecastTime: NBDateTimeModel?,
    val cardItems: List<LocationCardItem>
) {

    companion object {
        fun from(
            hourlyForecast: HourlyForecastModelData,
        ): LocationHourlyHourModel? {

            val cardItems = LocationCardItem.forHourly(
                hourlyForecast = hourlyForecast
            )

            return nbNullSafe(hourlyForecast.forecastTime) { forecastTime ->
                LocationHourlyHourModel(
                    forecastTime = forecastTime,
                    cardItems = cardItems
                )
            }
        }
    }

}