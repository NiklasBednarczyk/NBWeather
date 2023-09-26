package de.niklasbednarczyk.nbweather.data.onecall.models.daily

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.local.models.daily.DailyFeelsLikeTemperatureModelLocal
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.daily.DailyFeelsLikeTemperatureModelRemote
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.FeelsLikeForecastValue

data class DailyFeelsLikeTemperatureModelData(
    val morningTemperature: FeelsLikeForecastValue?,
    val dayTemperature: FeelsLikeForecastValue?,
    val eveningTemperature: FeelsLikeForecastValue?,
    val nightTemperature: FeelsLikeForecastValue?
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
                    morningTemperature = FeelsLikeForecastValue.from(l.morn),
                    dayTemperature = FeelsLikeForecastValue.from(l.day),
                    eveningTemperature = FeelsLikeForecastValue.from(l.eve),
                    nightTemperature = FeelsLikeForecastValue.from(l.night)
                )
            }
        }

    }

}