package de.niklasbednarczyk.nbweather.core.ui.grid

import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.values.NBValueIconModel
import de.niklasbednarczyk.nbweather.core.ui.values.NBValueItem

sealed interface NBGridItem {

    data class OneLine(
        val value: NBValueItem
    ) : NBGridItem

    data class TwoLines(
        val title: NBString?,
        val value: NBValueItem
    ) : NBGridItem

    data class ThreeLines(
        val title: NBString?,
        val valueIcon: NBValueIconModel,
        val value: NBValueItem
    ) : NBGridItem

}
