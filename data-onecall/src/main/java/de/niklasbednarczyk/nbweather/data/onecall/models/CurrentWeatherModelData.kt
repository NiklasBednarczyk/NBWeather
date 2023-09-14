package de.niklasbednarczyk.nbweather.data.onecall.models

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeValue
import de.niklasbednarczyk.nbweather.data.onecall.local.models.CurrentWeatherEntityLocal
import de.niklasbednarczyk.nbweather.data.onecall.models.common.WeatherModelData
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.CurrentWeatherModelRemote
import de.niklasbednarczyk.nbweather.data.onecall.values.units.DistanceValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.PercentValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.PrecipitationValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.PressureValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.TemperatureValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.UVIndexValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.WindSpeedValue
import de.niklasbednarczyk.nbweather.data.onecall.values.winddegrees.WindDegreesValue

data class CurrentWeatherModelData(
    val currentTime: NBDateTimeValue?,
    val sunrise: NBDateTimeValue?,
    val sunset: NBDateTimeValue?,
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

    internal companion object {

        fun remoteToLocal(
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

        fun localToData(
            local: CurrentWeatherEntityLocal?
        ): CurrentWeatherModelData {
            return CurrentWeatherModelData(
                currentTime = NBDateTimeValue.from(local?.dt),
                sunrise = NBDateTimeValue.from(local?.sunrise),
                sunset = NBDateTimeValue.from(local?.sunset),
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