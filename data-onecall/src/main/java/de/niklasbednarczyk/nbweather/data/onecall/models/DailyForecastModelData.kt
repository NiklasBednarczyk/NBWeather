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

        fun localToData(
            local: List<DailyForecastEntityLocal>?
        ): List<DailyForecastModelData> {
            return local.nbMap { l ->
                DailyForecastModelData(
                    forecastTime = NBDateTimeValue.from(l.dt),
                    sunrise = NBDateTimeValue.from(l.sunrise),
                    sunset = NBDateTimeValue.from(l.sunset),
                    moonrise = NBDateTimeValue.from(l.moonrise),
                    moonset = NBDateTimeValue.from(l.moonset),
                    moonPhase = MoonPhaseType.from(l.moonPhase),
                    temperature = DailyTemperatureModelData.localToData(l.temp),
                    feelsLikeTemperature = DailyFeelsLikeTemperatureModelData.localToData(l.feelsLike),
                    pressure = PressureForecastValue.from(l.pressure),
                    humidity = HumidityForecastValue.from(l.humidity),
                    dewPointTemperature = DewPointForecastValue.from(l.dewPoint),
                    windSpeed = WindSpeedForecastValue.from(l.windSpeed),
                    windGust = WindGustForecastValue.from(l.windGust),
                    windDegrees = WindDegreesForecastValue.from(l.windDeg),
                    cloudiness = CloudinessForecastValue.from(l.clouds),
                    uvIndex = UVIndexForecastValue.from(l.uvi),
                    probabilityOfPrecipitation = ProbabilityOfPrecipitationForecastValue.from(l.pop),
                    rainVolume = RainForecastValue.from(l.rain),
                    snowVolume = SnowForecastValue.from(l.snow),
                    weather = WeatherModelData.localToData(l.weather)
                )
            }
        }

        fun remoteToLocal(
            remote: List<DailyForecastModelRemote>?,
            metadataId: Long,
        ): List<DailyForecastEntityLocal> {
            return remote.nbMap { r ->
                DailyForecastEntityLocal(
                    metadataId = metadataId,
                    dt = r.dt,
                    sunrise = r.sunrise,
                    sunset = r.sunset,
                    moonrise = r.moonrise,
                    moonset = r.moonset,
                    moonPhase = r.moonPhase,
                    temp = DailyTemperatureModelData.remoteToLocal(r.temp),
                    feelsLike = DailyFeelsLikeTemperatureModelData.remoteToLocal(r.feelsLike),
                    pressure = r.pressure,
                    humidity = r.humidity,
                    dewPoint = r.dewPoint,
                    windSpeed = r.windSpeed,
                    windGust = r.windGust,
                    windDeg = r.windDeg,
                    clouds = r.clouds,
                    uvi = r.uvi,
                    pop = r.pop,
                    rain = r.rain,
                    snow = r.snow,
                    weather = WeatherModelData.remoteToLocal(r.weather?.firstOrNull())
                )
            }
        }

    }

}