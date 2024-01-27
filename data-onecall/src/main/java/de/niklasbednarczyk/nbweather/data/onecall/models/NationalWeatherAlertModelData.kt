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

        fun localToData(
            local: List<NationalWeatherAlertEntityLocal>?
        ): List<NationalWeatherAlertModelData> {
            return local.nbMap { l ->
                NationalWeatherAlertModelData(
                    senderName = NBString.Value.from(l.senderName),
                    eventName = NBString.Value.from(l.event),
                    startDate = NBDateTimeValue.from(l.start),
                    endDate = NBDateTimeValue.from(l.end),
                    description = NBString.Value.from(l.description),
                    tags = l.tags?.mapNotNull { tag -> NBString.Value.from(tag) }
                )
            }
        }

        fun remoteToLocal(
            remote: List<NationalWeatherAlertModelRemote>?,
            metadataId: Long,
        ): List<NationalWeatherAlertEntityLocal> {
            return remote.nbMap { r ->
                NationalWeatherAlertEntityLocal(
                    metadataId = metadataId,
                    senderName = r.senderName,
                    event = r.event,
                    start = r.start,
                    end = r.end,
                    description = r.description,
                    tags = r.tags
                )
            }
        }

    }

}