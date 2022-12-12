package de.niklasbednarczyk.openweathermap.feature.location.cards.models

import de.niklasbednarczyk.openweathermap.core.common.data.OwmTimeFormatType
import de.niklasbednarczyk.openweathermap.core.common.data.OwmUnitsType
import de.niklasbednarczyk.openweathermap.core.ui.card.OwmCardItem
import de.niklasbednarczyk.openweathermap.data.onecall.models.*
import de.niklasbednarczyk.openweathermap.data.onecall.values.datetime.TimezoneOffsetValue

sealed interface LocationCardItem : OwmCardItem {

    companion object {

        fun forHourly(
            timezoneOffset: TimezoneOffsetValue?,
            units: OwmUnitsType,
            timeFormat: OwmTimeFormatType,
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
            units: OwmUnitsType,
            timeFormat: OwmTimeFormatType,
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