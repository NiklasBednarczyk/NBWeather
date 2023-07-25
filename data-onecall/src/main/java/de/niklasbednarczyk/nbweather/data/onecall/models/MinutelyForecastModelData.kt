package de.niklasbednarczyk.nbweather.data.onecall.models

import de.niklasbednarczyk.nbweather.data.onecall.local.models.MinutelyForecastEntityLocal
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.MinutelyForecastModelRemote
import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeModel
import de.niklasbednarczyk.nbweather.data.onecall.values.units.PrecipitationValue

data class MinutelyForecastModelData(
    val forecastTime: NBDateTimeModel?,
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
            localList: List<MinutelyForecastEntityLocal>?,
            timezoneOffset: Long?
        ): List<MinutelyForecastModelData> {
            return localList?.map { local ->
                MinutelyForecastModelData(
                    forecastTime = NBDateTimeModel.from(local.dt, timezoneOffset),
                    precipitation = PrecipitationValue.from(local.precipitation)
                )
            } ?: emptyList()
        }

    }

}