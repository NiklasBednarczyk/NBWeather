package de.niklasbednarczyk.nbweather.feature.location.cards.models

import de.niklasbednarczyk.nbweather.core.ui.card.NBCardItem
import de.niklasbednarczyk.nbweather.data.onecall.models.CurrentWeatherModelData
import de.niklasbednarczyk.nbweather.data.onecall.models.DailyForecastModelData
import de.niklasbednarczyk.nbweather.data.onecall.models.HourlyForecastModelData
import de.niklasbednarczyk.nbweather.data.onecall.models.MinutelyForecastModelData
import de.niklasbednarczyk.nbweather.data.onecall.models.NationalWeatherAlertModelData

sealed interface LocationCardItem : NBCardItem {

    companion object {

        fun forDaily(
            dailyForecast: DailyForecastModelData
        ): List<LocationCardItem> {
            val items = mutableListOf<LocationCardItem?>()

            items.add(
                LocationCardOverviewItem.from(
                    temperature = dailyForecast.temperature?.dayTemperature,
                    feelsLikeTemperature = dailyForecast.feelsLikeTemperature?.dayTemperature,
                    weather = dailyForecast.weather,
                    alerts = null,
                    minutelyForecasts = null
                )
            )

            items.add(
                LocationCardWeatherModel.from(
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
                    temperature = dailyForecast.temperature
                )
            )

            items.add(
                LocationCardSunAndMoonModel.from(
                    dailyForecast = dailyForecast
                )
            )

            return items.filterNotNull()
        }


        fun forHourly(
            hourlyForecast: HourlyForecastModelData
        ): List<LocationCardItem> {
            val items = mutableListOf<LocationCardItem?>()

            items.add(
                LocationCardOverviewItem.from(
                    temperature = hourlyForecast.temperature,
                    feelsLikeTemperature = hourlyForecast.feelsLikeTemperature,
                    weather = hourlyForecast.weather,
                    alerts = null,
                    minutelyForecasts = null
                )
            )

            items.add(
                LocationCardWeatherModel.from(
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
            currentWeather: CurrentWeatherModelData,
            today: DailyForecastModelData?,
            alerts: List<NationalWeatherAlertModelData>,
            minutelyForecasts: List<MinutelyForecastModelData>
        ): List<LocationCardItem> {
            val items = mutableListOf<LocationCardItem?>()

            items.add(
                LocationCardOverviewItem.from(
                    temperature = currentWeather.currentTemperature,
                    feelsLikeTemperature = currentWeather.feelsLikeTemperature,
                    weather = currentWeather.weather,
                    alerts = alerts,
                    minutelyForecasts = minutelyForecasts
                )
            )

            items.add(
                LocationCardWeatherModel.from(
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
                    temperature = today?.temperature
                )
            )

            items.add(
                LocationCardSunAndMoonModel.from(
                    dailyForecast = today
                )
            )

            return items.filterNotNull()
        }

    }


}