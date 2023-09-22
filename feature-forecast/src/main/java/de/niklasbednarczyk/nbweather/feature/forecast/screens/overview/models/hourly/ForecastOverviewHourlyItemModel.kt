package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.hourly

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeDisplayModel
import de.niklasbednarczyk.nbweather.core.common.datetime.NBTimezoneOffsetValue
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.models.HourlyForecastModelData
import de.niklasbednarczyk.nbweather.data.onecall.values.weather.WeatherIconType

data class ForecastOverviewHourlyItemModel(
    val forecastTime: NBDateTimeDisplayModel,
    val weatherIcon: WeatherIconType,
    val temperature: NBUnitsValue,
    val probabilityOfPrecipitation: NBUnitsValue
) {

    companion object {

        fun from(
            hourlyForecast: HourlyForecastModelData,
            timezoneOffset: NBTimezoneOffsetValue?
        ): ForecastOverviewHourlyItemModel? {
            return nbNullSafe(
                NBDateTimeDisplayModel.from(hourlyForecast.forecastTime, timezoneOffset),
                hourlyForecast.weather?.icon,
                hourlyForecast.temperature?.getShort(),
                hourlyForecast.probabilityOfPrecipitation
            ) { forecastTime, weatherIcon, temperature, probabilityOfPrecipitation ->
                ForecastOverviewHourlyItemModel(
                    forecastTime = forecastTime,
                    weatherIcon = weatherIcon,
                    temperature = temperature,
                    probabilityOfPrecipitation = probabilityOfPrecipitation
                )
            }
        }

    }


}