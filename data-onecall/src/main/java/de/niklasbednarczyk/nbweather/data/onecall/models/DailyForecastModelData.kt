package de.niklasbednarczyk.nbweather.data.onecall.models

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeValue
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbMap
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.data.localremote.R
import de.niklasbednarczyk.nbweather.data.onecall.local.models.DailyForecastEntityLocal
import de.niklasbednarczyk.nbweather.data.onecall.models.common.WeatherModelData
import de.niklasbednarczyk.nbweather.data.onecall.models.daily.DailyFeelsLikeTemperatureModelData
import de.niklasbednarczyk.nbweather.data.onecall.models.daily.DailyTemperatureModelData
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.DailyForecastModelRemote
import de.niklasbednarczyk.nbweather.data.onecall.values.moon.MoonPhaseValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.PercentValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.PrecipitationValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.PressureValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.ProbabilityValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.TemperatureValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.UVIndexValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.WindSpeedValue
import de.niklasbednarczyk.nbweather.data.onecall.values.winddegrees.WindDegreesValue
import kotlin.math.abs
import kotlin.time.Duration.Companion.seconds

data class DailyForecastModelData(
    val forecastTime: NBDateTimeValue?,
    val sunrise: NBDateTimeValue?,
    val sunset: NBDateTimeValue?,
    val moonrise: NBDateTimeValue?,
    val moonset: NBDateTimeValue?,
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
            }
        }

    }

}