package de.niklasbednarczyk.nbweather.core.ui.values

import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconModel

data class NBValueIconModel(
    val icon: NBIconModel,
    val rotationDegrees: Float
) {

    companion object {

        fun NBIconModel.toValueIcon(rotationDegrees: Float = 0f): NBValueIconModel {
            return NBValueIconModel(
                icon = this,
                rotationDegrees = rotationDegrees
            )
        }

    }

}