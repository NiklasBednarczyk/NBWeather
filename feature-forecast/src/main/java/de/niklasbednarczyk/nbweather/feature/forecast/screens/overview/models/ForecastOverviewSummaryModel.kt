package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeDisplayModel
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.models.OneCallModelData
import de.niklasbednarczyk.nbweather.data.onecall.values.weather.WeatherConditionType
import de.niklasbednarczyk.nbweather.data.onecall.values.weather.WeatherIconType
import de.niklasbednarczyk.nbweather.data.onecall.values.winddegrees.WindDegreesValue

data class ForecastOverviewSummaryModel(
    val currentTemperature: NBUnitsValue,
    val minTemperature: NBUnitsValue,
    val maxTemperature: NBUnitsValue,
    val weatherIcon: WeatherIconType,
    val weatherCondition: WeatherConditionType,
    val windDegrees: WindDegreesValue,
    val windSpeed: NBUnitsValue,
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
                currentWeather.currentTemperature?.unitsValue,
                today?.temperature?.minDailyTemperature?.unitsValue?.toShort(),
                today?.temperature?.maxDailyTemperature?.unitsValue?.toShort(),
                currentWeather.weather?.icon,
                currentWeather.weather?.condition,
                currentWeather.windDegrees,
                currentWeather.windSpeed?.unitsValue,
                NBDateTimeDisplayModel.from(currentWeather.currentTime, timezoneOffset)
            ) { currentTemperature, minTemperature, maxTemperature, weatherIcon, weatherCondition, windDegrees, windSpeed, currentTime ->
                ForecastOverviewSummaryModel(
                    currentTemperature = currentTemperature,
                    minTemperature = minTemperature,
                    maxTemperature = maxTemperature,
                    weatherIcon = weatherIcon,
                    weatherCondition = weatherCondition,
                    windDegrees = windDegrees,
                    windSpeed = windSpeed,
                    currentTime = currentTime
                )
            }
        }

    }

}