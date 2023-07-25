package de.niklasbednarczyk.nbweather.feature.location.screens.daily.models

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeModel
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.models.DailyForecastModelData
import de.niklasbednarczyk.nbweather.feature.location.cards.models.LocationCardItem

data class LocationDailyDayModel(
    val forecastTime: NBDateTimeModel?,
    val cardItems: List<LocationCardItem>
) {

    companion object {
        fun from(
            dailyForecast: DailyForecastModelData,
        ): LocationDailyDayModel? {


            val cardItems = LocationCardItem.forDaily(
                dailyForecast = dailyForecast
            )

            return nbNullSafe(dailyForecast.forecastTime) { forecastTime ->
                LocationDailyDayModel(
                    forecastTime = forecastTime,
                    cardItems = cardItems
                )
            }
        }
    }

}