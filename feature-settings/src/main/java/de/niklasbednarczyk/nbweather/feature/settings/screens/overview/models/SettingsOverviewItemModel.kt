package de.niklasbednarczyk.nbweather.feature.settings.screens.overview.models

import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconModel
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBDestination

sealed interface SettingsOverviewItemModel {

    object Divider : SettingsOverviewItemModel

    data class Header(
        val text: NBString
    ) : SettingsOverviewItemModel

    data class Item(
        val icon: NBIconModel,
        val title: NBString?,
        val value: NBString?,
        val destination: NBDestination.WithoutArguments
    ) : SettingsOverviewItemModel

}