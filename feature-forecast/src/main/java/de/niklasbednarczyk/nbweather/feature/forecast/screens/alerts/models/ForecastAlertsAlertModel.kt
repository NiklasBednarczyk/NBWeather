package de.niklasbednarczyk.nbweather.feature.forecast.screens.alerts.models

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeDisplayModel
import de.niklasbednarczyk.nbweather.core.common.datetime.NBTimezoneOffsetValue
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafe
import de.niklasbednarczyk.nbweather.core.common.nullsafe.nbNullSafeList
import de.niklasbednarczyk.nbweather.data.onecall.models.NationalWeatherAlertModelData

data class ForecastAlertsAlertModel(
    val infoItems: List<ForecastAlertsAlertInfoItem>
) {

    companion object {

        fun from(
            nationalWeatherAlert: NationalWeatherAlertModelData,
            timezoneOffset: NBTimezoneOffsetValue?
        ): ForecastAlertsAlertModel? {
            val infoItems = mutableListOf<ForecastAlertsAlertInfoItem>()

            nbNullSafe(nationalWeatherAlert.eventName) { eventName ->
                infoItems.add(
                    ForecastAlertsAlertInfoItem.EventName(
                        text = eventName
                    )
                )
            }

            nbNullSafe(
                NBDateTimeDisplayModel.from(nationalWeatherAlert.startDate, timezoneOffset),
                NBDateTimeDisplayModel.from(nationalWeatherAlert.endDate, timezoneOffset)
            ) { startDate, endDate ->
                infoItems.add(
                    ForecastAlertsAlertInfoItem.Dates(
                        startDate = startDate,
                        endDate = endDate
                    )
                )
            }

            nbNullSafe(nationalWeatherAlert.description) { description ->
                infoItems.add(
                    ForecastAlertsAlertInfoItem.Description(
                        text = description
                    )
                )
            }

            nbNullSafe(nationalWeatherAlert.senderName) { senderName ->
                infoItems.add(
                    ForecastAlertsAlertInfoItem.SenderName(
                        text = senderName
                    )
                )
            }

            nbNullSafeList(nationalWeatherAlert.tags) { tags ->
                infoItems.add(
                    ForecastAlertsAlertInfoItem.Tags(
                        tags = tags
                    )
                )
            }

            return nbNullSafeList(infoItems) { i ->
                ForecastAlertsAlertModel(
                    infoItems = i
                )
            }
        }

    }

}