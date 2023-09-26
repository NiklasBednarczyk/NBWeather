package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeDisplayModel
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.models.OneCallModelData
import de.niklasbednarczyk.nbweather.data.onecall.types.weather.WeatherConditionType
import de.niklasbednarczyk.nbweather.data.onecall.types.weather.WeatherIconType
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.TemperatureForecastValue

data class ForecastOverviewSummaryModel(
    val currentTemperature: NBUnitsValue,
    val minTemperature: TemperatureForecastValue,
    val maxTemperature: TemperatureForecastValue,
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
                currentWeather.currentTemperature?.unitsValue,
                today?.temperature?.minDailyTemperature,
                today?.temperature?.maxDailyTemperature,
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