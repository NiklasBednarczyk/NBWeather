package de.niklasbednarczyk.nbweather.data.onecall.models

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeValue
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbMap
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.data.onecall.local.models.MinutelyForecastEntityLocal
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.MinutelyForecastModelRemote
import de.niklasbednarczyk.nbweather.data.onecall.values.units.values.PrecipitationValue

data class MinutelyForecastModelData(
    val forecastTime: NBDateTimeValue?,
    val precipitation: PrecipitationValue?
) {

    internal companion object {

        fun remoteToLocal(
            remoteList: List<MinutelyForecastModelRemote>?,
            metadataId: Long,
        ): List<MinutelyForecastEntityLocal> {
            return remoteList.nbMap { remote ->
                MinutelyForecastEntityLocal(
                    metadataId = metadataId,
                    dt = remote.dt,
                    precipitation = remote.precipitation
                )
            }
        }

        fun localToData(
            localList: List<MinutelyForecastEntityLocal>?
        ): List<MinutelyForecastModelData> {
            return localList.nbMap { local ->
                val precipitation = local.precipitation
                val precipitationValue = nbNullSafe(precipitation) { p -> PrecipitationValue(p) }

                MinutelyForecastModelData(
                    forecastTime = NBDateTimeValue.from(local.dt),
                    precipitation = precipitationValue
                )
            }
        }

    }

}