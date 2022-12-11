package de.niklasbednarczyk.openweathermap.core.ui.grid

import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.values.OwmValueIconModel
import de.niklasbednarczyk.openweathermap.core.ui.values.OwmValueItem

sealed interface OwmGridItem {

    data class OneLine(
        val value: OwmValueItem
    ) : OwmGridItem

    data class TwoLines(
        val title: OwmString?,
        val value: OwmValueItem
    ) : OwmGridItem

    data class ThreeLines(
        val title: OwmString?,
        val valueIcon: OwmValueIconModel,
        val value: OwmValueItem
    ) : OwmGridItem

}
