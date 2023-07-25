package de.niklasbednarczyk.nbweather.feature.settings.screens.list.models

import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconModel
import de.niklasbednarczyk.nbweather.core.ui.navigation.destination.NBDestination
import de.niklasbednarczyk.nbweather.core.ui.segmented.NBSegmentedControlModel

sealed interface SettingsListItemModel {

    object Divider : SettingsListItemModel

    data class Header(
        val text: NBString?
    ) : SettingsListItemModel

    data class ItemButtons(
        val segmentedControl: NBSegmentedControlModel<*>
    ) : SettingsListItemModel

    data class ItemDestination(
        val icon: NBIconModel,
        val title: NBString?,
        val value: NBString?,
        val destination: NBDestination.WithoutArguments
    ) : SettingsListItemModel

    data class ItemSwitch(
        val title: NBString?,
        val value: NBString?,
        val checked: Boolean,
        val onCheckedChange: (Boolean) -> Unit
    ) : SettingsListItemModel

}