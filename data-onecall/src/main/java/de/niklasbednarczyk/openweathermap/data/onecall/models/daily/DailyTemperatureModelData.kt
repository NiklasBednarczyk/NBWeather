package de.niklasbednarczyk.openweathermap.data.onecall.models.daily

import de.niklasbednarczyk.openweathermap.data.onecall.local.models.daily.DailyTemperatureModelLocal
import de.niklasbednarczyk.openweathermap.data.onecall.remote.models.daily.DailyTemperatureModelRemote
import de.niklasbednarczyk.openweathermap.data.onecall.values.number.TemperatureValue

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
            if (remote == null) return null
            return DailyTemperatureModelLocal(
                morn = remote.morn,
                day = remote.day,
                eve = remote.eve,
                night = remote.night,
                min = remote.min,
                max = remote.max
            )
        }

        internal fun localToData(
            local: DailyTemperatureModelLocal?
        ): DailyTemperatureModelData? {
            if (local == null) return null
            return DailyTemperatureModelData(
                morningTemperature = TemperatureValue.from(local.morn),
                dayTemperature = TemperatureValue.from(local.day),
                eveningTemperature = TemperatureValue.from(local.eve),
                nightTemperature = TemperatureValue.from(local.night),
                minDailyTemperature = TemperatureValue.from(local.min),
                maxDailyTemperature = TemperatureValue.from(local.max)
            )
        }

    }

}