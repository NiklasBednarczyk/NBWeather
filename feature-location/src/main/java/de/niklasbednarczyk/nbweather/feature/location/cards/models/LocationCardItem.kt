package de.niklasbednarczyk.nbweather.feature.location.cards.models

import de.niklasbednarczyk.nbweather.core.common.data.NBTimeFormatType
import de.niklasbednarczyk.nbweather.core.common.data.NBUnitsType
import de.niklasbednarczyk.nbweather.core.ui.card.NBCardItem
import de.niklasbednarczyk.nbweather.data.onecall.models.*
import de.niklasbednarczyk.nbweather.data.onecall.values.datetime.TimezoneOffsetValue

sealed interface LocationCardItem : NBCardItem {

    companion object {

        fun forDaily(
            timezoneOffset: TimezoneOffsetValue?,
            units: NBUnitsType,
            timeFormat: NBTimeFormatType,
            dailyForecast: DailyForecastModelData
        ): List<LocationCardItem> {
            val items = mutableListOf<LocationCardItem?>()

            items.add(
                LocationCardOverviewItem.from(
                    timeFormat = timeFormat,
                    units = units,
                    timezoneOffset = timezoneOffset,
                    temperature = dailyForecast.temperature?.dayTemperature,
                    feelsLikeTemperature = dailyForecast.feelsLikeTemperature?.dayTemperature,
                    weather = dailyForecast.weather,
                    alerts = null,
                    minutelyForecasts = null
                )
            )

            items.add(
                LocationCardWeatherModel.from(
                    units = units,
                    pressure = dailyForecast.pressure,
                    humidity = dailyForecast.humidity,
                    dewPointTemperature = dailyForecast.dewPointTemperature,
                    cloudiness = dailyForecast.cloudiness,
                    uvIndex = dailyForecast.uvIndex,
                    visibility = null,
                    windSpeed = dailyForecast.windSpeed,
                    windGust = dailyForecast.windGust,
                    windDegrees = dailyForecast.windDegrees,
                    rain1hVolume = dailyForecast.rainVolume,
                    snow1hVolume = dailyForecast.snowVolume,
                    probabilityOfPrecipitation = dailyForecast.probabilityOfPrecipitation
                )
            )

            items.add(
                LocationCardTemperaturesModel.from(
                    units = units,
                    temperature = dailyForecast.temperature,
                    feelsLikeTemperature = dailyForecast.feelsLikeTemperature
                )
            )

            items.add(
                LocationCardSunAndMoonModel.from(
                    timeFormat = timeFormat,
                    timezoneOffset = timezoneOffset,
                    dailyForecast = dailyForecast
                )
            )

            return items.filterNotNull()
        }


        fun forHourly(
            timezoneOffset: TimezoneOffsetValue?,
            units: NBUnitsType,
            timeFormat: NBTimeFormatType,
            hourlyForecast: HourlyForecastModelData
        ): List<LocationCardItem> {
            val items = mutableListOf<LocationCardItem?>()

            items.add(
                LocationCardOverviewItem.from(
                    timeFormat = timeFormat,
                    units = units,
                    timezoneOffset = timezoneOffset,
                    temperature = hourlyForecast.temperature,
                    feelsLikeTemperature = hourlyForecast.feelsLikeTemperature,
                    weather = hourlyForecast.weather,
                    alerts = null,
                    minutelyForecasts = null
                )
            )

            items.add(
                LocationCardWeatherModel.from(
                    units = units,
                    pressure = hourlyForecast.pressure,
                    humidity = hourlyForecast.humidity,
                    dewPointTemperature = hourlyForecast.dewPointTemperature,
                    cloudiness = hourlyForecast.cloudiness,
                    uvIndex = hourlyForecast.uvIndex,
                    visibility = hourlyForecast.visibility,
                    windSpeed = hourlyForecast.windSpeed,
                    windGust = hourlyForecast.windGust,
                    windDegrees = hourlyForecast.windDegrees,
                    rain1hVolume = hourlyForecast.rain1hVolume,
                    snow1hVolume = hourlyForecast.snow1hVolume,
                    probabilityOfPrecipitation = hourlyForecast.probabilityOfPrecipitation
                )
            )

            return items.filterNotNull()
        }

        fun forToday(
            timezoneOffset: TimezoneOffsetValue?,
            units: NBUnitsType,
            timeFormat: NBTimeFormatType,
            currentWeather: CurrentWeatherModelData,
            today: DailyForecastModelData?,
            alerts: List<NationalWeatherAlertModelData>,
            minutelyForecasts: List<MinutelyForecastModelData>
        ): List<LocationCardItem> {
            val items = mutableListOf<LocationCardItem?>()

            items.add(
                LocationCardOverviewItem.from(
                    timeFormat = timeFormat,
                    units = units,
                    timezoneOffset = timezoneOffset,
                    temperature = currentWeather.currentTemperature,
                    feelsLikeTemperature = currentWeather.feelsLikeTemperature,
                    weather = currentWeather.weather,
                    alerts = alerts,
                    minutelyForecasts = minutelyForecasts
                )
            )

            items.add(
                LocationCardWeatherModel.from(
                    units = units,
                    pressure = currentWeather.pressure,
                    humidity = currentWeather.humidity,
                    dewPointTemperature = currentWeather.dewPointTemperature,
                    cloudiness = currentWeather.cloudiness,
                    uvIndex = currentWeather.uvIndex,
                    visibility = currentWeather.visibility,
                    windSpeed = currentWeather.windSpeed,
                    windGust = currentWeather.windGust,
                    windDegrees = currentWeather.windDegrees,
                    rain1hVolume = currentWeather.rain1hVolume,
                    snow1hVolume = currentWeather.snow1hVolume,
                    probabilityOfPrecipitation = today?.probabilityOfPrecipitation
                )
            )

            items.add(
                LocationCardTemperaturesModel.from(
                    units = units,
                    temperature = today?.temperature,
                    feelsLikeTemperature = today?.feelsLikeTemperature
                )
            )

            items.add(
                LocationCardSunAndMoonModel.from(
                    timeFormat = timeFormat,
                    timezoneOffset = timezoneOffset,
                    dailyForecast = today
                )
            )

            return items.filterNotNull()
        }

    }


}