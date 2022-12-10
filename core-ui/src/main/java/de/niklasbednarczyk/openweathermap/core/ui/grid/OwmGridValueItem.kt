package de.niklasbednarczyk.openweathermap.core.ui.grid

import androidx.annotation.StringRes
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString

sealed interface OwmGridValueItem {

    data class Icon(
        val gridIcon: OwmGridIconModel
    ) : OwmGridValueItem

    class IconWithTexts(
        val gridIcon: OwmGridIconModel,
        vararg val texts: OwmString?
    ) : OwmGridValueItem

    class Texts(
        vararg val texts: OwmString?,
    ) : OwmGridValueItem

    class TextsWithFormat(
        vararg val texts: OwmString?,
        @StringRes val formatResId: Int
    ) : OwmGridValueItem

}