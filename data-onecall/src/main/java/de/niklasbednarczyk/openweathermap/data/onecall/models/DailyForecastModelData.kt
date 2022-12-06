package de.niklasbednarczyk.openweathermap.data.onecall.models

import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.data.localremote.R
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.DailyForecastEntityLocal
import de.niklasbednarczyk.openweathermap.data.onecall.models.common.WeatherModelData
import de.niklasbednarczyk.openweathermap.data.onecall.models.daily.DailyFeelsLikeTemperatureModelData
import de.niklasbednarczyk.openweathermap.data.onecall.models.daily.DailyTemperatureModelData
import de.niklasbednarczyk.openweathermap.data.onecall.remote.models.DailyForecastModelRemote
import de.niklasbednarczyk.openweathermap.data.onecall.values.datetime.DateTimeValue
import de.niklasbednarczyk.openweathermap.data.onecall.values.moon.MoonPhaseValue
import de.niklasbednarczyk.openweathermap.data.onecall.values.units.*
import de.niklasbednarczyk.openweathermap.data.onecall.values.winddegrees.WindDegreesValue
import kotlin.math.abs
import kotlin.time.Duration.Companion.seconds

data class DailyForecastModelData(
    val forecastTime: DateTimeValue?,
    val sunrise: DateTimeValue?,
    val sunset: DateTimeValue?,
    val moonrise: DateTimeValue?,
    val moonset: DateTimeValue?,
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

    val sunTime: OwmString?
        get() = getTime(sunrise, sunset)

    val moonTime: OwmString?
        get() = getTime(moonrise, moonset)

    private fun getTime(
        rise: DateTimeValue?,
        set: DateTimeValue?
    ): OwmString? {
        val riseValue = rise?.value ?: return null
        val setValue = set?.value ?: return null

        val duration = abs(riseValue - setValue).seconds

        return duration.toComponents { hours, minutes, seconds, _ ->
            val mins = if (seconds > 30) {
                minutes + 1
            } else {
                minutes
            }

            OwmString.Resource(
                R.string.format_hours_minutes,
                hours,
                mins
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
            localList: List<DailyForecastEntityLocal>?
        ): List<DailyForecastModelData> {
            return localList?.map { local ->
                DailyForecastModelData(
                    forecastTime = DateTimeValue.from(local.dt),
                    sunrise = DateTimeValue.from(local.sunrise),
                    sunset = DateTimeValue.from(local.sunset),
                    moonrise = DateTimeValue.from(local.moonrise),
                    moonset = DateTimeValue.from(local.moonset),
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