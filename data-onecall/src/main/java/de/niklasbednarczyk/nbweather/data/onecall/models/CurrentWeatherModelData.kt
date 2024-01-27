package de.niklasbednarczyk.nbweather.data.onecall.models

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeValue
import de.niklasbednarczyk.nbweather.data.onecall.local.models.CurrentWeatherEntityLocal
import de.niklasbednarczyk.nbweather.data.onecall.models.common.WeatherModelData
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.CurrentWeatherModelRemote
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.CloudinessForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.DewPointForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.FeelsLikeForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.HumidityForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.PressureForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.RainForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.SnowForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.TemperatureForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.UVIndexForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.VisibilityForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.WindGustForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.WindSpeedForecastValue
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.WindDegreesForecastValue

data class CurrentWeatherModelData(
    val currentTime: NBDateTimeValue?,
    val sunrise: NBDateTimeValue?,
    val sunset: NBDateTimeValue?,
    val currentTemperature: TemperatureForecastValue?,
    val feelsLikeTemperature: FeelsLikeForecastValue?,
    val pressure: PressureForecastValue?,
    val humidity: HumidityForecastValue?,
    val dewPointTemperature: DewPointForecastValue?,
    val cloudiness: CloudinessForecastValue?,
    val uvIndex: UVIndexForecastValue?,
    val visibility: VisibilityForecastValue?,
    val windSpeed: WindSpeedForecastValue?,
    val windGust: WindGustForecastValue?,
    val windDegrees: WindDegreesForecastValue?,
    val rain1hVolume: RainForecastValue?,
    val snow1hVolume: SnowForecastValue?,
    val weather: WeatherModelData?
) {

    internal companion object {

        fun localToData(
            local: CurrentWeatherEntityLocal?
        ): CurrentWeatherModelData {
            return CurrentWeatherModelData(
                currentTime = NBDateTimeValue.from(local?.dt),
                sunrise = NBDateTimeValue.from(local?.sunrise),
                sunset = NBDateTimeValue.from(local?.sunset),
                currentTemperature = TemperatureForecastValue.from(local?.temp),
                feelsLikeTemperature = FeelsLikeForecastValue.from(local?.feelsLike),
                pressure = PressureForecastValue.from(local?.pressure),
                humidity = HumidityForecastValue.from(local?.humidity),
                dewPointTemperature = DewPointForecastValue.from(local?.dewPoint),
                cloudiness = CloudinessForecastValue.from(local?.clouds),
                uvIndex = UVIndexForecastValue.from(local?.uvi),
                visibility = VisibilityForecastValue.from(local?.visibility),
                windSpeed = WindSpeedForecastValue.from(local?.windSpeed),
                windGust = WindGustForecastValue.from(local?.windGust),
                windDegrees = WindDegreesForecastValue.from(local?.windDeg),
                rain1hVolume = RainForecastValue.from(local?.rain1h),
                snow1hVolume = SnowForecastValue.from(local?.snow1h),
                weather = WeatherModelData.localToData(local?.weather)
            )
        }

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

    }

}