package de.niklasbednarczyk.openweathermap.feature.location.screens.alerts.models

import de.niklasbednarczyk.openweathermap.core.common.data.OwmTimeFormatType
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafeList
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.data.onecall.models.OneCallModelData

data class LocationAlertModel(
    val eventName: OwmString?,
    val startEndRange: OwmString?,
    val expandableItems: List<LocationAlertExpandableItem>
) {

    companion object {

        fun from(
            oneCall: OneCallModelData?,
            timeFormat: OwmTimeFormatType
        ): List<LocationAlertModel> {
            return owmNullSafe(oneCall) { oC ->
                val timezoneOffset = oC.metadata.timezoneOffset
                val alerts = oC.nationalWeatherAlerts

                alerts.map { alert ->
                    val startDate =
                        alert.startDate?.getDateTimeFormattedValue(timezoneOffset, timeFormat)
                    val endDate =
                        alert.endDate?.getDateTimeFormattedValue(timezoneOffset, timeFormat)

                    val startEndRange = owmNullSafe(startDate, endDate) { sD, eD ->
                        OwmString.Resource(R.string.format_date_range, sD, eD)
                    }

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
                    owmNullSafeList(tags) { t ->
                        expandableItems.add(
                            LocationAlertExpandableItem.Tags(
                                tags = t
                            )
                        )
                    }

                    LocationAlertModel(
                        eventName = alert.eventName,
                        startEndRange = startEndRange,
                        expandableItems = expandableItems
                    )
                }

            } ?: emptyList()
        }

    }


}