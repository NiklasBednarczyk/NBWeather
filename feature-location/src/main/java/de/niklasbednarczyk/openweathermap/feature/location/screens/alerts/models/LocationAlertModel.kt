package de.niklasbednarczyk.openweathermap.feature.location.screens.alerts.models

import androidx.annotation.StringRes
import de.niklasbednarczyk.openweathermap.core.common.data.OwmTimeFormatType
import de.niklasbednarczyk.openweathermap.core.common.nullsafe.owmNullSafeList
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.R
import de.niklasbednarczyk.openweathermap.data.onecall.models.OneCallModelData

data class LocationAlertModel(
    val eventName: OwmString?,
    val startDate: OwmString?,
    val endDate: OwmString?,
    @StringRes val startEndDateFormatResId: Int,
    val expandableItems: List<LocationAlertExpandableItem>
) {

    companion object {

        fun from(
            oneCall: OneCallModelData,
            timeFormat: OwmTimeFormatType
        ): List<LocationAlertModel> {
            val timezoneOffset = oneCall.metadata.timezoneOffset
            val alerts = oneCall.nationalWeatherAlerts

            val startEndDateFormatResId = R.string.format_date_range

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
                owmNullSafeList(tags) { t ->
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
                    startEndDateFormatResId = startEndDateFormatResId,
                    expandableItems = expandableItems
                )
            }
        }
    }
}