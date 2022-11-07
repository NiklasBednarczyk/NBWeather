package de.niklasbednarczyk.openweathermap.data.onecall.models

import de.niklasbednarczyk.openweathermap.data.onecall.values.datetime.DateTimeValue
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.CurrentWeatherEntityLocal
import de.niklasbednarczyk.openweathermap.data.onecall.models.common.WeatherModelData
import de.niklasbednarczyk.openweathermap.data.onecall.remote.models.CurrentWeatherModelRemote
import de.niklasbednarczyk.openweathermap.data.onecall.values.number.*
import de.niklasbednarczyk.openweathermap.data.onecall.values.winddegrees.WindDegreesValue

data class CurrentWeatherModelData(
    val currentTime: DateTimeValue?,
    val sunrise: DateTimeValue?,
    val sunset: DateTimeValue?,
    val currentTemperature: TemperatureValue?,
    val feelsLikeTemperature: TemperatureValue?,
    val pressure: PressureValue?,
    val humidity: PercentValue?,
    val dewPointTemperature: TemperatureValue?,
    val cloudiness: PercentValue?,
    val uvIndex: UVIndexValue?,
    val visibility: DistanceValue?,
    val windSpeed: WindSpeedValue?,
    val windGust: WindSpeedValue?,
    val windDegrees: WindDegreesValue?,
    val rain1hVolume: PrecipitationValue?,
    val snow1hVolume: PrecipitationValue?,
    val weather: WeatherModelData?
) {

    companion object {

        internal fun remoteToLocal(
            remote: CurrentWeatherModelRemote?,
            metadataId: Long,
        ): CurrentWeatherEntityLocal {
            return CurrentWeatherEntityLocal(
                metadataId = metadataId,
                dt = remote?.dt,
                sunrise = remote?.sunrise,
                sunset = remote?.sunset,
                temp = remote?.temp,
                feelsLike = remote?.feelsLike,
                pressure = remote?.pressure,
                humidity = remote?.humidity,
                dewPoint = remote?.dewPoint,
                clouds = remote?.clouds,
                uvi = remote?.uvi,
                visibility = remote?.visibility,
                windSpeed = remote?.windSpeed,
                windGust = remote?.windGust,
                windDeg = remote?.windDeg,
                rain1h = remote?.rain?.oneH,
                snow1h = remote?.snow?.oneH,
                weather = WeatherModelData.remoteToLocal(remote?.weather?.firstOrNull())
            )
        }

        internal fun localToData(
            local: CurrentWeatherEntityLocal?
        ): CurrentWeatherModelData {
            return CurrentWeatherModelData(
                currentTime = DateTimeValue.from(local?.dt),
                sunrise = DateTimeValue.from(local?.sunrise),
                sunset = DateTimeValue.from(local?.sunset),
                currentTemperature = TemperatureValue.from(local?.temp),
                feelsLikeTemperature = TemperatureValue.from(local?.feelsLike),
                pressure = PressureValue.from(local?.pressure),
                humidity = PercentValue.from(local?.humidity),
                dewPointTemperature = TemperatureValue.from(local?.dewPoint),
                cloudiness = PercentValue.from(local?.clouds),
                uvIndex = UVIndexValue.from(local?.uvi),
                visibility = DistanceValue.from(local?.visibility),
                windSpeed = WindSpeedValue.from(local?.windSpeed),
                windGust = WindSpeedValue.from(local?.windGust),
                windDegrees = WindDegreesValue.from(local?.windDeg),
                rain1hVolume = PrecipitationValue.from(local?.rain1h),
                snow1hVolume = PrecipitationValue.from(local?.snow1h),
                weather = WeatherModelData.localToData(local?.weather)
            )
        }

    }

}