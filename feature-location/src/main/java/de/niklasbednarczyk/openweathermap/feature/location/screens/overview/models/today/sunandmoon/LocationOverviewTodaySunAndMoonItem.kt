package de.niklasbednarczyk.openweathermap.feature.location.screens.overview.models.today.sunandmoon

import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIconModel

sealed interface LocationOverviewTodaySunAndMoonItem {

    data class Icon(
        val icon: OwmIconModel,
        val title: OwmString?,
        val value: OwmString?
    ) : LocationOverviewTodaySunAndMoonItem

    object Placeholder : LocationOverviewTodaySunAndMoonItem

}