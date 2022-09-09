package de.niklasbednarczyk.openweathermap.data.onecall.models

import de.niklasbednarczyk.openweathermap.core.data.localremote.models.values.common.DateTimeValue
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.values.units.*
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.CurrentWeatherEntityLocal
import de.niklasbednarczyk.openweathermap.data.onecall.models.common.WeatherModelData
import de.niklasbednarczyk.openweathermap.data.onecall.remote.models.CurrentWeatherModelRemote

data class CurrentWeatherModelData(
    val currentTime: DateTimeValue,
    val sunrise: DateTimeValue,
    val sunset: DateTimeValue,
    val currentTemperature: TemperatureValue,
    val feelsLikeTemperature: TemperatureValue,
    val pressure: PressureValue,
    val humidity: PercentValue,
    val dewPointTemperature: TemperatureValue,
    val cloudiness: PercentValue,
    val uvIndex: UVIndexValue,
    val visibility: DistanceValue,
    val windSpeed: SpeedValue,
    val windGust: SpeedValue,
    val windDegrees: WindDegreesValue,
    val rain1hVolume: VolumeValue,
    val snow1hVolume: VolumeValue,
    val weather: WeatherModelData
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
                currentTime = DateTimeValue(local?.dt),
                sunrise = DateTimeValue(local?.sunrise),
                sunset = DateTimeValue(local?.sunset),
                currentTemperature = TemperatureValue(local?.temp),
                feelsLikeTemperature = TemperatureValue(local?.feelsLike),
                pressure = PressureValue(local?.pressure),
                humidity = PercentValue(local?.humidity),
                dewPointTemperature = TemperatureValue(local?.dewPoint),
                cloudiness = PercentValue(local?.clouds),
                uvIndex = UVIndexValue(local?.uvi),
                visibility = DistanceValue(local?.visibility),
                windSpeed = SpeedValue(local?.windSpeed),
                windGust = SpeedValue(local?.windGust),
                windDegrees = WindDegreesValue(local?.windDeg),
                rain1hVolume = VolumeValue(local?.rain1h),
                snow1hVolume = VolumeValue(local?.snow1h),
                weather = WeatherModelData.localToData(local?.weather)
            )
        }

    }

}