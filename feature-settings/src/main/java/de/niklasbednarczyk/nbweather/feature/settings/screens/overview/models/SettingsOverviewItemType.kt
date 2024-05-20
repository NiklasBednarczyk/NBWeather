package de.niklasbednarczyk.nbweather.feature.settings.screens.overview.models

import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.R
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIconItem
import de.niklasbednarczyk.nbweather.core.ui.icons.NBIcons

enum class SettingsOverviewItemType {

    APPEARANCE,
    FONT,
    ORDER,
    UNITS;

    val icon: NBIconItem
        get() = when (this) {
            APPEARANCE -> NBIcons.Appearance
            FONT -> NBIcons.Font
            ORDER -> NBIcons.Order
            UNITS -> NBIcons.Units
        }

    val title: NBString
        get() = when (this) {
            APPEARANCE -> NBString.ResString(R.string.screen_settings_appearance_title)
            FONT -> NBString.ResString(R.string.screen_settings_font_title)
            ORDER -> NBString.ResString(R.string.screen_settings_order_title)
            UNITS -> NBString.ResString(R.string.screen_settings_units_title)
        }

    val description: NBString
        get() = when (this) {
            APPEARANCE -> NBString.ResString(R.string.screen_settings_appearance_description)
            FONT -> NBString.ResString(R.string.screen_settings_font_description)
            ORDER -> NBString.ResString(R.string.screen_settings_order_description)
            UNITS -> NBString.ResString(R.string.screen_settings_units_description)
        }

    companion object {

        fun from(
            isVariableFontAvailable: Boolean
        ): List<SettingsOverviewItemType> {
            val items = mutableListOf<SettingsOverviewItemType>()

            items.add(APPEARANCE)

            if (isVariableFontAvailable) {
                items.add(FONT)
            }

            items.add(UNITS)

            items.add(ORDER)

            return items
        }

    }

}