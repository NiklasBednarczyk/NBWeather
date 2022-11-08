package de.niklasbednarczyk.openweathermap.data.onecall.models.common

import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.common.WeatherModelLocal
import de.niklasbednarczyk.openweathermap.data.onecall.remote.models.common.WeatherModelRemote
import de.niklasbednarczyk.openweathermap.data.onecall.values.weather.WeatherDescriptionValue
import de.niklasbednarczyk.openweathermap.data.onecall.values.weather.WeatherIconValue

data class WeatherModelData(
    val description: WeatherDescriptionValue?,
    val icon: WeatherIconValue?
) {

    companion object {

        internal fun remoteToLocal(
            remote: WeatherModelRemote?,
        ): WeatherModelLocal? {
            return owmNullSafe(remote) { model ->
                WeatherModelLocal(
                    id = model.id,
                    main = model.main,
                    description = model.description,
                    icon = model.icon
                )
            }
        }

        internal fun localToData(
            local: WeatherModelLocal?
        ): WeatherModelData? {
            return owmNullSafe(local) { model ->
                WeatherModelData(
                    description = WeatherDescriptionValue.from(model.description),
                    icon = WeatherIconValue.from(model.icon)
                )
            }
        }

    }

}