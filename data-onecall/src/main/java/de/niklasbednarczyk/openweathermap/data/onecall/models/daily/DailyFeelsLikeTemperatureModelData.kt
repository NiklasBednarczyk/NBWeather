package de.niklasbednarczyk.openweathermap.data.onecall.models.daily

import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.daily.DailyFeelsLikeTemperatureModelLocal
import de.niklasbednarczyk.openweathermap.data.onecall.remote.models.daily.DailyFeelsLikeTemperatureModelRemote
import de.niklasbednarczyk.openweathermap.data.onecall.values.units.TemperatureValue

data class DailyFeelsLikeTemperatureModelData(
    val morningTemperature: TemperatureValue?,
    val dayTemperature: TemperatureValue?,
    val eveningTemperature: TemperatureValue?,
    val nightTemperature: TemperatureValue?
) {

    companion object {

        internal fun remoteToLocal(
            remote: DailyFeelsLikeTemperatureModelRemote?,
        ): DailyFeelsLikeTemperatureModelLocal? {
            return owmNullSafe(remote) { model ->
                DailyFeelsLikeTemperatureModelLocal(
                    morn = model.morn,
                    day = model.day,
                    eve = model.eve,
                    night = model.night
                )
            }
        }

        internal fun localToData(
            local: DailyFeelsLikeTemperatureModelLocal?
        ): DailyFeelsLikeTemperatureModelData? {
            return owmNullSafe(local) { model ->
                DailyFeelsLikeTemperatureModelData(
                    morningTemperature = TemperatureValue.from(model.morn),
                    dayTemperature = TemperatureValue.from(model.day),
                    eveningTemperature = TemperatureValue.from(model.eve),
                    nightTemperature = TemperatureValue.from(model.night)
                )
            }
        }

    }

}