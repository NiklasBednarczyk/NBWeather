package de.niklasbednarczyk.nbweather.core.ui.values

import androidx.annotation.StringRes
import de.niklasbednarczyk.nbweather.core.common.string.NBString

sealed interface NBValueItem {

    data class Icon(
        val valueIcon: NBValueIconModel
    ) : NBValueItem

    class IconWithTexts(
        val valueIcon: NBValueIconModel,
        vararg val texts: NBString?
    ) : NBValueItem

    class Texts(
        vararg val texts: NBString?,
    ) : NBValueItem

    class TextsWithFormat(
        vararg val texts: NBString?,
        @StringRes val formatResId: Int
    ) : NBValueItem

}