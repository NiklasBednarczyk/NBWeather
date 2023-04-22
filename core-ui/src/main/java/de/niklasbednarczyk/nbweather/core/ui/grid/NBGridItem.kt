package de.niklasbednarczyk.nbweather.core.ui.grid

import de.niklasbednarczyk.nbweather.core.common.string.NBString
import de.niklasbednarczyk.nbweather.core.ui.values.NBValueIconModel
import de.niklasbednarczyk.nbweather.core.ui.values.NBValueItem

sealed interface NBGridItem {

    private interface WithTitle {
        val title: NBString?
    }

    private interface WithValueIcon {
        val valueIcon: NBValueIconModel
    }

    private interface WithValue {
        val value: NBValueItem
    }

    data class OneLine(
        override val value: NBValueItem
    ) : NBGridItem, WithValue

    data class TwoLines(
        override val title: NBString?,
        override val value: NBValueItem
    ) : NBGridItem, WithTitle, WithValue

    data class ThreeLines(
        override val title: NBString?,
        override val valueIcon: NBValueIconModel,
        override val value: NBValueItem
    ) : NBGridItem, WithTitle, WithValueIcon, WithValue

    companion object {

        fun <T> List<T?>.toTitleList(): List<NBString?> where T : NBGridItem, T : WithTitle {
            return map { gridItem -> gridItem?.title }
        }

        fun <T> List<T?>.toValueIconList(): List<NBValueIconModel?> where T : NBGridItem, T : WithValueIcon {
            return map { gridItem -> gridItem?.valueIcon }
        }

        fun <T> List<T?>.toValueList(): List<NBValueItem?> where T : NBGridItem, T : WithValue {
            return map { gridItem -> gridItem?.value }
        }

    }


}
