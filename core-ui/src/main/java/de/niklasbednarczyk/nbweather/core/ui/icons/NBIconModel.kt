package de.niklasbednarczyk.nbweather.core.ui.icons

import androidx.annotation.DrawableRes
import de.niklasbednarczyk.nbweather.core.common.string.NBString

interface NBIconModel {

    @get:DrawableRes
    val resId: Int

    val contentDescription: NBString

}