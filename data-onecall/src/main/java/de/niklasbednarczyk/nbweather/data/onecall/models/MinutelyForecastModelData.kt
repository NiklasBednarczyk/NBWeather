package de.niklasbednarczyk.nbweather.data.onecall.models

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeValue
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbMap
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.local.models.MinutelyForecastEntityLocal
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.MinutelyForecastModelRemote
import de.niklasbednarczyk.nbweather.data.onecall.values.units.PrecipitationUnitsValue

data class MinutelyForecastModelData(
    val forecastTime: NBDateTimeValue?,
    val precipitation: PrecipitationUnitsValue?
) {

    internal companion object {

        fun localToData(
            local: List<MinutelyForecastEntityLocal>?
        ): List<MinutelyForecastModelData> {
            return local.nbMap { l ->
                val precipitation = l.precipitation
                val precipitationValue = nbNullSafe(precipitation) { p -> PrecipitationUnitsValue(p) }

                MinutelyForecastModelData(
                    forecastTime = NBDateTimeValue.from(l.dt),
                    precipitation = precipitationValue
                )
            }
        }

        fun remoteToLocal(
            remote: List<MinutelyForecastModelRemote>?,
            metadataId: Long,
        ): List<MinutelyForecastEntityLocal> {
            return remote.nbMap { r ->
                MinutelyForecastEntityLocal(
                    metadataId = metadataId,
                    dt = r.dt,
                    precipitation = r.precipitation
                )
            }
        }

    }

}