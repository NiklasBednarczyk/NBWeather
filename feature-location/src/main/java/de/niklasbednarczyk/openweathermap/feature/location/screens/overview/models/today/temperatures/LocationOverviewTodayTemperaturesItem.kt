package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.temperatures

import de.niklasbednarczyk.openweathermap.core.common.string.OwmString

sealed interface LocationOverviewTodayTemperaturesItem {

    val title: OwmString?

    val temperature: OwmString?

    data class Day(
        override val title: OwmString?,
        override val temperature: OwmString?,
        val feelsLikeTemperature: OwmString?
    ) : LocationOverviewTodayTemperaturesItem

    data class Threshold(
        override val title: OwmString?,
        override val temperature: OwmString?
    ) : LocationOverviewTodayTemperaturesItem

}