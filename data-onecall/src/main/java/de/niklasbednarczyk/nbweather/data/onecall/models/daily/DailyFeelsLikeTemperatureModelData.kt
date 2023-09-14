package de.niklasbednarczyk.nbweather.data.onecall.models.daily

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.local.models.daily.DailyFeelsLikeTemperatureModelLocal
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.daily.DailyFeelsLikeTemperatureModelRemote
import de.niklasbednarczyk.nbweather.data.onecall.values.units.TemperatureValue

data class DailyFeelsLikeTemperatureModelData(
    val morningTemperature: TemperatureValue?,
    val dayTemperature: TemperatureValue?,
    val eveningTemperature: TemperatureValue?,
    val nightTemperature: TemperatureValue?
) {

    internal companion object {

        fun remoteToLocal(
            remote: DailyFeelsLikeTemperatureModelRemote?,
        ): DailyFeelsLikeTemperatureModelLocal? {
            return nbNullSafe(remote) { r ->
                DailyFeelsLikeTemperatureModelLocal(
                    morn = r.morn,
                    day = r.day,
                    eve = r.eve,
                    night = r.night
                )
            }
        }

        fun localToData(
            local: DailyFeelsLikeTemperatureModelLocal?
        ): DailyFeelsLikeTemperatureModelData? {
            return nbNullSafe(local) { l ->
                DailyFeelsLikeTemperatureModelData(
                    morningTemperature = TemperatureValue.from(l.morn),
                    dayTemperature = TemperatureValue.from(l.day),
                    eveningTemperature = TemperatureValue.from(l.eve),
                    nightTemperature = TemperatureValue.from(l.night)
                )
            }
        }

    }

}