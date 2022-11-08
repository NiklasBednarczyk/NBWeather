package de.niklasbednarczyk.openweathermap.data.onecall.models

import de.niklasbednarczyk.openweathermap.data.onecall.local.models.HourlyForecastEntityLocal
import de.niklasbednarczyk.openweathermap.data.onecall.models.common.WeatherModelData
import de.niklasbednarczyk.openweathermap.data.onecall.remote.models.HourlyForecastModelRemote
import de.niklasbednarczyk.openweathermap.data.onecall.values.datetime.DateTimeValue
import de.niklasbednarczyk.openweathermap.data.onecall.values.units.*
import de.niklasbednarczyk.openweathermap.data.onecall.values.winddegrees.WindDegreesValue

data class HourlyForecastModelData(
    val forecastTime: DateTimeValue?,
    val temperature: TemperatureValue?,
    val feelsLikeTemperature: TemperatureValue?,
    val pressure: PressureValue?,
    val humidity: PercentValue?,
    val dewPointTemperature: TemperatureValue?,
    val uvIndex: UVIndexValue?,
    val cloudiness: PercentValue?,
    val visibility: DistanceValue?,
    val windSpeed: WindSpeedValue?,
    val windGust: WindSpeedValue?,
    val windDegrees: WindDegreesValue?,
    val probabilityOfPrecipitation: ProbabilityValue?,
    val rain1hVolume: PrecipitationValue?,
    val snow1hVolume: PrecipitationValue?,
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
                    forecastTime = DateTimeValue.from(local.dt),
                    temperature = TemperatureValue.from(local.temp),
                    feelsLikeTemperature = TemperatureValue.from(local.feelsLike),
                    pressure = PressureValue.from(local.pressure),
                    humidity = PercentValue.from(local.humidity),
                    dewPointTemperature = TemperatureValue.from(local.dewPoint),
                    uvIndex = UVIndexValue.from(local.uvi),
                    cloudiness = PercentValue.from(local.clouds),
                    visibility = DistanceValue.from(local.visibility),
                    windSpeed = WindSpeedValue.from(local.windSpeed),
                    windGust = WindSpeedValue.from(local.windGust),
                    windDegrees = WindDegreesValue.from(local.windDeg),
                    probabilityOfPrecipitation = ProbabilityValue.from(local.pop),
                    rain1hVolume = PrecipitationValue.from(local.rain1h),
                    snow1hVolume = PrecipitationValue.from(local.snow1h),
                    weather = WeatherModelData.localToData(local.weather)
                )
            } ?: emptyList()
        }

    }

}