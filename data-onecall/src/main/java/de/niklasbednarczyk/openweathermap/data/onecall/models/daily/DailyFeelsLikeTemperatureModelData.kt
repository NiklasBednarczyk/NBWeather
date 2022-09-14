package de.niklasbednarczyk.openweathermap.data.onecall.models.daily

import de.niklasbednarczyk.openweathermap.data.onecall.values.TemperatureValue
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.daily.DailyFeelsLikeTemperatureModelLocal
import de.niklasbednarczyk.openweathermap.data.onecall.remote.models.daily.DailyFeelsLikeTemperatureModelRemote

data class DailyFeelsLikeTemperatureModelData(
    val morningTemperature: TemperatureValue,
    val dayTemperature: TemperatureValue,
    val eveningTemperature: TemperatureValue,
    val nightTemperature: TemperatureValue
) {

    companion object {

        internal fun remoteToLocal(
            remote: DailyFeelsLikeTemperatureModelRemote?,
        ): DailyFeelsLikeTemperatureModelLocal? {
            if (remote == null) return null
            return DailyFeelsLikeTemperatureModelLocal(
                morn = remote.morn,
                day = remote.day,
                eve = remote.eve,
                night = remote.night
            )
        }

        internal fun localToData(
            local: DailyFeelsLikeTemperatureModelLocal?
        ): DailyFeelsLikeTemperatureModelData? {
            if (local == null) return null
            return DailyFeelsLikeTemperatureModelData(
                morningTemperature = TemperatureValue(local.morn),
                dayTemperature = TemperatureValue(local.day),
                eveningTemperature = TemperatureValue(local.eve),
                nightTemperature = TemperatureValue(local.night)
            )
        }

    }

}