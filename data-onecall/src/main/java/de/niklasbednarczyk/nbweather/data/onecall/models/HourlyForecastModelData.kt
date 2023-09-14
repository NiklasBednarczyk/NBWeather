package de.niklasbednarczyk.nbweather.data.onecall.models

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeValue
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbMap
import de.niklasbednarczyk.nbweather.data.onecall.local.models.HourlyForecastEntityLocal
import de.niklasbednarczyk.nbweather.data.onecall.models.common.WeatherModelData
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.HourlyForecastModelRemote
import de.niklasbednarczyk.nbweather.data.onecall.values.units.DistanceValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.PercentValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.PrecipitationValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.PressureValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.ProbabilityValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.TemperatureValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.UVIndexValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.WindSpeedValue
import de.niklasbednarczyk.nbweather.data.onecall.values.winddegrees.WindDegreesValue

data class HourlyForecastModelData(
    val forecastTime: NBDateTimeValue?,
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

    internal companion object {

        fun remoteToLocal(
            remoteList: List<HourlyForecastModelRemote>?,
            metadataId: Long,
        ): List<HourlyForecastEntityLocal> {
            return remoteList.nbMap { remote ->
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
            }
        }

        fun localToData(
            localList: List<HourlyForecastEntityLocal>?
        ): List<HourlyForecastModelData> {
            return localList.nbMap { local ->
                HourlyForecastModelData(
                    forecastTime = NBDateTimeValue.from(local.dt),
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
            }
        }

    }

}