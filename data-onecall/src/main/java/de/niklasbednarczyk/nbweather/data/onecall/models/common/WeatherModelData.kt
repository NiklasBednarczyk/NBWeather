package de.niklasbednarczyk.nbweather.data.onecall.models.common

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.local.models.common.WeatherModelLocal
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.common.WeatherModelRemote
import de.niklasbednarczyk.nbweather.data.onecall.types.weather.WeatherConditionType
import de.niklasbednarczyk.nbweather.data.onecall.types.weather.WeatherIconType

data class WeatherModelData(
    val icon: WeatherIconType?,
    val condition: WeatherConditionType?
) {

    internal companion object {

        fun localToData(
            local: WeatherModelLocal?
        ): WeatherModelData? {
            return nbNullSafe(local) { l ->
                WeatherModelData(
                    icon = WeatherIconType.from(l.icon),
                    condition = WeatherConditionType.from(l.id)
                )
            }
        }

        fun remoteToLocal(
            remote: List<WeatherModelRemote>?,
        ): WeatherModelLocal? {
            return nbNullSafe(remote?.firstOrNull()) { r ->
                WeatherModelLocal(
                    id = r.id,
                    main = r.main,
                    description = r.description,
                    icon = r.icon
                )
            }
        }

    }

}