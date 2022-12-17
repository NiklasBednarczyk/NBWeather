package de.niklasbednarczyk.nbweather.core.ui.image

import androidx.annotation.DrawableRes
import de.niklasbednarczyk.nbweather.core.common.string.NBString

interface NBImageModel {

    @get:DrawableRes
    val resIdDark: Int

    @get:DrawableRes
    val resIdLight: Int

    val contentDescription: NBString

}