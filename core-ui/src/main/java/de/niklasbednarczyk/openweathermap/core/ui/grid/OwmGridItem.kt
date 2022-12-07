package de.niklasbednarczyk.openweathermap.core.ui.grid

import androidx.annotation.StringRes
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIconModel

sealed interface OwmGridItem {

    data class Item(
        val title: OwmString?,
        val icon: OwmIconModel?,
        val value: Value
    ) : OwmGridItem

    object Placeholder : OwmGridItem

    sealed interface Value {
        data class IconWithUnit(
            val icon: OwmIconModel,
            val rotationDegrees: Float,
            val unit: OwmString?
        ) : Value

        class Texts(
            vararg val texts: OwmString?,
        ) : Value

        class TextsWithFormat(
            vararg val texts: OwmString?,
            @StringRes val formatResId: Int
        ) : Value

    }


}