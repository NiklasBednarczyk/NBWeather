package de.niklasbednarczyk.nbweather.data.onecall.models.daily

import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.local.models.daily.DailyTemperatureModelLocal
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.daily.DailyTemperatureModelRemote
import de.niklasbednarczyk.nbweather.data.onecall.values.forecast.TemperatureForecastValue

data class DailyTemperatureModelData(
    val morningTemperature: TemperatureForecastValue?,
    val dayTemperature: TemperatureForecastValue?,
    val eveningTemperature: TemperatureForecastValue?,
    val nightTemperature: TemperatureForecastValue?,
    val minDailyTemperature: TemperatureForecastValue?,
    val maxDailyTemperature: TemperatureForecastValue?
) {

    internal companion object {

        fun localToData(
            local: DailyTemperatureModelLocal?
        ): DailyTemperatureModelData? {
            return nbNullSafe(local) { l ->
                DailyTemperatureModelData(
                    morningTemperature = TemperatureForecastValue.from(l.morn),
                    dayTemperature = TemperatureForecastValue.from(l.day),
                    eveningTemperature = TemperatureForecastValue.from(l.eve),
                    nightTemperature = TemperatureForecastValue.from(l.night),
                    minDailyTemperature = TemperatureForecastValue.from(l.min),
                    maxDailyTemperature = TemperatureForecastValue.from(l.max)
                )
            }
        }

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

    }

}