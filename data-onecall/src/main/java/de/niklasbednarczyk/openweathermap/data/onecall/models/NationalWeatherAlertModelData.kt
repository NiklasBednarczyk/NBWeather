package de.niklasbednarczyk.openweathermap.data.onecall.models

import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.NationalWeatherAlertEntityLocal
import de.niklasbednarczyk.openweathermap.data.onecall.remote.models.NationalWeatherAlertModelRemote
import de.niklasbednarczyk.openweathermap.data.onecall.values.datetime.DateTimeValue

data class NationalWeatherAlertModelData(
    val senderName: OwmString?,
    val eventName: OwmString?,
    val startDate: DateTimeValue?,
    val endDate: DateTimeValue?,
    val description: OwmString?,
    val tags: List<OwmString>?
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
                    senderName = OwmString.Value.from(local.senderName),
                    eventName = OwmString.Value.from(local.event),
                    startDate = DateTimeValue.from(local.start),
                    endDate = DateTimeValue.from(local.end),
                    description = OwmString.Value.from(local.description),
                    tags = local.tags?.map { tag -> OwmString.Value.from(tag) }
                )
            } ?: emptyList()
        }

    }

}