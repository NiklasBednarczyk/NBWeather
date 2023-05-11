package de.niklasbednarczyk.nbweather.feature.location.screens.alerts.models

import de.niklasbednarczyk.nbweather.core.common.data.NBTimeFormatType
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafeList
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.data.onecall.models.OneCallModelData

data class LocationAlertModel(
    val eventName: NBString?,
    val startDate: NBString?,
    val endDate: NBString?,
    val expandableItems: List<LocationAlertExpandableItem>
) {

    companion object {

        fun from(
            oneCall: OneCallModelData,
            timeFormat: NBTimeFormatType
        ): List<LocationAlertModel> {
            val timezoneOffset = oneCall.metadata.timezoneOffset
            val alerts = oneCall.nationalWeatherAlerts

            return alerts.map { alert ->
                val startDate =
                    alert.startDate?.getDateTimeString(timezoneOffset, timeFormat)
                val endDate =
                    alert.endDate?.getDateTimeString(timezoneOffset, timeFormat)

                val expandableItems = mutableListOf<LocationAlertExpandableItem>()

                val description = alert.description
                if (description != null) {
                    expandableItems.add(
                        LocationAlertExpandableItem.Description(
                            text = description
                        )
                    )
                }

                val senderName = alert.senderName
                if (senderName != null) {
                    expandableItems.add(
                        LocationAlertExpandableItem.Sender(
                            text = senderName
                        )
                    )
                }

                val tags = alert.tags
                nbNullSafeList(tags) { t ->
                    expandableItems.add(
                        LocationAlertExpandableItem.Tags(
                            tags = t
                        )
                    )
                }

                LocationAlertModel(
                    eventName = alert.eventName,
                    startDate = startDate,
                    endDate = endDate,
                    expandableItems = expandableItems
                )
            }
        }
    }
}