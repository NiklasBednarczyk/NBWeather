package de.niklasbednarczyk.openweathermap.data.onecall.models.common

import de.niklasbednarczyk.openweathermap.core.data.localremote.models.values.weather.WeatherDescriptionValue
import de.niklasbednarczyk.openweathermap.core.data.localremote.models.values.weather.WeatherIconValue
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.common.WeatherModelLocal
import de.niklasbednarczyk.openweathermap.data.onecall.remote.models.common.WeatherModelRemote

data class WeatherModelData(
    val description: WeatherDescriptionValue,
    val icon: WeatherIconValue
) {

    companion object {

        internal fun remoteToLocal(
            remote: WeatherModelRemote?,
        ): WeatherModelLocal {
            return WeatherModelLocal(
                id = remote?.id,
                main = remote?.main,
                description = remote?.description,
                icon = remote?.icon
            )
        }

        internal fun localToData(
            local: WeatherModelLocal?
        ): WeatherModelData {
            return WeatherModelData(
                description = WeatherDescriptionValue(local?.description),
                icon = WeatherIconValue(local?.icon)
            )
        }

    }

}