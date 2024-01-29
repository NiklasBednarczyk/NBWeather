package de.niklasbednarczyk.nbweather.core.ui.icons

import androidx.annotation.DrawableRes
import de.niklasbednarczyk.nbweather.core.common.string.NBString

sealed interface NBIconItem {

    @get:DrawableRes
    val resId: Int

    val contentDescription: NBString

}