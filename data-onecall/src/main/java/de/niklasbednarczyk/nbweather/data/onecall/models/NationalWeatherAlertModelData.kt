package de.niklasbednarczyk.nbweather.data.onecall.models

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeValue
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbMap
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.data.onecall.local.models.NationalWeatherAlertEntityLocal
import de.niklasbednarczyk.nbweather.data.onecall.remote.models.NationalWeatherAlertModelRemote

data class NationalWeatherAlertModelData(
    val senderName: NBString?,
    val eventName: NBString?,
    val startDate: NBDateTimeValue?,
    val endDate: NBDateTimeValue?,
    val description: NBString?,
    val tags: List<NBString>?
) {

    internal companion object {

        fun remoteToLocal(
            remoteList: List<NationalWeatherAlertModelRemote>?,
            metadataId: Long,
        ): List<NationalWeatherAlertEntityLocal> {
            return remoteList.nbMap { remote ->
                NationalWeatherAlertEntityLocal(
                    metadataId = metadataId,
                    senderName = remote.senderName,
                    event = remote.event,
                    start = remote.start,
                    end = remote.end,
                    description = remote.description,
                    tags = remote.tags
                )
            }
        }

        fun localToData(
            localList: List<NationalWeatherAlertEntityLocal>?
        ): List<NationalWeatherAlertModelData> {
            return localList.nbMap { local ->
                NationalWeatherAlertModelData(
                    senderName = NBString.Value.from(local.senderName),
                    eventName = NBString.Value.from(local.event),
                    startDate = NBDateTimeValue.from(local.start),
                    endDate = NBDateTimeValue.from(local.end),
                    description = NBString.Value.from(local.description),
                    tags = local.tags?.mapNotNull { tag -> NBString.Value.from(tag) }
                )
            }
        }

    }

}