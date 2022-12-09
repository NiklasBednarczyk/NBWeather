package de.niklasbednarczyk.openweathermap.core.ui.grid

import androidx.annotation.StringRes
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString
import de.niklasbednarczyk.openweathermap.core.ui.icons.OwmIconModel

sealed interface OwmGridValueItem {

    data class IconWithUnit(
        val icon: OwmIconModel,
        val rotationDegrees: Float,
        val unit: OwmString?
    ) : OwmGridValueItem

    class Texts(
        vararg val texts: OwmString?,
    ) : OwmGridValueItem

    class TextsWithFormat(
        vararg val texts: OwmString?,
        @StringRes val formatResId: Int
    ) : OwmGridValueItem

}