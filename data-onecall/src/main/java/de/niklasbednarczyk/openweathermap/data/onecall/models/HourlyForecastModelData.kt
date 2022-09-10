package de.niklasbednarczyk.openweathermap.data.onecall.models

import de.niklasbednarczyk.openweathermap.core.data.localremote.models.values.common.DateTimeValue
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.values.onecall.*
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.HourlyForecastEntityLocal
import de.niklasbednarczyk.openweathermap.data.onecall.models.common.WeatherModelData
import de.niklasbednarczyk.openweathermap.data.onecall.remote.models.HourlyForecastModelRemote

data class HourlyForecastModelData(
    val forecastTime: DateTimeValue,
    val temperature: TemperatureValue,
    val feelsLikeTemperature: TemperatureValue,
    val pressure: PressureValue,
    val humidity: PercentValue,
    val dewPointTemperature: TemperatureValue,
    val uvIndex: UVIndexValue,
    val cloudiness: PercentValue,
    val visibility: DistanceValue,
    val windSpeed: SpeedValue,
    val windGust: SpeedValue,
    val windDegrees: WindDegreesValue,
    val probabilityOfPrecipitation: ProbabilityValue,
    val rain1hVolume: VolumeValue,
    val snow1hVolume: VolumeValue,
    val weather: WeatherModelData?
) {

    companion object {

        internal fun remoteToLocal(
            remoteList: List<HourlyForecastModelRemote>?,
            metadataId: Long,
        ): List<HourlyForecastEntityLocal> {
            return remoteList?.map { remote ->
                HourlyForecastEntityLocal(
                    metadataId = metadataId,
                    dt = remote.dt,
                    temp = remote.temp,
                    feelsLike = remote.feelsLike,
                    pressure = remote.pressure,
                    humidity = remote.humidity,
                    dewPoint = remote.dewPoint,
                    uvi = remote.uvi,
                    clouds = remote.clouds,
                    visibility = remote.visibility,
                    windSpeed = remote.windSpeed,
                    windGust = remote.windGust,
                    windDeg = remote.windDeg,
                    pop = remote.pop,
                    rain1h = remote.rain?.oneH,
                    snow1h = remote.snow?.oneH,
                    weather = WeatherModelData.remoteToLocal(remote.weather?.firstOrNull())
                )
            } ?: emptyList()
        }

        internal fun localToData(
            localList: List<HourlyForecastEntityLocal>?
        ): List<HourlyForecastModelData> {
            return localList?.map { local ->
                HourlyForecastModelData(
                    forecastTime = DateTimeValue(local.dt),
                    temperature = TemperatureValue(local.temp),
                    feelsLikeTemperature = TemperatureValue(local.feelsLike),
                    pressure = PressureValue(local.pressure),
                    humidity = PercentValue(local.humidity),
                    dewPointTemperature = TemperatureValue(local.dewPoint),
                    uvIndex = UVIndexValue(local.uvi),
                    cloudiness = PercentValue(local.clouds),
                    visibility = DistanceValue(local.visibility),
                    windSpeed = SpeedValue(local.windSpeed),
                    windGust = SpeedValue(local.windGust),
                    windDegrees = WindDegreesValue(local.windDeg),
                    probabilityOfPrecipitation = ProbabilityValue(local.pop),
                    rain1hVolume = VolumeValue(local.rain1h),
                    snow1hVolume = VolumeValue(local.snow1h),
                    weather = WeatherModelData.localToData(local.weather)
                )
            } ?: emptyList()
        }

    }

}