package de.niklasbednarczyk.openweathermap.core.ui.grid

import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIconModel

data class OwmGridIconModel(
    val icon: OwmIconModel,
    val rotationDegrees: Float
) {

    companion object {

        fun OwmIconModel.toGridIcon(rotationDegrees: Float = 0f): OwmGridIconModel {
            return OwmGridIconModel(
                icon = this,
                rotationDegrees = rotationDegrees
            )
        }

    }

}