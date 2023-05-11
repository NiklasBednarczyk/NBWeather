package de.niklasbednarczyk.nbweather.feature.location.tests

import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.feature.location.cards.models.LocationCardItem
import de.niklasbednarczyk.nbweather.feature.location.cards.models.LocationCardTemperaturesModel

interface LocationCardTest {

    fun createTestCards(name: String): List<LocationCardItem> {
        return listOf(
            LocationCardTemperaturesModel(
                cardTitle = NBString.Value.from("Card $name"),
                dayItems = listOf(),
                thresholdItems = listOf()
            )
        )
    }

    val List<LocationCardItem>.testCardTitle: NBString?
        get() = first().cardTitle

}