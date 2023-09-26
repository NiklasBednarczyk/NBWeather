package de.niklasbednarczyk.nbweather.core.ui.grid

import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconModel

data class NBGridIconModel(
    val icon: NBIconModel,
    val rotationDegrees: Float = 0f
)