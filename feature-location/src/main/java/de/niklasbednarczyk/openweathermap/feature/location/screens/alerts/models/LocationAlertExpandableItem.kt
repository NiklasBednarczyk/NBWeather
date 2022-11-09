package de.niklasbednarczyk.openweathermap.feature.location.screens.alerts.models

import de.niklasbednarczyk.openweathermap.core.common.string.OwmString

sealed interface LocationAlertExpandableItem {

    data class Description(
        val text: OwmString?
    ) : LocationAlertExpandableItem

    data class Sender(
        val text: OwmString?
    ) : LocationAlertExpandableItem

    data class Tags(
        val tags: List<OwmString>
    ) : LocationAlertExpandableItem

}