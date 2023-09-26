package de.niklasbednarczyk.nbweather.data.onecall.models

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeValue
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbMap
import de.niklasbednarczyk.nbweather.data.onecall.local.models.HourlyForecastEntityLocal
import de.niklasbednarczyk.nbweather.data.onecall.models.common.WeatherModelData
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.HourlyForecastModelRemote
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.CloudinessForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.DewPointForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.FeelsLikeForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.HumidityForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.PressureForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.ProbabilityOfPrecipitationForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.RainForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.SnowForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.TemperatureForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.UVIndexForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.VisibilityForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.WindGustForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.WindSpeedForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.WindDegreesForecastValue

data class HourlyForecastModelData(
    val forecastTime: NBDateTimeValue?,
    val temperature: TemperatureForecastValue?,
    val feelsLikeTemperature: FeelsLikeForecastValue?,
    val pressure: PressureForecastValue?,
    val humidity: HumidityForecastValue?,
    val dewPointTemperature: DewPointForecastValue?,
    val uvIndex: UVIndexForecastValue?,
    val cloudiness: CloudinessForecastValue?,
    val visibility: VisibilityForecastValue?,
    val windSpeed: WindSpeedForecastValue?,
    val windGust: WindGustForecastValue?,
    val windDegrees: WindDegreesForecastValue?,
    val probabilityOfPrecipitation: ProbabilityOfPrecipitationForecastValue?,
    val rain1hVolume: RainForecastValue?,
    val snow1hVolume: SnowForecastValue?,
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
                    temperature = TemperatureForecastValue.from(local.temp),
                    feelsLikeTemperature = FeelsLikeForecastValue.from(local.feelsLike),
                    pressure = PressureForecastValue.from(local.pressure),
                    humidity = HumidityForecastValue.from(local.humidity),
                    dewPointTemperature = DewPointForecastValue.from(local.dewPoint),
                    uvIndex = UVIndexForecastValue.from(local.uvi),
                    cloudiness = CloudinessForecastValue.from(local.clouds),
                    visibility = VisibilityForecastValue.from(local.visibility),
                    windSpeed = WindSpeedForecastValue.from(local.windSpeed),
                    windGust = WindGustForecastValue.from(local.windGust),
                    windDegrees = WindDegreesForecastValue.from(local.windDeg),
                    probabilityOfPrecipitation = ProbabilityOfPrecipitationForecastValue.from(local.pop),
                    rain1hVolume = RainForecastValue.from(local.rain1h),
                    snow1hVolume = SnowForecastValue.from(local.snow1h),
                    weather = WeatherModelData.localToData(local.weather)
                )
            }
        }

    }

}