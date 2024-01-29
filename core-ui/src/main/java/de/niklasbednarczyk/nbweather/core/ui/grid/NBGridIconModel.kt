package de.niklasbednarczyk.nbweather.core.ui.grid

import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconItem

data class NBGridIconModel(
    val icon: NBIconItem,
    val rotationDegrees: Float = 0f
)