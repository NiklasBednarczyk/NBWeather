package de.niklasbednarczyk.nbweather.core.common.settings.order

import com.google.errorprone.annotations.Immutable

@Immutable
data class NBOrderModel(
    val currentWeatherOrder: Int,
    val dailyOrder: Int,
    val hourlyOrder: Int,
    val precipitationOrder: Int,
    val sunAndMoonOrder: Int
) {

    companion object {

        fun createFake(): NBOrderModel {
            return NBOrderModel(
                currentWeatherOrder = 1,
                dailyOrder = 2,
                hourlyOrder = 3,
                precipitationOrder = 4,
                sunAndMoonOrder = 5
            )
        }

    }

}