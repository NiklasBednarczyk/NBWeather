package de.niklasbednarczyk.openweathermap.data.onecall.models

import de.niklasbednarczyk.openweathermap.core.data.localremote.models.values.DateTimeValue
import de.niklasbednarczyk.openweathermap.data.onecall.values.alert.AlertDescriptionValue
import de.niklasbednarczyk.openweathermap.data.onecall.values.alert.AlertEventValue
import de.niklasbednarczyk.openweathermap.data.onecall.values.alert.AlertSenderNameValue
import de.niklasbednarczyk.openweathermap.data.onecall.values.alert.AlertTagValue
import de.niklasbednarczyk.openweathermap.data.onecall.local.models.NationalWeatherAlertEntityLocal
import de.niklasbednarczyk.openweathermap.data.onecall.remote.models.NationalWeatherAlertModelRemote

data class NationalWeatherAlertModelData(
    val senderName: AlertSenderNameValue,
    val eventName: AlertEventValue,
    val startDate: DateTimeValue,
    val endDate: DateTimeValue,
    val description: AlertDescriptionValue,
    val tags: List<AlertTagValue>?
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
                    senderName = AlertSenderNameValue(local.senderName),
                    eventName = AlertEventValue(local.event),
                    startDate = DateTimeValue(local.start),
                    endDate = DateTimeValue(local.end),
                    description = AlertDescriptionValue(local.description),
                    tags = local.tags?.map { tag -> AlertTagValue(tag) }
                )
            } ?: emptyList()
        }

    }

}