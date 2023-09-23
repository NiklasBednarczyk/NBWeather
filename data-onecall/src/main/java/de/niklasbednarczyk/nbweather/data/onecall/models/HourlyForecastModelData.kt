package de.niklasbednarczyk.nbweather.data.onecall.models

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeValue
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbMap
import de.niklasbednarczyk.nbweather.data.onecall.local.models.HourlyForecastEntityLocal
import de.niklasbednarczyk.nbweather.data.onecall.models.common.WeatherModelData
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.HourlyForecastModelRemote
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.CloudinessUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.DewPointUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.FeelsLikeUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.HumidityUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.PressureUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.ProbabilityOfPrecipitationUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.RainUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.SnowUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.TemperatureUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.UVIndexUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.VisibilityUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.WindGustUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.WindSpeedUnitsValue
import de.niklasbednarczyk.nbweather.data.onecall.values.winddegrees.WindDegreesValue

data class HourlyForecastModelData(
    val forecastTime: NBDateTimeValue?,
    val temperature: TemperatureUnitsValue?,
    val feelsLikeTemperature: FeelsLikeUnitsValue?,
    val pressure: PressureUnitsValue?,
    val humidity: HumidityUnitsValue?,
    val dewPointTemperature: DewPointUnitsValue?,
    val uvIndex: UVIndexUnitsValue?,
    val cloudiness: CloudinessUnitsValue?,
    val visibility: VisibilityUnitsValue?,
    val windSpeed: WindSpeedUnitsValue?,
    val windGust: WindGustUnitsValue?,
    val windDegrees: WindDegreesValue?,
    val probabilityOfPrecipitation: ProbabilityOfPrecipitationUnitsValue?,
    val rain1hVolume: RainUnitsValue?,
    val snow1hVolume: SnowUnitsValue?,
    val weather: WeatherModelData?
) {

    internal companion object {

        fun remoteToLocal(
            remoteList: List<HourlyForecastModelRemote>?,
            metadataId: Long,
        ): List<HourlyForecastEntityLocal> {
            return remoteList.nbMap { remote ->
                HourlyForecastEntityLocal(
                    metadataId = metadataId,
                    dt = remote.dt,
                    temp = remote.temp,
                    feelsLike = remote.feelsLike,
                    pressure = remote.pressure,
                    humidity = remote.humidity,
                    dewPoint = remote.dewPoint,
                    uvi = remote.uvi,
                    clouds = remote.clouds,
                    visibility = remote.visibility,
                    windSpeed = remote.windSpeed,
                    windGust = remote.windGust,
                    windDeg = remote.windDeg,
                    pop = remote.pop,
                    rain1h = remote.rain?.oneH,
                    snow1h = remote.snow?.oneH,
                    weather = WeatherModelData.remoteToLocal(remote.weather?.firstOrNull())
                )
            }
        }

        fun localToData(
            localList: List<HourlyForecastEntityLocal>?
        ): List<HourlyForecastModelData> {
            return localList.nbMap { local ->
                HourlyForecastModelData(
                    forecastTime = NBDateTimeValue.from(local.dt),
                    temperature = TemperatureUnitsValue.from(local.temp),
                    feelsLikeTemperature = FeelsLikeUnitsValue.from(local.feelsLike),
                    pressure = PressureUnitsValue.from(local.pressure),
                    humidity = HumidityUnitsValue.from(local.humidity),
                    dewPointTemperature = DewPointUnitsValue.from(local.dewPoint),
                    uvIndex = UVIndexUnitsValue.from(local.uvi),
                    cloudiness = CloudinessUnitsValue.from(local.clouds),
                    visibility = VisibilityUnitsValue.from(local.visibility),
                    windSpeed = WindSpeedUnitsValue.from(local.windSpeed),
                    windGust = WindGustUnitsValue.from(local.windGust),
                    windDegrees = WindDegreesValue.from(local.windDeg),
                    probabilityOfPrecipitation = ProbabilityOfPrecipitationUnitsValue.from(local.pop),
                    rain1hVolume = RainUnitsValue.from(local.rain1h),
                    snow1hVolume = SnowUnitsValue.from(local.snow1h),
                    weather = WeatherModelData.localToData(local.weather)
                )
            }
        }

    }

}