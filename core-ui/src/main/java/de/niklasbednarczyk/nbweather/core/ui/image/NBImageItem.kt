package de.niklasbednarczyk.nbweather.core.ui.image

import androidx.annotation.DrawableRes
import de.niklasbednarczyk.nbweather.core.common.string.NBString

sealed interface NBImageItem {

    interface One : NBImageItem {

        @get:DrawableRes
        val resId: Int

    }

    interface Two : NBImageItem {

        @get:DrawableRes
        val resIdDark: Int

        @get:DrawableRes
        val resIdLight: Int

    }

    val contentDescription: NBString

}