package de.niklasbednarczyk.nbweather.data.onecall.models

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeValue
import de.niklasbednarczyk.nbweather.data.onecall.local.models.CurrentWeatherEntityLocal
import de.niklasbednarczyk.nbweather.data.onecall.models.common.WeatherModelData
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.CurrentWeatherModelRemote
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.CloudinessUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.DewPointUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.FeelsLikeUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.HumidityUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.PressureUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.RainUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.SnowUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.TemperatureUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.UVIndexUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.VisibilityUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.WindGustUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.WindSpeedUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.winddegrees.WindDegreesValue

data class CurrentWeatherModelData(
    val currentTime: NBDateTimeValue?,
    val sunrise: NBDateTimeValue?,
    val sunset: NBDateTimeValue?,
    val currentTemperature: TemperatureUnitsValue?,
    val feelsLikeTemperature: FeelsLikeUnitsValue?,
    val pressure: PressureUnitsValue?,
    val humidity: HumidityUnitsValue?,
    val dewPointTemperature: DewPointUnitsValue?,
    val cloudiness: CloudinessUnitsValue?,
    val uvIndex: UVIndexUnitsValue?,
    val visibility: VisibilityUnitsValue?,
    val windSpeed: WindSpeedUnitsValue?,
    val windGust: WindGustUnitsValue?,
    val windDegrees: WindDegreesValue?,
    val rain1hVolume: RainUnitsValue?,
    val snow1hVolume: SnowUnitsValue?,
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
                currentTemperature = TemperatureUnitsValue.from(local?.temp),
                feelsLikeTemperature = FeelsLikeUnitsValue.from(local?.feelsLike),
                pressure = PressureUnitsValue.from(local?.pressure),
                humidity = HumidityUnitsValue.from(local?.humidity),
                dewPointTemperature = DewPointUnitsValue.from(local?.dewPoint),
                cloudiness = CloudinessUnitsValue.from(local?.clouds),
                uvIndex = UVIndexUnitsValue.from(local?.uvi),
                visibility = VisibilityUnitsValue.from(local?.visibility),
                windSpeed = WindSpeedUnitsValue.from(local?.windSpeed),
                windGust = WindGustUnitsValue.from(local?.windGust),
                windDegrees = WindDegreesValue.from(local?.windDeg),
                rain1hVolume = RainUnitsValue.from(local?.rain1h),
                snow1hVolume = SnowUnitsValue.from(local?.snow1h),
                weather = WeatherModelData.localToData(local?.weather)
            )
        }

    }

}