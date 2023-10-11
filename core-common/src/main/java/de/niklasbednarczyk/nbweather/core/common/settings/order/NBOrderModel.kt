package de.niklasbednarczyk.nbweather.core.common.settings.order

import com.google.errorprone.annotations.Immutable

@Immutable
data class NBOrderModel(
    val currentWeatherOrder: Int,
    val dailyOrder: Int,
    val hourlyOrder: Int,
    val precipitationOrder: Int,
    val sunAndMoonOrder: Int
)