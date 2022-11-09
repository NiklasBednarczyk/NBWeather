package de.niklasbednarczyk.openweathermap.feature.location.screens.alerts.models

import de.niklasbednarczyk.openweathermap.core.common.data.OwmTimeFormatType
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafe
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.data.onecall.models.OneCallModelData

data class LocationAlertModel(
    val eventName: OwmString?,
    val startEndRange: OwmString?,
    val description: OwmString?,
    val senderName: OwmString?,
    val tags: List<OwmString>
) {

    companion object {

        fun from(oneCall: OneCallModelData?, timeFormat: OwmTimeFormatType): List<LocationAlertModel> {
            return owmNullSafe(oneCall) { oC ->
                val timezoneOffset = oC.metadata.timezoneOffset
                val alerts = oC.nationalWeatherAlerts

                alerts.map { alert ->
                    val startDate = alert.startDate?.getDateTime(timezoneOffset, timeFormat)
                    val endDate = alert.endDate?.getDateTime(timezoneOffset, timeFormat)

                    val startEndRange = owmNullSafe(startDate, endDate) { sD, eD ->
                        OwmString.Resource(R.string.format_date_range, sD, eD)
                    }

                    val tags = alert.tags ?: emptyList()

                    LocationAlertModel(
                        eventName = alert.eventName,
                        startEndRange = startEndRange,
                        description = alert.description,
                        senderName = alert.senderName,
                        tags = tags
                    )
                }

            } ?: emptyList()
        }

    }


}