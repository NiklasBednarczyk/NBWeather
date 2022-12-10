package de.niklasbednarczyk.openweathermap.core.ui.grid

import de.niklasbednarczyk.openweathermap.core.common.string.OwmString

sealed interface OwmGridItem {

    data class OneLine(
        val value: OwmGridValueItem
    ) : OwmGridItem

    data class TwoLines(
        val title: OwmString?,
        val value: OwmGridValueItem
    ) : OwmGridItem

    data class ThreeLines(
        val title: OwmString?,
        val gridIcon: OwmGridIconModel,
        val value: OwmGridValueItem
    ) : OwmGridItem

}
