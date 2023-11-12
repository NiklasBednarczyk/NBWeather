package de.niklasbednarczyk.nbweather.feature.forecast.screens.overview.models.daily

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeDisplayModel
import de.niklasbednarczyk.nbweather.core.common.datetime.NBTimezoneOffsetValue
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.settings.units.NBUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.models.DailyForecastModelData
import de.niklasbednarczyk.nbweather.data.onecall.types.weather.WeatherIconType

data class ForecastOverviewDailyItemModel(
    val forecastTime: NBDateTimeDisplayModel,
    val maxTemperature: NBUnitsValue,
    val minTemperature: NBUnitsValue,
    val probabilityOfPrecipitation: NBUnitsValue,
    val weatherIcon: WeatherIconType
) {

    companion object {

        fun from(
            timezoneOffset: NBTimezoneOffsetValue?,
            dailyForecast: DailyForecastModelData
        ): ForecastOverviewDailyItemModel? {
            val temperature = dailyForecast.temperature

            return nbNullSafe(
                NBDateTimeDisplayModel.from(dailyForecast.forecastTime, timezoneOffset),
                temperature?.maxDailyTemperature?.unitsValue?.toShort(),
                temperature?.minDailyTemperature?.unitsValue?.toShort(),
                dailyForecast.probabilityOfPrecipitation?.unitsValue,
                dailyForecast.weather?.icon
            ) { forecastTime, maxTemperature, minTemperature, probabilityOfPrecipitation, weatherIcon ->
                ForecastOverviewDailyItemModel(
                    forecastTime = forecastTime,
                    maxTemperature = maxTemperature,
                    minTemperature = minTemperature,
                    probabilityOfPrecipitation = probabilityOfPrecipitation,
                    weatherIcon = weatherIcon
                )
            }
        }

    }

}