package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.currentweather

import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIconModel

sealed interface LocationOverviewTodayCurrentWeatherItem {

    val icon: OwmIconModel
    val title: OwmString?
    val unit: OwmString?

    data class Icon(
        override val icon: OwmIconModel,
        override val title: OwmString?,
        override val unit: OwmString?,
        val value: OwmIconModel,
        val rotationDegrees: Float
    ) : LocationOverviewTodayCurrentWeatherItem

    data class Text(
        override val icon: OwmIconModel,
        override val title: OwmString?,
        override val unit: OwmString?,
        val value: OwmString?
    ) : LocationOverviewTodayCurrentWeatherItem

}