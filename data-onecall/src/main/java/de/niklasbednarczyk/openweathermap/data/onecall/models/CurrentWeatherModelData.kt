package de.niklasbednarczyk.openweathermap.data.onecall.models

import de.niklasbednarczyk.openweathermap.data.onecall.local.models.CurrentWeatherEntityLocal
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.common.WeatherModelLocal
import de.niklasbednarczyk.openweathermap.data.onecall.remote.models.CurrentWeatherModelRemote

data class CurrentWeatherModelData(
    val dt: Long? //TODO (#1) Do right with value classes
) {

    companion object {

        internal fun remoteToLocal(
            remote: CurrentWeatherModelRemote?,
            metadataId: Long,
        ): CurrentWeatherEntityLocal {
            val remoteWeather = remote?.weather?.firstOrNull()
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
                weather = WeatherModelLocal(
                    id = remoteWeather?.id,
                    main = remoteWeather?.main,
                    description = remoteWeather?.description,
                    icon = remoteWeather?.icon
                )
            )
        }

        internal fun localToData(
            local: CurrentWeatherEntityLocal?
        ): CurrentWeatherModelData {
            return CurrentWeatherModelData(
                dt = local?.dt
            )
        }

    }

}