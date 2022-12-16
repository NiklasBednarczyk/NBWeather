package de.niklasbednarczyk.nbweather.data.onecall.models

import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.data.onecall.local.models.NationalWeatherAlertEntityLocal
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.NationalWeatherAlertModelRemote
import de.niklasbednarczyk.nbweather.data.onecall.values.datetime.DateTimeValue

data class NationalWeatherAlertModelData(
    val senderName: NBString?,
    val eventName: NBString?,
    val startDate: DateTimeValue?,
    val endDate: DateTimeValue?,
    val description: NBString?,
    val tags: List<NBString>?
) {

    companion object {

        internal fun remoteToLocal(
            remoteList: List<NationalWeatherAlertModelRemote>?,
            metadataId: Long,
        ): List<NationalWeatherAlertEntityLocal> {
            return remoteList?.map { remote ->
                NationalWeatherAlertEntityLocal(
                    metadataId = metadataId,
                    senderName = remote.senderName,
                    event = remote.event,
                    start = remote.start,
                    end = remote.end,
                    description = remote.description,
                    tags = remote.tags
                )
            } ?: emptyList()
        }

        internal fun localToData(
            localList: List<NationalWeatherAlertEntityLocal>?
        ): List<NationalWeatherAlertModelData> {
            return localList?.map { local ->
                NationalWeatherAlertModelData(
                    senderName = NBString.Value.from(local.senderName),
                    eventName = NBString.Value.from(local.event),
                    startDate = DateTimeValue.from(local.start),
                    endDate = DateTimeValue.from(local.end),
                    description = NBString.Value.from(local.description),
                    tags = local.tags?.mapNotNull { tag -> NBString.Value.from(tag) }
                )
            } ?: emptyList()
        }

    }

}