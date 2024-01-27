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

        fun localToData(
            local: List<HourlyForecastEntityLocal>?
        ): List<HourlyForecastModelData> {
            return local.nbMap { l ->
                HourlyForecastModelData(
                    forecastTime = NBDateTimeValue.from(l.dt),
                    temperature = TemperatureForecastValue.from(l.temp),
                    feelsLikeTemperature = FeelsLikeForecastValue.from(l.feelsLike),
                    pressure = PressureForecastValue.from(l.pressure),
                    humidity = HumidityForecastValue.from(l.humidity),
                    dewPointTemperature = DewPointForecastValue.from(l.dewPoint),
                    uvIndex = UVIndexForecastValue.from(l.uvi),
                    cloudiness = CloudinessForecastValue.from(l.clouds),
                    visibility = VisibilityForecastValue.from(l.visibility),
                    windSpeed = WindSpeedForecastValue.from(l.windSpeed),
                    windGust = WindGustForecastValue.from(l.windGust),
                    windDegrees = WindDegreesForecastValue.from(l.windDeg),
                    probabilityOfPrecipitation = ProbabilityOfPrecipitationForecastValue.from(l.pop),
                    rain1hVolume = RainForecastValue.from(l.rain1h),
                    snow1hVolume = SnowForecastValue.from(l.snow1h),
                    weather = WeatherModelData.localToData(l.weather)
                )
            }
        }

        fun remoteToLocal(
            remote: List<HourlyForecastModelRemote>?,
            metadataId: Long,
        ): List<HourlyForecastEntityLocal> {
            return remote.nbMap { r ->
                HourlyForecastEntityLocal(
                    metadataId = metadataId,
                    dt = r.dt,
                    temp = r.temp,
                    feelsLike = r.feelsLike,
                    pressure = r.pressure,
                    humidity = r.humidity,
                    dewPoint = r.dewPoint,
                    uvi = r.uvi,
                    clouds = r.clouds,
                    visibility = r.visibility,
                    windSpeed = r.windSpeed,
                    windGust = r.windGust,
                    windDeg = r.windDeg,
                    pop = r.pop,
                    rain1h = r.rain?.oneH,
                    snow1h = r.snow?.oneH,
                    weather = WeatherModelData.remoteToLocal(r.weather?.firstOrNull())
                )
            }
        }

    }

}