package de.niklasbednarczyk.nbweather.data.onecall.models.daily

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.local.models.daily.DailyFeelsLikeTemperatureModelLocal
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.daily.DailyFeelsLikeTemperatureModelRemote
import de.niklasbednarczyk.nbweather.data.onecall.values.units.items.FeelsLikeUnitsValue

data class DailyFeelsLikeTemperatureModelData(
    val morningTemperature: FeelsLikeUnitsValue?,
    val dayTemperature: FeelsLikeUnitsValue?,
    val eveningTemperature: FeelsLikeUnitsValue?,
    val nightTemperature: FeelsLikeUnitsValue?
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
                    morningTemperature = FeelsLikeUnitsValue.from(l.morn),
                    dayTemperature = FeelsLikeUnitsValue.from(l.day),
                    eveningTemperature = FeelsLikeUnitsValue.from(l.eve),
                    nightTemperature = FeelsLikeUnitsValue.from(l.night)
                )
            }
        }

    }

}