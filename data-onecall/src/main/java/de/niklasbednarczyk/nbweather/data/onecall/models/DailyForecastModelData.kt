package de.niklasbednarczyk.nbweather.data.onecall.models

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeValue
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbMap
import de.niklasbednarczyk.nbweather.data.onecall.local.models.DailyForecastEntityLocal
import de.niklasbednarczyk.nbweather.data.onecall.models.common.WeatherModelData
import de.niklasbednarczyk.nbweather.data.onecall.models.daily.DailyFeelsLikeTemperatureModelData
import de.niklasbednarczyk.nbweather.data.onecall.models.daily.DailyTemperatureModelData
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.DailyForecastModelRemote
import de.niklasbednarczyk.nbweather.data.onecall.types.moon.MoonPhaseType
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.CloudinessForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.DewPointForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.HumidityForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.PressureForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.ProbabilityOfPrecipitationForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.RainForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.SnowForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.UVIndexForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.WindDegreesForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.WindGustForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.WindSpeedForecastValue

data class DailyForecastModelData(
    val forecastTime: NBDateTimeValue?,
    val sunrise: NBDateTimeValue?,
    val sunset: NBDateTimeValue?,
    val moonrise: NBDateTimeValue?,
    val moonset: NBDateTimeValue?,
    val moonPhase: MoonPhaseType?,
    val temperature: DailyTemperatureModelData?,
    val feelsLikeTemperature: DailyFeelsLikeTemperatureModelData?,
    val pressure: PressureForecastValue?,
    val humidity: HumidityForecastValue?,
    val dewPointTemperature: DewPointForecastValue?,
    val windSpeed: WindSpeedForecastValue?,
    val windGust: WindGustForecastValue?,
    val windDegrees: WindDegreesForecastValue?,
    val cloudiness: CloudinessForecastValue?,
    val uvIndex: UVIndexForecastValue?,
    val probabilityOfPrecipitation: ProbabilityOfPrecipitationForecastValue?,
    val rainVolume: RainForecastValue?,
    val snowVolume: SnowForecastValue?,
    val weather: WeatherModelData?
) {

    internal companion object {

        fun remoteToLocal(
            remoteList: List<DailyForecastModelRemote>?,
            metadataId: Long,
        ): List<DailyForecastEntityLocal> {
            return remoteList.nbMap { remote ->
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
            }
        }

        fun localToData(
            localList: List<DailyForecastEntityLocal>?
        ): List<DailyForecastModelData> {
            return localList.nbMap { local ->
                DailyForecastModelData(
                    forecastTime = NBDateTimeValue.from(local.dt),
                    sunrise = NBDateTimeValue.from(local.sunrise),
                    sunset = NBDateTimeValue.from(local.sunset),
                    moonrise = NBDateTimeValue.from(local.moonrise),
                    moonset = NBDateTimeValue.from(local.moonset),
                    moonPhase = MoonPhaseType.from(local.moonPhase),
                    temperature = DailyTemperatureModelData.localToData(local.temp),
                    feelsLikeTemperature = DailyFeelsLikeTemperatureModelData.localToData(local.feelsLike),
                    pressure = PressureForecastValue.from(local.pressure),
                    humidity = HumidityForecastValue.from(local.humidity),
                    dewPointTemperature = DewPointForecastValue.from(local.dewPoint),
                    windSpeed = WindSpeedForecastValue.from(local.windSpeed),
                    windGust = WindGustForecastValue.from(local.windGust),
                    windDegrees = WindDegreesForecastValue.from(local.windDeg),
                    cloudiness = CloudinessForecastValue.from(local.clouds),
                    uvIndex = UVIndexForecastValue.from(local.uvi),
                    probabilityOfPrecipitation = ProbabilityOfPrecipitationForecastValue.from(local.pop),
                    rainVolume = RainForecastValue.from(local.rain),
                    snowVolume = SnowForecastValue.from(local.snow),
                    weather = WeatherModelData.localToData(local.weather)
                )
            }
        }

    }

}