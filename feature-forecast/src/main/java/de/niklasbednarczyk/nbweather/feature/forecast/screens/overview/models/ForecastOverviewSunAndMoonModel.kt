package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models

import de.niklasbednarczyk.nbweather.core.common.coordinates.NBCoordinatesModel
import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeDisplayModel
import de.niklasbednarczyk.nbweather.core.common.datetime.NBTimezoneOffsetValue
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.models.CurrentWeatherModelData
import de.niklasbednarczyk.nbweather.data.onecall.models.DailyForecastModelData
import de.niklasbednarczyk.nbweather.feature.forecast.models.sunandmoon.SunAndMoonItem

data class ForecastOverviewSunAndMoonModel(
    val currentTime: NBDateTimeDisplayModel,
    val items: List<SunAndMoonItem>
) : ForecastOverviewItem {

    companion object {

        fun from(
            coordinates: NBCoordinatesModel,
            timezoneOffset: NBTimezoneOffsetValue?,
            currentWeather: CurrentWeatherModelData?,
            today: DailyForecastModelData?
        ): ForecastOverviewSunAndMoonModel? {
            val sunAndMoonItems = SunAndMoonItem.from(
                coordinates = coordinates,
                timezoneOffset = timezoneOffset,
                dailyForecast = today
            )
            return nbNullSafe(
                NBDateTimeDisplayModel.from(currentWeather?.currentTime, timezoneOffset),
                sunAndMoonItems
            ) { currentTime, items ->
                ForecastOverviewSunAndMoonModel(
                    currentTime = currentTime,
                    items = items
                )
            }
        }

    }

}