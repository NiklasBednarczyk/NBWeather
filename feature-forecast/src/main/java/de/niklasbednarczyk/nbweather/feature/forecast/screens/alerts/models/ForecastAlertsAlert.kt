package de.niklasbednarczyk.nbweather.feature.forecast.screens.alerts.models

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeDisplayModel
import de.niklasbednarczyk.nbweather.core.common.datetime.NBTimezoneOffsetValue
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafeList
import de.niklasbednarczyk.nbweather.data.onecall.models.NationalWeatherAlertModelData

data class ForecastAlertsAlert(
    val items: List<ForecastAlertsAlertItem>
) {

    companion object {

        fun from(
            timezoneOffset: NBTimezoneOffsetValue?,
            nationalWeatherAlert: NationalWeatherAlertModelData
        ): ForecastAlertsAlert {
            val items = mutableListOf<ForecastAlertsAlertItem>()

            nbNullSafe(nationalWeatherAlert.eventName) { eventName ->
                items.add(
                    ForecastAlertsAlertItem.EventName(
                        text = eventName
                    )
                )
            }

            nbNullSafe(
                NBDateTimeDisplayModel.from(nationalWeatherAlert.startDate, timezoneOffset),
                NBDateTimeDisplayModel.from(nationalWeatherAlert.endDate, timezoneOffset)
            ) { startDate, endDate ->
                items.add(
                    ForecastAlertsAlertItem.Dates(
                        startDate = startDate,
                        endDate = endDate
                    )
                )
            }

            nbNullSafe(nationalWeatherAlert.description) { description ->
                items.add(
                    ForecastAlertsAlertItem.Description(
                        text = description
                    )
                )
            }

            nbNullSafe(nationalWeatherAlert.senderName) { senderName ->
                items.add(
                    ForecastAlertsAlertItem.SenderName(
                        text = senderName
                    )
                )
            }

            nbNullSafeList(nationalWeatherAlert.tags) { tags ->
                items.add(
                    ForecastAlertsAlertItem.Tags(
                        tags = tags
                    )
                )
            }

            return ForecastAlertsAlert(
                items = items
            )
        }

    }

}