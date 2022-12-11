package de.niklasbednarczyk.openweathermap.core.ui.values

import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIconModel

data class OwmValueIconModel(
    val icon: OwmIconModel,
    val rotationDegrees: Float
) {

    companion object {

        fun OwmIconModel.toValueIcon(rotationDegrees: Float = 0f): OwmValueIconModel {
            return OwmValueIconModel(
                icon = this,
                rotationDegrees = rotationDegrees
            )
        }

    }

}