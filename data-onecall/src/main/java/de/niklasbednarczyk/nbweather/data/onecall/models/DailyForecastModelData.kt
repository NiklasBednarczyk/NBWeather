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
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.CloudinessUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.DewPointUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.HumidityUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.PressureUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.ProbabilityOfPrecipitationUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.RainUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.SnowUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.UVIndexUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.WindGustUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.WindSpeedUnitsValue
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
    val pressure: PressureUnitsValue?,
    val humidity: HumidityUnitsValue?,
    val dewPointTemperature: DewPointUnitsValue?,
    val windSpeed: WindSpeedUnitsValue?,
    val windGust: WindGustUnitsValue?,
    val windDegrees: WindDegreesValue?,
    val cloudiness: CloudinessUnitsValue?,
    val uvIndex: UVIndexUnitsValue?,
    val probabilityOfPrecipitation: ProbabilityOfPrecipitationUnitsValue?,
    val rainVolume: RainUnitsValue?,
    val snowVolume: SnowUnitsValue?,
    val weather: WeatherModelData?
) {

    val daylight: NBString?
        get() {
            val sunriseValue = sunrise?.value ?: return null
            val sunsetValue = sunset?.value ?: return null

            val duration = abs(sunriseValue - sunsetValue).seconds

            return duration.toComponents { hours, minutes, _, _ ->
                NBString.ResString(
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
                    pressure = PressureUnitsValue.from(local.pressure),
                    humidity = HumidityUnitsValue.from(local.humidity),
                    dewPointTemperature = DewPointUnitsValue.from(local.dewPoint),
                    windSpeed = WindSpeedUnitsValue.from(local.windSpeed),
                    windGust = WindGustUnitsValue.from(local.windGust),
                    windDegrees = WindDegreesValue.from(local.windDeg),
                    cloudiness = CloudinessUnitsValue.from(local.clouds),
                    uvIndex = UVIndexUnitsValue.from(local.uvi),
                    probabilityOfPrecipitation = ProbabilityOfPrecipitationUnitsValue.from(local.pop),
                    rainVolume = RainUnitsValue.from(local.rain),
                    snowVolume = SnowUnitsValue.from(local.snow),
                    weather = WeatherModelData.localToData(local.weather)
                )
            }
        }

    }

}