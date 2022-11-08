package de.niklasbednarczyk.openweathermap.data.onecall.models.daily

import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.daily.DailyTemperatureModelLocal
import de.niklasbednarczyk.openweathermap.data.onecall.remote.models.daily.DailyTemperatureModelRemote
import de.niklasbednarczyk.openweathermap.data.onecall.values.units.TemperatureValue

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
            return owmNullSafe(remote) { model ->
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
            return owmNullSafe(local) { model ->
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