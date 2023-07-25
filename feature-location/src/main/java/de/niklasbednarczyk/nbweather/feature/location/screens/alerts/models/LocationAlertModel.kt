package de.niklasbednarczyk.nbweather.feature.location.screens.alerts.models

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeModel
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafeList
import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.data.onecall.models.OneCallModelData

data class LocationAlertModel(
    val eventName: NBString?,
    val startDate: NBDateTimeModel?,
    val endDate: NBDateTimeModel?,
    val expandableItems: List<LocationAlertExpandableItem>
) {

    companion object {

        fun from(
            oneCall: OneCallModelData
        ): List<LocationAlertModel> {
            val alerts = oneCall.nationalWeatherAlerts

            return alerts.map { alert ->

                val expandableItems = mutableListOf<LocationAlertExpandableItem>()

                nbNullSafe(alert.description) { description ->
                    expandableItems.add(
                        LocationAlertExpandableItem.Description(
                            text = description
                        )
                    )
                }

                nbNullSafe(alert.senderName) { senderName ->
                    expandableItems.add(
                        LocationAlertExpandableItem.Sender(
                            text = senderName
                        )
                    )
                }

                nbNullSafeList(alert.tags) { tags ->
                    expandableItems.add(
                        LocationAlertExpandableItem.Tags(
                            tags = tags
                        )
                    )
                }

                LocationAlertModel(
                    eventName = alert.eventName,
                    startDate = alert.startDate,
                    endDate = alert.endDate,
                    expandableItems = expandableItems
                )
            }
        }
    }
}