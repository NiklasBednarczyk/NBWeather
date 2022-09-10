package de.niklasbednarczyk.openweathermap.data.onecall.models

import de.niklasbednarczyk.openweathermap.core.data.localremote.models.values.common.DateTimeValue
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.values.onecall.*
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.DailyForecastEntityLocal
import de.niklasbednarczyk.openweathermap.data.onecall.models.common.WeatherModelData
import de.niklasbednarczyk.openweathermap.data.onecall.models.daily.DailyFeelsLikeTemperatureModelData
import de.niklasbednarczyk.openweathermap.data.onecall.models.daily.DailyTemperatureModelData
import de.niklasbednarczyk.openweathermap.data.onecall.remote.models.DailyForecastModelRemote

data class DailyForecastModelData(
    val forecastTime: DateTimeValue,
    val sunrise: DateTimeValue,
    val sunset: DateTimeValue,
    val moonrise: DateTimeValue,
    val moonset: DateTimeValue,
    val moonPhase: MoonPhaseValue,
    val temperature: DailyTemperatureModelData?,
    val feelsLikeTemperature: DailyFeelsLikeTemperatureModelData?,
    val pressure: PressureValue,
    val humidity: PercentValue,
    val dewPointTemperature: TemperatureValue,
    val windSpeed: SpeedValue,
    val windGust: SpeedValue,
    val windDegrees: WindDegreesValue,
    val cloudiness: PercentValue,
    val uvIndex: UVIndexValue,
    val probabilityOfPrecipitation: ProbabilityValue,
    val rainVolume: VolumeValue,
    val snowVolume: VolumeValue,
    val weather: WeatherModelData?
) {

    companion object {

        internal fun remoteToLocal(
            remoteList: List<DailyForecastModelRemote>?,
            metadataId: Long,
        ): List<DailyForecastEntityLocal> {
            return remoteList?.map { remote ->
                DailyForecastEntityLocal(
                    metadataId = metadataId,
                    dt = remote.dt,
                    sunrise = remote.sunrise,
                    sunset = remote.sunset,
                    moonrise = remote.moonrise,
                    moonset = remote.moonset,
                    moonPhase = remote.moonPhase,
                    temp = DailyTemperatureModelData.remoteToLocal(remote.temp),
                    feelsLike = DailyFeelsLikeTemperatureModelData.remoteToLocal(remote.feelsLike),
                    pressure = remote.pressure,
                    humidity = remote.humidity,
                    dewPoint = remote.dewPoint,
                    windSpeed = remote.windSpeed,
                    windGust = remote.windGust,
                    windDeg = remote.windDeg,
                    clouds = remote.clouds,
                    uvi = remote.uvi,
                    pop = remote.pop,
                    rain = remote.rain,
                    snow = remote.snow,
                    weather = WeatherModelData.remoteToLocal(remote.weather?.firstOrNull())
                )
            } ?: emptyList()
        }

        internal fun localToData(
            localList: List<DailyForecastEntityLocal>?
        ): List<DailyForecastModelData> {
            return localList?.map { local ->
                DailyForecastModelData(
                    forecastTime = DateTimeValue(local.dt),
                    sunrise = DateTimeValue(local.sunrise),
                    sunset = DateTimeValue(local.sunset),
                    moonrise = DateTimeValue(local.moonrise),
                    moonset = DateTimeValue(local.moonset),
                    moonPhase = MoonPhaseValue(local.moonPhase),
                    temperature = DailyTemperatureModelData.localToData(local.temp),
                    feelsLikeTemperature = DailyFeelsLikeTemperatureModelData.localToData(local.feelsLike),
                    pressure = PressureValue(local.pressure),
                    humidity = PercentValue(local.humidity),
                    dewPointTemperature = TemperatureValue(local.dewPoint),
                    windSpeed = SpeedValue(local.windSpeed),
                    windGust = SpeedValue(local.windGust),
                    windDegrees = WindDegreesValue(local.windDeg),
                    cloudiness = PercentValue(local.clouds),
                    uvIndex = UVIndexValue(local.uvi),
                    probabilityOfPrecipitation = ProbabilityValue(local.pop),
                    rainVolume = VolumeValue(local.rain),
                    snowVolume = VolumeValue(local.snow),
                    weather = WeatherModelData.localToData(local.weather)
                )
            } ?: emptyList()
        }

    }

}