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

    companion object {

        internal fun remoteToLocal(
            remote: DailyTemperatureModelRemote?,
        ): DailyTemperatureModelLocal? {
            return nbNullSafe(remote) { model ->
                DailyTemperatureModelLocal(
                    morn = model.morn,
                    day = model.day,
                    eve = model.eve,
                    night = model.night,
                    min = model.min,
                    max = model.max
                )
            }
        }

        internal fun localToData(
            local: DailyTemperatureModelLocal?
        ): DailyTemperatureModelData? {
            return nbNullSafe(local) { model ->
                DailyTemperatureModelData(
                    morningTemperature = TemperatureValue.from(model.morn),
                    dayTemperature = TemperatureValue.from(model.day),
                    eveningTemperature = TemperatureValue.from(model.eve),
                    nightTemperature = TemperatureValue.from(model.night),
                    minDailyTemperature = TemperatureValue.from(model.min),
                    maxDailyTemperature = TemperatureValue.from(model.max)
                )
            }
        }

    }

}