package de.niklasbednarczyk.nbweather.data.onecall.models

import de.niklasbednarczyk.nbweather.data.onecall.values.datetime.DateTimeValue
import de.niklasbednarczyk.nbweather.data.onecall.values.units.PrecipitationValue
import de.niklasbednarczyk.nbweather.data.onecall.local.models.MinutelyForecastEntityLocal
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.MinutelyForecastModelRemote

data class MinutelyForecastModelData(
    val forecastTime: DateTimeValue?,
    val precipitation: PrecipitationValue?
) {

    companion object {

        internal fun remoteToLocal(
            remoteList: List<MinutelyForecastModelRemote>?,
            metadataId: Long,
        ): List<MinutelyForecastEntityLocal> {
            return remoteList?.map { remote ->
                MinutelyForecastEntityLocal(
                    metadataId = metadataId,
                    dt = remote.dt,
                    precipitation = remote.precipitation
                )
            } ?: emptyList()
        }

        internal fun localToData(
            localList: List<MinutelyForecastEntityLocal>?
        ): List<MinutelyForecastModelData> {
            return localList?.map { local ->
                MinutelyForecastModelData(
                    forecastTime = DateTimeValue.from(local.dt),
                    precipitation = PrecipitationValue.from(local.precipitation)
                )
            } ?: emptyList()
        }

    }

}