package de.niklasbednarczyk.nbweather.feature.location.screens.alerts.models

import de.niklasbednarczyk.nbweather.core.common.string.NBString

sealed interface LocationAlertExpandableItem {

    data class Description(
        val text: NBString
    ) : LocationAlertExpandableItem

    data class Sender(
        val text: NBString
    ) : LocationAlertExpandableItem

    data class Tags(
        val tags: List<NBString>
    ) : LocationAlertExpandableItem

}