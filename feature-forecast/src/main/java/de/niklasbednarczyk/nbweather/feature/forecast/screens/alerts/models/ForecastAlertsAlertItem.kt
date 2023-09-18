package de.niklasbednarczyk.nbweather.feature.forecast.screens.alerts.models

import de.niklasbednarczyk.nbweather.core.common.datetime.NBDateTimeDisplayModel
import de.niklasbednarczyk.nbweather.core.common.string.NBString

sealed interface ForecastAlertsAlertItem {

    data class Dates(
        val startDate: NBDateTimeDisplayModel,
        val endDate: NBDateTimeDisplayModel
    ) : ForecastAlertsAlertItem

    data class Description(
        val text: NBString
    ) : ForecastAlertsAlertItem

    data class EventName(
        val text: NBString
    ) : ForecastAlertsAlertItem

    data class SenderName(
        val text: NBString
    ) : ForecastAlertsAlertItem

    data class Tags(
        val tags: List<NBString>
    ) : ForecastAlertsAlertItem

}