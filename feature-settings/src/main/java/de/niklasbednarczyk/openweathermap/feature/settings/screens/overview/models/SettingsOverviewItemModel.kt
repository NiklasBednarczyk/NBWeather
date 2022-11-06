package de.niklasbednarczyk.openweathermap.feature.settings.screens.overview.models

import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIconModel
import de.niklasbednarczyk.openweathermap.core.ui.navigation.OwmNavigationDestination

sealed interface SettingsOverviewItemModel {

    object Divider : SettingsOverviewItemModel

    data class Header(
        val text: OwmString
    ) : SettingsOverviewItemModel

    data class Item(
        val icon: OwmIconModel,
        val title: OwmString,
        val value: OwmString,
        val destination: OwmNavigationDestination
    ) : SettingsOverviewItemModel

}