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

    companion object {

        internal fun remoteToLocal(
            remote: DailyFeelsLikeTemperatureModelRemote?,
        ): DailyFeelsLikeTemperatureModelLocal? {
            return nbNullSafe(remote) { model ->
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
            return nbNullSafe(local) { model ->
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