package de.niklasbednarczyk.nbweather.data.onecall.models.daily

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.local.models.daily.DailyTemperatureModelLocal
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.daily.DailyTemperatureModelRemote
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.TemperatureUnitsValue

data class DailyTemperatureModelData(
    val morningTemperature: TemperatureUnitsValue?,
    val dayTemperature: TemperatureUnitsValue?,
    val eveningTemperature: TemperatureUnitsValue?,
    val nightTemperature: TemperatureUnitsValue?,
    val minDailyTemperature: TemperatureUnitsValue?,
    val maxDailyTemperature: TemperatureUnitsValue?
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
                    morningTemperature = TemperatureUnitsValue.from(l.morn),
                    dayTemperature = TemperatureUnitsValue.from(l.day),
                    eveningTemperature = TemperatureUnitsValue.from(l.eve),
                    nightTemperature = TemperatureUnitsValue.from(l.night),
                    minDailyTemperature = TemperatureUnitsValue.from(l.min),
                    maxDailyTemperature = TemperatureUnitsValue.from(l.max)
                )
            }
        }

    }

}