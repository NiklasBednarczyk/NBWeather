package de.niklasbednarczyk.nbweather.data.onecall.models

import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.data.localremote.R
import de.niklasbednarczyk.nbweather.data.onecall.local.models.DailyForecastEntityLocal
import de.niklasbednarczyk.nbweather.data.onecall.models.common.WeatherModelData
import de.niklasbednarczyk.nbweather.data.onecall.models.daily.DailyFeelsLikeTemperatureModelData
import de.niklasbednarczyk.nbweather.data.onecall.models.daily.DailyTemperatureModelData
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.DailyForecastModelRemote
import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeModel
import de.niklasbednarczyk.nbweather.data.onecall.values.moon.MoonPhaseValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.*
import de.niklasbednarczyk.nbweather.data.onecall.values.winddegrees.WindDegreesValue
import kotlin.math.abs
import kotlin.time.Duration.Companion.seconds

data class DailyForecastModelData(
    val forecastTime: NBDateTimeModel?,
    val sunrise: NBDateTimeModel?,
    val sunset: NBDateTimeModel?,
    val moonrise: NBDateTimeModel?,
    val moonset: NBDateTimeModel?,
    val moonPhase: MoonPhaseValue?,
    val temperature: DailyTemperatureModelData?,
    val feelsLikeTemperature: DailyFeelsLikeTemperatureModelData?,
    val pressure: PressureValue?,
    val humidity: PercentValue?,
    val dewPointTemperature: TemperatureValue?,
    val windSpeed: WindSpeedValue?,
    val windGust: WindSpeedValue?,
    val windDegrees: WindDegreesValue?,
    val cloudiness: PercentValue?,
    val uvIndex: UVIndexValue?,
    val probabilityOfPrecipitation: ProbabilityValue?,
    val rainVolume: PrecipitationValue?,
    val snowVolume: PrecipitationValue?,
    val weather: WeatherModelData?
) {

    val daylight: NBString?
        get() {
            val sunriseValue = sunrise?.value ?: return null
            val sunsetValue = sunset?.value ?: return null

            val duration = abs(sunriseValue - sunsetValue).seconds

            return duration.toComponents { hours, minutes, _, _ ->
                NBString.Resource(
                    R.string.format_hours_minutes,
                    hours,
                    minutes
                )
            }

        }

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
            localList: List<DailyForecastEntityLocal>?,
            timezoneOffset: Long?
        ): List<DailyForecastModelData> {
            return localList?.map { local ->
                DailyForecastModelData(
                    forecastTime = NBDateTimeModel.from(local.dt, timezoneOffset),
                    sunrise = NBDateTimeModel.from(local.sunrise, timezoneOffset),
                    sunset = NBDateTimeModel.from(local.sunset, timezoneOffset),
                    moonrise = NBDateTimeModel.from(local.moonrise, timezoneOffset),
                    moonset = NBDateTimeModel.from(local.moonset, timezoneOffset),
                    moonPhase = MoonPhaseValue.from(local.moonPhase),
                    temperature = DailyTemperatureModelData.localToData(local.temp),
                    feelsLikeTemperature = DailyFeelsLikeTemperatureModelData.localToData(local.feelsLike),
                    pressure = PressureValue.from(local.pressure),
                    humidity = PercentValue.from(local.humidity),
                    dewPointTemperature = TemperatureValue.from(local.dewPoint),
                    windSpeed = WindSpeedValue.from(local.windSpeed),
                    windGust = WindSpeedValue.from(local.windGust),
                    windDegrees = WindDegreesValue.from(local.windDeg),
                    cloudiness = PercentValue.from(local.clouds),
                    uvIndex = UVIndexValue.from(local.uvi),
                    probabilityOfPrecipitation = ProbabilityValue.from(local.pop),
                    rainVolume = PrecipitationValue.from(local.rain),
                    snowVolume = PrecipitationValue.from(local.snow),
                    weather = WeatherModelData.localToData(local.weather)
                )
            } ?: emptyList()
        }

    }

}