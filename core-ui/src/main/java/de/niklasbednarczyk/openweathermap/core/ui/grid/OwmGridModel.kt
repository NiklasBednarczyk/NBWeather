package de.niklasbednarczyk.openweathermap.core.ui.grid

import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIconModel

data class OwmGridModel(
    val title: OwmString?,
    val icon: OwmIconModel?,
    val value: OwmGridValueItem
)