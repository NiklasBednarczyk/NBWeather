package de.niklasbednarczyk.nbweather.data.onecall.models.common

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.local.models.common.WeatherModelLocal
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.common.WeatherModelRemote
import de.niklasbednarczyk.nbweather.data.onecall.values.weather.WeatherDescriptionValue
import de.niklasbednarczyk.nbweather.data.onecall.values.weather.WeatherIconValue

data class WeatherModelData(
    val description: WeatherDescriptionValue?,
    val icon: WeatherIconValue?
) {

    companion object {

        internal fun remoteToLocal(
            remote: WeatherModelRemote?,
        ): WeatherModelLocal? {
            return nbNullSafe(remote) { model ->
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
            return nbNullSafe(local) { model ->
                WeatherModelData(
                    description = WeatherDescriptionValue.from(model.description),
                    icon = WeatherIconValue.from(model.icon)
                )
            }
        }

    }

}