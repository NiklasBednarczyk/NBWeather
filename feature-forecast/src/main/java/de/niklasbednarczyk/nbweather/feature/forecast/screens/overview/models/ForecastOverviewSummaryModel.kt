package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeDisplayModel
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.models.OneCallModelData
import de.niklasbednarczyk.nbweather.data.onecall.values.weather.WeatherConditionType
import de.niklasbednarczyk.nbweather.data.onecall.values.weather.WeatherIconType

data class ForecastOverviewSummaryModel(
    val currentTemperature: NBUnitsValue,
    val minTemperature: NBUnitsValue,
    val maxTemperature: NBUnitsValue,
    val weatherIcon: WeatherIconType,
    val weatherCondition: WeatherConditionType,
    val currentTime: NBDateTimeDisplayModel
) : ForecastOverviewItem {

    companion object {

        fun from(
            oneCall: OneCallModelData
        ): ForecastOverviewSummaryModel? {
            val timezoneOffset = oneCall.timezoneOffset
            val currentWeather = oneCall.currentWeather
            val today = oneCall.today

            return nbNullSafe(
                currentWeather.currentTemperature?.getLong(),
                today?.temperature?.minDailyTemperature?.getShort(),
                today?.temperature?.maxDailyTemperature?.getShort(),
                currentWeather.weather?.icon,
                currentWeather.weather?.condition,
                NBDateTimeDisplayModel.from(currentWeather.currentTime, timezoneOffset)
            ) { currentTemperature, minTemperature, maxTemperature, weatherIcon, weatherCondition, currentTime ->
                ForecastOverviewSummaryModel(
                    currentTemperature = currentTemperature,
                    minTemperature = minTemperature,
                    maxTemperature = maxTemperature,
                    weatherIcon = weatherIcon,
                    weatherCondition = weatherCondition,
                    currentTime = currentTime
                )
            }
        }

    }

}