package de.niklasbednarczyk.nbweather.data.onecall.models.daily

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.local.models.daily.DailyTemperatureModelLocal
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.daily.DailyTemperatureModelRemote
import de.niklasbednarczyk.nbweather.data.onecall.values.units.TemperatureValue

data class DailyTemperatureModelData(
    val morningTemperature: TemperatureValue?,
    val dayTemperature: TemperatureValue?,
    val eveningTemperature: TemperatureValue?,
    val nightTemperature: TemperatureValue?,
    val minDailyTemperature: TemperatureValue?,
    val maxDailyTemperature: TemperatureValue?
) {

    internal companion object {

        fun remoteToLocal(
            remote: DailyTemperatureModelRemote?,
        ): DailyTemperatureModelLocal? {
            return nbNullSafe(remote) { r ->
                DailyTemperatureModelLocal(
                    morn = r.morn,
                    day = r.day,
                    eve = r.eve,
                    night = r.night,
                    min = r.min,
                    max = r.max
                )
            }
        }

        fun localToData(
            local: DailyTemperatureModelLocal?
        ): DailyTemperatureModelData? {
            return nbNullSafe(local) { l ->
                DailyTemperatureModelData(
                    morningTemperature = TemperatureValue.from(l.morn),
                    dayTemperature = TemperatureValue.from(l.day),
                    eveningTemperature = TemperatureValue.from(l.eve),
                    nightTemperature = TemperatureValue.from(l.night),
                    minDailyTemperature = TemperatureValue.from(l.min),
                    maxDailyTemperature = TemperatureValue.from(l.max)
                )
            }
        }

    }

}