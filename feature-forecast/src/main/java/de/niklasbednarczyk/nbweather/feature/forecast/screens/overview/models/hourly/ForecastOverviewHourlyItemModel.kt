package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.hourly

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeDisplayModel
import de.niklasbednarczyk.nbweather.core.common.datetime.NBTimezoneOffsetValue
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.models.HourlyForecastModelData
import de.niklasbednarczyk.nbweather.data.onecall.types.weather.WeatherIconType
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.WindDegreesForecastValue

data class ForecastOverviewHourlyItemModel(
    val forecastTime: NBDateTimeDisplayModel,
    val weatherIcon: WeatherIconType,
    val temperature: NBUnitsValue,
    val probabilityOfPrecipitation: NBUnitsValue,
    val windDegrees: WindDegreesForecastValue
) {

    companion object {

        fun from(
            timezoneOffset: NBTimezoneOffsetValue?,
            hourlyForecast: HourlyForecastModelData
        ): ForecastOverviewHourlyItemModel? {
            return nbNullSafe(
                NBDateTimeDisplayModel.from(hourlyForecast.forecastTime, timezoneOffset),
                hourlyForecast.weather?.icon,
                hourlyForecast.temperature?.unitsValue?.toShort(),
                hourlyForecast.probabilityOfPrecipitation?.unitsValue,
                hourlyForecast.windDegrees
            ) { forecastTime, weatherIcon, temperature, probabilityOfPrecipitation, windDegrees ->
                ForecastOverviewHourlyItemModel(
                    forecastTime = forecastTime,
                    weatherIcon = weatherIcon,
                    temperature = temperature,
                    probabilityOfPrecipitation = probabilityOfPrecipitation,
                    windDegrees = windDegrees
                )
            }
        }

    }


}