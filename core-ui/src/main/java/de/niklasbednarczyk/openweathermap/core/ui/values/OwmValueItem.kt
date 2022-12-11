package de.niklasbednarczyk.openweathermap.core.ui.values

import androidx.annotation.StringRes
import de.niklasbednarczyk.openweathermap.core.common.string.OwmString

sealed interface OwmValueItem {

    data class Icon(
        val valueIcon: OwmValueIconModel
    ) : OwmValueItem

    class IconWithTexts(
        val valueIcon: OwmValueIconModel,
        vararg val texts: OwmString?
    ) : OwmValueItem

    class Texts(
        vararg val texts: OwmString?,
    ) : OwmValueItem

    class TextsWithFormat(
        vararg val texts: OwmString?,
        @StringRes val formatResId: Int
    ) : OwmValueItem

}