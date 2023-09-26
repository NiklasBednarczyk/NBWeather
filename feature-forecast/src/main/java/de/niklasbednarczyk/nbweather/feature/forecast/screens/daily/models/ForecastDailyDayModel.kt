package de.niklasbednarczyk.nbweather.feature.forecast.screens.daily.models

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeDisplayModel
import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeValue
import de.niklasbednarczyk.nbweather.core.common.datetime.NBTimezoneOffsetValue
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafeList
import de.niklasbednarczyk.nbweather.data.onecall.models.DailyForecastModelData

data class ForecastDailyDayModel(
    val dateTime: NBDateTimeValue,
    val infoItems: List<ForecastDailyDayInfoItem>
) {

    companion object {

        fun from(
            dailyForecast: DailyForecastModelData,
            timezoneOffset: NBTimezoneOffsetValue?
        ): ForecastDailyDayModel? {
            val dateTime = dailyForecast.forecastTime ?: return null

            val infoItems = mutableListOf<ForecastDailyDayInfoItem>()

            nbNullSafe(
                NBDateTimeDisplayModel.from(dateTime, timezoneOffset),
                dailyForecast.weather?.icon,
                dailyForecast.weather?.condition,
                dailyForecast.temperature?.minDailyTemperature,
                dailyForecast.temperature?.maxDailyTemperature,
            ) { forecastTime, weatherIcon, weatherCondition, minTemperature, maxTemperature ->
                infoItems.add(
                    ForecastDailyDayInfoItem.Summary(
                        forecastTime = forecastTime,
                        weatherIcon = weatherIcon,
                        weatherCondition = weatherCondition,
                        minTemperature = minTemperature,
                        maxTemperature = maxTemperature
                    )
                )
                infoItems.add(ForecastDailyDayInfoItem.Divider)
            }

            val forecastValues = listOfNotNull(
                dailyForecast.pressure,
                dailyForecast.humidity,
                dailyForecast.dewPointTemperature,
                dailyForecast.windSpeed,
                dailyForecast.windGust,
                dailyForecast.windDegrees,
                dailyForecast.cloudiness,
                dailyForecast.uvIndex,
                dailyForecast.probabilityOfPrecipitation,
                dailyForecast.rainVolume,
                dailyForecast.snowVolume,
            )
            nbNullSafeList(forecastValues) { fV ->
                infoItems.add(
                    ForecastDailyDayInfoItem.Forecasts(
                        forecastValues = fV
                    )
                )
                infoItems.add(ForecastDailyDayInfoItem.Divider)
            }

            nbNullSafe(
                NBDateTimeDisplayModel.from(dailyForecast.sunrise, timezoneOffset),
                NBDateTimeDisplayModel.from(dailyForecast.sunset, timezoneOffset),
                NBDateTimeDisplayModel.from(dailyForecast.moonrise, timezoneOffset),
                NBDateTimeDisplayModel.from(dailyForecast.moonset, timezoneOffset),
                dailyForecast.moonPhase
            ) { sunrise, sunset, moonrise, moonset, moonPhase ->
                infoItems.add(
                    ForecastDailyDayInfoItem.SunAndMoon(
                        sunrise = sunrise,
                        sunset = sunset,
                        moonrise = moonrise,
                        moonset = moonset,
                        moonPhase = moonPhase
                    )
                )
            }

            if (infoItems.lastOrNull() is ForecastDailyDayInfoItem.Divider) {
                infoItems.removeLastOrNull()
            }

            return nbNullSafeList(infoItems) { i ->
                ForecastDailyDayModel(
                    dateTime = dateTime,
                    infoItems = i
                )
            }
        }

    }

}